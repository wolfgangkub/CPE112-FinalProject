package datastruct;

import model.Patient;

public class MyQueue {
    protected Node head;
    protected Node tail;
    protected int size;

    public MyQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // นำเข้าต่อท้ายคิว (FIFO)
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // ดึงคิวคนแรกออก
    public Patient dequeue() {
        if (isEmpty()) return null;
        Patient data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    // ดูข้อมูลคนแรก (ไม่ดึงออก)
    public Patient peek() {
        if (isEmpty()) return null;
        return head.data;
    }

    // เมธอดสำหรับดึงโหนดแรกสุด เพื่อให้หน้าจอ Dashboard เอาไปวนลูปแสดงผลได้ง่ายๆ
    public Node getHead() {
        return head;
    }
}
