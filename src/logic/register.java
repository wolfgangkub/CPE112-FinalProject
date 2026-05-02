package logic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class register {

    public void insertData(String firstName, String lasttName, String dateOfBirth) {
        try (FileWriter writer = new FileWriter("Data/dataList.txt", true)) {
            writer.write(firstName + "  " + lasttName + "  " + dateOfBirth + "\n");
            System.out.println("Register succeed");
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void searchData(String keyword) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Data/dataList.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(keyword)) {
                    System.out.println("เจอข้อมูล: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("เกิดข้อผิดพลาด: " + e.getMessage());
        }
    }

}
