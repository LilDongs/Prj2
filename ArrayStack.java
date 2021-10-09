
/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {
   private T[] stack; // Array of stack entries
   private int topIndex; // Index of top entry
   private boolean integrityOK = false;
   private static final int DEFAULT_CAPACITY = 50;
   private static final int MAX_CAPACITY = 10000;

   public ArrayStack() {
      this(DEFAULT_CAPACITY);
   } // end default constructor

   public ArrayStack(int initialCapacity) {
      integrityOK = false;

      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[]) new Object[initialCapacity];
      stack = tempStack;
      topIndex = -1;
      integrityOK = true;
   } // end constructor

   @Override
   public void push(T newEntry) {
      checkintegrity();
      ensureCapacity();
      stack[topIndex + 1] = newEntry;
      topIndex++;
   }

   private void ensureCapacity() {
      if (topIndex >= stack.length - 1) {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      }
   }

   private void checkCapacity(int capacity) {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException(
               "Attempt to create a bag whose capacity exceeds " + "allowed maximum of " + MAX_CAPACITY);
   } // end checkCapacity

   // Throws an exception if receiving object is not initialized.
   private void checkintegrity() {
      if (!integrityOK)
         throw new SecurityException("ArrayBag object is corrupt.");
   } // end checkintegrity

   @Override
   public T pop() {
      checkintegrity();
      if (isEmpty())
         throw new EmptyStackException();
      else {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      }
   }

   @Override
   public T peek() {
      checkintegrity();
      if (isEmpty())
         throw new EmptyStackException();
      else
         return stack[topIndex];
   }

   @Override
   public boolean isEmpty() {
      return topIndex < 0;
   }

   @Override
   public void clear() {
      checkintegrity();
      while (topIndex > -1) {
         stack[topIndex] = null;
         topIndex--;
      }

   }

   double evaluatePostfix(String postfix,String[] variables, double[] values) {
      StackInterface<Double> expressionsValues = new ArrayStack<>();
      double answer = 0;
      for (int position = 0; position < postfix.length(); position++) {
         char currentChar = postfix.charAt(position);
         double frontExpression, backExpression;
         if (currentChar != '+' && currentChar != '-' && currentChar != '/' && currentChar != '*'
               && currentChar != '^') {
            expressionsValues.push(values[Arrays.asList(variables).indexOf(Character.toString(currentChar))]);
         } else {
            backExpression = expressionsValues.pop();
            frontExpression = expressionsValues.pop();
            answer = evaluation(currentChar,backExpression,frontExpression);
            expressionsValues.push(answer);
         }
      }

      return expressionsValues.pop();
   }

   private double evaluation(char operator, double backExpression, double frontExpression)
   {
      switch(operator)
      {
         case '+':
            return frontExpression + backExpression;
         case '-':          
            return frontExpression - backExpression;
         case '/':
            return frontExpression / backExpression;
         case '*':
            return frontExpression * backExpression;
         case '^':
            return Math.pow(frontExpression, backExpression);
      }
      return 0;
   }

   // < Implementations of the stack operations go here. >
   // < Implementations of the private methods go here; checkCapacity and
   // checkIntegrity
   // are analogous to those in Chapter 2. >
   // . . .
} // end ArrayStack
