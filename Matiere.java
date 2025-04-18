public class Matiere {
    private String nom;
    private double coefficient;
    
    public Matiere(String nom, double coefficient) {
        this.nom = nom;
        this.coefficient = coefficient;
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    
    public double getCoefficient() {
        return coefficient;
    }
    
    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
    
    @Override
    public String toString() {
        return "Matière: " + nom + ", Coefficient: " + coefficient;
    }
}
