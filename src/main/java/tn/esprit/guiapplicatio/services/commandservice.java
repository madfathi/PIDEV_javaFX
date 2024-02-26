package tn.esprit.guiapplicatio.services;


import java.sql.SQLException;
import java.util.List;

public interface commandservice <T>
{
    void ajouter(T t) throws SQLException;
    void modifier(T t) throws SQLException;
    void supprimer(int idc) throws SQLException;
    List<T>recuperer() throws SQLException;
    List<T>tri_par_nom() throws SQLException;
    List<T>chercher(String nom) throws SQLException;
    List<T>tri_par_nom2() throws SQLException;

}
