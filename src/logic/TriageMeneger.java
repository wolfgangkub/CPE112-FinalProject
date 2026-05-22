package logic;

/*
  ไฟล์: TriageMeneger.java
  ทำหน้าที่: ประเมินอาการของผู้ป่วยเพื่อคำนวณคะแนนฉุกเฉิน
  รายละเอียด: แปลงค่า pain, hr, temp เป็นคะแนนย่อย แล้วคำนวณ ISS เป็นความสำคัญของผู้ป่วย
  โครงสร้างข้อมูล: ใช้เมธอดในคลาสเดียว ไม่ใช้โครงสร้างข้อมูลพิเศษ
  อัลกอริทึม: เงื่อนไขช่วงค่าและสูตรคะแนนกำลังสอง
  วิธีทดสอบ: เรียก priorityScore กับค่าต่าง ๆ แล้วตรวจผลลัพธ์ตามช่วงที่คาดไว้
*/

public class TriageMeneger {

    /**
     * คำนวณคะแนนความสำคัญสำหรับการจัดลำดับผู้ป่วยฉุกเฉิน
     * ใช้คะแนนย่อยจากอาการปวด, อัตราการเต้นหัวใจ, และอุณหภูมิ
     */
    public int priorityScore(int pain, int hr, double temp) {
        int ISS = (pain(pain) * pain(pain)) + (hrScore(hr) * hrScore(hr)) + (tempScore(temp) * tempScore(temp));
        return ISS;
    }

    /**
     * ประเมินคะแนนจากระดับความเจ็บปวด
     * @param pain ระดับความเจ็บปวด 0-10
     * @return คะแนนย่อยสำหรับ pain
     */
    public int pain(int pain) {
        if (pain == 0) {
            return 0;
        } else if (pain >= 1 && pain <= 3) {
            return 1;
        } else if (pain >= 4 && pain <= 5) {
            return 2;
        } else if (pain >= 6 && pain <= 7) {
            return 3;
        } else if (pain >= 8 && pain <= 9) {
            return 4;
        } else {
            return 5;
        }
    }

    /**
     * ประเมินคะแนนจากอัตราการเต้นของหัวใจ
     * @param hr ค่าชีพจร (ครั้งต่อนาที)
     * @return คะแนนย่อยสำหรับ hr
     */
    public int hrScore(int hr) {
        if (hr >= 60 && hr <= 100) {
            return 0;
        } else if (hr >= 101 && hr <= 110) {
            return 1;
        } else if (hr >= 111 && hr <= 130) {
            return 2;
        } else if (hr >= 131 && hr <= 150) {
            return 3;
        } else if (hr > 150 || hr <= 59) {
            return 5;
        } else {
            return 0;
        }
    }

    /**
     * ประเมินคะแนนจากอุณหภูมิร่างกาย
     * @param temp อุณหภูมิหน่วยเซลเซียส
     * @return คะแนนย่อยสำหรับ temp
     */
    public int tempScore(double temp) {
        if (temp >= 36.0 && temp <= 37.5) {
            return 0;
        } else if (temp >= 37.6 && temp <= 38.0) {
            return 1;
        } else if (temp >= 38.1 && temp <= 39.0) {
            return 2;
        } else if (temp >= 39.1 && temp <= 40.0) {
            return 3;
        } else if (temp > 40.0 || temp < 35.0) {
            return 5;
        } else {
            return 0;
        }
    }
}
