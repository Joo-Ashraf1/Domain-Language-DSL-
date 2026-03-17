class OperatorNode extends ASTNODE{
    private char operator;
    private ASTNODE left;
    private ASTNODE right;

    public OperatorNode(char operator) {
        this.operator = operator;
        this.left=null;
        this.right=null;
    }
    public OperatorNode(char operator, ASTNode left, ASTNode right) {
    this.operator = operator;
    this.left = left;
    this.right = right;
}

    @Override
    public int evaluate() {
        int l=this.left.evaluate();
        int r=this.right.evaluate();
        if(this.operator=='+'){
            return l+r;
        }
        else if(this.operator=='-'){
            return l-r;
        }
        else if(this.operator=='*'){
            return l*r;
        }
        else{
            if(r==0){
                throw new ArithmeticException("Division by zero");

            }
            return l/r;
        }

    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix+this.operator);
    }



    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public ASTNode getLeft() {
        return left;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }
}