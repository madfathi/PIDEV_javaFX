package tn.esprit.projet3a.test;

import tn.esprit.projet3a.models.Evenment;
import tn.esprit.projet3a.models.Review;
import tn.esprit.projet3a.services.EvenmentService;
import tn.esprit.projet3a.services.ReviewService;
import tn.esprit.projet3a.utils.MyDatabase;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        EvenmentService es = new EvenmentService();
        ReviewService rss = new ReviewService();

        try {
           // es.ajouter(new Evenment("Are You Alien","16/02/2024","BSMNT","Briki"));
           // es.modifier(new Evenment(3,"Are You Alien","16/02/2024","BSMNT","Briki"));
           // es.supprimer(1);
          //  System.out.println(es.recuperer());
           // rss.ajouter(new Review(5,"Amazing Event",4));
          //  rss.modifier(new Review(1,4,"Amazing",5));
               // rss.supprimer(1);
            rss.ajouter(new Review(5,"Amazing Event",4));
            System.out.println(rss.recuperer());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
