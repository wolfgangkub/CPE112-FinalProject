/*
  ไฟล์: RegisterFrame.java
  ทำหน้าที่: หน้า UI สำหรับลงทะเบียนผู้ป่วยใหม่
  รายละเอียด: รับข้อมูลพื้นฐานผู้ป่วยใหม่แล้วบันทึกลง Patient.csv ก่อนส่งไปหน้าซักประวัติ
  โครงสร้างข้อมูล: ใช้ Swing component เป็นฟอร์มและ DataManeger เป็นตัวจัดการข้อมูล
  อัลกอริทึม: เมื่อกด Save จะเรียก DataManeger.register() และสร้าง Patient object
  วิธีทดสอบ: กรอกข้อมูลใหม่แล้วกด Save ตรวจว่าไฟล์ Patient.csv เพิ่มบรรทัดใหม่
*/
package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import model.Patient;

import storage.DataManeger;

public class RegisterFrame {
    /**
     * เปิดหน้า RegisterFrame โดยไม่มี ID เริ่มต้น
     */
    public static void main(String[] args) {
        open("");
    }

    /**
     * เปิดหน้าลงทะเบียน และรับ ID ที่ส่งมาจาก WelcomeFrame
     */
    public static void open(String id) {
        DataManeger data = new DataManeger();
        JFrame frame = new JFrame("Register");
        JLabel fullNameLabel = new JLabel("Full name:");
        JTextField fullNameInput = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        JSpinner ageInput = new JSpinner(new SpinnerNumberModel(1, 1, 120, 1));

        JLabel GenderLabel = new JLabel("Gender:");
        JTextField GenderInput = new JTextField();

        JLabel diseaseLabel = new JLabel("โรคประจำตัว:");
        JTextField diseaseInput = new JTextField();

        JLabel bloodLabel = new JLabel("กรุ๊ปเลือด:");
        JTextField bloodInput = new JTextField();

        JButton backButton = new JButton("Back");
        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            String name = fullNameInput.getText();
            int age = (int) ageInput.getValue();
            String gender = GenderInput.getText();
            String disease = diseaseInput.getText();
            String bloodGroup = bloodInput.getText();
            data.register(id, name, age, gender, disease, bloodGroup);
            Patient patient = new Patient(id, name, age, gender, disease, bloodGroup);
            JOptionPane.showMessageDialog(null, "บันทึกข้อมูลเสร็จเรียบร้อยแล้ว!", "แจ้งเตือน",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            TriageFrame.open(patient);
        });

        fullNameLabel.setBounds(30, 25, 100, 25);
        fullNameInput.setBounds(130, 25, 180, 25);

        ageLabel.setBounds(30, 65, 100, 25);
        ageInput.setBounds(130, 65, 180, 25);

        GenderLabel.setBounds(30, 105, 100, 25);
        GenderInput.setBounds(130, 105, 180, 25);

        diseaseLabel.setBounds(30, 145, 100, 25);
        diseaseInput.setBounds(130, 145, 180, 25);

        bloodLabel.setBounds(30, 185, 100, 25);
        bloodInput.setBounds(130, 185, 180, 25);

        backButton.setBounds(60, 225, 90, 30);
        saveButton.setBounds(190, 225, 90, 30);

        backButton.addActionListener(e -> {
            frame.dispose();
            WelcomeFrame.main(new String[0]);
        });

        frame.add(fullNameLabel);
        frame.add(fullNameInput);
        frame.add(ageLabel);
        frame.add(ageInput);
        frame.add(GenderLabel);
        frame.add(GenderInput);
        frame.add(diseaseLabel);
        frame.add(diseaseInput);
        frame.add(bloodLabel);
        frame.add(bloodInput);
        frame.add(backButton);
        frame.add(saveButton);
        frame.setSize(360, 310);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
