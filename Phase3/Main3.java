import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;
            System.out.println("Input: " + input);

            Parser parser = new Parser(input);
            ASTNode root = parser.parse();

            AstToPrefix transformer = new AstToPrefix();
            String prefix = transformer.transform(root);
            System.out.println("Prefix Form: " + prefix);

            try{
                EvaluatePrefix evaluator = new EvaluatePrefix();
                int result = evaluator.evaluate(prefix);
                System.out.println("Final Result: " + result);
            }
            catch (ArithmeticException e) {
                System.out.println("Math Error: " + e.getMessage());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            




            System.out.println("----------");
        }
    }
    
}
