package tn.esprit.guiapplicatio.services;

import tn.esprit.guiapplicatio.models.Offre;
import javafx.collections.ObservableList;

public interface IServiceOffre  <T>{
    public ObservableList<T> readOffre();
}
