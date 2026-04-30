package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    // กำหนดชื่อไฟล์ที่จะใช้เซฟข้อมูล
    private static final String FILE_NAME = "patients_db.csv";

    // Method 1: บันทึกข้อมูลผู้ป่วยลงไฟล์
    public static void savePatient(Patient patient) {
        // ใช้ FileWriter และ BufferedWriter ในการเขียนไฟล์
        // (true คือการต่อท้ายไฟล์เดิม ไม่ลบของเก่าทิ้ง)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(patient.toCSV()); // เอาข้อมูลที่แปลงเป็น CSV มาเขียน
            bw.newLine(); // ขึ้นบรรทัดใหม่
            System.out.println("✅ บันทึกข้อมูลผู้ป่วย " + patient.getName() + " สำเร็จ!");
        } catch (IOException e) {
            System.out.println("❌ เกิดข้อผิดพลาดในการบันทึกไฟล์: " + e.getMessage());
        }
    }

    // Method 2: ค้นหาผู้ป่วยจาก ID
    public static Patient findPatientById(String id) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return null; // ถ้าไฟล์ยังไม่มี แปลว่าไม่เคยมีข้อมูลผู้ป่วยเลย
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            // อ่านทีละบรรทัดจนกว่าจะหมดไฟล์
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // หั่นข้อความด้วยลูกน้ำ (,)
                
                // ถ้ารหัส ID ตรงกัน ให้สร้าง Object Patient คืนค่ากลับไป
                if (data.length == 6 && data[0].equals(id)) {
                    String foundId = data[0];
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String gender = data[3];
                    String disease = data[4];
                    String bloodGroup = data[5];
                    
                    return new Patient(foundId, name, age, gender, disease, bloodGroup);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ เกิดข้อผิดพลาดในการอ่านไฟล์: " + e.getMessage());
        }

        // ถ้าหาจนจบไฟล์แล้วไม่เจอ ให้คืนค่า null
        return null; 
    }

    // --- ส่วนของการจัดการสถานะคิว (Queue Persistence) ---
    
    public static void saveQueueState(String dateStr, java.util.HashMap<String, datastruct.MyQueue> queues) {
        String filename = "queue_" + dateStr + ".csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String dept : queues.keySet()) {
                datastruct.MyQueue queue = queues.get(dept);
                datastruct.Node current = queue.getHead();
                while (current != null) {
                    Patient p = current.data;
                    // บันทึก: แผนก, ID ผู้ป่วย, PriorityScore, อาการ
                    String safeSymptoms = p.getSymptoms().replace(",", " "); // กันบัคลูกน้ำใน csv
                    bw.write(dept + "," + p.getId() + "," + p.getPriorityScore() + "," + safeSymptoms);
                    bw.newLine();
                    current = current.next;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ ไม่สามารถบันทึกสถานะคิวได้: " + e.getMessage());
        }
    }

    public static void loadQueueState(String dateStr, java.util.HashMap<String, datastruct.MyQueue> queues) {
        String filename = "queue_" + dateStr + ".csv";
        File file = new File(filename);
        if (!file.exists()) return; // ไม่มีไฟล์คิวเก่า

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String dept = data[0];
                    String id = data[1];
                    int score = Integer.parseInt(data[2]);
                    String symptoms = (data.length >= 4) ? data[3] : "-";

                    Patient p = findPatientById(id);
                    if (p != null) {
                        p.setPriorityScore(score);
                        p.setDepartment(dept); // เซฟแผนกกลับเข้าตัวคนไข้ด้วย (แก้บัคแผนกหายตอนเปิดแอพใหม่)
                        p.setSymptoms(symptoms);
                        if (queues.containsKey(dept)) {
                            queues.get(dept).enqueue(p);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("❌ โหลดคิวล้มเหลว: " + e.getMessage());
        }
    }

    // --- ส่วนของการจัดการประวัติ (History Persistence) ---

    public static void saveHistory(String dateStr, datastruct.MyStack history) {
        String filename = "history_" + dateStr + ".csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            datastruct.Node current = history.getTop();
            java.util.ArrayList<String> temp = new java.util.ArrayList<>();
            while (current != null) {
                // เซฟ ID, แผนก, อาการ
                String safeSymptoms = current.data.getSymptoms().replace(",", " ");
                temp.add(current.data.getId() + "," + current.data.getDepartment() + "," + safeSymptoms);
                current = current.next;
            }
            // เขียนจากเก่าไปใหม่ (bottom to top)
            for (int i = temp.size() - 1; i >= 0; i--) {
                bw.write(temp.get(i));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ ไม่สามารถบันทึกประวัติได้: " + e.getMessage());
        }
    }

    public static void loadHistory(String dateStr, datastruct.MyStack history) {
        String filename = "history_" + dateStr + ".csv";
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 1) {
                    String id = data[0].trim();
                    Patient p = findPatientById(id);
                    if (p != null) {
                        if (data.length >= 2) {
                            p.setDepartment(data[1].trim());
                        }
                        if (data.length >= 3) {
                            p.setSymptoms(data[2].trim());
                        }
                        history.push(p);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("❌ โหลดประวัติล้มเหลว: " + e.getMessage());
        }
    }
}
