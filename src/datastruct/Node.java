package datastruct;

import model.Patient;

/*
  ไฟล์: Node.java
  ทำหน้าที่: โหนดพื้นฐานสำหรับโครงสร้างข้อมูลลิงก์ลิสต์ในโปรเจค
  รายละเอียด: เก็บข้อมูล Patient และตัวชี้ next เพื่อเชื่อมลิงก์ลิสต์
  โครงสร้างข้อมูล: ใช้ร่วมกับ Queue, PriorityQueue, Stack
  อัลกอริทึม: ไม่มีตรรกะเพิ่มเติม เป็นโครงสร้างข้อมูลพื้นฐาน
  วิธีทดสอบ: สร้าง Node แล้วตรวจว่า data และ next ถูกตั้งค่า
*/
public class Node {
    public Patient data;
    public Node next;

    public Node(Patient data) {
        this.data = data;
        this.next = null;
    }

}