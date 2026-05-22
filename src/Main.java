
/*
  ไฟล์: Main.java
  ทำหน้าที่: Entry point ของโปรแกรม
  รายละเอียด: เรียกหน้า UI ต้อนรับ (WelcomeFrame) เพื่อเริ่มใช้งานระบบ
  โครงสร้างข้อมูล: ไม่มีข้อมูลคงที่ ใช้เป็นจุดเริ่มต้น
  วิธีทดสอบ: รัน Main แล้วควรเปิดหน้าจอ WelcomeFrame
*/

import ui.WelcomeFrame;

public class Main {
    /**
     * จุดเริ่มต้นของโปรแกรม
     * เรียกหน้า WelcomeFrame เพื่อเริ่มต้นการใช้งานระบบ
     */
    public static void main(String[] args) {
        WelcomeFrame.main(new String[0]);
    }
}
