package tn.esprit.applictiongui.test;
import java.sql.SQLException;

import tn.esprit.applictiongui.model.commande;
import tn.esprit.applictiongui.model.panier;
import tn.esprit.applictiongui.service.commandeservice;
import tn.esprit.applictiongui.service.panierservice;

public class Main {
    public static void main (String[] args) throws SQLException {
        commandeservice cs= new commandeservice();
        panierservice ps=new panierservice();
        //cs.ajouter(new commande(1234,"fathi","jarbou3","fathi@gmail.com","nourja3fer","waywa"));
      //cs.modifier(new commande(4,22,"salim","sfexi","salim@gmail.com","a","azerty"));
        //cs.supprimer(3);
        //System.out.println(cs.recuperer());
      //ps.ajouter(new panier(1,"ahmed","tag",33));
        //ps.modifier(new panier(2,23,"salim","sfexi"));
        //ps.supprimer(4);
        //System.out.println(ps.recuperer());
       //System.out.println(cs.tri_par_nom());
        //System.out.println(cs.chercher("salim"));
     //System.out.println(ps.tri_par_nom());
    }
}
