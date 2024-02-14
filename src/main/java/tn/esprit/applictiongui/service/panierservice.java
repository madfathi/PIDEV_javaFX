package tn.esprit.applictiongui.service;


import tn.esprit.applictiongui.model.commande;
import tn.esprit.applictiongui.model.panier;
import util.mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class panierservice implements iservice <panier>{
    private Connection connection;
    public panierservice(){connection= mydatabase.getInstance().getConnection();}
    @Override
    public void ajouter(panier panier) throws SQLException {
        String req="INSERT INTO panier (quantite,nomp,img,pt) VALUES('"+panier.getQuantite()+"','"+panier.getNomp()+"','"+panier.getImg()+"','"+panier.getPt()+"')";

        Statement st=connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(panier panier) throws SQLException {
        String req="UPDATE panier SET quantite=?,nomp=?,img=?,pt=? WHERE idp=?";

        PreparedStatement cs=connection.prepareStatement(req);

        cs.setInt(1, (panier.getQuantite()));
        cs.setString(2,panier.getNomp());
        cs.setString(3,panier.getImg());
        cs.setInt(4,panier.getPt());
        cs.setInt(5,panier.getIdp());
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
            pa.setPt(rs.getInt("pt"));
            paniers.add(pa);
        }
        return paniers;

    }

    @Override
    public List<panier> tri_par_nom() throws SQLException {
        List<panier> paniers=new ArrayList<>();
        String req = "SELECT * FROM panier ORDER BY quantite";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(req);

        while (rs.next())
        {
            panier pa=new panier();
            pa.setIdp(rs.getInt("idp"));
            pa.setQuantite(rs.getInt("quantite"));
            pa.setNomp(rs.getString("nomp"));
            pa.setImg(rs.getString("img"));
            pa.setPt(rs.getInt("pt"));
            paniers.add(pa);
        }
        return paniers;

    }

    @Override
    public List<panier> chercher(String nom) throws SQLException {
        return null;
    }


}
