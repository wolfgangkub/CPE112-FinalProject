package logic;

import datastruct.Queue;
import datastruct.PriorityQueue;
import model.Patient;

public class QueueManeger {
    private static Queue cadio = new Queue();
    private static Queue neuro = new Queue();
    private static Queue ortho = new Queue();
    private static Queue general = new Queue();
    private static PriorityQueue emergency = new PriorityQueue();

    public static void sendPatient(String sentence, boolean er, Patient patient, int pain, int hr, double temp) {
        TriageMeneger piority = new TriageMeneger();
        if (er) {
            int score = piority.priorityScore(pain, hr, temp);
            patient.setPriorityScore(score);
            patient.setDepartment("EMERGENCY");
            emergency.enqueue(patient);

        } else if (sentence.contains("หน้าอก") || sentence.contains("หัวใจ")) {
            patient.setDepartment("CADIO");
            cadio.enqueue(patient);

        } else if (sentence.contains("หัว")) {
            patient.setDepartment("NEURO");
            neuro.enqueue(patient);

        } else if (sentence.contains("กระดูก")) {
            patient.setDepartment("ORTHO");
            ortho.enqueue(patient);

        } else {
            patient.setDepartment("GENERAL");
            general.enqueue(patient);
        }

    }

    public static Queue getCadio() {

        return cadio;

    }

    public static Queue getNeuro() {
        return neuro;
    }

    public static Queue getOrtho() {
        return ortho;
    }

    public static Queue getGeneral() {
        return general;
    }

    public static PriorityQueue getEmergency() {
        return emergency;
    }
}
