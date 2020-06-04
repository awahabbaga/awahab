public class Categorie {
    
    private String nom;
    private int nombreProduits;
    public static int nombreCategorieCreer = 0;
    
    
    /**
     * Categorie Constructeur
     *
     * @param nom Un paramètre
     * @param nombre Un paramètre
     */
    public Categorie(String nom, int nombreProduits){
        setNom(nom);
        setNombreProduits(nombreProduits);
        nombreCategorieCreer++;

    }

    /**
     * Méthode setNom
     *
     * @param nom Un paramètre
     */
    public void setNom(String nom) {
        this.nom = nom;
        
    }

    /**
     * Méthode setNombreProduits
     *
     * @param nombreProduits Un paramètre
     */
    public void setNombreProduits(int nombreProduits) {
        this.nombreProduits = nombreProduits;
        
    }

    /**
     * Méthode getNom
     *
     * @return La valeur de retour
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode getNombreProduits
     *
     * @return La valeur de retour
     */
    public int getNombreProduits() {
        return nombreProduits;
        
    }

    public String formatter() {
        return nom+"|"+nombreProduits;
        
    }

    
}