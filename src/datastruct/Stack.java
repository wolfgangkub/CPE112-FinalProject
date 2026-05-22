package datastruct;

import model.Patient;

/*
  ไฟล์: Stack.java
  ทำหน้าที่: โครงสร้างข้อมูลสแตกสำหรับเก็บประวัติชั่วคราว
  รายละเอียด: push และ pop ข้อมูล Patient ในลักษณะ LIFO
  โครงสร้างข้อมูล: ลิงก์ลิสต์แบบ singly linked list
  อัลกอริทึม: push เพิ่มบนสุด, pop ดึงบนสุด
  วิธีทดสอบ: push ข้อมูลหลายรายการแล้ว pop ออกมาทีละรายการเพื่อตรวจลำดับ
*/
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