package datastructures;

import models.Patient;
import java.util.List;

public interface IHospitalQueue {
    void enqueue(Patient p);   // นำคนไข้เข้าคิว
    Patient dequeue();         // เรียกคนไข้คิวแรกออกมารักษา
    Patient peek();            // ดูข้อมูลคนไข้คิวแรก (แต่ไม่เอาออกจากคิว)
    boolean isEmpty();         // ตรวจสอบว่าคิวว่างหรือไม่
    int size();                // ดูจำนวนคนในคิว
    
    // สำคัญมากสำหรับหน้า Dashboard: ดึงข้อมูลคิวทั้งหมดออกมาแสดงผลในตาราง
    List<Patient> getQueueList(); 
}