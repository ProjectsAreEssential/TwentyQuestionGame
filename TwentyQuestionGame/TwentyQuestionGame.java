import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class TwentyQuestionGame {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      QuestionTree tree;
      
      File f = new File("tree.txt");
      if (f.exists() && f.length() > 0) {
         try {
            Scanner fileScanner = new Scanner(f);
            tree = new QuestionTree(fileScanner);
            fileScanner.close();
         } catch (FileNotFoundException e) {
            System.out.println("Error loading saved tree. Starting new game.");
            tree = new QuestionTree();
         }
      } else {
         tree = new QuestionTree();
      }
      
      boolean playAgain = true;
      while (playAgain) {
         tree.playGame();
         System.out.print("\nDo you want to play again? (yes/no): ");
         String response = console.nextLine().trim().toLowerCase();
         playAgain = response.equals("yes") || response.equals("y");
         
         System.out.println();      
      }
      
      try {
         PrintStream output = new PrintStream(f);
         tree.save(output);
         output.close();
         System.out.println("Game saved. Goodbye!");
      } catch (FileNotFoundException e) {
         System.out.println("Error saving game.");
      }
      
      console.close();
   }
}