package tn.esprit.demogui.models;

public class User {
    private int id;
    private String nom,prenom,mdp;
     public User(int id, String nom, String prenom, String mdp){
         this.id=id;
         this.nom = nom;
         this.prenom = prenom;
         this.mdp = mdp;
     }

    public User(String nom, String prenom, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;

    }

    public User() {

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom=" + nom +
                ", prenom=" + prenom +
                ", mdp=" + mdp +
                '}';
    }
}
