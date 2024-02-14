package tn.esprit.guiapplication.services;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;
import tn.esprit.guiapplication.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProgramService implements IService<Program> {
    private Connection connection;


    public ProgramService() {

        connection = MyDatabase.getInstance().getConnection();
    }

    public void ajouter(Program program) throws SQLException {
        String req = " INSERT INTO program (titre,niveau,description,prix) VALUES ('" + program.getTitre() + "' , '" + program.getNiveau() + "','" + program.getDescription() + "'," + program.getPrix() + ")";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Program program) throws SQLException {
        String req = "UPDATE program SET titre = ?, niveau = ? , description = ? , prix = ? WHERE id_p = ? " ;
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, program.getTitre());
        ps.setString(2, program.getNiveau());
        ps.setString(3, program.getDescription());
        ps.setInt(4, program.getPrix());
        ps.setInt(5, program.getId_p());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id_p) throws SQLException {
        String req = "DELETE FROM program WHERE id_p= ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_p);
        ps.executeUpdate();

    }

    @Override
    public List<Program> recuperer() throws SQLException {
        List<Program> programs = new ArrayList<>();
        String req ="SELECT * FROM program";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            Program program = new Program();
            program.setId_p(rs.getInt("id_p"));
            program.setTitre(rs.getString("titre"));
            program.setNiveau(rs.getString("niveau"));
            program.setDescription(rs.getString("description"));
            program.setPrix(rs.getInt("prix"));


            programs.add(program);

        }
        return programs;
    }
}