package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.HospitalManager;
import datastruct.MyQueue;
import datastruct.Node;
import model.Patient;

public class DashboardFrame extends JFrame {
    
    private JTextArea emergencyArea;
    private JTextArea cardioArea;
    private JTextArea orthoArea;
    private JTextArea neuroArea;
    private JTextArea generalArea;

    public DashboardFrame() {
        HospitalManager manager = HospitalManager.getInstance();
        setTitle("Smart Hospital - Dashboard ประจำวันที่: " + manager.getCurrentDate());
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // สร้างส่วนหัว
        JLabel titleLabel = new JLabel("ระบบจัดการคิวโรงพยาบาล", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // แผงรวมคิวทั้ง 5 แผนก
        JPanel queuesPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        
        emergencyArea = createQueuePanel("🔴 EMERGENCY", "EMERGENCY", queuesPanel, true);
        cardioArea = createQueuePanel("❤️ CARDIO", "CARDIO", queuesPanel, false);
        orthoArea = createQueuePanel("🦴 ORTHO", "ORTHO", queuesPanel, false);
        neuroArea = createQueuePanel("🧠 NEURO", "NEURO", queuesPanel, false);
        generalArea = createQueuePanel("🟢 GENERAL", "GENERAL", queuesPanel, false);

        add(new JScrollPane(queuesPanel), BorderLayout.CENTER);

        // ปุ่มควบคุมด้านล่าง
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton refreshButton = new JButton("รีเฟรชข้อมูลคิว");
        JButton newPatientButton = new JButton("รับผู้ป่วยใหม่");
        JButton historyButton = new JButton("ดูประวัติการรักษา");

        bottomPanel.add(refreshButton);
        bottomPanel.add(newPatientButton);
        bottomPanel.add(historyButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // อัพเดทข้อมูลครั้งแรก
        updateQueues();

        // ใส่ Action ให้ปุ่มด้านล่าง
        refreshButton.addActionListener(e -> updateQueues());
        
        newPatientButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        historyButton.addActionListener(e -> {
            new HistoryFrame().setVisible(true);
        });
    }

    private JTextArea createQueuePanel(String title, String departmentCode, JPanel parentPanel, boolean showScore) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // ปุ่มเรียกคิว (Dequeue)
        JButton callNextButton = new JButton("เรียกคิวถัดไป");
        callNextButton.addActionListener(e -> {
            Patient p = HospitalManager.getInstance().callNextPatient(departmentCode);
            if (p != null) {
                JOptionPane.showMessageDialog(this, "เรียกผู้ป่วย: " + p.getName() + "\n(นำออกจากคิวและเก็บลงประวัติแล้ว)", "เรียกคิว", JOptionPane.INFORMATION_MESSAGE);
                updateQueues(); // อัพเดทหน้าจอใหม่
            } else {
                JOptionPane.showMessageDialog(this, "ไม่มีคิวรอในแผนกนี้", "แจ้งเตือน", JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(callNextButton, BorderLayout.SOUTH);

        parentPanel.add(panel);
        
        return textArea;
    }

    // ฟังก์ชันอัพเดทข้อความในช่องคิวต่างๆ
    private void updateQueues() {
        HospitalManager manager = HospitalManager.getInstance();
        
        updateArea(emergencyArea, manager.getQueueByDepartment("EMERGENCY"), true);
        updateArea(cardioArea, manager.getQueueByDepartment("CARDIO"), false);
        updateArea(orthoArea, manager.getQueueByDepartment("ORTHO"), false);
        updateArea(neuroArea, manager.getQueueByDepartment("NEURO"), false);
        updateArea(generalArea, manager.getQueueByDepartment("GENERAL"), false);
    }

    private void updateArea(JTextArea area, MyQueue queue, boolean showScore) {
        area.setText(""); // ล้างข้อมูลเก่า
        if (queue == null || queue.isEmpty()) {
            area.append("ไม่มีคิว\n");
            return;
        }

        try {
            Node current = queue.getHead();
            int count = 1;
            while (current != null) {
                Patient p = current.data;
                if (showScore) {
                    area.append(count + ". " + p.getName() + " (คะแนน " + p.getPriorityScore() + ")\n");
                } else {
                    area.append(count + ". " + p.getName() + "\n");
                }
                current = current.next;
                count++;
            }
            area.append("\nรวม: " + queue.getSize() + " คิว");
        } catch (Exception e) {
            area.append("ไม่สามารถดึงข้อมูลคิวได้");
        }
    }
}
