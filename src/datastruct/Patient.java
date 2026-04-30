package datastruct;

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
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUnderlyingDisease() {
        return underlyingDisease;
    }

    public void setUnderlyingDisease(String underlyingDisease) {
        this.underlyingDisease = underlyingDisease;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
