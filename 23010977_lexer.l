%{
#include <stdio.h>
%}

%%
[0-9]+ {return NUMBER; }

"+" {return PLUS}
"-" {return MINUS}
"/" {return DIVISION}
"*" {return MULT}
"(" {return OPEN}
")" { return CLOSING}
"\n" {EOL;}
%%

