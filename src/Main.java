import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.util.Enumeration;
import javax.swing.SwingUtilities;
import gui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // ตั้งค่าฟอนต์เริ่มต้นของโปรแกรมทั้งหมดเป็น Tahoma เพื่อให้รองรับภาษาไทย
        setUIFont(new FontUIResource(new Font("Tahoma", Font.PLAIN, 14)));

        // ใช้ SwingUtilities.invokeLater เพื่อความปลอดภัยในการเปิดหน้าต่าง GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // สร้างและแสดงหน้าต่าง LoginFrame
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }

    // Method สำหรับเปลี่ยนฟอนต์ทุกอย่างในโปรแกรม (ปุ่ม, ข้อความ, ช่องกรอก ฯลฯ)
    private static void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, f);
        }
    }
}
