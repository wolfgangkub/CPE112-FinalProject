package datastruct;

public class Node {
    private int id;
    private String name;
    private Node history;
    private Node next;

    public Node(int id) {
        this.id = 0;
        this.next = null;
        this.name = null;
        this.history = null;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getHistory() {
        return this.history;
    }

    public void setHistory(Node history) {
        this.history = history;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
