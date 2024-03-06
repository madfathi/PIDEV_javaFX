package  tn.esprit.guiapplicatio.services;

import tn.esprit.guiapplicatio.controllers.Panier;
import tn.esprit.guiapplicatio.models.panier;
import tn.esprit.guiapplicatio.models.PanierProduit;
import tn.esprit.guiapplicatio.models.Produit;
import javafx.collections.ObservableList;

import java.util.List;

public interface IServicePanierProduit <T>{
    void ajouterProduitAuPanier(panier Panier, int idProduit);
    ObservableList<T> getProduitsDuPanierUtilisateur(panier Panier);
    void DeleteProduitAuPanier(panier Panier, int idProduit);

    public ObservableList<PanierProduit> getAllProduitsPanier();
    public float facture(panier Panier);

    }
