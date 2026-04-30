package datastruct;

import model.Patient;

// สืบทอดมาจาก MyQueue แต่เปลี่ยนวิธีแทรกข้อมูล (enqueue)
public class MyPriorityQueue extends MyQueue {

    @Override
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        
        // ถ้าคิวว่าง
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        // กรณีที่คิวใหม่สำคัญกว่า(หรือเท่ากับ)คิวแรกสุด (head) 
        // ให้อยู่หน้าสุดเลย
        if (patient.getPriorityScore() > head.data.getPriorityScore()) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        // กรณีที่ไม่ได้อยู่หน้าสุด ต้องหาตำแหน่งที่เหมาะสม
        Node current = head;
        // วนหาตำแหน่งจนกว่าจะเจอจุดที่คิวต่อไปมีความสำคัญน้อยกว่าตัวเรา
        // หรือเจอจุดจบของคิว
        while (current.next != null && current.next.data.getPriorityScore() >= patient.getPriorityScore()) {
            current = current.next;
        }

        // แทรก newNode เข้าไป
        newNode.next = current.next;
        current.next = newNode;

        // ถ้าแทรกที่ท้ายสุด ต้องอัพเดท tail ด้วย
        if (newNode.next == null) {
            tail = newNode;
        }

        size++;
    }
}
