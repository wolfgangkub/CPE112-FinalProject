package ui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class TriageFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ซักประวัติและอาการ");
        JPanel patientPanel = new JPanel();
        JLabel nameLabel = new JLabel("ชื่อ:");
        JLabel diseaseLabel = new JLabel("โรคประจำตัว: ");

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
        JTextField painInput = new JTextField();
        ((AbstractDocument) painInput.getDocument()).setDocumentFilter(new NumberRangeFilter(0, 10));

        JLabel hrLabel = new JLabel("ระดับความหายใจลำบาก (0 - 10):");
        JTextField hrInput = new JTextField();
        ((AbstractDocument) hrInput.getDocument()).setDocumentFilter(new NumberRangeFilter(0, 10));

        JLabel tempLabel = new JLabel("อุณหภูมิร่างกาย (°C):");
        JTextField tempInput = new JTextField();

        JLabel erLabel = new JLabel("อาการวิกฤต (หมดสติ/เลือดออกหนัก):");
        JCheckBox erInput = new JCheckBox("ใช่(ฉุกเฉิน)");

        JButton finish = new JButton("ประเมินและจัดคิว");

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
        finish.setBounds(115, 310, 170, 30);

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
        frame.add(finish);
        frame.setSize(400, 380);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class NumberRangeFilter extends DocumentFilter {
        private final int min;
        private final int max;

        NumberRangeFilter(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs)
                throws BadLocationException {
            replace(fb, offset, 0, text, attrs);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String nextText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

            if (nextText.isEmpty()) {
                super.replace(fb, offset, length, text, attrs);
                return;
            }

            if (!nextText.matches("\\d+")) {
                return;
            }

            int value = Integer.parseInt(nextText);
            if (value >= min && value <= max) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
