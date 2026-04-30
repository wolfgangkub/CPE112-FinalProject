package model;

import datastruct.MyQueue;
import datastruct.MyPriorityQueue;
import datastruct.MyStack;
import java.util.HashMap;
import java.time.LocalDate;

public class HospitalManager {
    // ใช้ Singleton Pattern เพื่อให้มีศูนย์กลางคิวที่เดียวในโปรแกรม
    private static HospitalManager instance;
    
    // เก็บ Queue แยกตามแผนก
    private HashMap<String, MyQueue> queues;
    
    // เก็บประวัติคนที่ถูกเรียกคิวแล้วของวันนี้
    private MyStack history;
    
    // วันที่ปัจจุบัน (ใช้เป็นชื่อไฟล์)
    private String currentDate;

    private HospitalManager() {
        queues = new HashMap<>();
        history = new MyStack();
        currentDate = LocalDate.now().toString(); // เช่น "2023-10-24"

        // สร้างคิวธรรมดาสำหรับแผนกทั่วไป
        queues.put("GENERAL", new MyQueue());
        queues.put("CARDIO", new MyQueue());
        queues.put("ORTHO", new MyQueue());
        queues.put("NEURO", new MyQueue());
        
        // สร้าง Priority Queue สำหรับแผนกฉุกเฉิน (จะแทรกคิวตามคะแนน)
        queues.put("EMERGENCY", new MyPriorityQueue());
        
        // โหลดข้อมูลคิวและประวัติของวันนี้กลับมา (ถ้ามี)
        FileManager.loadQueueState(currentDate, queues);
        FileManager.loadHistory(currentDate, history);
    }

    public static HospitalManager getInstance() {
        if (instance == null) {
            instance = new HospitalManager();
        }
        return instance;
    }

    // เอาคนไข้เข้าคิวตามแผนก
    public void addPatientToQueue(Patient patient, String department) {
        if (queues.containsKey(department)) {
            queues.get(department).enqueue(patient);
            System.out.println("จัดคิว " + patient.getName() + " ไปยังแผนก " + department);
            // เซฟคิวทุกครั้งที่มีการเปลี่ยนแปลง
            FileManager.saveQueueState(currentDate, queues);
        }
    }

    // ดึงคิวออก (เรียกคิวถัดไป)
    public Patient callNextPatient(String department) {
        if (queues.containsKey(department)) {
            Patient p = queues.get(department).dequeue();
            if (p != null) {
                history.push(p); // เก็บลงประวัติ
                FileManager.saveQueueState(currentDate, queues); // อัพเดทไฟล์คิว
                FileManager.saveHistory(currentDate, history); // อัพเดทไฟล์ประวัติ
            }
            return p;
        }
        return null;
    }

    // ดึงคิวแผนกที่ต้องการมาเพื่อแสดงผล
    public MyQueue getQueueByDepartment(String department) {
        return queues.get(department);
    }

    // ดึงประวัติของวันนี้
    public MyStack getHistory() {
        return history;
    }

    public String getCurrentDate() {
        return currentDate;
    }
}
