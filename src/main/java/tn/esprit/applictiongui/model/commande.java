package tn.esprit.applictiongui.model;

import java.util.List;

public class commande {
    private int idc,tel;
    private String nom,pre,mail,addr;
    private List<String> pani;

    // Getter
    public List<String> getPani() {
        return pani;
    }

    // Setter
    public void setPani(List<String> nouvelleValeur) {
        this.pani = nouvelleValeur;
    }

    public commande(int idc,int tel, String nom, String pre, String mail, String addr, List<String>  pani) {
        this.idc = idc;
        this.tel = tel;
        this.nom = nom;
        this.pre = pre;
        this.mail = mail;
        this.addr = addr;
        this.pani = pani;
    }

    public commande( String nom, String pre) {

        this.nom = nom;
        this.pre = pre;
    }

    public commande(List<String> pani) {
        this.pani = pani;
    }

    public commande(int tel, String nom, String pre, String mail, String addr, List<String> pani)
    {

        this.tel=tel;
        this.nom=nom;
        this.pre=pre;
        this.mail=mail;
        this.addr=addr;
        this.pani=pani;

    }

    public commande(int idc, int tel, String nom, String pre, String mail, String addr) {
        this.idc = idc;
        this.tel = tel;
        this.nom = nom;
        this.pre = pre;
        this.mail = mail;
        this.addr = addr;
    }

    public commande() {

    }

    /*public String getPani() {
        return pani;
    }*/


    public int getIdc() {
        return idc;
    }

    public int getTel() {
        return tel;
    }

    public String getAddr() {
        return addr;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public String getPre() {
        return pre;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

   /* public void setPani(String pani) {
        this.pani = pani;
    }*/

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("commande :");
        sb.append("\n");
       // sb.append("idc=").append(idc);
        sb.append("\n");
        sb.append("tel=").append(tel);
        sb.append("\n");
        sb.append("nom='").append(nom).append("'");
        sb.append("\n");
        sb.append("pre='").append(pre).append("'");
        sb.append("\n");
        sb.append("mail='").append(mail).append("'");
        sb.append("\n");
        sb.append("add='").append(addr).append("'");
        sb.append("\n");
        sb.append("pani='").append(pani).append("'");
        sb.append("\n");
        sb.append(":");
        return sb.toString();
    }


}
