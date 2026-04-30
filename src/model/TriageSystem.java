package model;

public class TriageSystem {

    // คำนวณคะแนนและจัดการให้คิว
    public static void evaluateAndAssign(Patient patient, int painLevel, int breathingDiff, double temp, boolean isCritical, String symptomText) {
        
        int severityScore = 0;
        int priorityScore = 0;
        String department = "GENERAL"; // ค่าเริ่มต้น

        // 1) กฎฉุกเฉิน (Critical Override)
        if (isCritical) {
            severityScore = 1000;
            priorityScore = 1000;
            department = "EMERGENCY";
        } else {
            // 2) คำนวณ Severity Score พื้นฐาน
            severityScore = (painLevel * 2) + (breathingDiff * 3);
            if (temp >= 39.0) {
                severityScore += 3;
            }

            // 3) คำนวณ Age Bonus (กลุ่มเสี่ยง เด็ก และ ผู้สูงอายุ)
            int ageBonus = 0;
            if (patient.getAge() <= 12 || patient.getAge() >= 60) {
                ageBonus = 2; // เพิ่มคะแนนให้กลุ่มเสี่ยง
            }

            priorityScore = severityScore + ageBonus;

            // 4) กำหนดแผนก (Routing Logic) จาก Keyword อาการ
            String symptom = symptomText.toLowerCase();
            if (symptom.contains("chest") || symptom.contains("หน้าอก") || symptom.contains("หัวใจ")) {
                department = "CARDIO";
            } else if (symptom.contains("head") || symptom.contains("หัว") || symptom.contains("ประสาท")) {
                department = "NEURO";
            } else if (symptom.contains("bone") || symptom.contains("fracture") || symptom.contains("กระดูก") || symptom.contains("หัก")) {
                department = "ORTHO";
            } else {
                department = "GENERAL";
            }
        }

        // เซฟคะแนนและแผนกลงในตัวผู้ป่วย
        patient.setPriorityScore(priorityScore);
        patient.setDepartment(department);
        // เก็บอาการไว้แสดงผลในประวัติ
        patient.setSymptoms(symptomText.isEmpty() ? "-" : symptomText);
        
        // ส่งผู้ป่วยเข้าคิวในแผนกที่กำหนด
        HospitalManager.getInstance().addPatientToQueue(patient, department);
    }
}
