%{
#include <stdio.h>
#include <stdlib.h>
%}
int yylex();
void yyerror(const char *s);

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


%left PLUS MINUS
%left MULT DIVISION

%%


input:
    |
    input line
    ;


line:
    expression EOL{printf("Answer:%d",$1);}
    | EOL;


expression:
    term  {$$=$1} | 
    expression PLUS term {$$=$1 + $3}|
    expression MINUS term {$$=$1-$3}

term:
    term MULT factor{$$=$1 * $3}|
    term DIVISION factor{$$=$1 / $3}|
    factor{$$=$1};

factor:
    NUMBER{$$=$1}|
    OPEN expression CLOSING{$$=$2};

%%

void yyerror(const char *s) {
    fprintf(stderr, "Error: %s\n", s);
}

int main() {
    yyparse();
    return 0;
}