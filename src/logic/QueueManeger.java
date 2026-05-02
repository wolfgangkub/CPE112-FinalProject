package logic;

public class QueueManeger {
    public void sendPatient(String sentence) {
        if (sentence.contains("หน้าอก") || sentence.contains("หัวใจ")) {
            // ส่งไปแผนกcadio
        } else if (sentence.contains("หัว")) {
            // ส่งไปแผนกNeuro
        } else if (sentence.contains("กระดูก")) {
            // ส่งไปแผนก ortho
        } else {
            // ส่งไปgeneral
        }
    }
}
