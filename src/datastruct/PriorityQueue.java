package datastruct;

import model.Patient;

/*
  ไฟล์: PriorityQueue.java
  ทำหน้าที่: โครงสร้างข้อมูลคิวฉุกเฉินที่จัดลำดับตาม priority score
  รายละเอียด: สืบทอดจาก Queue และแก้ไข enqueue ให้แทรกผู้ป่วยตามคะแนน Priority
  โครงสร้างข้อมูล: ลิงก์ลิสต์แบบเดียวกับ Queue แต่แทรกตำแหน่งเหมาะสมตามคะแนน
  อัลกอริทึม: เปรียบเทียบ dynamicPriorityScore ของผู้ป่วยใหม่กับสมาชิกเดิม แล้ววางตำแหน่งให้เหมาะสม
  วิธีทดสอบ: enqueue ผู้ป่วยที่มีคะแนนต่างกันและตรวจลำดับการออก
*/
public class PriorityQueue extends Queue {
    /**
     * เพิ่มผู้ป่วยเข้า priority queue โดยเรียงลำดับตามคะแนนความสำคัญ
     * ถ้าผู้ป่วยมี dynamicPriorityScore สูงกว่า front จะถูกวางไว้หน้าสุด
     */
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
        if (patient.getPriorityScore() > front.data.getDynamicPriorityScore()) {
            newNode.next = front;
            front = newNode;
            size++;
            return;
        }

        // ไม่ได้อยุ่หน้าสุด ต้องหาคิวที่เหมาะสม (ไปแทรกคิวที่สำคัญน้อยกว่า)
        Node current = front;
        // วนหาตำแหน่งจนกว่าจะเจอจุดที่คิวต่อไปมีความสำคัญน้อยกว่าตัวผู้ป่วยใหม่

        while (current.next != null && current.next.data.getDynamicPriorityScore() >= patient.getPriorityScore()) {
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