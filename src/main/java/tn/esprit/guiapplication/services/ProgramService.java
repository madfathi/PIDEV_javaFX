package tn.esprit.guiapplication.services;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;
import tn.esprit.guiapplication.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class ProgramService implements IService<Program> {
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
        ps.setInt(5, program.getClient().getId_c()); // Assuming client_id is the foreign key in the program table
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
       // ps.setInt(5, program.getClient().getId_c()); // Assuming client_id is the foreign key in the program table
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

            // Create and set the client associated with the program
            Client client = new Client();
            client.setId_c(rs.getInt("id_client"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            client.setAge(rs.getInt("age"));
            client.setPoids(rs.getInt("poids"));
            // Assuming 'name' is a column in the 'client' table
            // Set other client attributes as needed

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

            // Create and set the client associated with the program
            Client client = new Client();
            client.setId_c(rs.getInt("id_client"));
            client.setNom(rs.getString("nom")); // Assuming 'name' is a column in the 'client' table
            // Set other client attributes as needed

            program.setClient(client);

            return program;
        } else {
            return null; // Return null if program with the specified ID is not found
        }
    }

    /*
    public void sortByProgram(List<Program> programs) {
        programs.sort(Comparator.comparing(Program::getTitre));

        for (Program program : programs) {
            System.out.println(program);
        }

    }

*/


    public Program getProgramByName(String name) throws SQLException {
        String req = "SELECT * FROM program WHERE titre = ?";
        ProgramService cs = new ProgramService();

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Program program = new Program();
                    program.setId_p(rs.getInt("id_p"));
                  //  program.setClient(cs.getClientByName(rs.getString("id_c")));
                    program.setTitre(rs.getString("titre"));
                    program.setNiveau(rs.getString("niveau"));
                    program.setDescription(rs.getString("description"));
                    program.setPrix(rs.getInt("prix"));

                    return program;
                }
            }
        }

        // Return null or throw an exception if the category is not found
        return null; // You can choose a suitable default value
    }
    }



