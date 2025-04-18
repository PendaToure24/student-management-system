public class Etudiant {
    //Attributs
    private String prenom;
    private String nom;
    private String dateNaiss;
    private int id;
    private static int compteur = 0;

    //Constructeur
    public Etudiant(String nom, String prenom, String dateNaiss){
        this.id = ++compteur;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
    }

    //Getters
    public String getPrenom(){
        return prenom;
    }
    public String getNom(){
        return nom;
    }
    public String getDateNaiss(){
        return dateNaiss;
    }
    public int getId(){
        return id;
    }

    //Setters
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setDateNaiss(String dateNaiss){
        this.dateNaiss = dateNaiss;
    }
    public void setId (int id){
        this.id = id;
    }

    public String toString(){
        return id + " " +prenom +" "+ nom+" "+dateNaiss;
    }
}
