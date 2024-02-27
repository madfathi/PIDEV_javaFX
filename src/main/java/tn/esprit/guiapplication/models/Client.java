package tn.esprit.guiapplication.models;

public class Client {
    private int id_c;
    private int age;
    private  int poids;
    private  String nom;
    private  String prenom;

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
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public  int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public  int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public  String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "client{" +
                ", nom='" + nom +
                ", prenom='" + prenom +
                ", age=" + age +
                ", poids=" + poids +

                '}';
    }

}
