import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;
            
            // Build AST (needed for transformation)
            Parser parser = new Parser(input);
            ASTNode root = parser.parse();
            
            // Transform to prefix
            AstToPrefix transformer = new AstToPrefix();
            String prefix = transformer.transform(root);
            
            // Evaluate prefix
            EvaluatePrefix evaluator = new EvaluatePrefix();
            int result = evaluator.evaluate(prefix);
            
            // Output
            System.out.println("Input: " + input);
            System.out.println("Prefix Form: " + prefix);
            System.out.println("Final Result: " + result);
            System.out.println("----------");
        }
    }
    
}
