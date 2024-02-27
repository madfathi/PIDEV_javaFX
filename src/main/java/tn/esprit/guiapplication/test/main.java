package tn.esprit.guiapplication.test;

import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.services.ProgramService;

import java.sql.SQLException;
import java.util.List;

public class main {
    public static void main(String[] args) throws SQLException {

        ClientService cs = new ClientService();
       //cs.ajouter(new Client(44, 87, "MED", "JARDAK"));
        // cs.modifier(new Client(5,10,80 ,"AHMED", "ABIDA"));
        //cs.supprimer(17);
        //System.out.println(cs.recuperer());


        ProgramService ps = new ProgramService();
        // ps.ajouter(new Program ("biennn","exelent" ,"bravo", 100));
        // ps.modifier(new Program(2,"hgj","ghhb","ghjjh",500));
        //ps.supprimer(2);
        //System.out.println(ps.recuperer());

        ProgramService programService = new ProgramService();


        // Test adding a program
       try {
            Client client = new Client(); // Assuming you have a way to create a Client object
            client.setId_c(17); // Set client ID



            Program programToAdd = new Program(4, "NEW", " moyen", "WELCOME",100, client);
            programToAdd.setClient(client); // Set the client associated with the program
            programService.ajouter(programToAdd);
            System.out.println("Program added successfully.");
            System.out.println(programToAdd);

        } catch (SQLException e) {
            e.printStackTrace();
        }



        // Test updating a program
        try {
            int programIdToModify = 23; // Set the ID of the program you want to modify
            Program programToModify = ps.rechercherParId(programIdToModify); // Retrieve the program by its ID
            if (programToModify != null) {
                // Modify the program attributes as needed
                programToModify.setTitre("New Title");
                programToModify.setNiveau("New Level");
                programToModify.setDescription("New Description");
                programToModify.setPrix(200); // Example modification, change the price

                // Call the modifier method in ProgramService
                ps.modifier(programToModify);
                System.out.println("Program modified successfully.");
            } else {
                System.out.println("Program with ID " + programIdToModify + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Test updating a program


        // Test retrieving programs
        try {
            List<Program> programs = programService.recuperer();
            System.out.println("List of programs:");
            for (Program program : programs) {
                System.out.println(program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test deleting a program
        try {
            String programIdToDelete = "Régime"; // Set the ID of the program you want to delete
            programService.supprimer(programIdToDelete);
            System.out.println("Program deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    }
