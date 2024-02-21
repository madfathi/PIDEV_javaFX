//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tn.esprit.guiapplicatio.models;

public class Client {
    private int id_c;
    private int age;
    private int poids;
    private String nom;
    private String prenom;

    public Client(int age, int poids, String nom, String prenom) {
        this.age = age;
        this.poids = poids;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Client() {
    }

    public Client(int id_c, int age, int poids, String nom, String prenom) {
        this.id_c = id_c;
        this.age = age;
        this.poids = poids;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId_c() {
        return this.id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoids() {
        return this.poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String toString() {
        return "client{id_c=" + this.id_c + ", age=" + this.age + ", poids=" + this.poids + ", nom='" + this.nom + "', prenom='" + this.prenom + "'}";
    }
}
