package datastruct;

import model.Patient;

// สืบทอดมาจาก MyQueue แต่เปลี่ยนวิธีแทรกข้อมูล (enqueue)
public class PriorityQueue extends Queue {
    @Override
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            size++;
            return;
        }

        // กรณีที่คิวใหม่สำคัญกว่า(หรือเท่ากับ)คิวแรกสุด (front)
        // ให้อยู่หน้าสุดเลย
        if (patient.getPriorityScore() > front.data.getPriorityScore()) {
            newNode.next = front;
            front = newNode;
            size++;
            return;
        }

        // ไม่ได้อยุ่หน้าสุด ต้องหาคิวที่เหมาะสม (ไปแทรกคิวที่สำคัญน้อยกว่า)
        Node current = front;
        // วนหาตำแหน่งจนกว่าจะเจอจุดที่คิวต่อไปมีความสำคัญน้อยกว่าตัวผู้ป่วยใหม่

        while (current.next != null && current.data.getPriorityScore() >= patient.getPriorityScore()) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;

        // หากออกมาแทรกท้ายสุด ต้องอัพเดต rear
        if (newNode.next == null) {
            rear = newNode;
        }
    }

}