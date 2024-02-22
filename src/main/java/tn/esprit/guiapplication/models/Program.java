package tn.esprit.guiapplication.models;

public class Program {
    private int id_p;
    private String titre;
    private  String niveau;
    private  String description;
    private  int prix;
    private Client client;

    public Client getClient() {

        return client;
    }


    public Program(int id_p, String titre, String niveau, String description, int prix, Client client) {
        this.id_p = id_p;
        this.titre = titre;
        this.niveau = niveau;
        this.description = description;
        this.prix = prix;
        this.client = client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Program (String titre , String niveau , String description , int prix) {

        this.titre = titre;
        this.niveau = niveau;
        this.description = description;
        this.prix = prix;

    }

    public Program() {

    }

    public Program(int id_p, String titre , String niveau , String description , int prix) {
        this.id_p = id_p;
        this.titre = titre;
        this.niveau = niveau;
        this.description = description;
        this.prix = prix;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override

    public String toString() {
        return "Program{" +
                "id_p=" + id_p +
                ", titre='" + titre + '\'' +
                ", niveau='" + niveau + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", client=" + client + // Assuming client has a toString() method or you want to display its ID or name
                '}';
    }

}
