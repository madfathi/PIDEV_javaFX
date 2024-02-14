package tn.esprit.projet3a.services;

import tn.esprit.projet3a.models.Review;
import tn.esprit.projet3a.utils.MyDatabase;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewService implements IService<Review> {

    private Connection connection;

    public ReviewService(){
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Review review) throws SQLException {
        String req="INSERT INTO review (nbr_star,description,id_event) VALUES ('"+review.getNbr_star()+"','"+review.getDescription()+"','"+review.getId_event()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(Review review) throws SQLException {
        String req = "UPDATE review SET nbr_star = ? , description = ? , id_event = ? WHERE id_review = ?";
        PreparedStatement rss = connection.prepareStatement(req);
        rss.setInt(1, review.getNbr_star());
        rss.setString(2, review.getDescription());
        rss.setInt(3, review.getId_event());
        rss.setInt(4, review.getId_review());
        rss.executeUpdate();

    }

    @Override
    public void supprimer(int id_review) throws SQLException {
        String req = "DELETE FROM review WHERE id_review = ?";
        PreparedStatement rss = connection.prepareStatement(req);
        rss.setInt(1, id_review);
        rss.executeUpdate();

    }

    @Override
    public List<Review> recuperer() throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String req = "SELECT * FROM review";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Review review = new Review();
            review.setId_review(rs.getInt("id_review"));
            review.setNbr_star(rs.getInt("nbr_star"));
            review.setDescription(rs.getString("description"));
            review.setId_event(rs.getInt("id_event"));

            reviews.add(review);
        }
        return reviews;
    }
}
