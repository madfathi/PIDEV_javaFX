package tn.esprit.demogui.services;

import tn.esprit.demogui.models.Fidelite;
import tn.esprit.demogui.models.User;
import tn.esprit.demogui.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FideliteService implements IService<Fidelite> {

    private Connection connection;


    public FideliteService() throws SQLException {
        connection = MyDatabase.getInstance().getConnection();
    }


    @Override
    public void ajouter(Fidelite fidelite) throws SQLException {
        String req = "INSERT INTO fidelite(montant , code_promo) VALUES ( '" + fidelite.getMontant() + "' , '" + fidelite.getCode_promo() + "')";
        Statement st=connection.createStatement();
        st.executeUpdate(req);

    }


    @Override
    public void modifier(Fidelite fidelite) throws SQLException {
        String req="UPDATE fidelite SET montant=?,code_promo=? WHERE id=? ";

        PreparedStatement us=connection.prepareStatement(req);

        us.setInt(1,fidelite.getMontant());
        us.setString(2,fidelite.getCode_promo());
        us.setInt(3,fidelite.getId());
        us.executeUpdate();
    }

    @Override
    public void supprimer(Fidelite fidelite) {

    }

    @Override
    public void supprimer(int id) throws SQLException {

        String req = "DELETE FROM fidelite WHERE id = ? ";
        PreparedStatement us = connection.prepareStatement(req);
        us.setInt(1, id);
        us.executeUpdate();
    }

    @Override
    public List<Fidelite> recupperer() throws SQLException {
        List<Fidelite> fidelites = new ArrayList<>();
        String req = "SELECT * FROM fidelite";
        Statement us = connection.createStatement();
        ResultSet rs = us.executeQuery(req);


        while (rs.next()) {
            Fidelite fidelite = new Fidelite();
            fidelite.setId(rs.getInt("id"));
            fidelite.setMontant(rs.getInt("montant"));
            fidelite.setCode_promo(rs.getString("code_promo"));



            fidelites.add(fidelite);

        }
        return fidelites;
    }
}