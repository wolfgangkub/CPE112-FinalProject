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

public class TriageFrame {
    public static void main(String[] args) {
        open(null);
    }

    public static void open(Patient patient) {
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

        JLabel hrLabel = new JLabel("ระดับความหายใจลำบาก (0 - 10):");
        JSpinner hrInput = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

        JLabel tempLabel = new JLabel("อุณหภูมิร่างกาย (°C):");
        JSpinner tempInput = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

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
            QueueManeger.sendPatient(primaryInput.getText(), erInput.isSelected(), patient, pain, hr);
            frame.dispose();
            DashBoard.main(new String[0]);
        });

        primaryLabel.setBounds(25, 100, 120, 25);
        primaryInput.setBounds(150, 100, 220, 25);
        painJLabel.setBounds(25, 140, 170, 25);
        painInput.setBounds(200, 140, 170, 25);
        hrLabel.setBounds(25, 180, 200, 25);
        hrInput.setBounds(225, 180, 145, 25);
        tempLabel.setBounds(25, 220, 170, 25);
        tempInput.setBounds(200, 220, 170, 25);
        erLabel.setBounds(25, 260, 240, 25);
        erInput.setBounds(260, 260, 120, 25);
        backButton.setBounds(60, 310, 90, 30);
        finish.setBounds(165, 310, 150, 30);

        frame.add(patientPanel);
        frame.add(primaryLabel);
        frame.add(primaryInput);
        frame.add(painJLabel);
        frame.add(painInput);
        frame.add(hrLabel);
        frame.add(hrInput);
        frame.add(tempLabel);
        frame.add(tempInput);
        frame.add(erLabel);
        frame.add(erInput);
        frame.add(backButton);
        frame.add(finish);
        frame.setSize(400, 380);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
