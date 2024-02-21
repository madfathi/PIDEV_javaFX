package tn.esprit.guiapplicatio.test;

import tn.esprit.guiapplicatio.models.Reservation;
import tn.esprit.guiapplicatio.models.Seance;

import tn.esprit.guiapplicatio.models.commande;
import tn.esprit.guiapplication.models.panier;
import tn.esprit.guiapplicatio.services.ReservationService;

import tn.esprit.guiapplicatio.services.SeanceService;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {



        SeanceService ss = new SeanceService();
        ss.ajouter(new Seance(4500, 6, "fitness", "jqshkq"));
        //ss.modifier(new Seance(8,5000,6,"fdfd","444"));
        //ss.supprimer(14);


        System.out.println(ss.recuperer());
        //System.out.println(re.recuperer());







    }
}








