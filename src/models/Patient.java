package models;

import java.io.Serializable;
import java.time.LocalDateTime;

// implements Serializable เพื่อให้ฝั่ง Data เซฟลงไฟล์ได้
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    // 1. ข้อมูลพื้นฐาน (จากหน้า Register)
    private String id;
    private String name;
    private int age;
    private String gender;
    private String underlyingDisease; // โรคประจำตัว
    private String bloodGroup;        // กรุ๊ปเลือด

    // 2. ข้อมูลอาการ (จากหน้า Triage)
    private String symptoms;
    private int painLevel;       // 0-10
    private int breathingLevel;  // 0-10
    private boolean hasHighFever; // ไข้สูง (Temp >= 39)
    private boolean isCritical;   // อาการวิกฤต (ฉุกเฉินสูงสุด)

    // 3. ข้อมูลที่ระบบคำนวณให้ (จาก TriageManager)
    private int severityScore;
    private int priorityScore;
    private String department;
    private LocalDateTime registerTime;

    // Constructor เปล่า (สำหรับสร้าง Object เตรียมไว้)
    public Patient() {
        this.registerTime = LocalDateTime.now(); // เก็บเวลาตอนลงทะเบียนทันที
    }

    // --- Getters และ Setters (ให้ทุกคนใช้ร่วมกัน) ---

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

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public int getPainLevel() { return painLevel; }
    public void setPainLevel(int painLevel) { this.painLevel = painLevel; }

    public int getBreathingLevel() { return breathingLevel; }
    public void setBreathingLevel(int breathingLevel) { this.breathingLevel = breathingLevel; }

    public boolean isHasHighFever() { return hasHighFever; }
    public void setHasHighFever(boolean hasHighFever) { this.hasHighFever = hasHighFever; }

    public boolean isCritical() { return isCritical; }
    public void setCritical(boolean critical) { this.isCritical = critical; }

    public int getSeverityScore() { return severityScore; }
    public void setSeverityScore(int severityScore) { this.severityScore = severityScore; }

    public int getPriorityScore() { return priorityScore; }
    public void setPriorityScore(int priorityScore) { this.priorityScore = priorityScore; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public LocalDateTime getRegisterTime() { return registerTime; }
    public void setRegisterTime(LocalDateTime registerTime) { this.registerTime = registerTime; }
}