
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

   String evaluatePostfix(String postfix) {
      StackInterface<String> expressions = new ArrayStack<>();
      for (int position = 0; position < postfix.length(); position++) {
         String value = Character.toString(postfix.charAt(position)), frontExpression, backExpression;
         if (!value.equals("+") && !value.equals("-") && !value.equals("/") && !value.equals("*")
               && !value.equals("^")) {
            expressions.push(value);
         } else {
            backExpression = expressions.pop();
            frontExpression = expressions.pop();
            expressions.push("(" + frontExpression + value + backExpression + ")");
         }
      }

      return expressions.pop();
   }

   // < Implementations of the stack operations go here. >
   // < Implementations of the private methods go here; checkCapacity and
   // checkIntegrity
   // are analogous to those in Chapter 2. >
   // . . .
} // end ArrayStack
