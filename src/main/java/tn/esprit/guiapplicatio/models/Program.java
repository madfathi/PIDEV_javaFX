//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tn.esprit.guiapplication.models;

public class Program {
    private int id_p;
    private String titre;
    private String niveau;
    private String description;
    private int prix;

    public Program(String titre, String niveau, String description, int prix) {
        this.titre = titre;
        this.niveau = niveau;
        this.description = description;
        this.prix = prix;
    }

    public Program() {
    }

    public Program(int id_p, String titre, String niveau, String description, int prix) {
        this.id_p = id_p;
        this.titre = titre;
        this.niveau = niveau;
        this.description = description;
        this.prix = prix;
    }

    public int getId_p() {
        return this.id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNiveau() {
        return this.niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return this.prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String toString() {
        return "Program{id_p=" + this.id_p + ", titre='" + this.titre + "', niveau='" + this.niveau + "', description='" + this.description + "', prix=" + this.prix + "}";
    }
}
