// Visitor.java
public class Visitor extends Person {
    private String visitorId;
    private String ticketType;

    public Visitor() {
        super(); // Calls the default constructor of Person
        this.visitorId = "DefaultVisitorID";
        this.ticketType = "Gold";
    }

    public Visitor(String name, int age, String email, String visitorId, String ticketType) {
        super(name, age, email);
        this.visitorId = visitorId;
        this.ticketType = ticketType;
    }

    // Getters and Setters
    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
