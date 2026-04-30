package datastruct;

import model.Patient;

public class MyStack {
    private Node top;
    private int size;

    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    // เอาข้อมูลเข้า Stack (LIFO: Last-In, First-Out) คนล่าสุดจะอยู่บนสุด
    public void push(Patient patient) {
        Node newNode = new Node(patient);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // ดึงข้อมูลใบบนสุดออก
    public Patient pop() {
        if (isEmpty()) return null;
        Patient data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }

    // ดึง Node บนสุด เพื่อใช้วนลูปแสดงผล
    public Node getTop() {
        return top;
    }
}
