public class Dietitian extends HealthProfessional {
    private String nutritionSpecialty;



    public Dietitian(int id, String name, String specialization, String nutritionSpecialty) {
        super(id, name, specialization);
        this.nutritionSpecialty = nutritionSpecialty;
    }

    public void printDetails() {
        super.printDetails();
        System.out.println("Nutrition Specialty: " + nutritionSpecialty);
        System.out.println("Health Professional Type: Dietitian");
    }

    // Getters and setters
    public String getNutritionSpecialty() {
        return nutritionSpecialty;
    }

    public void setNutritionSpecialty(String nutritionSpecialty) {
        this.nutritionSpecialty = nutritionSpecialty;
    }
}