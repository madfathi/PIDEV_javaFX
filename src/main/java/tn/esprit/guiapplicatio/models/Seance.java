package tn.esprit.guiapplicatio.models;

import java.util.Date;

public class Seance {

    public int id_seance,  nb_maximal;
    public String type_seance,categorie,duree_seance;
    public Date date_fin;

    public Seance(int id_seance, String duree_seance, int nb_maximal, String type_seance, String categorie,Date date_fin) {
        this.id_seance = id_seance;
        this.duree_seance = duree_seance;
        this.nb_maximal = nb_maximal;
        this.type_seance = type_seance;
        this.categorie = categorie;
        this.date_fin = date_fin;

    }

    public Seance(String duree_seance, int nb_maximal, String type_seance, String categorie,Date date_fin) {
        this.duree_seance = duree_seance;
        this.nb_maximal = nb_maximal;
        this.type_seance = type_seance;
        this.categorie = categorie;

        this.date_fin = date_fin;
    }

    public Seance() {


    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public java.sql.Date getDate_fin() {
        return (java.sql.Date) date_fin;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id_seance=" + id_seance +
                ", nb_maximal=" + nb_maximal +
                ", type_seance='" + type_seance + '\'' +
                ", categorie='" + categorie + '\'' +
                ", duree_seance='" + duree_seance + '\'' +
                ", date_fin=" + date_fin +
                '}';
    }

    public int getId_seance() {
        return id_seance;
    }

    public void setId_seance(int id_seance) {
        this.id_seance = id_seance;
    }

    public String getDuree_seance() {
        return duree_seance;
    }

    public void setDuree_seance(String duree_seance) {
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

}
