package tn.esprit.applictiongui.model;

public class produit {
    private int quantite,prix,id;
    private String categorie_produit,image,nom;

    @Override
    public String toString() {
        return "produit{" +
                "quantite=" + quantite +
                ", prix=" + prix +
                ", id=" + id +
                ", categorie_produit='" + categorie_produit + '\'' +
                ", image='" + image + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }

    public produit(int quantite, int prix, String categorie_produit, String image, String nom) {
        this.quantite = quantite;
        this.prix = prix;
        this.categorie_produit = categorie_produit;
        this.image = image;
        this.nom = nom;
    }

    public produit(int quantite, int prix, int id, String categorie_produit, String image,String nom) {
        this.quantite = quantite;
        this.prix = prix;
        this.id = id;
        this.categorie_produit = categorie_produit;
        this.image = image;
        this.nom = nom;
    }

    public produit() {
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie_produit() {
        return categorie_produit;
    }

    public void setCategorie_produit(String categorie_produit) {
        this.categorie_produit = categorie_produit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
