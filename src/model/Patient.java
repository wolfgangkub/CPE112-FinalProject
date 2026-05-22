package model;

/*
  ไฟล์: Patient.java
  ทำหน้าที่: โมเดลข้อมูลผู้ป่วยหลัก
  รายละเอียด: เก็บข้อมูลพื้นฐานของผู้ป่วย เช่น id, name, age, gender, underlyingDisease, bloodGroup
  รวมทั้ง priorityScore, department, symptoms, enqueueTime เพื่อใช้กำหนดคิวและประวัติ
  โครงสร้างข้อมูล: POJO เก็บฟิลด์และ getter/setter
  อัลกอริทึม: getDynamicPriorityScore เพิ่มคะแนนตามเวลารอ
  วิธีทดสอบ: สร้าง Patient แล้วตรวจ getter/setter กับ dynamic priority
*/

public class Patient {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String underlyingDisease;
    private String bloodGroup;

    private int priorityScore;
    private String department;
    private String symptoms;
    private long enqueueTime;

    /**
     * สร้าง Patient ใหม่จากข้อมูลพื้นฐาน
     * @param id เลขประจำตัวผู้ป่วย
     * @param name ชื่อผู้ป่วย
     * @param age อายุผู้ป่วย
     * @param gender เพศผู้ป่วย
     * @param underlyingDisease โรคประจำตัว
     * @param bloodGroup กรุ๊ปเลือด
     */
    public Patient(String id, String name, int age, String gender, String underlyingDisease, String bloodGroup) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.underlyingDisease = underlyingDisease;
        this.bloodGroup = bloodGroup;
        this.priorityScore = 0;
        this.department = "NONE";
        this.symptoms = "-";
        this.enqueueTime = System.currentTimeMillis();
    }

    /**
     * คืนค่า ID ของผู้ป่วย
     */
    public String getId() {
        return id;
    }

    /**
     * ตั้งค่า ID ของผู้ป่วย
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * คืนค่าชื่อผู้ป่วย
     */
    public String getName() {
        return name;
    }

    /**
     * ตั้งค่าชื่อผู้ป่วย
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * คืนค่าอายุผู้ป่วย
     */
    public int getAge() {
        return age;
    }

    /**
     * ตั้งค่าอายุผู้ป่วย
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * คืนค่าเพศของผู้ป่วย
     */
    public String getGender() {
        return gender;
    }

    /**
     * ตั้งค่าเพศของผู้ป่วย
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * คืนค่าโรคประจำตัวของผู้ป่วย
     */
    public String getUnderlyingDisease() {
        return underlyingDisease;
    }

    /**
     * ตั้งค่าโรคประจำตัวของผู้ป่วย
     */
    public void setUnderlyingDisease(String underlyingDisease) {
        this.underlyingDisease = underlyingDisease;
    }

    /**
     * คืนค่ากรุ๊ปเลือดของผู้ป่วย
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * ตั้งค่ากรุ๊ปเลือดของผู้ป่วย
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * คืนค่าคะแนน priority ของผู้ป่วย
     */
    public int getPriorityScore() {
        return priorityScore;
    }

    /**
     * ตั้งค่าคะแนน priority ของผู้ป่วย
     */
    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }

    /**
     * คืนค่าแผนกที่ผู้ป่วยถูกส่งไป
     */
    public String getDepartment() {
        return department;
    }

    /**
     * ตั้งค่าแผนกที่ผู้ป่วยถูกส่งไป
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * คืนค่าอาการของผู้ป่วย
     */
    public String getSymptoms() {
        return symptoms;
    }

    /**
     * ตั้งค่าอาการของผู้ป่วย
     */
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    /**
     * คืนค่าเวลาที่ผู้ป่วยเข้าคิว (มิลลิวินาที)
     */
    public long getEnqueueTime() {
        return enqueueTime;
    }

    public void setEnqueueTime(long enqueueTime) {
        this.enqueueTime = enqueueTime;
    }

    public int getDynamicPriorityScore() {
        long secondsWaiting = (System.currentTimeMillis() - this.enqueueTime) / 1000;
        return this.priorityScore + (int)secondsWaiting;
    }
}