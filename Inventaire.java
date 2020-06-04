import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

/**
 * CopyOfInventaire : contient toutes les informations sur le nombre de voitures
 * disponibles pour la location et sur le nombre de voitures déjà louées.
 *
 * @author Abdoul Wahab Bagayoko
 * @version 20-04-2020
 *
 */
public class Inventaire {

    // Déclaration des constantes
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final String PRODUIT_FICHIER = "InventaireProduits.txt";
    public static final String CATEGORIE_FICHIER = "InventaireCategorie.txt";

    public static int taille() {
        File file = new File("InventaireProduits.txt");
        return (int) file.length();
    }

    // private static Produit [] tableauProduits = new
    // Produit[Produit.nombreProduitCreer];
    private static Categorie[] tableauCategories = new Categorie[Categorie.nombreCategorieCreer];
    private static ArrayList<Produit> listProduits = new ArrayList<Produit>();

  

    public static Produit trouver(String nom , String cat){
        String id = nom + "+"+ cat;
        Produit rp = null;
        for (int i = 0; i < listProduits.size(); i++) {
            Produit p = listProduits.get(i);
            if (id.equalsIgnoreCase(p.getId())) {
                rp = p;
                break;
            }
        }

        return rp;


    }

    /**
     * Lire les données sur le nombre de voitures disponibles pour la location et
     * sur le nombre de voitures déjà louées à partir du fichier
     * InventairePneus.txt. Ces données seront transférées du fichier vers le
     * tableau.
     */
    public static void lire(String nomFichier) {
        // Déclarations des variables
        FileReader fluxConnecteur;
        BufferedReader fluxTampon;
        String uneLigne;
        String[] lesAttributsInventaire;
        int nb = 0;
        try {
            // Création d'un flux connecteur
            // Assurez-vous que ce fichier existe dans le même
            // dossier que votre classe
            fluxConnecteur = new FileReader(nomFichier);

            // Création d'un flux tampon
            fluxTampon = new BufferedReader(fluxConnecteur);
            listProduits.clear(); 

            // lecture ligne par ligne
            while (fluxTampon.ready()) {

                uneLigne = fluxTampon.readLine(); // Lecture d'une ligne
                uneLigne.trim();
                // Découper la ligne en tabeau de chaines de caractères avec | comme séparateur

                lesAttributsInventaire = uneLigne.split("\\|");

                if (nomFichier.equals(PRODUIT_FICHIER)) {
                    // for (int i = 1; i < lesAttributsInventaire.length
                    // && lesAttributsInventaire.length != 1
                    // && lesAttributsInventaire[i].trim() != null
                    // && uneLigne.trim() != null
                    // && uneLigne.trim() != ""; i++) {
                    int i = 1;
                    String nom = lesAttributsInventaire[i].trim();

                    String categorie = lesAttributsInventaire[++i].trim();

                    int nombreDisponible = Integer.parseInt(lesAttributsInventaire[++i].trim());

                    int nombreVendus = Integer.parseInt(lesAttributsInventaire[++i].trim());

                    Produit prod = new Produit(nom, categorie, nombreDisponible, nombreVendus);

                    listProduits.add(prod);

                    // tableauProduits[nb] = new Produit(nom, categorie, nombreDisponible,
                    // nombreVendus);
                    // System.out.println(tableauProduits[nb].formatter());
                    System.out.println(listProduits.get(nb).formatter());
                    nb++;

                    // }
                } else if (nomFichier.equals(CATEGORIE_FICHIER)) {
                    for (int i = 0; i < lesAttributsInventaire.length; i++) {

                        String nom = lesAttributsInventaire[i++].trim();

                        int nombre = Integer.parseInt(lesAttributsInventaire[i].trim());

                        tableauCategories[nb] = new Categorie(nom, nombre);
                    }
                }

            }
            // Fermeture d'un fichier
            fluxTampon.close();
            fluxConnecteur.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier non trouvé ");
        } catch (IOException e) {
            System.out.println("Erreur d'entrée / sortie");
        } catch (NumberFormatException e) {
            System.out.println("Donnée invalide !");
        }
    }

