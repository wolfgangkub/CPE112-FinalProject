package logic;

public class TriageMeneger {

    public int priorityScore(int pain, int hr, int ageFactor) {
        int severityScore = (pain * 2) + (hr * 3);
        int score = severityScore + ageFactor;
        return score;
    }
}