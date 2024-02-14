package tn.esprit.demogui.services;

import tn.esprit.demogui.models.User;
import tn.esprit.demogui.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IService<User> {

    private Connection connection;


    public UserService() throws SQLException {
        connection = MyDatabase.getInstance().getConnection();
    }


    @Override
    public void ajouter(User user) throws SQLException {
        String req = "INSERT INTO user(nom , prenom , mdp) VALUES ( '" + user.getNom() + "' , '" + user.getPrenom() + "' , '" + user.getMdp() + "')";
         Statement st=connection.createStatement();
            st.executeUpdate(req);

    }


    @Override
    public void modifier(User user) throws SQLException {
        String req="UPDATE user SET nom=?,prenom =?,mdp=? WHERE id=? ";

        PreparedStatement us=connection.prepareStatement(req);

        us.setString(1,user.getNom());
        us.setString(2,user.getPrenom());
        us.setString(3,user.getMdp());
        us.setInt(4,user.getId());
        us.executeUpdate();
    }

    @Override
    public void supprimer(User user) {

    }

    @Override
    public void supprimer(int id) throws SQLException {

        String req = "DELETE FROM user WHERE id = ? ";
        PreparedStatement us = connection.prepareStatement(req);
        us.setInt(1, id);
        us.executeUpdate();
    }

    @Override
    public List<User> recupperer() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "SELECT * FROM user";
        Statement us = connection.createStatement();
        ResultSet rs = us.executeQuery(req);


        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));

            user.setMdp(rs.getString("mdp"));


            users.add(user);

        }
        return users;
    }
    }
