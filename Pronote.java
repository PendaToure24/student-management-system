import java.util.List;
import java.util.Scanner;

public class Pronote {
    private static Scanner sc = new Scanner(System.in);
    private static GestionnaireEtudiants gestionnaire = new GestionnaireEtudiants();
    
    public static void main(String[] args) {
        // Ajouter quelques données de test
        initialiserDonneesTest();
        
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = lireChoix();
            
            switch (choix) {
                case 1:
                    ajouterEtudiant();
                    break;
                case 2:
                    afficherTousLesEtudiants();
                    break;
                case 3:
                    rechercherEtudiant();
                    break;
                case 4:
                    ajouterMatiere();
                    break;
                case 5:
                    afficherToutesLesMatieres();
                    break;
                case 6:
                    ajouterNote();
                    break;
                case 7:
                    afficherBulletin();
                    break;
                case 0:
                    continuer = false;
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        
        sc.close();
    }
    
    private static void afficherMenu() {
        System.out.println("\n===== SYSTÈME DE GESTION D'ÉTUDIANTS =====");
        System.out.println("1. Ajouter un étudiant");
        System.out.println("2. Afficher tous les étudiants");
        System.out.println("3. Rechercher un étudiant");
        System.out.println("4. Ajouter une matière");
        System.out.println("5. Afficher toutes les matières");
        System.out.println("6. Ajouter une note");
        System.out.println("7. Afficher le bulletin d'un étudiant");
        System.out.println("0. Quitter");
        System.out.print("Votre choix: ");
    }
    
    private static int lireChoix() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void ajouterEtudiant() {
        System.out.println("\n----- Ajouter un étudiant -----");
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Prénom: ");
        String prenom = sc.nextLine();
        System.out.print("Date de naissance (JJ/MM/AAAA): ");
        String dateNaissance = sc.nextLine();
        
        Etudiant etudiant = new Etudiant(nom, prenom, dateNaissance);
        gestionnaire.ajouterEtudiant(etudiant);
    }
    
    private static void afficherTousLesEtudiants() {
        System.out.println("\n----- Liste des étudiants -----");
        gestionnaire.afficherTousLesEtudiants();
    }
    
    private static void rechercherEtudiant() {
        System.out.println("\n----- Rechercher un étudiant -----");
        System.out.println("1. Rechercher par ID");
        System.out.println("2. Rechercher par nom");
        System.out.print("Votre choix: ");
        int choix = lireChoix();
        
        switch (choix) {
            case 1:
                System.out.print("ID: ");
                try {
                    int id = Integer.parseInt(sc.nextLine());
                    Etudiant etudiant = gestionnaire.rechercherEtudiantParId(id);
                    if (etudiant != null) {
                        System.out.println("Étudiant trouvé: " + etudiant);
                    } else {
                        System.out.println("Aucun étudiant trouvé avec cet ID.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID invalide.");
                }
                break;
            case 2:
                System.out.print("Nom: ");
                String nom = sc.nextLine();
                List<Etudiant> resultats = gestionnaire.rechercherEtudiantParNom(nom);
                if (!resultats.isEmpty()) {
                    System.out.println("Étudiants trouvés:");
                    for (Etudiant e : resultats) {
                        System.out.println(e);
                    }
                } else {
                    System.out.println("Aucun étudiant trouvé avec ce nom.");
                }
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }
    
    private static void ajouterMatiere() {
        System.out.println("\n----- Ajouter une matière -----");
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Coefficient: ");
        double coefficient = 1.0;
        try {
            coefficient = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Coefficient invalide, utilisation de la valeur par défaut (1.0).");
        }
        
        Matiere matiere = new Matiere(nom, coefficient);
        gestionnaire.ajouterMatiere(matiere);
    }
    
    private static void afficherToutesLesMatieres() {
        System.out.println("\n----- Liste des matières -----");
        gestionnaire.afficherToutesLesMatieres();
    }
    
    private static void ajouterNote() {
        System.out.println("\n----- Ajouter une note -----");
        System.out.print("ID de l'étudiant: ");
        try {
            int idEtudiant = Integer.parseInt(sc.nextLine());
            Etudiant etudiant = gestionnaire.rechercherEtudiantParId(idEtudiant);
            if (etudiant == null) {
                System.out.println("Étudiant non trouvé.");
                return;
            }
            
            // Afficher les matières disponibles pour aider l'utilisateur
            System.out.println("Matières disponibles:");
            gestionnaire.afficherToutesLesMatieres();
            
            System.out.print("Nom de la matière: ");
            String nomMatiere = sc.nextLine();
            Matiere matiere = gestionnaire.rechercherMatiereParNom(nomMatiere);
            if (matiere == null) {
                System.out.println("Matière non trouvée.");
                return;
            }
            
            System.out.print("Note (sur 20): ");
            double valeur = 0;
            try {
                valeur = Double.parseDouble(sc.nextLine());
                if (valeur < 0 || valeur > 20) {
                    System.out.println("La note doit être comprise entre 0 et 20.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Note invalide.");
                return;
            }
            
            Note note = new Note(etudiant, matiere, valeur);
            gestionnaire.ajouterNote(note);
        } catch (NumberFormatException e) {
            System.out.println("ID invalide.");
        }
    }
    
    private static void afficherBulletin() {
        System.out.println("\n----- Afficher le bulletin d'un étudiant -----");
        System.out.print("ID de l'étudiant: ");
        try {
            int idEtudiant = Integer.parseInt(sc.nextLine());
            gestionnaire.afficherNotesEtudiant(idEtudiant);
        } catch (NumberFormatException e) {
            System.out.println("ID invalide.");
        }
    }
    
    private static void initialiserDonneesTest() {
        // Ajout de quelques étudiants
        Etudiant e1 = new Etudiant("Touré", "Penda", "24/12/2004");
        Etudiant e2 = new Etudiant("Fall", "Mame Saye", "07/09/2008");
        Etudiant e3 = new Etudiant("Dioum", "Seydina Ababacar Counta", "18/12/1848");
        Etudiant e4 = new Etudiant("Mbappé", "Kylian", "20/12/1998");
        gestionnaire.ajouterEtudiant(e1);
        gestionnaire.ajouterEtudiant(e2);
        gestionnaire.ajouterEtudiant(e3);
        
        //Ajout de matières
        Matiere m1 = new Matiere("Mathematiques", 3.0);
        Matiere m2 = new Matiere("Programmation orientee objet", 4.0);
        Matiere m3 = new Matiere("Systeme d'exploitation", 3.0);
        gestionnaire.ajouterMatiere(m1);
        gestionnaire.ajouterMatiere(m2);
        gestionnaire.ajouterMatiere(m3);
        
        // Ajout de quelques notes
        gestionnaire.ajouterNote(new Note(e1, m1, 15.5));
        gestionnaire.ajouterNote(new Note(e1, m2, 16));
        gestionnaire.ajouterNote(new Note(e1, m3, 8));

        gestionnaire.ajouterNote(new Note(e2, m1, 16.0));
        gestionnaire.ajouterNote(new Note(e2, m2, 13.5));
        gestionnaire.ajouterNote(new Note(e2, m3, 10));

        gestionnaire.ajouterNote(new Note(e3, m1, 15.0));
        gestionnaire.ajouterNote(new Note(e3, m2, 14));
        gestionnaire.ajouterNote(new Note(e3, m3, 12));

    }
}