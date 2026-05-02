package datastruct;

import model.Patient;

public class Queue {
    protected Node front;
    protected Node rear;
    protected int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(Patient data) { // เพิ่มข้อมูลเข้า
        Node newNode = new Node(data);
        if (this.rear == null) {
            this.front = this.rear = newNode;
            return;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
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
        size--;
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return this.size;
    }

    public Patient peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    public Node getHead() {
        return front;
    }
}
