package tn.esprit.projet3a.services;

import com.mysql.cj.xdevapi.Client;
import tn.esprit.projet3a.models.Evenment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.projet3a.models.EventElement;
import tn.esprit.projet3a.models.Review;
import tn.esprit.projet3a.utils.MyDatabase;

import static java.sql.DriverManager.getConnection;

public class EvenmentService implements IService<Evenment> {

    private static Connection connection;

    public EvenmentService() {
        connection = MyDatabase.getInstance().getConnection();
    }


   public List<Evenment> selectid() throws SQLException {
       List<Evenment> evenments = new ArrayList<>();
       String req = "SELECT id_event FROM evenment";
       Statement st = connection.createStatement();
       ResultSet rs = st.executeQuery(req);

       while (rs.next()) {
           Evenment evenment = new Evenment();
           evenment.setId_event(rs.getInt("id_event"));
           evenments.add(evenment);
       }
       return evenments;
   }
    @Override
    public void ajouter(Evenment evenment) throws SQLException {
        String req = "INSERT INTO evenment (nom_event, date_event, lieu_event, nom_star, image) VALUES ('" +
                evenment.getNom_event() + "', '" +
                evenment.getDate_event() + "', '" +
                evenment.getLieu_event() + "', '" +
                evenment.getNom_star() + "', '" + // Corrected concatenation
                evenment.getImage() + "')";       // Corrected concatenation
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Integer id_event, Evenment evenment) throws SQLException {
        String req = "UPDATE evenment SET nom_event = ?, date_event = ?, lieu_event = ?, nom_star = ? WHERE id_event = ?";
        try (PreparedStatement es = connection.prepareStatement(req)) {
            es.setString(1, evenment.getNom_event());
            es.setDate(2, new java.sql.Date(evenment.getDate_event().getTime()));
            es.setString(3, evenment.getLieu_event());
            es.setString(4, evenment.getNom_star());
           es.setInt (5, evenment.getId_event());
            int rowsAffected = es.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No rows updated for id_event: " + id_event);
            } else {
                System.out.println("Successfully updated id_event: " +id_event);
            }
            // Don't call rollback in auto-commit mode
        } catch (SQLException e) {
            // Handle the exception gracefully
            System.err.println("Error updating record with id_event: " + id_event);
            e.printStackTrace();
            throw e; // Rethrow the exception
        }
    }




    @Override
    public void modifierReview(Integer id_review, Review review) throws SQLException {

    }

    @Override
    public void supprimerReview(int id_review) throws SQLException {

    }


    @Override
    public void supprimer(int id_event) throws SQLException {
        String req = "DELETE FROM evenment WHERE id_event = ?";
        PreparedStatement es = connection.prepareStatement(req);
        es.setInt(1, id_event);
        es.executeUpdate();

    }

    @Override
    public List<Evenment> recuperer() throws SQLException {
        List<Evenment> evenments = new ArrayList<>();
        String req = "SELECT * FROM evenment ORDER BY date_event";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            Evenment evenment = new Evenment();
            evenment.setId_event(rs.getInt("id_event"));
            evenment.setNom_event(rs.getString("nom_event"));
            evenment.setDate_event(rs.getDate("date_event"));
            evenment.setLieu_event(rs.getString("lieu_event"));
            evenment.setNom_star(rs.getString("nom_star"));
            evenment.setImage(rs.getString("image"));
            evenments.add(evenment);
        }
        return evenments;
    }

    public List<Evenment> tri() throws SQLException {
        List<Evenment> evenments = new ArrayList<>();
        String req = "SELECT * FROM evenment ORDER BY nom_event";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);


        while (rs.next()) {
            Evenment evenment = new Evenment();
            evenment.setId_event(rs.getInt("id_event"));
            evenment.setNom_event(rs.getString("nom_event"));
            evenment.setDate_event(rs.getDate("date_event"));
            evenment.setLieu_event(rs.getString("lieu_event"));
            evenment.setNom_star(rs.getString("nom_star"));
            evenments.add(evenment);
        }
        return evenments;
    }

    public List<EventElement> rechercheEvenment(String nom_event) throws SQLException {
        List<EventElement> elements = new ArrayList<>();
        String req = "SELECT e.id_event, e.nom_event, e.date_event, e.lieu_event, e.nom_star, e.image, r.id_review, r.nbr_star, r.description " +
                "FROM evenment e LEFT JOIN review r ON e.id_event = r.id_event WHERE e.nom_event LIKE ?";
        PreparedStatement es = connection.prepareStatement(req);
        es.setString(1, "%" + nom_event + "%"); // Use LIKE operator for partial matching
        ResultSet rs = es.executeQuery();

        // Iterate over the ResultSet
        while (rs.next()) {
            Evenment e = new Evenment();
            Review r = new Review();

            // Set event details
            e.setId_event(rs.getInt("id_event"));
            e.setNom_event(rs.getString("nom_event"));
            e.setDate_event(rs.getDate("date_event"));
            e.setLieu_event(rs.getString("lieu_event"));
            e.setNom_star(rs.getString("nom_star"));
            e.setImage(rs.getString("image"));

            // Set review details
            r.setId_review(rs.getInt("id_review"));
            r.setNbr_star(rs.getInt("nbr_star"));
            r.setDescription(rs.getString("description"));

            elements.add(e);
            elements.add(r);
        }

        return elements;
    }




    public static List<EventElement> afficheravis() throws SQLException {
        List<EventElement> elements = new ArrayList<>();
        String req = "SELECT e.nom_event, r.id_review, r.nbr_star, r.description " +
                "FROM evenment e INNER JOIN review r ON e.id_event = r.id_event";
        PreparedStatement es = connection.prepareStatement(req);
        ResultSet rs = es.executeQuery();

        while (rs.next()) {
            Evenment e = new Evenment();
            Review r = new Review();

            e.setNom_event(rs.getString("nom_event"));
            r.setId_review(rs.getInt("id_review"));
            r.setNbr_star(rs.getInt("nbr_star"));
            r.setDescription(rs.getString("description"));

            elements.add(e);
            elements.add(r);
        }

        return elements;
    }





}
