class NumNode extends ASTNode {
    private int value;

    public NumNode(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix+this.value);
    }
}