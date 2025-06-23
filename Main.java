package utils;

public class Main {
    public static void main(String[] args) {
        // Test ArrayList
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        System.out.println("ArrayList: " + arrayList);

        // Test SinglyLinkedList
        List<String> singly = new SinglyLinkedList<>();
        singly.add("Dog");
        singly.add("Cat");
        singly.add("Bird");
        System.out.println("SinglyLinkedList: " + singly);

        // Test Doubly LinkedList
        List<String> doubly = new LinkedList<>();
        doubly.add("Red");
        doubly.add("Blue");
        doubly.add("Green");
        System.out.println("LinkedList (Doubly): " + doubly);

        // Test MyStack
        MyStack<String> stack = new MyStack<>();
        stack.push("One");
        stack.push("Two");
        stack.push("Three");
        System.out.println("Stack: " + stack);
        stack.pop();
        System.out.println("After pop: " + stack);

        // Test MyQueue
        MyQueue<String> queue = new MyQueue<>();
        queue.add("First");
        queue.add("Second");
        queue.add("Third");
        System.out.println("Queue: " + queue);
        queue.remove();
        System.out.println("After remove: " + queue);
    }
}
