package tn.esprit.guiapplication.services;

import java.sql.ResultSet;

import tn.esprit.guiapplication.models.Reservation;
import tn.esprit.guiapplication.models.panier;
import tn.esprit.guiapplication.utils.MyDatabase;
import tn.esprit.guiapplication.models.Seance;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;




import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class panierservice implements IService <panier>{
    private Connection connection;
    public panierservice(){connection= MyDatabase.getInstance().getConnection();}
    @Override
    public void ajouter(panier panier) throws SQLException {
        String req="INSERT INTO panier (quantite,nomp,img) VALUES('"+panier.getQuantite()+"','"+panier.getNomp()+"','"+panier.getImg()+"')";

        Statement st=connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(panier panier) throws SQLException {
        String req="UPDATE panier SET quantite=?,nomp=?,img=? WHERE idp=?";

        PreparedStatement cs=connection.prepareStatement(req);

        cs.setInt(1, (panier.getQuantite()));
        cs.setString(2,panier.getNomp());
        cs.setString(3,panier.getImg());
        cs.setInt(4,panier.getIdp());
        cs.executeUpdate();
    }

    @Override
    public void supprimer(int idp) throws SQLException {
        String req="DELETE FROM panier WHERE idp=?";

        PreparedStatement cs=connection.prepareStatement(req);
        cs.setInt(1,idp);
        cs.executeUpdate();
    }

    @Override
    public List<panier> recuperer() throws SQLException {
        List<panier> paniers= new ArrayList<>();
        String req="SELECT * FROM panier";
        Statement cs=connection.createStatement();
        ResultSet rs= cs.executeQuery(req);
        while (rs.next())
        {
            panier pa=new panier();
            pa.setIdp(rs.getInt("idp"));
            pa.setQuantite(rs.getInt("quantite"));
            pa.setNomp(rs.getString("nomp"));
            pa.setImg(rs.getString("img"));
            paniers.add(pa);
        }
        return paniers;

    }}
