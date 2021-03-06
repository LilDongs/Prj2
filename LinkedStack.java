import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in a chain of nodes.
 * 
 * @author Frank M. Carrano and Timothy M. Henry
 * @version 5.0
 */
public class LinkedStack<T> implements StackInterface<T> {
   private Node topNode; // References the first node in the chain

   public LinkedStack() {
      topNode = null;
   } // end default constructor

   // < Implementations of the stack operations go here. >
   // . . .

   private class Node {
      private T data; // Entry in stack
      private Node next; // Link to next node

      private Node(T dataPortion) {
         this(dataPortion, null);
      } // end constructor

      private Node(T dataPortion, Node linkPortion) {
         data = dataPortion;
         next = linkPortion;
      } // end constructor

      private T getData() {
         return data;
      } // end getData

      private void setData(T newData) {
         data = newData;
      } // end setData

      private Node getNextNode() {
         return next;
      } // end getNextNode

      private void setNextNode(Node nextNode) {
         next = nextNode;
      } // end setNextNode
   } // end Node

   @Override
   public void push(T newEntry) {
      Node newNode = new Node(newEntry, topNode);
      topNode = newNode;
   }

   @Override
   public T pop() {
      T top = peek();
      topNode = topNode.getNextNode();
      return top;
   }

   @Override
   public T peek() {
      if (isEmpty())
         throw new EmptyStackException();
      else
         return topNode.getData();
   }

   @Override
   public boolean isEmpty() {
      return topNode == null;
   }

   @Override
   public void clear() {
      topNode = null;
   }

   String covertToPostfix(String equation) {
      StackInterface<Character> operators = new LinkedStack<>();
      String postfix = "";
      for (int position = 0; position < equation.length(); position++) {
         char unit = equation.charAt(position);
         if (Character.isJavaIdentifierPart(unit))
            postfix += unit;

         else if (unit == '(')
            operators.push(unit);

         else if (unit == ')') {
            while (!operators.isEmpty() && operators.peek() != '(') {
               postfix += operators.pop();
            }
            operators.pop();
         }

         else if (unit == ' ') {
         }

         else {
            while (!operators.isEmpty() && priorityValue(unit) <= priorityValue(operators.peek())) {
               postfix += operators.pop();
            }
            operators.push(unit);
         }

      }
      while (!operators.isEmpty()) {
         postfix += operators.pop();
      }
      return postfix;
   }

   private int priorityValue(char operator) {
      switch (operator) {
         case '+':
         case '-':
            return 1;
         case '*':
         case '/':
            return 2;
         case '^':
            return 3;
      }
      return -1;
   }
} // end LinkedStack
