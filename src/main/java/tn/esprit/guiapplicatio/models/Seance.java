package tn.esprit.guiapplicatio.models;

public class Seance {

    public int id_seance, duree_seance, nb_maximal;
    public String type_seance,categorie;

    public Seance(int id_seance, int duree_seance, int nb_maximal, String type_seance, String categorie) {
        this.id_seance = id_seance;
        this.duree_seance = duree_seance;
        this.nb_maximal = nb_maximal;
        this.type_seance = type_seance;
        this.categorie = categorie;
    }

    public Seance(int duree_seance, int nb_maximal, String type_seance, String categorie) {
        this.duree_seance = duree_seance;
        this.nb_maximal = nb_maximal;
        this.type_seance = type_seance;
        this.categorie = categorie;
    }

    public Seance() {


    }

    public int getId_seance() {
        return id_seance;
    }

    public void setId_seance(int id_seance) {
        this.id_seance = id_seance;
    }

    public int getDuree_seance() {
        return duree_seance;
    }

    public void setDuree_seance(int duree_seance) {
        this.duree_seance = duree_seance;
    }

    public int getNb_maximal() {
        return nb_maximal;
    }

    public void setNb_maximal(int nb_maximal) {
        this.nb_maximal = nb_maximal;
    }

    public String getType_seance() {
        return type_seance;
    }

    public void setType_seance(String type_seance) {
        this.type_seance = type_seance;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id_seance=" + id_seance +
                ", duree_seance=" + duree_seance +
                ", nb_maximal=" + nb_maximal +
                ", type_seance='" + type_seance + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
