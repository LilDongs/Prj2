public class ArrayStackTest<T> extends ArrayStack<T> {
    public static void main(String[] args) {
        ArrayStack hello = new ArrayStack<>();
        System.out.println(hello.evaluatePostfix("ABC*EF-/+"));
    }
}