package tn.esprit.guiapplicatio.services;
import java.util.List;
import  tn.esprit.guiapplicatio.models.Reclamation;

public interface NewInterface<T> {
    public void ajouter(T r);
    public List<T> afficher();
    public void supprimer(int id) ;
    public void modifier(int id , Reclamation nouvelleReclamation);

}

