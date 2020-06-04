import java.util.*;



public class Principal {

   
    final static char REPONSE_OUI = 'O';
    final static char REPONSE_NON = 'N';
    final static String MENU_PRINCIPALE = "Menu Principale\n"+
                                        "--------------------------\n"+
                                        "1.Produits\n"+
                                        "2.Categories\n"+
                                        "3.Voir l'inventaire\n"+
                                        "4.Quitter\n";
    final static String MENU_CATEGORIE = "Menu Catégorie\n"+
                                        "--------------------------\n"+
                                        "1.Créer nouvelle catégorie\n"+
                                        "2.Modifier\n"+
                                        "3.Supprimer\n"+
                                        "4.Quitter la Catégorie\n"+
                                        "5.Quitter le programme\n";
    final static String MENU_PRODUIT = "Menu Produit\n"+
                                        "--------------------------\n"+
                                        "1.Créer nouveau produit\n"+
                                        "2.Mettre en stock\n"+
                                        "3.Modifier\n"+
                                        "4.Supprimer\n"+
                                        "5.Quitter le Produit\n"+
                                        "6.Quitter le programme\n";

    final static String CHANGE_MENU =   "1.Nom\n"+
                                        "2.Catégorie\n";
    final static String MES_BIENVENNU = "BONJOUR ET BIENVENNU\n";
    final static String MES_REMERCIEMENT = "Merci et à bientôt!!!\n";

    

