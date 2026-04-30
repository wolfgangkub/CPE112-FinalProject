package model;

public class Patient {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String underlyingDisease; // โรคประจำตัว
    private String bloodGroup; // กรุ๊ปเลือด

    // ตัวแปรสำหรับระบบคิว
    private int priorityScore; // คะแนนความเร่งด่วน (ยิ่งมากยิ่งได้คิวแรก)
    private String department; // แผนกที่ถูกส่งไป
    private String symptoms; // อาการเบื้องต้นตอนซักประวัติ

    // คอนสตรัคเตอร์ (Constructor) เอาไว้สร้าง Object ใหม่
    public Patient(String id, String name, int age, String gender, String underlyingDisease, String bloodGroup) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.underlyingDisease = underlyingDisease;
        this.bloodGroup = bloodGroup;
        this.priorityScore = 0; // เริ่มต้นให้เป็น 0 ไว้ก่อน
        this.department = "NONE";
        this.symptoms = "-";
    }

    // --- Getter & Setter Methods ---
    // (เอาไว้ดึงข้อมูลหรือแก้ไขข้อมูลทีหลัง)
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getUnderlyingDisease() { return underlyingDisease; }
    public void setUnderlyingDisease(String underlyingDisease) { this.underlyingDisease = underlyingDisease; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public int getPriorityScore() { return priorityScore; }
    public void setPriorityScore(int priorityScore) { this.priorityScore = priorityScore; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    // แปลงข้อมูลเป็น String สำหรับเซฟลงไฟล์ (คั่นด้วยลูกน้ำ CSV)
    public String toCSV() {
        return id + "," + name + "," + age + "," + gender + "," + underlyingDisease + "," + bloodGroup;
    }

    // แปลงข้อมูลมาแสดงผลแบบอ่านง่าย
    @Override
    public String toString() {
        return "ID: " + id + " | ชื่อ: " + name + " | อายุ: " + age + " | โรคประจำตัว: " + underlyingDisease + " | กรุ๊ปเลือด: " + bloodGroup;
    }
}
