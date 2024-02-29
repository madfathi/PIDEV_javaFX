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
        StringBuilder sb = new StringBuilder();
        sb.append("panier:");
        sb.append("\n");
        //sb.append("idp=").append(idp);
        sb.append("\n");
        sb.append("quantite=").append(quantite);
        sb.append("\n");
        sb.append("pt=").append(pt);
        sb.append("\n");
        sb.append("nomp='").append(nomp).append("'");
        sb.append("\n");
        sb.append("prod_id='").append(prod_id).append("'");
        sb.append("\n");
        sb.append("img='").append(img).append("'");
        sb.append("\n");
        sb.append(":");
        return sb.toString();
    }
}