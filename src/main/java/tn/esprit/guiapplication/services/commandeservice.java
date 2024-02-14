package tn.esprit.guiapplication.services;

import tn.esprit.guiapplication.utils.MyDatabase;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import java.sql.*;
import tn.esprit.guiapplication.models.commande;

public class commandeservice implements IService <commande> {
    private Connection connection;
    public commandeservice()
    {
        connection= MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(commande commande) throws SQLException {
        String req="INSERT INTO commande (tel,nom,pre,mail,addr,pani) VALUES('"+commande.getTel()+"','"+commande.getNom()+"','"+commande.getPre()+"','"+commande.getMail()+"','"+commande.getAddr()+"','"+commande.getPani()+"')";

        Statement st=connection.createStatement();
        st.executeUpdate(req);


    }

    @Override
    public void modifier(commande commande) throws SQLException {
        String req="UPDATE commande SET tel=?,nom=?,pre=?,mail=?,addr=?,pani=? WHERE idc=?";

        PreparedStatement cs=connection.prepareStatement(req);

        cs.setInt(1, (commande.getTel()));
        cs.setString(2,commande.getNom());
        cs.setString(3,commande.getPre());
        cs.setString(4,commande.getMail());
        cs.setString(5,commande.getAddr());
        cs.setString(6,commande.getPani());
        cs.setInt(7,commande.getIdc());
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
            co.setMail(rs.getString("nom"));
            co.setNom(rs.getString("pre"));
            co.setPre(rs.getString("mail"));
            co.setPani(rs.getString("addr"));
            co.setPani(rs.getString("pani"));
            commandes.add(co);
        }
        return commandes;

    }


}
