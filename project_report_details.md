# สรุปโปรเจค CPE112-FinalProject

## วัตถุประสงค์ของไฟล์
เอกสารนี้อธิบายรายละเอียดทุกไฟล์ในโปรเจค โดยเน้นส่วน ข้อ B ของรายงาน:
- โครงสร้างข้อมูลที่ใช้ในแต่ละไฟล์
- การทำงานของแต่ละเมธอด
- อัลกอริทึมและตรรกะที่นำมาใช้
- วิธีการใช้งาน และการทดสอบเบื้องต้น

---

## รายชื่อไฟล์และรายละเอียด

### `src/Main.java`
- ทำหน้าที่: จุดเริ่มต้นของโปรแกรม
- รายละเอียด: เรียก `WelcomeFrame.main()` เพื่อเปิดหน้าจอแรก
- เมธอด:
  - `main(String[] args)`: entry point ของโปรแกรม

### `src/model/Patient.java`
- ทำหน้าที่: โมเดลข้อมูลผู้ป่วยหลัก
- รายละเอียด: เก็บข้อมูลพื้นฐานผู้ป่วยและข้อมูลคิว
- ฟิลด์หลัก:
  - `id`, `name`, `age`, `gender`, `underlyingDisease`, `bloodGroup`
  - `priorityScore`, `department`, `symptoms`, `enqueueTime`
- เมธอด:
  - `Patient(...)`: สร้างผู้ป่วยใหม่พร้อมฟิลด์เริ่มต้น
  - `getId()`, `setId(...)`
  - `getName()`, `setName(...)`
  - `getAge()`, `setAge(...)`
  - `getGender()`, `setGender(...)`
  - `getUnderlyingDisease()`, `setUnderlyingDisease(...)`
  - `getBloodGroup()`, `setBloodGroup(...)`
  - `getPriorityScore()`, `setPriorityScore(...)`
  - `getDepartment()`, `setDepartment(...)`
  - `getSymptoms()`, `setSymptoms(...)`
  - `getEnqueueTime()`, `setEnqueueTime(...)`
  - `getDynamicPriorityScore()`: รวมคะแนน priority เดิมกับเวลารอ
- อัลกอริทึมสำคัญ: `getDynamicPriorityScore()` ใช้เวลารอเพื่อปรับลำดับคิว
- วิธีทดสอบ: สร้าง `Patient`, ตั้งค่า field ต่าง ๆ แล้วตรวจ getter/setter

### `src/logic/TriageMeneger.java`
- ทำหน้าที่: ประเมินอาการผู้ป่วยและเรียกคะแนนฉุกเฉิน
- รายละเอียด: แปลงข้อมูล `pain`, `hr`, `temp` เป็นคะแนนย่อย แล้วรวมเป็นคะแนนรวม
- เมธอด:
  - `priorityScore(int pain, int hr, double temp)`: สรุปคะแนนทั้งหมด
  - `pain(int pain)`: แปลงระดับปวดเป็นคะแนนย่อย
  - `hrScore(int hr)`: แปลงอัตราการเต้นหัวใจเป็นคะแนนย่อย
  - `tempScore(double temp)`: แปลงอุณหภูมิเป็นคะแนนย่อย
- อัลกอริทึม: เงื่อนไขช่วงค่า (`if...else`) และการคูณตัวเองเพื่อถ่วงน้ำหนัก
- วิธีทดสอบ: ทดสอบค่าต่างระดับและตรวจผลลัพธ์กับเกณฑ์ในแต่ละช่วง

### `src/logic/QueueManeger.java`
- ทำหน้าที่: จัดส่งผู้ป่วยไปยัง queue ที่เหมาะสม
- รายละเอียด: มี queue ของแผนกทั่วไป 4 แผนก และ `PriorityQueue` สำหรับฉุกเฉิน
- เมธอด:
  - `sendPatient(String sentence, boolean er, Patient patient, int pain, int hr, double temp)`: ตัดสินใจส่งผู้ป่วย
  - `getCadio()`, `getNeuro()`, `getOrtho()`, `getGeneral()`: คืน queue แต่ละแผนก
  - `getEmergency()`: คืน priority queue ฉุกเฉิน
