/*
  ไฟล์: DataManeger.java
  ทำหน้าที่: อ่านและเขียนข้อมูลผู้ป่วยในไฟล์ CSV
  รายละเอียด: ลงทะเบียนผู้ป่วยใหม่, ค้นหา ID, โหลดข้อมูล Patient จากไฟล์
  โครงสร้างข้อมูล: ใช้ไฟล์ CSV เป็นแหล่งข้อมูลหลัก ไม่มีโครงสร้างข้อมูลภายในอื่น ๆ
  อัลกอริทึม: ใช้ BufferedReader อ่านทีละบรรทัด, split(",") เพื่อแยกฟิลด์, และ BufferedWriter เขียนบรรทัดใหม่
  วิธีทดสอบ: เรียก register() แล้วตรวจไฟล์ Patient.csv, เรียก findPatient() / returnPatient() เพื่อตรวจสอบผล
*/
package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import model.Patient;

public class DataManeger {
    private static final String PATIENT_FILE = "src/Data/Patient.csv";

    /**
     * บันทึกข้อมูลผู้ป่วยใหม่ลงไฟล์ CSV
     */
    public void register(String id, String name, int age, String gender, String disease, String bloodGroup) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE, true))) {
            String line = String.format("%s,%s,%d,%s,%s,%s", id, name, age, gender, disease, bloodGroup);
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการเขียนไฟล์: " + e.getMessage());
        }
    }

    /**
     * ค้นหา ID ในไฟล์ผู้ป่วย
     * @return true หากพบ ID
     */
    public boolean findPatient(String searchId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 1 && data[0].equals(searchId)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการอ่านไฟล์: " + e.getMessage());
            return false;
        }
    }

    /**
     * คืนค่าโรคประจำตัวของผู้ป่วยจากไฟล์
     */
    public String returnDisease(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5 && data[0].equals(id)) {
                    return data[4];
                }
            }
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการอ่านไฟล์: " + e.getMessage());
        }
        return "";
    }

    /**
     * คืนค่าชื่อผู้ป่วยจากไฟล์
     */
    public String returnName(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].equals(id)) {
                    return data[1];
                }
            }
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการอ่านไฟล์: " + e.getMessage());
        }
        return "";
    }

    /**
     * โหลดข้อมูลผู้ป่วยจากไฟล์และสร้าง Patient object
     */
    public Patient returnPatient(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5 && data[0].equals(id)) {
                    int age = Integer.parseInt(data[2]);
                    Patient patient = new Patient(data[0], data[1], age, data[3], data[4], data[5]);
                    return patient;
                }
            }
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการอ่านไฟล์: " + e.getMessage());
        }
        return null;
    }
}
