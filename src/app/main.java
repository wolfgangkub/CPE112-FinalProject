package app;

import javax.swing.JButton;
import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Welcome to hospital");
        JButton buttonRegister = new JButton("Register");

        buttonRegister.setBounds(40, 20, 100, 20);
        buttonRegister.addActionListener(e -> {
            frame.dispose();
            registerApp.main(new String[0]);
        });

        frame.add(buttonRegister);
        frame.setSize(360, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
