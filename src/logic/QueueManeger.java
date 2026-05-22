package logic;

import datastruct.Queue;
import datastruct.PriorityQueue;
import model.Patient;

/*
  ไฟล์: QueueManeger.java
  ทำหน้าที่: จัดผู้ป่วยเข้าสู่คิวแผนกที่เหมาะสม
  รายละเอียด: แยกผู้ป่วยเป็น emergency, CADIO, NEURO, ORTHO, GENERAL ตามอาการและสถานะฉุกเฉิน
  โครงสร้างข้อมูล: เก็บ Queue สำหรับแผนกทั่วไปและ PriorityQueue สำหรับ emergency
  อัลกอริทึม: ใช้คำสั่งเงื่อนไข sentence.contains(...) เพื่อแยกเป้าหมาย และใช้คะแนนจาก TriageMeneger เมื่อฉุกเฉิน
  วิธีทดสอบ: เรียก sendPatient ด้วยข้อมูลอาการต่างกัน แล้วตรวจว่าเข้า queue ถูกต้อง
*/

public class QueueManeger {
    private static Queue cadio = new Queue();
    private static Queue neuro = new Queue();
    private static Queue ortho = new Queue();
    private static Queue general = new Queue();
    private static PriorityQueue emergency = new PriorityQueue();

    /**
     * ส่งผู้ป่วยเข้า queue ที่เหมาะสมตามอาการ
     * @param sentence อาการบรรยายเบื้องต้น
     * @param er ค่าบ่งชี้ว่าฉุกเฉินหรือไม่
     * @param patient ข้อมูลผู้ป่วย
     * @param pain ระดับความเจ็บปวด
     * @param hr อัตราการเต้นหัวใจ
     * @param temp อุณหภูมิร่างกาย
     */
    public static void sendPatient(String sentence, boolean er, Patient patient, int pain, int hr, double temp) {
        TriageMeneger piority = new TriageMeneger();
        if (er) {
            int score = piority.priorityScore(pain, hr, temp);
            patient.setPriorityScore(score);
            patient.setDepartment("EMERGENCY");
            emergency.enqueue(patient);

        } else if (sentence.contains("หน้าอก") || sentence.contains("หัวใจ")) {
            patient.setDepartment("CADIO");
            cadio.enqueue(patient);

        } else if (sentence.contains("หัว")) {
            patient.setDepartment("NEURO");
            neuro.enqueue(patient);

        } else if (sentence.contains("กระดูก")) {
            patient.setDepartment("ORTHO");
            ortho.enqueue(patient);

        } else {
            patient.setDepartment("GENERAL");
            general.enqueue(patient);
        }

    }

    /**
     * คืนค่า queue ของแผนก CADIO
     */
    public static Queue getCadio() {

        return cadio;

    }

    /**
     * คืนค่า queue ของแผนก NEURO
     */
    public static Queue getNeuro() {
        return neuro;
    }

    /**
     * คืนค่า queue ของแผนก ORTHO
     */
    public static Queue getOrtho() {
        return ortho;
    }

    /**
     * คืนค่า queue ของแผนก GENERAL
     */
    public static Queue getGeneral() {
        return general;
    }

    /**
     * คืนค่า priority queue สำหรับแผนก EMERGENCY
     */
    public static PriorityQueue getEmergency() {
        return emergency;
    }
}
