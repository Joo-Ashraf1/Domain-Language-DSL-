public class Parser{
    private String [] tokens;
    private int pos;

    public Parser(String input) {
        this.tokens = input.split(" ");
        this.pos = 0;
    }

    public ASTNode parse(){
        return parseExpr();
    }


    public ASTNode parseExpr(){
        ASTNode left=parseTerm();
        while(match('+')||match('-')){
            char operator=tokens[pos].charAt(0);
            pos++;
            ASTNode right=parseTerm();
            left=new OperatorNode(operator,left,right);

        }
        return left;

    }
    public ASTNode parseTerm(){
        ASTNode left=parseNum();
        while(match('*')||match('/')){
            char operator=tokens[pos].charAt(0);
            pos++;
            ASTNode right=parseNum();
            left=new OperatorNode(operator,left,right);

        }
        return left;

    }

    public ASTNode parseNum(){
        int value=Integer.parseInt(tokens[pos]);
        pos++;
        return new NumNode(value);
    }

    public boolean match(char c){
        if(pos<tokens.length && tokens[pos].length()==1 && tokens[pos].charAt(0)==c){

            return true;
        }
        return false;
    }
}