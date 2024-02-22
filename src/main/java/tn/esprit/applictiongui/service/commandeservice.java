package tn.esprit.applictiongui.service;


import tn.esprit.applictiongui.model.commande;
import util.mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class commandeservice implements iservice <commande> {
    private Connection connection;
    public commandeservice()
    {
        connection=mydatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(commande commande) throws SQLException {
        String req="INSERT INTO commande (tel,nom,pre,mail,addr,pani) VALUES('"+commande.getTel()+"','"+commande.getNom()+"','"+commande.getPre()+"','"+commande.getMail()+"','"+commande.getAddr()+"','"+commande.getPani()+"')";

        Statement st=connection.createStatement();
        st.executeUpdate(req);


    }

    @Override
    public void modifier(commande commande) throws SQLException {
        String req="UPDATE commande SET tel=?,nom=?,pre=?,mail=?,addr=? WHERE idc=?";

        PreparedStatement cs=connection.prepareStatement(req);

        cs.setInt(1, (commande.getTel()));
        cs.setString(2,commande.getNom());
        cs.setString(3,commande.getPre());
        cs.setString(4,commande.getMail());
        cs.setString(5,commande.getAddr());

        cs.setInt(6,commande.getIdc());
        cs.executeUpdate();

    }

    @Override
    public void supprimer(int idc) throws SQLException {
        String req="DELETE FROM commande WHERE idc=?";

        PreparedStatement cs=connection.prepareStatement(req);
        cs.setInt(1,idc);
        cs.executeUpdate();

    }

    @Override
    public List<commande>recuperer() throws SQLException {
        List<commande> commandes=new ArrayList<>();
        String req="SELECT * FROM commande";

        Statement cs=connection.createStatement();
        ResultSet rs= cs.executeQuery(req);
        while (rs.next())
        {
            commande co=new commande();
            co.setIdc(rs.getInt("idc"));
            co.setTel(rs.getInt("tel"));
            co.setMail(rs.getString("mail"));
            co.setNom(rs.getString("nom"));
            co.setPre(rs.getString("pre"));
            co.setAddr(rs.getString("addr"));
            co.setPani(Collections.singletonList(rs.getString("pani")));
            commandes.add(co);
        }
        return commandes;

    }

    @Override
    public List<commande> tri_par_nom() throws SQLException {
        List<commande> commandes=new ArrayList<>();
        String req = "SELECT * FROM commande ORDER BY nom asc";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(req);

        while (rs.next())
        {
            commande co=new commande();

            co.setIdc(rs.getInt("idc"));
            co.setTel(rs.getInt("tel"));
            co.setMail(rs.getString("nom"));
            co.setNom(rs.getString("pre"));
            co.setPre(rs.getString("mail"));
            co.setAddr(rs.getString("addr"));
            co.setPani(Collections.singletonList(rs.getString("pani")));
            commandes.add(co);
        }
        return commandes;


    }

    @Override
    public List<commande> chercher(String nom) throws SQLException {
        List<commande> commandes=new ArrayList<>();
        String req = "SELECT * FROM commande WHERE nom = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, nom);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())
        {
            commande co=new commande();
            co.setIdc(rs.getInt("idc"));
            co.setTel(rs.getInt("tel"));
            co.setNom(rs.getString("nom"));
            co.setPre(rs.getString("pre"));
            co.setMail(rs.getString("mail"));
            co.setAddr(rs.getString("addr"));
            co.setPani(Collections.singletonList(rs.getString("pani")));
            commandes.add(co);
        }
        return commandes;


    }

    @Override
    public List<commande> tri_par_nom2() throws SQLException {
        List<commande> commandes=new ArrayList<>();
        String req = "SELECT * FROM commande ORDER BY nom desc";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(req);

        while (rs.next())
        {
            commande co=new commande();

            co.setIdc(rs.getInt("idc"));
            co.setTel(rs.getInt("tel"));
            co.setMail(rs.getString("nom"));
            co.setNom(rs.getString("pre"));
            co.setPre(rs.getString("mail"));
            co.setAddr(rs.getString("addr"));
            co.setPani(Collections.singletonList(rs.getString("pani")));
            commandes.add(co);
        }
        return commandes;
    }
}






