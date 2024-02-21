

package tn.esprit.guiapplicatio.services;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {

    void ajouter(T t) throws SQLException;

    void modifier(T t) throws SQLException;

    void supprimer(int id) throws SQLException;
boolean idEsists(int id) throws SQLException;
    List<T> recuperer() throws SQLException;
    ObservableList<T> sortCategorieAsc();
    ObservableList<T> sortCategorieDesc();

    ObservableList<T> readCategorie();


}
