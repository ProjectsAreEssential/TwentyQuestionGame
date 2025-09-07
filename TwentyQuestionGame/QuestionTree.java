import java.util.Scanner;
import java.io.PrintStream;

public class QuestionTree {
   
   // Instance fields
   private QuestionTreeNode overallRoot; // Top node
   
   // Constructor (default)
   public QuestionTree() {
      overallRoot = new QuestionTreeNode("Computer");
   }
   
   // Constructor (saved tree) 
   public QuestionTree(Scanner input) {
      load(input); // Recounstructs the tree from the file
   }
   
   // Play Game
   public void playGame() {
      Scanner console = new Scanner(System.in);
      System.out.println("Think of an object, and I'll try to guess it!");
      
      traverseAndAsk(overallRoot, console);
   }

   // Traverse
   private void traverseAndAsk(QuestionTreeNode node, Scanner console) {
      if (node.yes == null && node.no == null) {
         System.out.print("Is it " + node.data + "? ");
         String answer = console.nextLine().trim().toLowerCase();
         
         if (answer.equals("yes") || answer.equals("y")) {
            System.out.println("The computer has guessed correctly");
         } else {
            learnNewAnswer(node, console);
         }
      } else {
         System.out.println(node.data + " (yes/no)?");
         String answer2 = console.nextLine().trim().toLowerCase();
        
         if (answer2.equals("yes") || answer2.equals("y")) {
            traverseAndAsk(node.yes, console);
         } else {
            traverseAndAsk(node.no, console);
         }
      }
   }
   
   // Learning
   private void learnNewAnswer(QuestionTreeNode leaf, Scanner console) {
      System.out.print("I give up. What was your object? ");
      String newAnswer = console.nextLine().trim().toLowerCase();
      
      System.out.print("Please give me a question to distinguish " + newAnswer + " from " + leaf.data + ". ");
      String distinguish = console.nextLine().trim().toLowerCase();      
      
      System.out.print("What is the answer for " + newAnswer + "? (yes/no): ");
      String response = console.nextLine().trim().toLowerCase();
      
      String oldAnswer = leaf.data;
      leaf.data = distinguish;
      
      if (response.equals("yes") || response.equals("y")) {
         leaf.yes = new QuestionTreeNode(newAnswer);
         leaf.no = new QuestionTreeNode(oldAnswer);   
      } else {
         leaf.no = new QuestionTreeNode(newAnswer);
         leaf.yes = new QuestionTreeNode(oldAnswer);
      }
   }
   
   // Save
   public void save(PrintStream output) {
      saveHelper(overallRoot, output);
   }
   
   // Save helper
   private void saveHelper(QuestionTreeNode node, PrintStream output) {
      if (node.yes == null && node.no == null) {
         output.println("A:" + node.data); // Leaf = answer
      } else {
         output.println("Q:" + node.data);
         saveHelper(node.yes, output);
         saveHelper(node.no, output);
      }
   }
   
   // Load
   public void load(Scanner input) {
      overallRoot = loadHelper(input);
   }
   
   // Load helper
   private QuestionTreeNode loadHelper(Scanner input) {
      String line = input.nextLine();
      if (line.startsWith("A:")) {
         return new QuestionTreeNode(line.substring(2).trim());   
      } else {
         QuestionTreeNode qNode = new QuestionTreeNode(line.substring(2).trim());
         qNode.yes = loadHelper(input);
         qNode.no = loadHelper(input);
         return qNode;
      }
   }
}