package datastruct;

import model.Patient;

/*
  ไฟล์: Queue.java
  ทำหน้าที่: โครงสร้างข้อมูลคิว FIFO สำหรับผู้ป่วยทั่วไป
  รายละเอียด: จัดการ enqueue และ dequeue ตามลำดับเข้าก่อนออกก่อน
  โครงสร้างข้อมูล: ลิงก์ลิสต์แบบ singly linked list โดยเก็บ front และ rear
  อัลกอริทึม: enqueue ต่อที่ rear, dequeue ดึงจาก front
  วิธีทดสอบ: เพิ่มผู้ป่วยหลายรายการ แล้ว dequeue ออกมาทีละรายการเพื่อตรวจลำดับ
*/

public class Queue {
    protected Node front;
    protected Node rear;
    protected int size;

    /**
     * สร้างคิวว่างใหม่
     */
    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * เพิ่มผู้ป่วยเข้าคิว (เข้าท้าย)
     */
    public void enqueue(Patient data) {
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

    /**
     * นำผู้ป่วยออกจากคิวด้านหน้า
     * @return Patient ตัวแรกในคิว หรือ null หากคิวว่าง
     */
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

    /**
     * ตรวจสอบว่าคิวว่างหรือไม่
     * @return true ถ้าคิวไม่มีข้อมูล
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * คืนค่าจำนวนผู้ป่วยในคิว
     */
    public int getSize() {
        return this.size;
    }

    /**
     * ดูหัวคิวโดยไม่เอาออก
     * @return Patient ตัวแรกของคิว หรือ null ถ้าว่าง
     */
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