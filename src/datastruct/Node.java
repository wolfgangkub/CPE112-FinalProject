package datastruct;

import model.Patient;

public class Node {
    public Patient data;
    public Node next;

    public Node(Patient data) {
        this.data = data;
        this.next = null;
    }

}
