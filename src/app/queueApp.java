package app;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class queueApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dashboard");
        JLabel main = new JLabel("ระบบจัดการคิวในโรงพยาบาล");
        main.setBounds(480, 10, 250, 25);

        JPanel emergencyPanel = new JPanel();
        TitledBorder emergencyBorder = BorderFactory.createTitledBorder("EMERGENCY🚨");
        emergencyBorder.setTitleColor(Color.BLACK);
        emergencyPanel.setBorder(emergencyBorder);
        emergencyPanel.setLayout(null);
        emergencyPanel.setBounds(30, 40, 200, 400);

        JPanel emergencyBox = new JPanel();
        emergencyBox.setLayout(null);
        emergencyBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        emergencyBox.setBackground(Color.WHITE);
        emergencyBox.setBounds(8, 25, 184, 310);
        emergencyPanel.add(emergencyBox);

        JPanel cadioPanel = new JPanel();
        TitledBorder cadioBorder = BorderFactory.createTitledBorder("CADIO❤️");
        cadioPanel.setBorder(cadioBorder);
        cadioPanel.setLayout(null);
        cadioPanel.setBounds(250, 40, 200, 400);

        JPanel cadioBox = new JPanel();
        cadioBox.setLayout(null);
        cadioBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cadioBox.setBackground(Color.WHITE);
        cadioBox.setBounds(8, 25, 184, 310);
        cadioPanel.add(cadioBox);

        JPanel orthoPanel = new JPanel();
        TitledBorder orthBorder = BorderFactory.createTitledBorder("ORTHO🦴");
        orthoPanel.setBorder(orthBorder);
        orthoPanel.setLayout(null);
        orthoPanel.setBounds(470, 40, 200, 400);

        JPanel orthoBox = new JPanel();
        orthoBox.setLayout(null);
        orthoBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        orthoBox.setBackground(Color.WHITE);
        orthoBox.setBounds(8, 25, 184, 310);
        orthoPanel.add(orthoBox);

        JPanel neuroPanel = new JPanel();
        TitledBorder neuroBorder = BorderFactory.createTitledBorder("NEURO🧠");
        neuroPanel.setBorder(neuroBorder);
        neuroPanel.setLayout(null);
        neuroPanel.setBounds(690, 40, 200, 400);

        JPanel neuroBox = new JPanel();
        neuroBox.setLayout(null);
        neuroBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        neuroBox.setBackground(Color.WHITE);
        neuroBox.setBounds(8, 25, 184, 310);
        neuroPanel.add(neuroBox);

        JPanel generalPane = new JPanel();
        TitledBorder generalBorder = BorderFactory.createTitledBorder("GENERAL🟢");
        generalPane.setBorder(generalBorder);
        generalPane.setLayout(null);
        generalPane.setBounds(910, 40, 200, 400);

        JPanel generalBox = new JPanel();
        generalBox.setLayout(null);
        generalBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        generalBox.setBackground(Color.WHITE);
        generalBox.setBounds(8, 25, 184, 310);
        generalPane.add(generalBox);

        frame.add(main);
        frame.add(emergencyPanel);
        frame.add(cadioPanel);
        frame.add(orthoPanel);
        frame.add(neuroPanel);
        frame.add(generalPane);
        frame.setSize(1135, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
