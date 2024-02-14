package tn.esprit.guiapplication.test;
import tn.esprit.guiapplication.models.Reservation;
import tn.esprit.guiapplication.models.Seance;

import tn.esprit.guiapplication.services.ReservationService;

import tn.esprit.guiapplication.services.SeanceService;
import tn.esprit.guiapplication.utils.MyDatabase;
import java.sql.SQLException;
public class Main {


    public static void main(String[] args) throws SQLException {

        ReservationService re=new ReservationService();
          re.ajouter(new Reservation("fitness","fathi","fathi@esprit.tn",12345));
        re.modifier(new Reservation(1,"fitness","ahmed","fathi@esprit.tn",12345));
        re.supprimer(3);

        System.out.println(re.recuperer());

        SeanceService ss=new SeanceService();
ss.modifier(new Seance(7,4500,6,"fdfd","444"));
ss.supprimer(7);

     System.out.println(ss.recuperer());



    }
}








