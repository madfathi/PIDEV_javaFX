package tn.esprit.guiapplicatio.services;

import java.sql.ResultSet;

import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.utils.MyDatabase;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeanceService implements IService<Seance> {

    private Connection connection;
    private Statement statement;

    private PreparedStatement pst;


    public SeanceService() {
        connection = MyDatabase.getInstance().getConnection();


    }

    @Override
    public void ajouter(Seance seance) throws SQLException {

        String req = "INSERT INTO seance (type_seance,duree_seance,nb_maximal,categorie,date_fin) VALUES ('" + seance.getType_seance() + "','" + seance.getDuree_seance() + "','" + seance.getNb_maximal() + "','" + seance.getCategorie() + "','" + seance.getDate_fin() + "')";

        Statement st = connection.createStatement();

        st.executeUpdate(req);


    }
    @Override
    public ObservableList<Seance> sortCategorieDesc()
    {
        ObservableList<Seance> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM seance order by type_seance desc";
            statement = connection.createStatement();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Seance c = new Seance(rs.getInt("ID_seance"),rs.getString("duree_seance"),rs.getInt("nb_maximal"),rs.getString("type_seance"),rs.getString("categorie"),rs.getDate("date_fin"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public List<Seance> getAllSeances() throws SQLException {
        List<Seance> seances = new ArrayList<>();


        ResultSet resultSet = null;

        try {
            Statement st = connection.createStatement();
            // Establish the database connection

            resultSet = statement.executeQuery("SELECT * FROM seances");

            // Iterate over the result set and create Seance objects
            while (resultSet.next()) {
                Seance seance = new Seance();
                seance.setId_seance(resultSet.getInt("id"));
                seance.setType_seance(resultSet.getString("type_seance"));
                seance.setCategorie(resultSet.getString("categorie"));
                seance.setDuree_seance(resultSet.getString("duree_seance"));
                seance.setNb_maximal(resultSet.getInt("nb_maximal"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            throw e;
        } finally {
            // Close resources in the finally block

        }

        return seances;
    }
    @Override
    public ObservableList<Seance> readCategorie()
    {
        String requete = "select * from seance";
        ObservableList<Seance> list = FXCollections.observableArrayList();

        try {
            statement = connection.createStatement();
            ResultSet rs=statement.executeQuery(requete);
            while (rs.next()) {
                Seance c = new Seance(rs.getInt("ID_seance"),rs.getString("duree_seance"),rs.getInt("nb_maximal"),rs.getString("type_seance"),rs.getString("categorie"),rs.getDate("date_fin"));
                list.add(c);  }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public ObservableList<Seance> sortCategorieAsc()
    {
        ObservableList<Seance> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM seance order by type_seance asc";
            statement = connection.createStatement();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Seance c = new Seance(rs.getInt("ID_seance"),rs.getString("duree_seance"),rs.getInt("nb_maximal"),rs.getString("type_seance"),rs.getString("categorie"),rs.getDate("date_fin"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }


    @Override
    public void modifier(Seance seance) throws SQLException {
        String req = "update seance SET type_seance =?,duree_seance=?, nb_maximal=?, categorie=? where id_seance=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, seance.getType_seance());
        ps.setString(2, seance.getDuree_seance());
        ps.setInt(3, seance.getNb_maximal());
        ps.setString(4, seance.getCategorie());
        ps.setInt(5, seance.getId_seance());

        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM seance WHERE id_seance = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }

    @Override
    public boolean idEsists(int id) throws SQLException {
        boolean exists = false;
        ResultSet resultS = null;
        try {
        String query = "SELECT COUNT(*) FROM seance WHERE id_seance = ?";
        pst = connection.prepareStatement(query);
        pst.setInt(1, id);
         resultS = pst.executeQuery();
        if (resultS.next()) {
            int count = resultS.getInt(1);
            exists = count > 0;
        }
    } finally {

    }

    return exists;
    }

    @Override
    public List<Seance> recuperer() throws SQLException {
        List<Seance> seances = new ArrayList<>();
        String query = "SELECT * FROM seance ";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Seance seance = new Seance();
            seance.setId_seance(rs.getInt("ID_Seance"));
            seance.setType_seance(rs.getString("Type_seance"));
            seance.setNb_maximal(rs.getInt("Nb_maximal"));
            seance.setDuree_seance(rs.getString("Duree_seance"));
            seance.setCategorie(rs.getString("categorie"));
            seance.setDate_fin(rs.getDate("date_fin"));
            seances.add(seance);

        }
        return seances;

    }
}