    /**
     * Méthode main
     *
     * @param args Un paramètre
     */
    public static void main(String[] args) {
        //Booleans
        boolean estValide = false;
        
        //Produit
        String nomProduit;
        String nomCategorie;
        int nombreDisponible;
        int nombreVendus;

        
        //Options
        int optionPrincipale = 0;
        int optionProduit = 0;
        int optionCategorie = 0;
        
        

        //Lire les fichiersd
       Inventaire.lire(Inventaire.PRODUIT_FICHIER);
        
       // Inventaire.lire(Inventaire.CATEGORIE_FICHIER);




        //Afficher le message de bienveue
        afficherMessageBienvennu();

        do {
            afficherMenus(MENU_PRINCIPALE);
            optionPrincipale =  saisirValiderOption(1,4);

            switch (optionPrincipale) {
                case 1:
                
                     boolean estvalide1 = false;
                     do {
                        afficherMenus(MENU_PRODUIT);
                        optionProduit = saisirValiderOption(1, 6);

                        switch (optionProduit) {
                            case 1:
                                //Saisir les infos sur le produits
                                nomProduit = saisirNom(" du produit ");
                                nomCategorie = saisirNom(" de la categorie ");
                                Produit p = Inventaire.trouver(nomProduit, nomCategorie);
                                if(p!=null) {
                                    System.out.println("Produit existe deja");
                                    p.print();

                                    changeSomething();


                                    

                                    nombreDisponible = saisirNombre();
                                    
                                    p.setNombreDisponible(nombreDisponible);
                                    
                                }else {
                                    //Créer le nouveau produit.
                                    nombreDisponible = saisirNombre();
                                    Produit newProduit = new Produit(nomProduit, nomCategorie, nombreDisponible);
                                    // Inventaire.add(newProduit);
                                    Inventaire.ecrire(Inventaire.PRODUIT_FICHIER, newProduit.formatter());
                                    System.out.println(newProduit.formatter());
                                    System.out.println();
                                }


                                
                                Inventaire.lire(Inventaire.PRODUIT_FICHIER);

                                
                                break;
                        
                            case 2:
                                break;

                            case 3:
                                
                            break;
                            case 4:

                                break;
                            
                            case 5:

                                estvalide1 = true;
                                break;
                            case 6:

                                Inventaire.ecrire(Inventaire.CATEGORIE_FICHIER);
                                System.out.println(MES_REMERCIEMENT);
                                estvalide1 = true;
                                estValide = true;
                                break;
                            
                        }

                         
                     } while (!estvalide1);


                    
                    break;
            
                case 2:

                     boolean estvalide2 = false;
                     do {
                        afficherMenus(MENU_CATEGORIE);
                        optionCategorie = saisirValiderOption(1, 5);

                        switch (optionCategorie) {
                            case 1:
                                
                                
                                break;
                        
                            case 2:
                                break;

                            case 3:
                                

                                break;

                            case 4:

                                estvalide2 = true;
                                break;
                            case 5:

                                Inventaire.ecrire(Inventaire.CATEGORIE_FICHIER);
                                System.out.println(MES_REMERCIEMENT);
                            
                                estvalide2 = true;
                                estValide = true;

                                break;
                            
                        }

                         
                     } while (!estvalide2);

                     break;

                case 3:
                    Inventaire.afficher();
                    break;


                case 4:
                     
                     //Inventaire.ecrire(Inventaire.PRODUIT_FICHIER);
                     Inventaire.ecrire(Inventaire.CATEGORIE_FICHIER);
                     System.out.println(MES_REMERCIEMENT);
                     

                     estValide = true;
                    break;
                    
            }

            

        } while (!estValide);
        

        

        
    }
    
     
    public static boolean validerNom (String nom){
        boolean estValide;

        if (nom.length() >= 2 && nom.length() <= 25){
            estValide = true;
        } else {
            estValide = false;
        }

        return estValide; 
    }

    
    public static String saisirNom(String type){

        String nom;
        Scanner scan = new Scanner(System.in);
        boolean estValide = false;

        do{
            System.out.println("Entrez le nom "+type.trim()+"(2 - 25 caractères)");
            // nom = Clavier.lireString();
            nom = scan.nextLine();
            nom = nom.trim();
            
            estValide = validerNom(nom);
            if (!estValide){
                System.out.println("Le nom est invalide!");
            } 

 
        } while(!estValide); 
        // scan.close();
        return nom;
    }

    
    /**
     * Méthode afficherMessageBienvennu
     *
     */
    public static void afficherMessageBienvennu(){
        System.out.println(MES_BIENVENNU);
    }

    
    /**
     * Méthode afficherMenus
     *
     * @param menuString Un paramètre
     */
    public static void afficherMenus(String menuString){
        System.out.println(menuString);
    }

    
    /**
     * Méthode saisirValiderOption
     *
     * @param min Un paramètre
     * @param max Un paramètre
     * @return La valeur de retour
     */
    public static int  saisirValiderOption(int min, int max){
        Scanner scan = new Scanner(System.in);
        int option = 0;
        boolean estValide = false;

        do{
            try {
                System.out.println("Entrez votre choix :");
                option = scan.nextInt();

                //scan.close();
                if(option >= min && option <= max){
                    estValide = true;
                }else{
                    estValide = false;
                }

                

            } catch (NumberFormatException|InputMismatchException e) {
                estValide = false;
            }finally{
                if (!estValide) {
                    System.out.println("Votre choix est invalide!");
                }
                
            }

        }while(!estValide);
        //scan.close();
        return option;
        
        
    }


    public static int  saisirNombre() {
        Scanner scan = new Scanner(System.in);
        int number = 0;
        

       
        try{
            System.out.println("Entrez la quantité du produit");
            number = scan.nextInt();
            

        } catch (NumberFormatException e) {
            System.out.println("Votre nombre est invalide!");
        }finally{
            // scan.close();
        }
        

    
    
    return number;
    }



    public static void changeSomething() {

        char ans = ' ';
        int option;

        System.out.println("Voulez-vous modifier quelque chose?");
        ans = reponseOuiNon();
        if (ans == REPONSE_NON) {
            System.out.println("D'accord merci!");
            
        } else if (ans == REPONSE_OUI) {
            
            System.out.println(CHANGE_MENU);
            option = saisirValiderOption(1, 2);

            if (option == 1) {

                
            } else if (option == 2) {
                
            }


        }

        
    }


    public static char reponseOuiNon(){
        Scanner scan = new Scanner(System.in);
        
        char reponse;
        boolean estValide = false;

        do{
            System.out.println("Que voulez-vous changer?");
            reponse = (scan.nextLine()).charAt(0);
            reponse = Character.toUpperCase(reponse);
            if  (reponse != REPONSE_OUI 
            && reponse != REPONSE_NON){
                System.out.println("La réponse est invalide!");
            } else {
                estValide = true;
            }
        }while(!estValide);
        return reponse;
    }













} 
