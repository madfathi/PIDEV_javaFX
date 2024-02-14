package tn.esprit.demogui.services;

import java.sql.SQLException;
import java.util.List;
public interface IService<T> {
        void ajouter (T t) throws SQLException;

        void modifier (T t) throws SQLException;

        void supprimer (T t);

        void supprimer(int id) throws SQLException;

        List<T> recupperer() throws SQLException;

    }

