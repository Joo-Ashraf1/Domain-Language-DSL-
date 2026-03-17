import java.util.Queue;

public class AstToPrefix {
    public String transform(ASTNode node){
        if(node instanceof NumNode){
            NumNode num=(NumNode) node;
            return Integer.toString(num.getValue());
        }
        else{
            OperatorNode op=(OperatorNode) node;
            return "(" + op.getOperator() + " " + transform (op.getLeft())+ " "+ transform(op.getRight()) + ")";
        }
    }

    
}
