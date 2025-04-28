// Employee.java
public class Employee extends Person {
    private String employeeId;
    private String position;

    public Employee() {
        super(); // Calls the default constructor of Person
        this.employeeId = "DefaultID";
        this.position = "Default Position";
    }

    public Employee(String name, int age, String email, String employeeId, String position) {
        super(name, age, email);
        this.employeeId = employeeId;
        this.position = position;
    }

    // Getters and Setters
    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }
}
