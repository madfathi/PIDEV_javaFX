package tn.esprit.guiapplication.models;


public class Produit {
    private int id;
    private String nom;
    private String categorie_produit;
    private int quantite;
    private int prix;
    private String image;

    public Produit() {

    }

    public Produit(int id, String nom, String categorie_produit, int quantite, int prix, String image) {
        this.id = id;
        this.nom = nom;
        this.categorie_produit = categorie_produit;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
    }

    public Produit(String nom, String categorie_produit, int quantite, int prix, String image) {
        this.nom = nom;
        this.categorie_produit = categorie_produit;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie_produit() {
        return categorie_produit;
    }

    public void setCategorie_produit(String categorie_produit) {
        this.categorie_produit = categorie_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", categorie_produit='" + categorie_produit + '\'' +
                ", quantite='" + quantite + '\'' +
                ", prix='" + prix + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}