- ตรรกะการตัดสินใจ:
  - `er == true` -> ใช้คะแนน Triage และส่งไป Emergency
  - คำว่า `หน้าอก` หรือ `หัวใจ` -> CADIO
  - คำว่า `หัว` -> NEURO
  - คำว่า `กระดูก` -> ORTHO
  - อื่น ๆ -> GENERAL
- วิธีทดสอบ: ส่งข้อมูลอาการหลายรูปแบบและตรวจ queue เป้าหมาย

### `src/datastruct/Queue.java`
- ทำหน้าที่: โครงสร้างข้อมูลคิว FIFO
- รายละเอียด: ใช้ linked list แบบง่าย มี `front` และ `rear`
- เมธอด:
  - `Queue()`: สร้างคิวว่าง
  - `enqueue(Patient data)`: เพิ่มผู้ป่วยเข้าท้ายคิว
  - `dequeue()`: นำผู้ป่วยจากหัวคิวออก
  - `isEmpty()`: ตรวจว่าคิวว่างหรือไม่
  - `getSize()`: คืนค่าจำนวนสมาชิก
  - `peek()`: ดูหัวคิวโดยไม่ถอดออก
  - `getHead()`: คืน Node แรกเพื่อใช้แสดงผล
- อัลกอริทึม: enqueue ต่อท้าย `rear`, dequeue ดึงจาก `front`, ปรับ pointer เมื่อว่าง
- วิธีทดสอบ: ใส่และดึงข้อมูลหลายรายการเพื่อตรวจลำดับ FIFO

### `src/datastruct/PriorityQueue.java`
- ทำหน้าที่: โครงสร้าง priority queue สำหรับผู้ป่วยฉุกเฉิน
- รายละเอียด: สืบทอดจาก `Queue` แต่แทรกตามคะแนน
- เมธอด:
  - `enqueue(Patient patient)`: แทรกผู้ป่วยตามลำดับ `dynamicPriorityScore`
- ตรรกะการทำงาน:
  - ถ้า queue ว่างหรือคะแนนสูงกว่า `front` ให้แทรกหน้าสุด
  - มิฉะนั้นวนหา node ที่คะแนนต่อไปน้อยกว่าแล้วแทรก
- วิธีทดสอบ: ใส่ผู้ป่วยคะแนนต่างกันแล้วตรวจลำดับการออก

### `src/datastruct/Stack.java`
- ทำหน้าที่: โครงสร้างข้อมูลสแตก LIFO
- รายละเอียด: ใช้เก็บประวัติชั่วคราวหรือข้อมูลย้อนกลับ
- เมธอด:
  - `Stack()`: สร้าง stack ว่าง
  - `push(Patient patient)`: เก็บผู้ป่วยด้านบน
  - `pop()`: เอาผู้ป่วยด้านบนออก
  - `getTop()`: คืน Node ด้านบนโดยไม่เอาออก
- วิธีทดสอบ: push ข้อมูลหลายรายการแล้ว pop ออกเพื่อตรวจลำดับ

### `src/datastruct/Node.java`
- ทำหน้าที่: โหนดพื้นฐานของ linked list
- รายละเอียด: เก็บข้อมูล `Patient` และ pointer `next`
- เมธอด:
  - `Node(Patient data)`: สร้าง node พร้อมข้อมูลผู้ป่วย

### `src/storage/DataManeger.java`
- ทำหน้าที่: จัดการไฟล์ข้อมูลผู้ป่วย
- รายละเอียด: อ่าน, เขียน, ค้นหา, และสร้าง `Patient`
- เมธอด:
  - `register(...)`: เขียนข้อมูลผู้ป่วยใหม่ลง CSV
  - `findPatient(String searchId)`: ตรวจสอบว่ามี ID อยู่หรือไม่
  - `returnDisease(String id)`: คืนค่าโรคประจำตัว
  - `returnName(String id)`: คืนค่าชื่อผู้ป่วย
  - `returnPatient(String id)`: สร้าง `Patient` จากไฟล์
