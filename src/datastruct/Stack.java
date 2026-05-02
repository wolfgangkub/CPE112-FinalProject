package datastruct;

import model.Patient;

public class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public void push(Patient patient) {
        Node newNode = new Node(patient);
        newNode.next = top;
        top = newNode;
    }

    public Patient pop() {
        if (top == null)
            return null;
        Patient data = top.data;
        top = top.next;
        return data;
    }

    public Node getTop() {
        return top;
    }
}
