%{
#include <stdio.h>
%}

%union{
    int num;
}


%token<num> NUMBER;
%token EOL;

%%
input:
    line input
    ;

line:
    expression EOL{printf("Answer:%d",$1);}
|   EOL;

%%

int yyerror(char *s) {
    fprintf(stderr, "Error: %s\n", s);
    return 0;
}

int main() {
    yyparse();
    return 0;
}