    /**
     * Écrire les données sur le nombre de voitures disponibles pour la location et
     * sur le nombre de voitures déjà louées dans le fichier InventairePneus.txt.
     * Ces données seront transférées du tableau vers le fichier.
     */
    public static void ecrire(String nomFichier) {
        // Déclarations des variables
        PrintWriter fluxPrintWriter;

        try {
            // Création d'un flux connecteur
            fluxPrintWriter = new PrintWriter(nomFichier);

            // Écriture des lignes dansle fichier.
            if (nomFichier.equals(PRODUIT_FICHIER)) {
                for (int i = 0; i < listProduits.size(); i++) {
                    // Écrit les lignes les unes après les autres.
                    fluxPrintWriter.println(listProduits.get(i).formatter());
                }
            } else if (nomFichier.equals(CATEGORIE_FICHIER)) {
                for (int i = 0; i < tableauCategories.length && tableauCategories[i] != null; i++) {
                    // Écrit les lignes les unes après les autres.
                    fluxPrintWriter.println(tableauCategories[i].formatter());
                }
            }

            // Fermeture du flux d'écriture
            fluxPrintWriter.close();

        } catch (IOException e) {
            System.out.println("Erreur d'entrée / sortie");
        }

    }

    public static void ecrire(String nomFichier, String ligne) {
        // Déclarations des variables

        FileWriter fluxConnecteur;
        BufferedWriter fluxTampon;
        boolean append = true;
        try {
            // Création d'un flux connecteur

            fluxConnecteur = new FileWriter(nomFichier, append);
            fluxTampon = new BufferedWriter(fluxConnecteur);

            // Écriture des lignes dansle fichier.
            if (nomFichier.equals(PRODUIT_FICHIER)) {
                // Écrit les lignes les unes après les autres.
                fluxTampon.write(ligne);
                fluxTampon.newLine();

            } else if (nomFichier.equals(CATEGORIE_FICHIER)) {

                // Écrit les lignes les unes après les autres.
                fluxTampon.write(ligne);
                fluxTampon.newLine();

            }

            // Fermeture du flux d'écriture
            fluxTampon.close();
            fluxConnecteur.close();

        } catch (IOException e) {
            System.out.println("Erreur d'entrée / sortie");
        }

    }

    public static void afficher() {
        String alien = "| %-15s | %-15s | %-6d | %-6d |%n";
        System.out.println("Nom\tCategorie\tDispo\tVendu");
        System.out.format("+-----------------+-----------------+--------+--------+%n");
        System.out.format("| Nom Produit     | Categorie       | Dispo  | Vendu  |%n");
        System.out.format("+-----------------+-----------------+--------+--------+%n");
        for (Produit p : listProduits) {
            System.out.print(p.line(alien));
            
        }
        System.out.format("+-----------------+-----------------+--------+--------+%n");

    }

    /**
     * Augmenter le nombre de voitures louées pour le type et la grandeur passés en
     * paramètres.
     *
     * @param type       le type de la voiture
     * @param grandeur   la grandeur de la voiture
     * @param nbVoitures le nombre de voitures louées
     *
     *                   public static void augmenterNbVoituresLouees(char type,
     *                   char grandeur, int nbVoitures) { int ajouter = nbVoitures;
     * 
     *                   for(int i = 0; i < tableauVoituresInventaires.length; i++){
     *                   if (type == tableauVoituresInventaires[i].getTypeVoiture()
     *                   && grandeur ==
     *                   tableauVoituresInventaires[i].getGrandeurVoiture()){
     *                   ajouter +=
     *                   tableauVoituresInventaires[i].getNombreVoitureLouee();
     * 
     *                   tableauVoituresInventaires[i].setNombreVoitureLouee(ajouter);
     * 
     *                   } } }
     */

    /**
     * Diminuer le nombre de voitures disponibles pour le type et la grandeur passés
     * en paramètres.
     *
     * @param type       le type de la voiture
     * @param grandeur   la grandeur de la voiture
     * @param nbVoitures le nombre de voitures louées
     *
     *                   public static void diminuerNbVoituresDisponibles(char type,
     *                   char grandeur, int nbVoitures) { //int dimunuer = -
     *                   nbVoitures; for(int i = 0; i <
     *                   tableauVoituresInventaires.length &&
     *                   tableauVoituresInventaires[i] != null; i++) { if (type ==
     *                   tableauVoituresInventaires[i].getTypeVoiture() && grandeur
     *                   == tableauVoituresInventaires[i].getGrandeurVoiture()){
     *                   //dimunuer +=
     *                   tableauVoituresInventaires[i].getNombreVoitureDisponible();
     * 
     *                   tableauVoituresInventaires[i].setNombreVoitureDisponible(tableauVoituresInventaires[i].getNombreVoitureDisponible()
     *                   - nbVoitures); }
     * 
     *                   } }
     */

