package tn.esprit.demogui.test;

import tn.esprit.demogui.models.User;
import tn.esprit.demogui.services.FideliteService;
import tn.esprit.demogui.services.UserService;
import tn.esprit.demogui.utils.MyDatabase;
import tn.esprit.demogui.models.Fidelite;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService us = new UserService();

           us.ajouter(new User("samar", "bouslimi", "samaro"));

            us.modifier(new User(8,"yasmine","bouslimi","amrirrrbo"));



        ////SUPRIMER

            us.supprimer(9);
        System.out.println(us.recupperer());

        FideliteService fs = new FideliteService();
        fs.ajouter(new Fidelite(12, 800, "azerty12"));
        fs.modifier(new Fidelite(2,760,"zizou23"));

        ////SUPRIMER

        fs.supprimer(1);
        System.out.println(us.recupperer());
    }}
