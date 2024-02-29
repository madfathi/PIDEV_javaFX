package tn.esprit.guiapplicatio.services;

import java.sql.SQLException;
import java.util.List;
public interface IServicee<T> {
    void ajouter(T t) throws SQLException;
    void modifier (T t) throws SQLException;
    void supprimer(String id_c) throws SQLException;

    List<T> recuperer() throws SQLException;
   List<T> getAll() throws SQLException;

}

