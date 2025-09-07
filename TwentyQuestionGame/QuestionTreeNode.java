public class QuestionTreeNode {
   
   // Instance fields
   public String data;
   public QuestionTreeNode yes; // Left branch
   public QuestionTreeNode no; // Right branch
   
   // Constructor (leaf node)
   public QuestionTreeNode(String data) {
      this(data, null, null);
   }
   
   // Constructor (branch node)
   public QuestionTreeNode(String data, QuestionTreeNode yes, QuestionTreeNode no) {
      this.data = data;
      this.yes = yes;
      this.no = no;
   }
}