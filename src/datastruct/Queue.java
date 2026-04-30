package datastruct;

import datastruct.Node;

public class Queue {
    private Node front;
    private Node rear;

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(int data) { // เพิ่มข้อมูลเข้า
        Node newNode = new Node(data);
        if (this.rear == null) {
            this.front = this.rear = newNode;
            return;
        }
        this.rear.setNext(newNode);
        this.rear = newNode;
    }

    public void dequeue() {
        if (this.front == null) {
            System.out.println("Queue is empty");
            return;
        }
        this.front = this.front.getNext();
        if (this.front == null) {
            this.rear = null;
        }
    }

    public boolean isEmpty() {
        if (this.front == null && this.rear == null) {
            return true;
        }
        return false;
    }
}
