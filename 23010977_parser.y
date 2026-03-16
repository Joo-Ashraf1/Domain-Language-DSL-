%{
#include <stdio.h>
%}

%union{
    int num;
}


%token<num> NUMBER;
%token EOL;
%token MINUS
%token MULT
%token PLUS
%token CLOSING
%token OPEN
%token DIVISION


%%
input:
    line input
    ;

line:
    expression EOL{printf("Answer:%d",$1);}
|   EOL;

expression:
    term|
    expression PLUS term {expression+term}|
    expression MINUS term {expression-term}

term:
    term MULT factor{term*factor}|
    term DIVISION factor{term/factor}|
    factor{factor};

factor:
    NUMBER| expression;

%%

int yyerror(char *s) {
    fprintf(stderr, "Error: %s\n", s);
    return 0;
}

int main() {
    yyparse();
    return 0;
}