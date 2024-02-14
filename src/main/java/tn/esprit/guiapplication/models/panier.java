package tn.esprit.guiapplication.models;

public class panier {
    private int idp,quantite;
    private String nomp;
    private String img;

    public panier() {
    }

    public panier(int idp, int quantite, String nomp, String img) {
        this.idp = idp;
        this.quantite = quantite;
        this.nomp = nomp;
        this.img = img;
    }

    public panier(int quantite, String nomp, String img) {
        this.quantite = quantite;
        this.nomp = nomp;
        this.img = img;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "panier{" +
                "idp=" + idp +
                ", quantite=" + quantite +
                ", nomp='" + nomp + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
