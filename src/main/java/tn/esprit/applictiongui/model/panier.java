package tn.esprit.applictiongui.model;

public class panier {
    private int idp,quantite,pt;
    private String nomp;
    private String img;

    public panier() {
    }

    public panier(int idp, int quantite, String nomp, String img,int pt) {
        this.idp = idp;
        this.quantite = quantite;
        this.nomp = nomp;
        this.img = img;
        this.pt=pt;
    }

    public panier(int quantite, String nomp, String img,int pt) {
        this.quantite = quantite;
        this.nomp = nomp;
        this.img = img;
        this.pt=pt;
    }

    public int getPt() {
        return pt;
    }

    public void setPt(int pt) {
        this.pt = pt;
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
                ", pt=" + pt +
                ", nomp='" + nomp + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}