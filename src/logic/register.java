package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class register {
    private static final String DATA_FILE = "src/Data/dataList.txt";

    public void insertData(String name, String age, String gender) {
        try (FileWriter writer = new FileWriter(DATA_FILE, true)) {
            writer.write(name + "," + age + "," + gender + System.lineSeparator());
            System.out.println("Register succeed");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchData(String keyword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(keyword)) {
                    System.out.println("Found: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
