package tn.esprit.guiapplication.services;
import java.sql.ResultSet;
import tn.esprit.guiapplication.models.Seance;
import tn.esprit.guiapplication.utils.MyDatabase;
import tn.esprit.guiapplication.models.Seance;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class SeanceService implements IService<Seance> {

    private Connection connection;


    public SeanceService() {
        connection = MyDatabase.getInstance().getConnection();


    }

    @Override
    public void ajouter(Seance seance) throws SQLException {

        String req = "INSERT INTO seance (type_seance,duree_seance,nb_maximal,categorie) VALUES ('" + seance.getType_seance() + "','" + seance.getDuree_seance() + "','" + seance.getNb_maximal() + "'," + seance.getCategorie()+ ")";

        Statement st = connection.createStatement();

st.executeUpdate(req);



    }

    @Override
    public void modifier(Seance seance) throws SQLException {
String req ="update seance SET type_seance =?,duree_seance=?, nb_maximal=?, categorie=? where id_seance=?";
PreparedStatement ps = connection.prepareStatement(req);
ps.setString(1,seance.getType_seance());
ps.setInt(2,seance.getDuree_seance());
ps.setInt(3,seance.getNb_maximal());
ps.setString(4, seance.getCategorie());
ps.setInt(5,seance.getId_seance());

ps.executeUpdate();
    }

    @Override
    public void supprimer(int id_seance) throws SQLException {
        String sql = "DELETE FROM seance WHERE id_seance = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id_seance);
        ps.executeUpdate();
    }



    @Override
    public List<Seance> recuperer() throws SQLException {
        List<Seance> seances = new ArrayList<>();
        String query = "SELECT * FROM seance ";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
Seance seance =new Seance();
            seance.setId_seance(rs.getInt("ID_Seance"));
            seance.setType_seance(rs.getString("Type_seance"));
            seance.setNb_maximal(rs.getInt("Nb_maximal"));
            seance.setDuree_seance(rs.getInt("Duree_seance"));
            seance.setCategorie(rs.getString("categorie"));
seances.add(seance);

        }
        return seances;

    }
}