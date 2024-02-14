package tn.esprit.guiapplication.services;
import java.sql.ResultSet;

import tn.esprit.guiapplication.models.Reservation;
import tn.esprit.guiapplication.models.Seance;
import tn.esprit.guiapplication.utils.MyDatabase;
import tn.esprit.guiapplication.models.Seance;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
public class ReservationService implements IService<Reservation> {
    private Connection connection;
    public ReservationService() {
        connection = MyDatabase.getInstance().getConnection();


    }

    @Override
    public void ajouter(Reservation reservation) throws SQLException {
        String req = "INSERT INTO reservation (type_reservation,username,email,phone) VALUES ('" + reservation.getType_reservation()+ "','" + reservation.getUsername() + "','" + reservation.getEmail() + "'," + reservation.getPhone()+ ")";

        Statement st = connection.createStatement();

        st.executeUpdate(req);

    }

    @Override
    public void modifier(Reservation reservation) throws SQLException {
        String req ="update reservation SET type_reservation =?,username=?,email=?, phone=? where id_reservation=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1,reservation.getType_reservation());
        ps.setString(2,reservation.getUsername());
        ps.setString(3,reservation.getEmail());
        ps.setInt(4, reservation.getPhone());
        ps.setInt(5,reservation.getId_reservation());

        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id_reservation) throws SQLException {
        String sql = "DELETE FROM reservation WHERE id_reservation = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id_reservation);
        ps.executeUpdate();
    }

    @Override
    public List<Reservation> recuperer() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation ";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Reservation reservation =new Reservation();
            reservation.setId_reservation(rs.getInt("Id_reservation"));
            reservation.setType_reservation(rs.getString("Type_reservation"));

            reservation.setUsername(rs.getString("Username"));
            reservation.setEmail(rs.getString("Email"));
            reservation.setPhone(rs.getInt("Phone"));

            reservations.add(reservation);

        }
        return reservations;

    }
}
