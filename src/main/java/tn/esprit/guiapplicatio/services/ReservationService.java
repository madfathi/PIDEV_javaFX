package tn.esprit.guiapplicatio.services;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.guiapplicatio.models.Reservation;
import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.utils.MyDatabase;
import tn.esprit.guiapplicatio.models.Seance;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class ReservationService implements IService<Reservation> {


    private Statement statement;
    private PreparedStatement pst;
    private Connection connection;
    public boolean idEsists(int id) throws SQLException {
  /*      boolean exists = false;
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
            // Fermer les ressources
            if (resultS != null) {
                resultS.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
*/
        return false;
    }

    public ReservationService() {
        connection = MyDatabase.getInstance().getConnection();


    }
    @Override
    public ObservableList<Reservation> readCategorie()
    {

      String requete = "select * from reservation";
        ObservableList<Reservation> list = FXCollections.observableArrayList();

        try {
            statement = connection.createStatement();
            ResultSet rs=statement.executeQuery(requete);
            while (rs.next()) {
                Reservation c = new Reservation(rs.getInt("id_reservation"),rs.getString("type_reservation"),rs.getString("username"),rs.getString("email"),rs.getInt("phone"),rs.getInt("id_seance"));
                list.add(c);  }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return list;


    }

    @Override
    public ObservableList<Reservation> sortCategorieDesc()
    {
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        /*try {
            String req = "SELECT * FROM seance order by type_seance desc";
            statement = connection.createStatement();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Seance c = new Seance(rs.getInt("ID_seance"),rs.getInt("duree_seance"),rs.getInt("nb_maximal"),rs.getString("type_seance"),rs.getString("categorie"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
*/
        return list;
    }
    @Override
    public ObservableList<Reservation> sortCategorieAsc()
    {
        ObservableList<Reservation> list = FXCollections.observableArrayList();
       /* try {
            String req = "SELECT * FROM seance order by type_seance asc";
            statement = connection.createStatement();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Seance c = new Seance(rs.getInt("ID_seance"),rs.getInt("duree_seance"),rs.getInt("nb_maximal"),rs.getString("type_seance"),rs.getString("categorie"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
*/
        return list;
    }


    @Override
    public void ajouter(Reservation reservation) throws SQLException {
        String req = "INSERT INTO reservation (type_reservation,username,email,phone,id_seance) VALUES ('" + reservation.getType_reservation() + "','" + reservation.getUsername() + "','" + reservation.getEmail() + "','" + reservation.getPhone() + "'," + reservation.getId_seance() +")";

        Statement st = connection.createStatement();

        st.executeUpdate(req);

    }

    @Override
    public void modifier(Reservation reservation) throws SQLException {
        String req = "update reservation SET type_reservation =?,username=?,email=?, phone=?,id_seance=? where id_reservation=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, reservation.getType_reservation());
        ps.setString(2, reservation.getUsername());
        ps.setString(3, reservation.getEmail());
        ps.setInt(4, reservation.getPhone());
        ps.setInt(5, reservation.getId_seance());
        ps.setInt(6, reservation.getId_reservation());

        ps.executeUpdate();
    }

    @Override
    public void modifier2(Seance seance) throws SQLException {

    }

    @Override
    public void supprimer(int id_reservation) throws SQLException {
        String sql = "DELETE FROM reservation WHERE id_reservation = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id_reservation);
        ps.executeUpdate();
    }

    @Override
    public List<Reservation> recuperer() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation ";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId_reservation(rs.getInt("Id_reservation"));
            reservation.setType_reservation(rs.getString("Type_reservation"));

            reservation.setUsername(rs.getString("Username"));
            reservation.setEmail(rs.getString("Email"));
            reservation.setPhone(rs.getInt("Phone"));
            reservation.setId_seance(rs.getInt("id_seance"));


            reservations.add(reservation);

        }
        return reservations;

    }
}
