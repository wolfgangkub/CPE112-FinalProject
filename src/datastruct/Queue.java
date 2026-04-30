package datastruct;

import datastruct.Node;

public class Queue {
    private Node front;
    private Node rear;

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Patient data) { // เพิ่มข้อมูลเข้า
        Node newNode = new Node(data);
        if (this.rear == null) {
            this.front = this.rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public Patient dequeue() {
        if (this.front == null) {
            System.out.println("Queue is empty");
            return null;
        }
        Patient data = front.data;
        front = front.next;
        if (this.front == null) {
            this.rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        if (this.front == null && this.rear == null) {
            return true;
        }
        return false;
    }

    public Patient peek() {
        if (this.front == null && this.rear == null) {
            return null;
        }
        return front.data;
    }

    public Node getHead() {
        return front;
    }
}
