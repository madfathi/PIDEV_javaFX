package service;
import java.util.List;
import entities.Reclamation;

public interface NewInterface<T> {
    public void ajouter(T r);
    public List<T> afficher();
    public void supprimer(int id) ;
    public void modifier(int id , Reclamation nouvelleReclamation);

}

