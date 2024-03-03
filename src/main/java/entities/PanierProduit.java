package entities;

public class PanierProduit {

    int idPanierProduit;
    Produit produit;
    panier panier;

    public PanierProduit()
    {

    }
    public PanierProduit(int idPanierProduit,panier panier,Produit produit)
    {
        this.idPanierProduit=idPanierProduit;
        this.produit=produit;
        this.panier=panier;
    }

    public Produit getProduit() {
        return produit;
    }

    public panier getPanier() {
        return panier;
    }

    public int getIdPanierProduit() {
        return idPanierProduit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setPanier(panier panier) {
        this.panier = panier;
    }

    public void setIdPanierProduit(int idPanierProduit) {
        this.idPanierProduit = idPanierProduit;
    }
}
