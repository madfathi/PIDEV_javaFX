package tn.esprit.guiapplicatio.models;

public class Program {
    private int id_p;
    private String titre;
    private  String niveau;
    private  String description;
    private  int prix;
    private Client client;
    private String image;

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
        this.image = image;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Program (String titre , String niveau , String description , int prix, String image) {

        this.titre = titre;
        this.niveau = niveau;
        this.description = description;
        this.prix = prix;
        this.image = image;

    }

    public Program() {

    }

    public Program(int id_p, String titre , String niveau , String description , int prix, String image) {
        this.id_p = id_p;
        this.titre = titre;
        this.niveau = niveau;
        this.description = description;
        this.prix = prix;
        this.image = image;

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
   public String getImage() {return image;}
    public String setImage(String image) {this.image = image;return image;}
    @Override

    public String toString() {
        return "Program{" +
                "titre='" + titre + '\'' +
                ", client=" + client +
                ", niveau='" + niveau + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +'\'' +
                ", image='" + image + '\'' +
                // Assuming client has a toString() method or you want to display its ID or name
                '}';
    }

}
