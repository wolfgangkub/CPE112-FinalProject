package ui;

import java.awt.Font;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class WelcomeFrame {
    public static void main(String[] args) {
        setUIFont(new FontUIResource(new Font("Tahoma", Font.PLAIN, 14)));
        JFrame frame = new JFrame("Welcome to hospital");
        JLabel idLebel = new JLabel("กรุณากรอกเลขประจำตัว (ID):");
        JTextField idInput = new JTextField();
        JButton findButton = new JButton("ค้นหา / ลงทะเบียน");
        JButton dashButton = new JButton("ข้ามไปหน้าจัดการคิว (DashBoard)");

        idLebel.setBounds(20, 30, 220, 25);
        idInput.setBounds(200, 30, 180, 25);
        findButton.setBounds(95, 80, 200, 25);
        dashButton.setBounds(70, 120, 250, 25);
        frame.setSize(400, 250);
        findButton.addActionListener(e -> {
            String id = idInput.getText();
        });
        dashButton.addActionListener(e -> {
            frame.dispose();
            DashBoard.main(new String[0]);
        });
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(idInput);
        frame.add(idLebel);
        frame.add(findButton);
        frame.add(dashButton);
        frame.setVisible(true);
    }

    private static void setUIFont(FontUIResource font) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, font);
            }
        }
    }
}
