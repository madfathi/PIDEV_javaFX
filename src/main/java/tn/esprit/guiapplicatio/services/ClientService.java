package tn.esprit.guiapplicatio.services;
import tn.esprit.guiapplicatio.models.Client;
import tn.esprit.guiapplicatio.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientService implements IServicee<Client>{
  private static Connection connection;


  public ClientService(){

      connection = MyDatabase.getInstance().getConnection();
  }
    public Client getClientByID(String name) {
        Client client = null;
        String req = "SELECT * FROM client WHERE nom = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client = new Client();

                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setAge(rs.getInt("age"));
                client.setPoids(rs.getInt("poids"));
                client.setHauteur(rs.getInt("hauteur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }



  public void ajouter(Client client) throws SQLException {
    String req =" INSERT INTO client (nom,prenom,age,poids,hauteur) VALUES ('"+client.getNom()+"' , '"+client.getPrenom()+"','"+client.getAge()+"','"+client.getPoids()+"','"+client.getHauteur()+"')";
      Statement st =connection.createStatement();
      st.executeUpdate(req);
    }


    @Override
    public void modifier(Client client) throws SQLException {
        String req = "UPDATE client SET nom = ?, prenom = ? , age = ? , poids = ? , hauteur = ? WHERE id_c = ? "  ;

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(req)) {
                ps.setString(1, client.getNom());
                ps.setString(2, client.getPrenom());
                ps.setInt(3, client.getAge());
                ps.setInt(4, client.getPoids());
                ps.setInt(5, client.getHauteur());
                ps.setInt(6, client.getId_c());
                int rowsAffected = ps.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
            }


            connection.commit();
        } catch (SQLException e) {

            e.printStackTrace();
            connection.rollback();
            throw e;
        } finally {

            connection.setAutoCommit(true);
        }

    }





    @Override
    public  List<Client> getAll() {
        List<Client> L = new ArrayList<>();
        String req = "SELECT * FROM client";
        try {
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                Client c = new Client();
                c.setId_c(res.getInt("id_c"));
                c.setNom(res.getString("nom"));
                c.setPrenom(res.getString("prenom"));
                c.setAge(res.getInt("age"));
                c.setPoids(res.getInt("poids"));
                c.setHauteur(res.getInt("hauteur"));
                L.add(c);
                System.out.println(c);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while listing the clients: " + e.getMessage());
        }
        return L;
    }


    @Override
    public  void supprimer( String nom) throws SQLException {
     String req = "DELETE FROM client WHERE nom = ?";
     PreparedStatement cs = connection.prepareStatement(req);
     cs.setString(1, nom);
    cs.executeUpdate();
    }

    @Override
    public List<Client> recuperer() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String req ="SELECT * FROM client";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

      while (rs.next()){
        Client client = new Client();
        client.setId_c(rs.getInt("id_c"));
        client.setAge(rs.getInt("age"));
        client.setPoids(rs.getInt("poids"));
        client.setHauteur(rs.getInt("hauteur"));
        client.setNom(rs.getString("nom"));
        client.setPrenom(rs.getString("prenom"));


        clients.add(client);

      }
      return clients;
    }
    public static void sortByClient(List<Client> clients) {
        clients.sort(Comparator.comparing(Client::getNom));
        System.out.println("Sorted Clients:");
        for (Client client : clients) {
            System.out.println(client);
        }
    }
    public Client getClientByName(String clientName) throws SQLException {
        String req = "SELECT * FROM client WHERE nom = ?";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, clientName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client();
                    client.setId_c(rs.getInt("id_c"));
                    client.setNom(rs.getString("nom"));

                    return client;
                }
            }
        }


        return null;
    }

}
