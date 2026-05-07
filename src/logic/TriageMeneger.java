package logic;

public class TriageMeneger {

    public int priorityScore(int pain, int hr, double temp) {
        int ISS = (pain(pain) * pain(pain)) + (hrScore(hr) * hrScore(hr)) + (tempScore(temp) * tempScore(temp));
        return ISS;
    }

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
