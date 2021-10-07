
public class LinkedStackTest<T> extends LinkedStack<T> {
    public static void main(String[] args) {
        LinkedStack hello = new LinkedStack<>();
        System.out.println(hello.covertToPostfix("A + B/C*(D-A)^F^H"));
    }
}