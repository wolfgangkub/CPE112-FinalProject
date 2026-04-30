package app;

import hospitalSystem.register;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class registerApp {
    public static void main(String[] args) {
        register worker = new register();
        JFrame frame = new JFrame("Register");
        JLabel firstNameLabel = new JLabel("First name:");
        JTextField firstNameInput = new JTextField();

        JLabel lastNameLabel = new JLabel("Last name:");
        JTextField lastNameInput = new JTextField();

        JLabel birthDateLabel = new JLabel("Date of birth:");
        JLabel dateLabel = new JLabel("(25/04/2550)");
        JTextField birthDateInput = new JTextField();

        JButton backButton = new JButton("Back");
        JButton saveButton = new JButton("Save");

        firstNameLabel.setBounds(30, 25, 100, 25);
        firstNameInput.setBounds(130, 25, 180, 25);

        lastNameLabel.setBounds(30, 65, 100, 25);
        lastNameInput.setBounds(130, 65, 180, 25);

        birthDateLabel.setBounds(30, 105, 100, 25);
        dateLabel.setBounds(30, 118, 100, 25);
        birthDateInput.setBounds(130, 105, 180, 25);

        backButton.setBounds(60, 150, 90, 30);
        saveButton.setBounds(190, 150, 90, 30);

        saveButton.addActionListener(e -> {
            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();
            String dateOfBirth = birthDateInput.getText();

            worker.insertData(firstName, lastName, dateOfBirth);

            JOptionPane.showMessageDialog(frame, "Register succeed");
            firstNameInput.setText("");
            lastNameInput.setText("");
            birthDateInput.setText("");
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            main.main(new String[0]);
        });

        frame.add(firstNameLabel);
        frame.add(firstNameInput);
        frame.add(lastNameLabel);
        frame.add(lastNameInput);
        frame.add(birthDateLabel);
        frame.add(dateLabel);
        frame.add(birthDateInput);
        frame.add(backButton);
        frame.add(saveButton);
        frame.setSize(360, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
