package tn.esprit.applictiongui.model;

public class panier {
    private int idp,quantite,pt;
    private String nomp,prod_id;
    private String img;

    public panier() {
    }

    public panier(int quantite, int pt, String nomp) {
        this.quantite = quantite;
        this.pt = pt;
        this.nomp = nomp;
    }

    public panier(int idp, int quantite, String nomp, String img, int pt, String prod_id) {
        this.idp = idp;
        this.quantite = quantite;
        this.nomp = nomp;
        this.img = img;
        this.pt=pt;
        this.prod_id=prod_id;
    }

    public panier(int quantite, String nomp, String img,int pt,String prod_id) {
        this.quantite = quantite;
        this.nomp = nomp;
        this.img = img;
        this.pt=pt;
        this.prod_id=prod_id;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
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
                ", prod_id='" + prod_id + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}