public class Produit {

    public static int nombreProduitCreer = 0;
    private int nombreDisponible;
    private int nombreVendus;
    private String categorie;
    private String nom;
    private String id;

    public Produit(String nom, String categorie, int nombreDisponible, int nombreVendus){
        setNom(nom);
        setCategorie(categorie);
        setNombreDisponible(nombreDisponible);
        setNombreVendus(nombreVendus);
        id = nom+ "+" + categorie;
        nombreProduitCreer++;
    }

    public Produit(String nom, String categorie, int nombreDisponible) {
        setNom(nom);
        setCategorie(categorie);
        setNombreDisponible(nombreDisponible);
        setNombreVendus(0);
        id = nom+ "+" + categorie;
        nombreProduitCreer++;
    }

    public void setNom(String nom) {
        this.nom = nom;
        
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }    

    public void setNombreDisponible(int nombreDisponible) {
        this.nombreDisponible = nombreDisponible;
    }

    public void setNombreVendus(int nombreVendus) {
        this.nombreVendus = nombreVendus;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getNombreDisponible() {
        return nombreDisponible;
    }

    public String getId(){
        return nom+ "+" + categorie;
    }

    public int getNombreVendus() {
        return nombreVendus;
    }

    public String line(String format){
        // "%s\t%s\t%d\t%d",
        return String.format(format, nom , categorie, nombreDisponible, nombreVendus);
    }
    public void print() {
        String alien = "| %-15s | %-15s | %-6d | %-6d |%n";

        System.out.format("+-----------------+-----------------+--------+--------+%n");
        System.out.format("| Nom Produit     | Categorie       | Dispo  | Vendu  |%n");
        System.out.format("+-----------------+-----------------+--------+--------+%n");
        
            System.out.print(line(alien));
            
        
        System.out.format("+-----------------+-----------------+--------+--------+%n");

    }
    public String formatter() {

        return id + "|" + nom+"|"+categorie+"|"+nombreDisponible+"|"+nombreVendus;
        
    }

}