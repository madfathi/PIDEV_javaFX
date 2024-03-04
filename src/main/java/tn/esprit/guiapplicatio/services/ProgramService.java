package tn.esprit.guiapplicatio.services;
import tn.esprit.guiapplicatio.utils.MyDatabase;
import tn.esprit.guiapplicatio.models.Client;
import tn.esprit.guiapplicatio.models.Program;
import tn.esprit.guiapplicatio.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class ProgramService implements IServicee<Program> {
    private Connection connection;

    public ProgramService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Program program) throws SQLException {
        String req = "INSERT INTO program (titre, niveau, description, prix, id_client, image) VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, program.getTitre());
        ps.setString(2, program.getNiveau());
        ps.setString(3, program.getDescription());
        ps.setInt(4, program.getPrix());
        ps.setInt(5, program.getClient().getId_c());
        ps.setString(6, program.getImage());
        ps.executeUpdate();

    }

    @Override
    public void modifier(Program program) throws SQLException {
        String req = "UPDATE program SET titre = ?, niveau = ?, description = ?, prix = ? WHERE id_p = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, program.getTitre());
        ps.setString(2, program.getNiveau());
        ps.setString(3, program.getDescription());
        ps.setInt(4, program.getPrix());
        ps.setInt(5, program.getId_p());
        ps.executeUpdate();
    }

    @Override
    public List<Program> getAll() {
        return null;
    }
    @Override
    public void supprimer(String titre) throws SQLException {
        String req = "DELETE FROM program WHERE titre = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, titre);
        ps.executeUpdate();
    }

    @Override
    public List<Program> recuperer() throws SQLException {
        List<Program> programs = new ArrayList<>();
        String req = "SELECT p.*, c.* FROM program p LEFT JOIN client c ON p.id_client = c.id_c";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            Program program = new Program();
            program.setId_p(rs.getInt("id_p"));
            program.setTitre(rs.getString("titre"));
            program.setNiveau(rs.getString("niveau"));
            program.setDescription(rs.getString("description"));
            program.setPrix(rs.getInt("prix"));


            Client client = new Client();
            client.setId_c(rs.getInt("id_client"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            client.setAge(rs.getInt("age"));
            client.setPoids(rs.getInt("poids"));


            program.setClient(client);

            programs.add(program);
        }
        return programs;
    }

    public Program rechercherParId(int id_p) throws SQLException {
        String req = "SELECT p.*, c.* FROM program p LEFT JOIN client c ON p.id_client = c.id_c WHERE p.id_p = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_p);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Program program = new Program();
            program.setId_p(rs.getInt("id_p"));
            program.setTitre(rs.getString("titre"));
            program.setNiveau(rs.getString("niveau"));
            program.setDescription(rs.getString("description"));
            program.setPrix(rs.getInt("prix"));


            Client client = new Client();
            client.setId_c(rs.getInt("id_client"));
            client.setNom(rs.getString("nom"));


            program.setClient(client);

            return program;
        } else {
            return null;
        }
    }




    public Program getProgramByName(String name) throws SQLException {
        String req = "SELECT * FROM program WHERE titre = ?";
        ProgramService cs = new ProgramService();

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Program program = new Program();
                    program.setId_p(rs.getInt("id_p"));
                    program.setTitre(rs.getString("titre"));
                    program.setNiveau(rs.getString("niveau"));
                    program.setDescription(rs.getString("description"));
                    program.setPrix(rs.getInt("prix"));

                    return program;
                }
            }
        }

        return null;
    }
    }



