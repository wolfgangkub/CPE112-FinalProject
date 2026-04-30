package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.FileManager;
import model.Patient;

public class LoginFrame extends JFrame {
    private JTextField idField;
    private JButton searchButton;

    public LoginFrame() {
        // ตั้งค่าหน้าต่างหลัก
        setTitle("Smart Hospital - ระบบลงทะเบียน");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ให้อยู่ตรงกลางจอ
        setLayout(new BorderLayout());

        // สร้างส่วนบน (กรอก ID)
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topPanel.add(new JLabel("กรุณากรอกรหัสประจำตัว (ID):"));
        
        idField = new JTextField(10);
        topPanel.add(idField);

        searchButton = new JButton("ค้นหา / ลงทะเบียน");
        topPanel.add(searchButton);
        
        JButton viewDashboardButton = new JButton("ข้ามไปหน้าจัดการคิว (Dashboard)");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        buttonPanel.add(viewDashboardButton);

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // ใส่ Action ให้ปุ่มไปหน้า Dashboard ทันที
        viewDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardFrame dashboardFrame = new DashboardFrame();
                dashboardFrame.setVisible(true);
                dispose(); // ปิดหน้าต่าง Login
            }
        });

        // ใส่ Action ให้ปุ่มค้นหา
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "กรุณากรอก ID", "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // ค้นหาข้อมูลจากไฟล์ (ที่ทำใน Phase 1)
                Patient patient = FileManager.findPatientById(id);
                
                if (patient != null) {
                    // ถ้าเจอผู้ป่วยเก่า
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "พบข้อมูลผู้ป่วยเก่า!\n" + patient.toString(), 
                        "ยินดีต้อนรับกลับ", JOptionPane.INFORMATION_MESSAGE);
                    
                    // ส่งไปหน้าซักประวัติอาการ (Triage)
                    TriageFrame triageFrame = new TriageFrame(patient);
                    triageFrame.setVisible(true);
                    dispose(); // ปิดหน้าต่าง Login
                } else {
                    // ถ้าไม่เจอ ให้เปิดหน้าต่างลงทะเบียน
                    showRegisterDialog(id);
                }
            }
        });
    }

    // หน้าต่างเล็ก (Dialog) สำหรับลงทะเบียนผู้ป่วยใหม่
    private void showRegisterDialog(String newId) {
        JDialog dialog = new JDialog(this, "ลงทะเบียนผู้ป่วยใหม่", true);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField diseaseField = new JTextField();
        JTextField bloodField = new JTextField();

        dialog.add(new JLabel(" ชื่อ-นามสกุล:")); dialog.add(nameField);
        dialog.add(new JLabel(" อายุ:")); dialog.add(ageField);
        dialog.add(new JLabel(" เพศ:")); dialog.add(genderField);
        dialog.add(new JLabel(" โรคประจำตัว:")); dialog.add(diseaseField);
        dialog.add(new JLabel(" กรุ๊ปเลือด:")); dialog.add(bloodField);

        JButton saveButton = new JButton("บันทึกข้อมูล");
        dialog.add(new JLabel("")); // ช่องว่าง
        dialog.add(saveButton);

        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String disease = diseaseField.getText();
                String blood = bloodField.getText();

                // สร้าง Object และเซฟลงไฟล์
                Patient newPatient = new Patient(newId, name, age, gender, disease, blood);
                FileManager.savePatient(newPatient);

                JOptionPane.showMessageDialog(dialog, "บันทึกข้อมูลเรียบร้อยแล้ว!", "สำเร็จ", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose(); // ปิดหน้าต่างลงทะเบียน
                
                // ส่งไปหน้าซักประวัติอาการ (Triage) ต่อ
                TriageFrame triageFrame = new TriageFrame(newPatient);
                triageFrame.setVisible(true);
                LoginFrame.this.dispose(); // ปิดหน้าต่าง Login หลักด้วย
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "กรุณากรอกอายุเป็นตัวเลข", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }
}
