package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Patient;
import model.TriageSystem;

public class TriageFrame extends JFrame {
    private Patient patient;

    public TriageFrame(Patient patient) {
        this.patient = patient;

        setTitle("ซักประวัติและอาการ - " + patient.getName());
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ข้อมูลผู้ป่วยด้านบน
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBorder(BorderFactory.createTitledBorder("ข้อมูลผู้ป่วย"));
        infoPanel.add(new JLabel("ชื่อ: " + patient.getName() + " (อายุ " + patient.getAge() + " ปี)"));
        infoPanel.add(new JLabel("โรคประจำตัว: " + patient.getUnderlyingDisease()));
        add(infoPanel, BorderLayout.NORTH);

        // แบบฟอร์มซักประวัติอาการ
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("อาการเบื้องต้น:"));
        JTextField symptomField = new JTextField();
        formPanel.add(symptomField);

        formPanel.add(new JLabel("ระดับความเจ็บปวด (0-10):"));
        JSpinner painSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        formPanel.add(painSpinner);

        formPanel.add(new JLabel("ระดับความหายใจลำบาก (0-10):"));
        JSpinner breathingSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        formPanel.add(breathingSpinner);

        formPanel.add(new JLabel("อุณหภูมิร่างกาย (°C):"));
        JTextField tempField = new JTextField("37.0");
        formPanel.add(tempField);

        formPanel.add(new JLabel("อาการวิกฤต (หมดสติ/เลือดออกหนัก):"));
        JCheckBox criticalBox = new JCheckBox("ใช่ (ฉุกเฉิน)");
        formPanel.add(criticalBox);

        add(formPanel, BorderLayout.CENTER);

        // ปุ่มยืนยัน
        JButton submitButton = new JButton("ประเมินและจัดคิว");
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String symptom = symptomField.getText();
                    int pain = (Integer) painSpinner.getValue();
                    int breathing = (Integer) breathingSpinner.getValue();
                    double temp = Double.parseDouble(tempField.getText());
                    boolean isCritical = criticalBox.isSelected();

                    // เรียกใช้ TriageSystem ประเมินอาการและจัดลงคิว
                    TriageSystem.evaluateAndAssign(patient, pain, breathing, temp, isCritical, symptom);

                    JOptionPane.showMessageDialog(TriageFrame.this, "ประเมินสำเร็จ! จัดผู้ป่วยลงคิวเรียบร้อย", "สำเร็จ", JOptionPane.INFORMATION_MESSAGE);
                    
                    dispose(); // ปิดหน้าต่างซักประวัติ
                    
                    // ไปหน้าต่าง Dashboard
                    DashboardFrame dashboardFrame = new DashboardFrame();
                    dashboardFrame.setVisible(true);
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TriageFrame.this, "กรุณากรอกอุณหภูมิเป็นตัวเลข", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(submitButton, BorderLayout.SOUTH);
    }
}
