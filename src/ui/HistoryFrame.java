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

        dateLabel.setBounds(10, 20, 140, 25);
        dateInput.setBounds(155, 20, 150, 25);
        searchButton.setBounds(310, 20, 120, 25);

        JButton backButton = new JButton("ย้อนกลับ (กลับไป DashBoard)");
        backButton.setBounds(120, 345, 200, 30);
        backButton.addActionListener(e -> {
            frame.dispose();
            DashBoard.main(new String[0]);
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.setBackground(Color.white);
        mainPanel.setBounds(20, 60, 400, 270);

        searchButton.addActionListener(e -> {
            updateHistory(mainPanel, history.show(dateInput.getText()));
        });

        frame.add(mainPanel);
        frame.add(dateLabel);
        frame.add(dateInput);
        frame.add(searchButton);
        frame.add(backButton);
        frame.setSize(440, 425);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void updateHistory(JPanel box, String text) {
        box.removeAll();

        String[] lines = text.split("\n");
        int y = 5;

        for (int i = 0; i < lines.length; i++) {
            JLabel label = new JLabel((i + 1) + ". " + lines[i]);
            label.setBounds(5, y, 360, 20);
            box.add(label);
            y += 25;
        }

        box.revalidate();
        box.repaint();
    }

}
