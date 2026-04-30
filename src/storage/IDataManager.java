package storage;

import models.Patient;
import java.util.List;

public interface IDataManager {
    // บันทึกข้อมูลคนไข้ใหม่ลงไฟล์
    void savePatient(Patient p);               
    
    // ค้นหาข้อมูลคนไข้ด้วย ID (ใช้ในหน้า Welcome UI)
    Patient getPatientById(String id);         
    
    // โหลดประวัติคนไข้ทั้งหมดกลับมาในระบบตอนเปิดโปรแกรม
    List<Patient> loadAllPatients();           
}