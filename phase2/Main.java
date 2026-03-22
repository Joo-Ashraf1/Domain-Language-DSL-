import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    while(scanner.hasNextLine()) {
        String input = scanner.nextLine().trim();
        
        if(input.isEmpty()) continue;
        
        System.out.println("Input: " + input);
        
        Parser parser = new Parser(input);
        ASTNode root = parser.parse();
        
        System.out.println("AST:");
        root.print("");
        
        System.out.println("Result: " + root.evaluate());
        System.out.println("----------");
    }
}

}