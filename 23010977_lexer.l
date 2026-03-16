%{
#include <stdio.h>
#include "23010977_parser.tab.h"
#include <stdlib.h>
%}

%%
[0-9]+ {yylval.num = atoi(yytext); return NUMBER; }

"+" {return PLUS;}
"-" {return MINUS;}
"/" {return DIVISION;}
"*" {return MULT;}
"(" {return OPEN;}
")" { return CLOSING;}
[ \t]   ;
"\n" {return EOL;}
%%

int yywrap(){
    return 1;
}
