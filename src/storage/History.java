package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import datastruct.Stack;
import datastruct.Node;
import model.Patient;

public class History {
    private static final String HISTORY_FILE = "src/Data/History.csv";
    private Stack historyStack = new Stack();

    public void insert(String id, String name, String symptom, String department) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String today = LocalDate.now().format(formatter);

        // สร้าง Patient ชั่วคราวเพื่อ push เข้า Stack
        Patient temp = new Patient(id, name, 0, "", "", "");
        temp.setSymptoms(symptom);
        temp.setDepartment(department);
        historyStack.push(temp);

        // บันทึกลงไฟล์ CSV ด้วย
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            String line = String.format("%s,%s,%s,%s,%s", today, id, name, symptom, department);
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการเขียนไฟล์: " + e.getMessage());
        }
    }

    public String showToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String today = LocalDate.now().format(formatter);
        return show(today);
    }

    public String show(String date) {
        // อ่านจากไฟล์ CSV แล้ว push เข้า Stack เพื่อกลับลำดับ (คนใหม่สุดอยู่บนสุด)
        Stack tempStack = new Stack();
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5 && data[0].equals(date)) {
                    Patient temp = new Patient(data[1], data[2], 0, "", "", "");
                    temp.setSymptoms(data[3]);
                    temp.setDepartment(data[4]);
                    tempStack.push(temp);
                    count++;
                } else if (data.length >= 3 && data[0].equals(date)) {
                    // รองรับข้อมูลเก่า (date,name,symptom)
                    Patient temp = new Patient("-", data[1], 0, "", "", "");
                    temp.setSymptoms(data[2]);
                    temp.setDepartment("-");
                    tempStack.push(temp);
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการอ่านไฟล์: " + e.getMessage());
        }

        // Pop จาก Stack เพื่อแสดง (คนใหม่สุด = เลขสูงสุด อยู่บรรทัดบนสุด)
        String result = "";
        int num = count;
        Node current = tempStack.getTop();
        while (current != null) {
            Patient p = current.data;
            result += num + ". " + p.getName() + " (ID: " + p.getId() + ") | อาการ: " + p.getSymptoms() + " | แผนก: " + p.getDepartment() + "\n";
            num--;
            current = current.next;
        }
        return result;
    }
}
