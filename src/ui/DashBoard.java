package ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class DashBoard {
    public static void main(String[] args) {

    }

    public static void open() {
        JFrame frame = new JFrame("Dashboard");
        JLabel main = new JLabel("ระบบจัดการคิวในโรงพยาบาล");
        main.setBounds(480, 10, 250, 25);

        JPanel queueContainer = new JPanel();
        queueContainer.setLayout(null);
        queueContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        queueContainer.setBounds(20, 35, 1100, 420);

        JPanel emergencyPanel = new JPanel();
        TitledBorder emergencyBorder = BorderFactory.createTitledBorder("EMERGENCY🚨");
        emergencyBorder.setTitleColor(Color.BLACK);
        emergencyPanel.setBorder(emergencyBorder);
        emergencyPanel.setLayout(null);
        emergencyPanel.setBounds(10, 10, 200, 400);

        JPanel emergencyBox = new JPanel();
        emergencyBox.setLayout(null);
        emergencyBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        emergencyBox.setBackground(Color.WHITE);
        emergencyBox.setBounds(8, 25, 184, 335);
        emergencyPanel.add(emergencyBox);

        JButton emergencyButton = new JButton("เรียกคิวถัดไป");
        emergencyButton.setBounds(14, 367, 170, 25);
        emergencyPanel.add(emergencyButton);

        JPanel cadioPanel = new JPanel();
        TitledBorder cadioBorder = BorderFactory.createTitledBorder("CADIO❤️");
        cadioPanel.setBorder(cadioBorder);
        cadioPanel.setLayout(null);
        cadioPanel.setBounds(230, 10, 200, 400);

        JPanel cadioBox = new JPanel();
        cadioBox.setLayout(null);
        cadioBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cadioBox.setBackground(Color.WHITE);
        cadioBox.setBounds(8, 25, 184, 335);
        cadioPanel.add(cadioBox);

        JButton cadioButton = new JButton("เรียกคิวถัดไป");
        cadioButton.setBounds(14, 367, 170, 25);
        cadioPanel.add(cadioButton);

        JPanel orthoPanel = new JPanel();
        TitledBorder orthBorder = BorderFactory.createTitledBorder("ORTHO🦴");
        orthoPanel.setBorder(orthBorder);
        orthoPanel.setLayout(null);
        orthoPanel.setBounds(450, 10, 200, 400);

        JPanel orthoBox = new JPanel();
        orthoBox.setLayout(null);
        orthoBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        orthoBox.setBackground(Color.WHITE);
        orthoBox.setBounds(8, 25, 184, 335);
        orthoPanel.add(orthoBox);

        JButton orthoButton = new JButton("เรียกคิวถัดไป");
        orthoButton.setBounds(14, 367, 170, 25);
        orthoPanel.add(orthoButton);

        JPanel neuroPanel = new JPanel();
        TitledBorder neuroBorder = BorderFactory.createTitledBorder("NEURO🧠");
        neuroPanel.setBorder(neuroBorder);
        neuroPanel.setLayout(null);
        neuroPanel.setBounds(670, 10, 200, 400);

        JPanel neuroBox = new JPanel();
        neuroBox.setLayout(null);
        neuroBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        neuroBox.setBackground(Color.WHITE);
        neuroBox.setBounds(8, 25, 184, 335);
        neuroPanel.add(neuroBox);

        JButton neuroButton = new JButton("เรียกคิวถัดไป");
        neuroButton.setBounds(14, 367, 170, 25);
        neuroPanel.add(neuroButton);

        JPanel generalPane = new JPanel();
        TitledBorder generalBorder = BorderFactory.createTitledBorder("GENERAL🟢");
        generalPane.setBorder(generalBorder);
        generalPane.setLayout(null);
        generalPane.setBounds(890, 10, 200, 400);

        JPanel generalBox = new JPanel();
        generalBox.setLayout(null);
        generalBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        generalBox.setBackground(Color.WHITE);
        generalBox.setBounds(8, 25, 184, 335);
        generalPane.add(generalBox);

        JButton generalButton = new JButton("เรียกคิวถัดไป");
        generalButton.setBounds(14, 367, 170, 25);
        generalPane.add(generalButton);

        JButton refeshButton = new JButton("รีเฟรชข้อมูลคิว");
        refeshButton.setBounds(380, 470, 120, 25);

        JButton inpuButton = new JButton("รับผู้ป่วยใหม่");
        inpuButton.setBounds(510, 470, 120, 25);

        inpuButton.addActionListener(e -> {
            frame.dispose();
            WelcomeFrame.main(new String[0]);
        });

        JButton historyButton = new JButton("ดูประวัติการรักษา");
        historyButton.setBounds(640, 470, 130, 25);

        queueContainer.add(emergencyPanel);
        queueContainer.add(cadioPanel);
        queueContainer.add(orthoPanel);
        queueContainer.add(neuroPanel);
        queueContainer.add(generalPane);

        frame.add(main);
        frame.add(queueContainer);
        frame.add(refeshButton);
        frame.add(inpuButton);
        frame.add(historyButton);
        frame.setSize(1130, 540);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
