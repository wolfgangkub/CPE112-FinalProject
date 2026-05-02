package ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HistoryFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("History system");
        JLabel dateLabel = new JLabel("ใส่วันที่(YYYY-MM-DD):");
        JTextField dateInput = new JTextField();
        JButton searchButton = new JButton("ดูประวัติ");

        dateLabel.setBounds(10, 20, 140, 25);
        dateInput.setBounds(155, 20, 150, 25);
        searchButton.setBounds(310, 20, 120, 25);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.setBackground(Color.white);
        mainPanel.setBounds(20, 60, 400, 270);

        frame.add(mainPanel);
        frame.add(dateLabel);
        frame.add(dateInput);
        frame.add(searchButton);
        frame.setSize(440, 380);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