- อัลกอริทึม: ใช้ `BufferedReader`/`BufferedWriter` และ `split(",")`
- วิธีทดสอบ: บันทึกผู้ป่วยใหม่และอ่านข้อมูลกลับเพื่อตรวจสอบความถูกต้อง

### `src/storage/History.java`
- ทำหน้าที่: บันทึกและแสดงประวัติการรักษา
- รายละเอียด: เขียนไฟล์ `History.csv` และอ่านข้อมูลตามวันที่
- เมธอด:
  - `insert(...)`: บันทึกประวัติลงไฟล์และ push ไปยัง stack ชั่วคราว
  - `showToday()`: แสดงประวัติของวันนี้
  - `show(String date)`: แสดงประวัติของวันที่ระบุ
- ตรรกะการทำงาน:
  - ใช้ stack เพื่อกลับลำดับข้อมูลก่อนแสดง
  - รองรับฟอร์แมตบันทึกเก่าและใหม่
- วิธีทดสอบ: บันทึกประวัติแล้วตรวจข้อมูลที่ `show()` คืนค่า

### `src/ui/WelcomeFrame.java`
- ทำหน้าที่: หน้า input ID ต้อนรับผู้ใช้งาน
- รายละเอียด: รับ ID แล้วตัดสินใจว่าไปหน้าซักประวัติหรือหน้าลงทะเบียน
- เมธอด:
  - `main(String[] args)`: สร้างหน้าจอและปุ่ม
  - `setUIFont(FontUIResource font)`: ตั้งค่าฟอนต์ของ UI
- วิธีทดสอบ: กรอก ID ที่มีและไม่มีเพื่อตรวจหน้าถัดไป

### `src/ui/RegisterFrame.java`
- ทำหน้าที่: ฟอร์มลงทะเบียนผู้ป่วยใหม่
- รายละเอียด: รับข้อมูลพื้นฐาน, บันทึกลง archivo แล้วไป `TriageFrame`
- เมธอด:
  - `main(String[] args)`: เรียก `open("")`
  - `open(String id)`: สร้างหน้าลงทะเบียน
- วิธีทดสอบ: ลงทะเบียนผู้ป่วยใหม่และตรวจไฟล์ `Patient.csv`

### `src/ui/TriageFrame.java`
- ทำหน้าที่: ฟอร์มประเมินอาการก่อนจัดคิว
- รายละเอียด: เก็บข้อมูลอาการและค่าตัวเลขเพื่อส่งไป queue
- เมธอด:
  - `main(String[] args)`: เรียก `open(null)` เพื่อเปิดหน้าจอ
  - `open(Patient patient)`: สร้างฟอร์มและปุ่มประเมิน
- วิธีทดสอบ: ประเมินผู้ป่วยและตรวจ queue ใน Dashboard

### `src/ui/DashBoard.java`
- ทำหน้าที่: แสดงและจัดการคิวผู้ป่วย
- รายละเอียด: มีแผนก Emergency, CADIO, ORTHO, NEURO, GENERAL พร้อมปุ่มเรียกคิว
- เมธอด:
  - `main(String[] args)`: สร้างหน้าจอ Dashboard ทั้งหมด
  - `updateQueueView(JPanel box, Queue queue)`: แสดงรายการคิวบน UI
  - `emergencyBeep()`: ฟังก์ชันเสียงเตือนฉุกเฉิน
- วิธีทดสอบ: เรียกผู้ป่วยจากคิวแต่ละแผนกและตรวจประวัติบันทึก

### `src/ui/HistoryFrame.java`
- ทำหน้าที่: ดูประวัติการรักษาตามวันที่
- รายละเอียด: รับวันที่และแสดงประวัติจาก `History` class
- เมธอด:
  - `main(String[] args)`: สร้างหน้า History และตั้งค่าวันเริ่มต้น
  - `updateHistory(JPanel box, String text)`: แปลงผลลัพธ์เป็น label เพื่อนำเสนอ
- วิธีทดสอบ: ดูประวัติวันที่มีข้อมูลใน `History.csv`
