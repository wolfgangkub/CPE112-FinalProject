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

    public static void sendPatient(String sentence, boolean er, Patient patient, int pain, int hr) {
        TriageMeneger piority = new TriageMeneger();
        if (er) {
            patient.setPriorityScore(piority.priorityScore(pain, hr, patient.getAge()));
            emergency.enqueue(patient);
        } else if (sentence.contains("หน้าอก") || sentence.contains("หัวใจ")) {
            cadio.enqueue(patient);
        } else if (sentence.contains("หัว")) {
            neuro.enqueue(patient);
        } else if (sentence.contains("กระดูก")) {
            ortho.enqueue(patient);
        } else {
            general.enqueue(patient);
        }
    }

    public static Queue getCadio() { return cadio; }
    public static Queue getNeuro() { return neuro; }
    public static Queue getOrtho() { return ortho; }
    public static Queue getGeneral() { return general; }
    public static PriorityQueue getEmergency() { return emergency; }
}
