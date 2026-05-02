package logic;

public class TriageMeneger {
    public int severityScore(int pain, int hr) {
        int score = (pain * 2) + (hr * 3);
        return score;
    }

    public int priorityScore(int severityScore, int waiting, int ageFactor, boolean critical) {
        int score = severityScore + waiting + ageFactor;
        if (critical) {
            score = 1000;
        }
        return score;
    }
}