
public class EvaluatePrefix {
    private int i;
    private String [] tokens;
    public EvaluatePrefix(){
        this.i=0;
        

    }
    public int evaluate(String prefix){
        prefix = prefix.replace("(", "( ").replace(")", " )");
        tokens = prefix.trim().replaceAll("\\s+", " ").split(" ");
        i=0; // i had a problem with muliple inputs
        return evalrec();
    }

    private int evalrec(){
        if(Character.isDigit(tokens[i].charAt(0))){
            int n= Integer.parseInt(tokens[i]);
            i++;
            return n;

        }
        else{ //here if i get (
            i++;
            char op=tokens[i].charAt(0);
            i++;
            int left = evalrec();
            int right= evalrec();
            i++;
            if (op == '+') return left + right;
            if (op == '-') return left - right;
            if (op == '*') return left * right;
            return left / right;
        }
    }
    
}
