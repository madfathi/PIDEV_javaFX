package tn.esprit.projet3a.models;

public class Evenment {
    private int id_event;
    private String nom_event,date_event,lieu_event,nom_star;

    public Evenment(int id_event, String nom_event, String date_event, String lieu_event, String nom_star) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.date_event = date_event;
        this.lieu_event = lieu_event;
        this.nom_star = nom_star;
    }

    public Evenment(String nom_event, String date_event, String lieu_event, String nom_star) {
        this.nom_event = nom_event;
        this.date_event = date_event;
        this.lieu_event = lieu_event;
        this.nom_star = nom_star;
    }

    public Evenment() {

    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public String getNom_star() {
        return nom_star;
    }

    public void setNom_star(String nom_star) {
        this.nom_star = nom_star;
    }

    @Override
    public String toString(){
        return "Evenment{" +
                "id_event=" + id_event +
                ", nom_event='" + nom_event + '\'' +
                ", date_event='" + date_event + '\'' +
                ", lieu_event='" + lieu_event + '\'' +
                ", nom_star='" + nom_star + '\'' +
                '}';
    }

}
