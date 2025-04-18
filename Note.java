public class Note {
    //Attributs
    private Etudiant etudiant;
    private Matiere matiere;
    private double valeur;

    //Constructeur
    public Note (Etudiant etudiant, Matiere matiere, double valeur){
        this.etudiant = etudiant;
        this.matiere = matiere;
        this.valeur = valeur;
    }

    //Getters
    public Etudiant getEtudiant() {
        return etudiant;
    }
    public Matiere getMatiere(){
        return matiere;
    }
    public double getValeur(){
        return valeur;
    }

    //Setters
    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
    }
    public void setMatiere(Matiere matiere){
        this.matiere = matiere;
    }
    public void setValeur(double valeur){
        this.valeur = valeur;
    }

    //Calculer les points(note x coefficient)
    public double calculerPoints(){
        return valeur * matiere.getCoefficient();
    }

    public String toString(){
        return "Etudiant: "+etudiant.getPrenom()+" "+etudiant.getNom()+", Matiere: "+matiere.getNom()+", Note: "+valeur;
    }
}
