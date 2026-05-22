/*
  ไฟล์: TriageFrame.java
  ทำหน้าที่: หน้าซักประวัติและประเมินอาการผู้ป่วยก่อนจัดคิว
  รายละเอียด: รับข้อมูลอาการเบื้องต้น, ระดับปวด, HR, อุณหภูมิ, สถานะฉุกเฉิน แล้วส่งข้อมูลเข้า QueueManeger
  โครงสร้างข้อมูล: ใช้ Swing UI components และส่งต่อ Patient object
  อัลกอริทึม: เมื่อกดประเมินจะเรียก QueueManeger.sendPatient() เพื่อจัดผู้ป่วยไปคิวตามเกณฑ์
  วิธีทดสอบ: เปิดหน้าโดยมี Patient แล้วกรอกข้อมูลและกดประเมิน ดูว่าผู้ป่วยไปอยู่คิวถูกต้อง
*/
package ui;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import logic.QueueManeger;
import model.Patient;
import storage.History;

public class TriageFrame {
    /**
     * เปิดหน้า TriageFrame โดยไม่มีผู้ป่วยเริ่มต้น
     */
    public static void main(String[] args) {
        open(null);
    }

    /**
     * เปิดหน้าซักประวัติและประเมินอาการสำหรับผู้ป่วยที่ได้รับข้อมูลไว้แล้ว
     */
    public static void open(Patient patient) {
        History history = new History();
        JFrame frame = new JFrame("ซักประวัติและอาการ");
        JPanel patientPanel = new JPanel();
        JLabel nameLabel = new JLabel("ชื่อ: " + patient.getName());
        JLabel diseaseLabel = new JLabel("โรคประจำตัว: " + patient.getUnderlyingDisease());

        TitledBorder patientBorder = BorderFactory.createTitledBorder("ข้อมูลผู้ป่วย");
        patientPanel.setBorder(patientBorder);
        patientPanel.setLayout(null);
        patientPanel.setBounds(23, 10, 350, 80);
        nameLabel.setBounds(15, 23, 200, 20);
        diseaseLabel.setBounds(15, 50, 200, 20);

        patientPanel.add(nameLabel);
        patientPanel.add(diseaseLabel);

        JLabel primaryLabel = new JLabel("อาการเบื้องต้น:");
        JTextField primaryInput = new JTextField();

        JLabel painJLabel = new JLabel("ระดับความเจ็บปวด (0 - 10):");
        JSpinner painInput = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

        JLabel hrLabel = new JLabel("อัตราการเต้นหัวใจ :");
        JSpinner hrInput = new JSpinner(new SpinnerNumberModel(0, 0, 300, 1));

        JLabel pressureLabel = new JLabel("ความดันร่างกาย :");
        JTextField pressureInput = new JTextField();

        JLabel tempLabel = new JLabel("อุณหภูมิร่างกาย (°C):");
        JSpinner tempInput = new JSpinner(new SpinnerNumberModel(36.5, 0.0, 100.0, 0.1));

        JLabel erLabel = new JLabel("อาการวิกฤต (หมดสติ/เลือดออกหนัก):");
        JCheckBox erInput = new JCheckBox("ใช่(ฉุกเฉิน)");

        JButton backButton = new JButton("ย้อนกลับ");
        JButton finish = new JButton("ประเมินและจัดคิว");

        backButton.addActionListener(e -> {
            frame.dispose();
            WelcomeFrame.main(new String[0]);
        });

        finish.addActionListener(e -> {
            int pain = (int) painInput.getValue();
            int hr = (int) hrInput.getValue();
            double temp = (double) tempInput.getValue();
            patient.setSymptoms(primaryInput.getText());
            QueueManeger.sendPatient(primaryInput.getText(), erInput.isSelected(), patient, pain, hr, temp);
            frame.dispose();
            DashBoard.main(new String[0]);
        });

        primaryLabel.setBounds(25, 100, 120, 25);
        primaryInput.setBounds(150, 100, 220, 25);
        painJLabel.setBounds(25, 140, 170, 25);
        painInput.setBounds(200, 140, 170, 25);
        hrLabel.setBounds(25, 180, 200, 25);
        hrInput.setBounds(200, 180, 170, 25);
        pressureLabel.setBounds(25, 220, 170, 25);
        pressureInput.setBounds(200, 220, 170, 25);
        tempLabel.setBounds(25, 260, 170, 25);
        tempInput.setBounds(200, 260, 170, 25);
        erLabel.setBounds(25, 300, 240, 25);
        erInput.setBounds(260, 300, 120, 25);
        backButton.setBounds(60, 340, 90, 30);
        finish.setBounds(165, 340, 150, 30);

        frame.add(patientPanel);
        frame.add(primaryLabel);
        frame.add(primaryInput);
        frame.add(painJLabel);
        frame.add(painInput);
        frame.add(hrLabel);
        frame.add(hrInput);
        frame.add(pressureLabel);
        frame.add(pressureInput);
        frame.add(tempLabel);
        frame.add(tempInput);
        frame.add(erLabel);
        frame.add(erInput);
        frame.add(backButton);
        frame.add(finish);
        frame.setSize(400, 420);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
