package tn.esprit.projet3a.services;

import tn.esprit.projet3a.models.Evenment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.projet3a.utils.MyDatabase;

public class EvenmentService implements IService<Evenment> {

    private Connection connection;
    public EvenmentService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Evenment evenment) throws SQLException {
        String req = "INSERT INTO evenment (nom_event,date_event,lieu_event,nom_star) VALUES ('" + evenment.getNom_event() + "', '" + evenment.getDate_event() + "', '" + evenment.getLieu_event() + "', '" + evenment.getNom_star() + "')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);



    }

    @Override
    public void modifier(Evenment evenment) throws SQLException {
        String req = "UPDATE evenment SET nom_event = ? , date_event = ? , lieu_event = ? , nom_star = ? WHERE id_event = ?";
        PreparedStatement es = connection.prepareStatement(req);
        es.setString(1, evenment.getNom_event());
        es.setString(2, evenment.getDate_event());
        es.setString(3, evenment.getLieu_event());
        es.setString(4, evenment.getNom_star());
        es.setInt(5, evenment.getId_event());
        es.executeUpdate();

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
        String req = "SELECT * FROM evenment";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while(rs.next()){
            Evenment evenment = new Evenment();
            evenment.setId_event(rs.getInt("id_event"));
            evenment.setNom_event(rs.getString("nom_event"));
            evenment.setDate_event(rs.getString("date_event"));
            evenment.setLieu_event(rs.getString("lieu_event"));
            evenment.setNom_star(rs.getString("nom_star"));

            evenments.add(evenment);
        }
        return evenments;
    }
}
