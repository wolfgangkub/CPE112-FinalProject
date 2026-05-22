/*
  ไฟล์: WelcomeFrame.java
  ทำหน้าที่: หน้าเริ่มต้นของโปรแกรมสำหรับกรอก ID ผู้ป่วย
  รายละเอียด: ค้นหา ID ในไฟล์ Patient.csv, ไปหน้าซักประวัติถ้าพบ, ไปหน้าลงทะเบียนถ้าไม่พบ
  โครงสร้างข้อมูล: ใช้งาน Swing UI components ไม่มีโครงสร้างข้อมูลเพิ่มเติม
  อัลกอริทึม: ตรวจสอบการมีอยู่ของ ID โดยเรียก DataManeger.findPatient(id)
  วิธีทดสอบ: เปิดหน้าแล้วกรอก ID ที่มีและไม่มีใน Patient.csv ดูการเปลี่ยนหน้า
*/
package ui;

import java.awt.Font;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import storage.DataManeger;
import model.Patient;

public class WelcomeFrame {
    /**
     * เปิดหน้าจอต้อนรับและให้ผู้ใช้กรอก ID ผู้ป่วย
     * ถ้ามีข้อมูลในระบบจะไปหน้าซักประวัติ, ถ้าไม่มีก็ไปหน้าลงทะเบียน
     */
    public static void main(String[] args) {
        DataManeger data = new DataManeger();
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
            String id = idInput.getText().trim();
            if (data.findPatient(id)) {
                frame.dispose();
                TriageFrame.open(data.returnPatient(id));
            } else {
                frame.dispose();
                RegisterFrame.open(id);
            }
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

    /**
     * ตั้งค่า Font ของ UI ให้อยู่ในรูปแบบเดียวกันทั่วทั้งแอป
     */
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
