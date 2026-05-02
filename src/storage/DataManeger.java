package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class DataManeger {
    private static final String PATIENT_FILE = "Patient.csv";

    public void register(String id, String name, String age, String gender, String disease, String bloodGroup) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE, true))) {
            String line = String.format("%s,%s,%s,%s,%s,%s", id, name, age, gender, disease, bloodGroup);
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการเขียนไฟล์: " + e.getMessage());
        }
    }

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
}
