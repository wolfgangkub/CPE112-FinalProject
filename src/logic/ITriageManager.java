package logic;

import models.Patient;

public interface ITriageManager {
    // 1. คำนวณคะแนนความรุนแรง
    void calculateSeverity(Patient p); 
    
    // 2. แยกแผนกตามอาการและคะแนน (เช่น ไป ER หรือไปแผนกเฉพาะทาง)
    void assignDepartment(Patient p);  
    
    // 3. เมธอดหลักที่หน้า UI จะเรียกใช้ (รวมข้อ 1 และ 2 ไว้ด้วยกัน)
    void processPatient(Patient p);    
}