package logic;

import datastruct.Queue;
import datastruct.PriorityQueue;
import model.Patient;

public class QueueManeger {
    private static Queue cadio = new Queue();
    private static Queue neuro = new Queue();
    private static Queue ortho = new Queue();
    private static Queue general = new Queue();
    private static PriorityQueue emergency = new PriorityQueue();

    // รับผู้ป่วยเข้าคิวตามแผนก โดยประเมินจากอาการ (sentence) และสถานะฉุกเฉิน (er)
    public static void sendPatient(String sentence, boolean er, Patient patient, int pain, int hr) {
        TriageMeneger piority = new TriageMeneger();
        // ถ้าเป็นผู้ป่วยฉุกเฉิน ให้คำนวณคะแนนความสำคัญ (Priority Score) และเข้าคิวฉุกเฉิน บวกคะแนนพิเศษ 1000
        if (er) {
            int score = piority.priorityScore(pain, hr, patient.getAge()) + 1000;
            patient.setPriorityScore(score);
            patient.setDepartment("EMERGENCY");
            emergency.enqueue(patient);
        // ถ้ามีอาการเกี่ยวกับ หน้าอก หรือ หัวใจ ให้เข้าคิวอายุรกรรมหัวใจ (Cardio)
        } else if (sentence.contains("หน้าอก") || sentence.contains("หัวใจ")) {
            patient.setDepartment("CADIO");
            cadio.enqueue(patient);
        // ถ้ามีอาการเกี่ยวกับ หัว ให้เข้าคิวศัลยกรรมประสาท (Neuro)
        } else if (sentence.contains("หัว")) {
            patient.setDepartment("NEURO");
            neuro.enqueue(patient);
        // ถ้ามีอาการเกี่ยวกับ กระดูก ให้เข้าคิวศัลยกรรมกระดูก (Ortho)
        } else if (sentence.contains("กระดูก")) {
            patient.setDepartment("ORTHO");
            ortho.enqueue(patient);
        // อาการอื่นๆ ให้เข้าคิวตรวจโรคทั่วไป (General)
        } else {
            patient.setDepartment("GENERAL");
            general.enqueue(patient);
        }
    }

    public static Queue getCadio() { return cadio; }
    public static Queue getNeuro() { return neuro; }
    public static Queue getOrtho() { return ortho; }
    public static Queue getGeneral() { return general; }
    public static PriorityQueue getEmergency() { return emergency; }
}
