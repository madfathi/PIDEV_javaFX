package tn.esprit.guiapplication.services;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements IService<Client>{
  private Connection connection;


  public ClientService(){

      connection = MyDatabase.getInstance().getConnection();
  }

    public void ajouter(Client client) throws SQLException {
    String req =" INSERT INTO client (nom,prenom,age,poids) VALUES ('"+client.getNom()+"' , '"+client.getPrenom()+"','"+client.getAge()+"',"+client.getPoids()+")";
      Statement st =connection.createStatement();
      st.executeUpdate(req);
    }

    @Override
    public void modifier(Client client) throws SQLException {
      String req = "UPDATE client SET nom = ?, prenom = ? , age = ? , poids = ? WHERE id_c = ? " ;
      PreparedStatement cs = connection.prepareStatement(req);
      cs.setString(1, client.getNom());
      cs.setString(2, client.getPrenom());
      cs.setInt(3, client.getAge());
      cs.setInt(4, client.getPoids());
      cs.setInt(5, client.getId_c());
      cs.executeUpdate();

    }

    @Override
    public void supprimer(int id_c) throws SQLException {
     String req = "DELETE FROM client WHERE id_c= ?";
     PreparedStatement cs = connection.prepareStatement(req);
     cs.setInt(1, id_c);
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
        client.setNom(rs.getString("nom"));
        client.setPrenom(rs.getString("prenom"));


        clients.add(client);

      }
      return clients;
    }
}
