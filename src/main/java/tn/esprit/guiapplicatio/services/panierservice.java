

package tn.esprit.guiapplicatio.services;
import tn.esprit.guiapplicatio.models.commande;
import tn.esprit.applictiongui.model.panier;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.sql.ResultSet;

import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.utils.MyDatabase;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class panierservice implements commandservice <panier>{
    private Connection connection;
    public panierservice(){connection= MyDatabase.getInstance().getConnection();}
    @Override
    public void ajouter(panier panier) throws SQLException {
        String req="INSERT INTO panier (quantite,nomp,img,pt,prod_id) VALUES('"+panier.getQuantite()+"','"+panier.getNomp()+"','"+panier.getImg()+"','"+panier.getPt()+"','"+panier.getProd_id()+"')";

        Statement st=connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(panier panier) throws SQLException {
        String req="UPDATE panier SET quantite=?,nomp=?,img=?,pt=?,prod_id=? WHERE idp=?";

        PreparedStatement cs=connection.prepareStatement(req);

        cs.setInt(1, (panier.getQuantite()));
        cs.setString(2,panier.getNomp());
        cs.setString(3,panier.getImg());
        cs.setInt(4,panier.getPt());
        cs.setInt(5,panier.getIdp());
        cs.setString(6,panier.getProd_id());
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
            pa.setProd_id(rs.getString("prod_id"));
            paniers.add(pa);
        }
        return paniers;

    }

    @Override
    public List<panier> tri_par_nom() throws SQLException {
        List<panier> paniers=new ArrayList<>();
        String req = "SELECT * FROM panier ORDER BY pt";
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
            pa.setProd_id(rs.getString("prod_id"));
            paniers.add(pa);
        }
        return paniers;

    }

    @Override
    public List<panier> chercher(String nom) throws SQLException {
        List<panier> paniers=new ArrayList<>();
        String req = "SELECT * FROM panier ORDER BY pt asc ";
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
            pa.setProd_id(rs.getString("prod_id"));
            paniers.add(pa);
        }
        return paniers;
    }

    @Override
    public List<panier> tri_par_nom2() throws SQLException {
        List<panier> paniers=new ArrayList<>();
        String req = "SELECT * FROM panier ORDER BY pt desc";
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
            pa.setProd_id(rs.getString("prod_id"));

            paniers.add(pa);
        }
        return paniers;
    }


}
