package tn.esprit.guiapplication.test;

import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.services.ProgramService;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {

        ClientService cs = new ClientService() ;

        //cs.ajouter(new Client (20,90 ,"ali", "abidi"));
         //cs.modifier(new Client(10,25,80 ,"AHMED", "ABIDA"));
       //cs.supprimer(9);
         System.out.println(cs.recuperer());


        ProgramService ps = new ProgramService();
       // ps.ajouter(new Program ("biennn","exelent" ,"bravo", 100));
       // ps.modifier(new Program(2,"hgj","ghhb","ghjjh",500));
        //ps.supprimer(2);
        System.out.println(ps.recuperer());
    }
}
