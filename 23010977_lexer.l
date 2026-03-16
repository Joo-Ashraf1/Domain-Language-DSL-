%{
#include <stdio.h>
%}

%%
[0-9]+ {return NUMBER; } 
"\n" {EOL;}
%%

int main(int argc, char **argv) {
    yylex();
    return 0;
}