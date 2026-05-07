package ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datastruct.Node;
import datastruct.Queue;
import storage.History;

public class HistoryFrame {
    public static void main(String[] args) {
        History history = new History();
        JFrame frame = new JFrame("History system");
        JLabel dateLabel = new JLabel("ใส่วันที่(DD-MM-YYYY):");
        JTextField dateInput = new JTextField();
        JButton searchButton = new JButton("ดูประวัติ");

        // ตั้งค่าเริ่มต้นเป็นวันนี้
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateInput.setText(java.time.LocalDate.now().format(formatter));

        dateLabel.setBounds(10, 20, 140, 25);
        dateInput.setBounds(155, 20, 150, 25);
        searchButton.setBounds(310, 20, 120, 25);

        JButton backButton = new JButton("ย้อนกลับ (กลับไป DashBoard)");
        backButton.setBounds(100, 345, 250, 30);
        backButton.addActionListener(e -> {
            frame.dispose();
            DashBoard.main(new String[0]);
        });

        // หัวข้อคอลัมน์: ID - ชื่อ - อาการ - แผนก
        JLabel headerLabel = new JLabel("รายละเอียด: ID - ชื่อ - อาการ - แผนก");
        headerLabel.setBounds(20, 45, 400, 15);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.setBackground(Color.white);
        mainPanel.setBounds(20, 60, 400, 270);

        searchButton.addActionListener(e -> {
            updateHistory(mainPanel, history.show(dateInput.getText()));
        });

        frame.add(headerLabel);
        frame.add(mainPanel);
        frame.add(dateLabel);
        frame.add(dateInput);
        frame.add(searchButton);
        frame.add(backButton);
        frame.setSize(440, 425);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // แสดงประวัติของวันนี้ทันทีเมื่อเปิดหน้า
        updateHistory(mainPanel, history.showToday());

        frame.setVisible(true);
    }

    public static void updateHistory(JPanel box, String text) {
        box.removeAll();

        if (text == null || text.trim().isEmpty()) {
            JLabel emptyLabel = new JLabel("  ไม่พบประวัติการรักษาในวันที่เลือก");
            emptyLabel.setBounds(5, 5, 360, 20);
            box.add(emptyLabel);
            box.revalidate();
            box.repaint();
            return;
        }

        String[] lines = text.split("\n");
        int y = 5;

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().isEmpty())
                continue;
            JLabel label = new JLabel(lines[i]);
            label.setBounds(5, y, 390, 20);
            box.add(label);
            y += 25;
        }

        box.revalidate();
        box.repaint();
    }

}
