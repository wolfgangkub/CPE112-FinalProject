package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class History {
    private static final String HISTORY_FILE = "src/Data/History.csv";

    public void insert(String name, String symptom) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String today = LocalDate.now().format(formatter);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            String line = String.format("%s,%s,%s", today, name, symptom);
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการเขียนไฟล์: " + e.getMessage());
        }
    }

    public String show(String date) {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3 && data[0].equals(date)) {
                    result += data[1] + " - " + data[2];
                }
            }
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาดในการอ่านไฟล์: " + e.getMessage());
        }
        return result;
    }
}