    /**
     * Vérifier si le nombre de voitures disponibles pour le type et la grandeur
     * passés en paramètres est supérieur à 0.
     *
     * @param type     le type de la voiture
     * @param grandeur la grandeur de la voiture
     * @param vrai     si le nombre de voitures disponibles pour le type et la
     *                 grandeur passés est supérieur à 0. Sinon faux
     * 
     *                 public static boolean estDisponible(char type, char grandeur)
     *                 { boolean estDisponible = false; for(int i = 0; i <
     *                 tableauVoituresInventaires.length &&
     *                 tableauVoituresInventaires[i] != null; i++){ if (type ==
     *                 tableauVoituresInventaires[i].getTypeVoiture() && grandeur ==
     *                 tableauVoituresInventaires[i].getGrandeurVoiture() &&
     *                 tableauVoituresInventaires[i].getNombreVoitureDisponible() >
     *                 0){ estDisponible = true; } } return estDisponible; }
     */

    /**
     * Afficher le nombre de voitures louées (même si ce nombre est égal à 0)
     *
     * public static void afficherVoituresLouees() { int nombreVoitureLouee = 0;
     * System.out.println("\t\t "+ApplicationPrincipale.NOM_ENTREPRISE);
     * System.out.println(); System.out.print("Date et Heure
     * :"+ApplicationPrincipale.determinerDateHeureFacturation().format(formatter));
     * System.out.println("\n"+ApplicationPrincipale.ADRESSE_ENTREPRISE+"\t\t\t"+ApplicationPrincipale.NUMERO_TEL_ENTREPRISE);
     * System.out.println();
     * System.out.println("-------------------------------------------------------------------------------------------");
     * System.out.println("\t\t\t CopyOfInventaire des voitures louées");
     * System.out.println("-------------------------------------------------------------------------------------------");
     * System.out.println(); System.out.println(" Type de voiture Grandeur de
     * voiture Nombres de voiture"); for(int i = 0; i <
     * tableauVoituresInventaires.length && tableauVoituresInventaires[i] != null;
     * i++){ System.out.print(" "+tableauVoituresInventaires[i].typeVoiture()+"\t\t
     * "+ tableauVoituresInventaires[i].getGrandeurVoiture()+ "\t\t\t"
     * +tableauVoituresInventaires[i].getNombreVoitureLouee()+"\n");
     * nombreVoitureLouee += tableauVoituresInventaires[i].getNombreVoitureLouee();
     * } System.out.println("\t ------------------------------------------");
     * System.out.println("\t Le nombre de voiture louées est "+nombreVoitureLouee);
     * 
     * }
     */

    /**
     * Afficher le nombre de voitures disponibles (même si ce nombre est égal à 0)
     *
     * 
     * public static void afficherVoituresDisponibles() { int
     * nombreVoitureDisponible = 0; System.out.println("\t\t
     * "+ApplicationPrincipale.NOM_ENTREPRISE); System.out.println();
     * System.out.print("Date et Heure
     * :"+ApplicationPrincipale.determinerDateHeureFacturation().format(formatter));
     * System.out.println("\n"+ApplicationPrincipale.ADRESSE_ENTREPRISE+"\t\t\t"+ApplicationPrincipale.NUMERO_TEL_ENTREPRISE);
     * System.out.println();
     * System.out.println("-------------------------------------------------------------------------------------------");
     * System.out.println("\t\t\t CopyOfInventaire des voitures disponibles");
     * System.out.println("-------------------------------------------------------------------------------------------");
     * System.out.println(); System.out.println(" Type de voiture Grandeur de
     * voiture Nombres de voiture"); for(int i = 0; i <
     * tableauVoituresInventaires.length && tableauVoituresInventaires[i] != null;
     * i++){ System.out.print(" "+tableauVoituresInventaires[i].typeVoiture()+"\t\t
     * "+ tableauVoituresInventaires[i].getGrandeurVoiture()+ "\t\t\t"
     * +tableauVoituresInventaires[i].getNombreVoitureDisponible()+"\n");
     * nombreVoitureDisponible +=
     * tableauVoituresInventaires[i].getNombreVoitureDisponible(); }
     * System.out.println("\t ----------------------------------------------");
     * System.out.println("\t Le nombre de voiture Disponibles est
     * "+nombreVoitureDisponible);
     * 
     * }
     */

}
