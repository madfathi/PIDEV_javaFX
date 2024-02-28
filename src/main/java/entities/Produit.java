package entities;

public class Produit {
    private int idProduit;
    private String nomProduit;
    private int prix;
    private int quantite;
    private Categorie categorie;
    private String imageProduit;
    private Offre offre;

    public Produit(int  idProduit, String nomProduit, int quantite, int prix, Categorie categorie, String imageProduit,Offre offre) {
        this. idProduit =  idProduit;
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.imageProduit = imageProduit;
        this.offre=offre;
    }

    public Produit(String nomProduit, int quantite, int prix, Categorie categorie, String imageProduit,Offre offre) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.imageProduit = imageProduit;
        this.offre=offre;
    }
public Produit(String nomProduit,int prix)
{
    this.nomProduit=nomProduit;
    this.prix=prix;
}

    public Produit() {
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int  idProduit) {
        this.idProduit =  idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit= nomProduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    @Override
    public String toString() {
        return "Produit{" + "nom produit=" + nomProduit + ", prix=" + prix + ", quantite=" + quantite + ", categorie=" + categorie + ", image produit=" + imageProduit + '}';
    }

}


