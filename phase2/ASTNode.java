
abstract class ASTNode {
    public abstract int evaluate();
    public abstract void print(String prefix);
}
/*
bison -d 23010977_parser.y
flex 23010977_lexer.l
gcc 23010977_parser.tab.c lex.yy.c -o mini -lfl
./mini    
*/