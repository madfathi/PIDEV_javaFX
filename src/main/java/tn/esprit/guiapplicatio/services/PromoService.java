package tn.esprit.guiapplicatio.services;

import tn.esprit.guiapplicatio.models.CodePromo;
import tn.esprit.guiapplicatio.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class PromoService implements IServices<CodePromo> {
    private Connection connection;

    public PromoService() throws SQLException {
        connection = MyDatabase.getInstance().getConnection();
    }

/*    @Override
    public void ajouter(CodePromo codePromo) throws SQLException {
        String req = "INSERT INTO code_promo (code, date_exp, utilise, user_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, codePromo.getCode());
        ps.setTimestamp(2,new java.sql.Date(codePromo.getDate_exp().getTime())); // Utiliser directement getDate_exp()
        ps.setInt(3, codePromo.getUtilise());
        ps.setInt(4, codePromo.getUser_id());
        ps.executeUpdate();
        ps.close();
    }
*/
@Override
public void ajouter(CodePromo codePromo) throws SQLException {
    String req = "INSERT INTO code_promo (code, date_exp, montant, user_id) VALUES ('" +
            codePromo.getCode() + "', '" +
            codePromo.getDate_exp() + "', " +
            codePromo.getmontant() + ", " +
            codePromo.getUser_id() + ")" ; // Corrected concatenation
    Statement st = connection.createStatement();
    st.executeUpdate(req);
}


    @Override
    public void modifier(CodePromo codePromo) throws SQLException {
        String req = "UPDATE code_promo SET code = ?, date_exp = ?, utilise = ? WHERE user_id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, codePromo.getCode());
        ps.setTimestamp(2, new Timestamp(codePromo.getDate_exp().getTime())); // Utiliser directement getDate_exp()
        ps.setInt(3, codePromo.getmontant());
        ps.setInt(4, codePromo.getUser_id());
        ps.executeUpdate();
    }






    @Override
    public void supprimer(CodePromo codePromo) {

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM code_promo WHERE user_id = ? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();
    }


    @Override
    public List<CodePromo> recupperer() throws SQLException {
        List<CodePromo> codesPromo = new ArrayList<>();
        String req = "SELECT * FROM code_promo";
        Statement ps = connection.createStatement();
        ResultSet rs = ps.executeQuery(req);

        while (rs.next()) {
            CodePromo code_promo = new CodePromo();
          //  code_promo.setId(rs.getInt("id"));
            code_promo.setCode(rs.getInt("code"));
            code_promo.setDate_exp(rs.getDate("date_exp"));
            code_promo.setmontant(rs.getInt("montant"));
            code_promo.setUser_id(rs.getInt("user_id"));

            codesPromo.add(code_promo);
        }

        return codesPromo;
    }


    public boolean codeExisteDeja(int code) {
        PromoService promoService = null;
        try {
            promoService = new PromoService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Récupérer la liste des codes promo existants
            List<CodePromo> codesPromo = promoService.recupperer();

            // Parcourir la liste des codes promo pour vérifier si le code existe déjà
            for (CodePromo codePromo : codesPromo) {
                if (codePromo.getCode() == code) {
                    // Le code existe déjà dans la base de données
                    return true;
                }
            }

            // Le code n'existe pas dans la base de données
            return false;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}

