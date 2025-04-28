public class GeneralPractitioner extends HealthProfessional {
    private String clinicAddress;



    public GeneralPractitioner(int id, String name, String specialization, String clinicAddress) {
        super(id, name, specialization);
        this.clinicAddress = clinicAddress;
    }

    public void printDetails() {
        super.printDetails();
        System.out.println("Clinic Address: " + clinicAddress);
        System.out.println("Health Professional Type: General Practitioner");
    }

    // Getters and setters
    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }
}