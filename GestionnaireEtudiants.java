// Fichier: GestionnaireEtudiants.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionnaireEtudiants {
    private List<Etudiant> etudiants;
    private List<Matiere> matieres;
    private Map<Integer, List<Note>> notes; // Map d'ID d'étudiant à ses notes
    
    public GestionnaireEtudiants() {
        this.etudiants = new ArrayList<>();
        this.matieres = new ArrayList<>();
        this.notes = new HashMap<>();
    }
    
    // Méthodes pour gérer les étudiants
    public void ajouterEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
        notes.put(etudiant.getId(), new ArrayList<>());
        System.out.println("Étudiant ajouté avec succès!");
    }
    
    public Etudiant rechercherEtudiantParId(int id) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getId() == id) {
                return etudiant;
            }
        }
        return null;
    }
    
    public List<Etudiant> rechercherEtudiantParNom(String nom) {
        List<Etudiant> resultats = new ArrayList<>();
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getNom().toLowerCase().contains(nom.toLowerCase())) {
                resultats.add(etudiant);
            }
        }
        return resultats;
    }
    
    public void afficherTousLesEtudiants() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant enregistré.");
            return;
        }
        
        System.out.println("Liste des étudiants:");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }
    }
    
    // Méthodes pour gérer les matières
    public void ajouterMatiere(Matiere matiere) {
        matieres.add(matiere);
        System.out.println("Matière ajoutée avec succès!");
    }
    
    public Matiere rechercherMatiereParNom(String nom) {
        for (Matiere matiere : matieres) {
            if (matiere.getNom().equalsIgnoreCase(nom)) {
                return matiere;
            }
        }
        return null;
    }
    
    public void afficherToutesLesMatieres() {
        if (matieres.isEmpty()) {
            System.out.println("Aucune matière enregistrée.");
            return;
        }
        
        System.out.println("Liste des matières:");
        for (Matiere matiere : matieres) {
            System.out.println(matiere);
        }
    }
    
    // Méthodes pour gérer les notes
    public void ajouterNote(Note note) {
        int idEtudiant = note.getEtudiant().getId();
        if (notes.containsKey(idEtudiant)) {
            notes.get(idEtudiant).add(note);
            System.out.println("Note ajoutée avec succès!");
        } else {
            System.out.println("Étudiant non trouvé!");
        }
    }
    
    public void afficherNotesEtudiant(int idEtudiant) {
        Etudiant etudiant = rechercherEtudiantParId(idEtudiant);
        if (etudiant == null) {
            System.out.println("Étudiant non trouvé!");
            return;
        }
        
        List<Note> notesEtudiant = notes.get(idEtudiant);
        if (notesEtudiant.isEmpty()) {
            System.out.println("Aucune note enregistrée pour cet étudiant.");
            return;
        }
        
        System.out.println("Notes de " + etudiant.getPrenom() + " " + etudiant.getNom() + ":");
        double totalPoints = 0;
        double totalCoefficients = 0;
        
        for (Note note : notesEtudiant) {
            Matiere matiere = note.getMatiere();
            System.out.println(matiere.getNom() + ": " + note.getValeur() + 
                               " (coefficient: " + matiere.getCoefficient() + ")");
            totalPoints += note.calculerPoints();
            totalCoefficients += matiere.getCoefficient();
        }
        
        if (totalCoefficients > 0) {
            double moyenne = totalPoints / totalCoefficients;
            System.out.println("Moyenne générale: " + String.format("%.2f", moyenne));
        }
    }
}