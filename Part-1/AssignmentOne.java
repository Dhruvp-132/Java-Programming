import java.util.ArrayList;
public class AssignmentOne {
    public static void main(String[] args) {
        // Part 3 – Using classes and objects
        System.out.println("// Part 3 – Using classes and objects");
        GeneralPractitioner gp1 = new GeneralPractitioner(1, "Dr. Dhruv", "General Practice", "123 Main St");
        GeneralPractitioner gp2 = new GeneralPractitioner(2, "Dr. Patel", "General Practice", "456 Elm St");
        GeneralPractitioner gp3 = new GeneralPractitioner(3, "Dr. Vivek", "General Practice", "789 Oak St");
        Dietitian d1 = new Dietitian(4, "Dr. Rocky", "Nutrition", "Sports Nutrition");
        Dietitian d2 = new Dietitian(5, "Dr. Rakesh", "Nutrition", "Pediatric Nutrition");

        // Doctor's information from a file
        gp1.printDetails();
        gp2.printDetails();
        gp3.printDetails();
        d1.printDetails();
        d2.printDetails();

        System.out.println("------------------------------");

// Part 4 – Class Appointment


        ArrayList<Appointment> appointments = new ArrayList<>();

        createAppointment(appointments, "Rony", "1234567890", "10:00", gp1);
        createAppointment(appointments, "Roy", "0987654321", "14:30", gp2);
        createAppointment(appointments, "Jack", "5551234567", "08:00", d1);
        createAppointment(appointments, "Jackma", "5559876543", "16:00", d2);

        printExistingAppointments(appointments);

        cancelBooking(appointments, "5551234567");

        printExistingAppointments(appointments);
    }
// Part 5 – Collection of appointments
    public static void createAppointment(ArrayList<Appointment> appointments, String patientName, String mobilePhone, String timeSlot, HealthProfessional doctor) {
        Appointment appointment = new Appointment(patientName, mobilePhone, timeSlot, doctor);
        appointments.add(appointment);

    }

    public static void printExistingAppointments(ArrayList<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("No existing appointments.");
        }
        else
        {
            for (Appointment appointment : appointments) {
                appointment.printDetails();
                System.out.println();
            }

        }
    }

    public static void cancelBooking(ArrayList<Appointment> appointments, String mobilePhone) {
        for (Appointment appointment : appointments) {
            if (appointment.getMobilePhone().equals(mobilePhone)) {
                appointments.remove(appointment);
                System.out.println("Appointment cancelled for " + mobilePhone);
                return;
            }
        }
        System.out.println("No appointment found for " + mobilePhone);
    }
}
