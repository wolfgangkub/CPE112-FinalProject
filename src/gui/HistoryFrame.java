package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.HospitalManager;
import datastruct.MyStack;
import datastruct.Node;
import model.Patient;
import model.FileManager;
import java.time.LocalDate;

public class HistoryFrame extends JFrame {
    
    private JTextArea historyArea;
    private JTextField dateField;

    public HistoryFrame() {
        setTitle("ประวัติการรักษา (History)");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ปิดแค่หน้านี้ ไม่ปิดโปรแกรมหลัก
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ส่วนค้นหาตามวันที่
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("ใส่วันที่ (YYYY-MM-DD): "));
        dateField = new JTextField(LocalDate.now().toString(), 10);
        topPanel.add(dateField);
        
        JButton searchButton = new JButton("ดูประวัติ");
        topPanel.add(searchButton);
        add(topPanel, BorderLayout.NORTH);

        // ส่วนแสดงผลประวัติ
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        historyArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(historyArea), BorderLayout.CENTER);

        // ดึงประวัติของวันนี้มาโชว์ก่อนเลยตอนเปิดหน้าต่าง
        showHistory(LocalDate.now().toString());

        // Event ปุ่มค้นหา
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHistory(dateField.getText().trim());
            }
        });
    }

    private void showHistory(String dateStr) {
        historyArea.setText("กำลังโหลดประวัติวันที่ " + dateStr + "...\n\n");
        
        MyStack stackToView;
        
        // ถ้าเป็นของวันนี้ ให้ดึงจาก HospitalManager เลย เพราะอัพเดทสุด
        if (dateStr.equals(HospitalManager.getInstance().getCurrentDate())) {
            stackToView = HospitalManager.getInstance().getHistory();
        } else {
            // ถ้าเป็นของวันอื่น ให้ไปโหลดจากไฟล์มาใหม่
            stackToView = new MyStack();
            FileManager.loadHistory(dateStr, stackToView);
        }

        if (stackToView.isEmpty()) {
            historyArea.append("❌ ไม่มีประวัติการรักษาสำหรับวันที่นี้");
            return;
        }

        Node current = stackToView.getTop();
        int count = stackToView.getSize(); // เริ่มต้นด้วยเลขมากสุด (คนล่าสุด)
        while (current != null) {
            Patient p = current.data;
            historyArea.append(count + ". " + p.getName() + " (ID: " + p.getId() + ") - แผนก: " + p.getDepartment() + " | อาการ: " + p.getSymptoms() + "\n");
            current = current.next;
            count--; // ลดเลขลงเรื่อยๆ คนแรกสุดที่อยู่ล่างสุดจะได้เลข 1
        }
    }
}
