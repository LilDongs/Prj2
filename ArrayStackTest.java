public class ArrayStackTest<T> extends ArrayStack<T> {
    public static void main(String[] args) {
        ArrayStack hello = new ArrayStack<>();
        String[] variables = {"a","b","c","d","e"};
        double[] values = {2,3,4,5,6};
        System.out.println(hello.evaluatePostfix("ab*ca-/de*+",variables,values));
        System.out.println(hello.evaluatePostfix("ab*ca-/de*+"));
    }
}