package tn.esprit.projet3a.test;

import tn.esprit.projet3a.models.Evenment;
import tn.esprit.projet3a.models.Review;
import tn.esprit.projet3a.services.EvenmentService;
import tn.esprit.projet3a.services.ReviewService;
import tn.esprit.projet3a.utils.MyDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EvenmentService es = new EvenmentService();
        ReviewService rss = new ReviewService();


        try {


            // Appelez la méthode recherche avec l'identifiant de l'événement que vous souhaitez rechercher
           /* List<Evenment> resultatsRecherche = EvenmentService.recherche(8);
            System.out.println(resultatsRecherche);
            Evenment evenment = resultatsRecherche.get(0);
            List<Review> reviews = evenment.getReviews();
            System.out.println(reviews);*/





            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse("2024-02-19");
            System.out.println("Parsed date: " + date); // Add this line to check the parsed date
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            // es.ajouter(new Evenment("farbika", sqlDate, "lazy club", "yagi"));
            // es.ajouter(new Evenment("ARE YOU ALIEN ",16/02/2024,"BSMNT","Briki"));
             // es.modifier(8,new Evenment("lol ",sqlDate,"BSMNT","ahmed","hazbdkhzd"));
            // es.supprimer(9);
              System.out.println(EvenmentService.afficheravis());
            // System.out.println(es.tri());
           // System.out.println(es.recherche(8));
          // rss.ajouter(new Review(5,"boring",8));
          //  rss.modifier(new Review(1,4,"Amazing",5));
               // rss.supprimer(1);
           // rss.ajouter(new Review(5,"Amazing Event",4));
           // System.out.println(rss.recuperer());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
