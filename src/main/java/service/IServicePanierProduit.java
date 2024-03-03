package service;

import controller.Panier;
import entities.panier;
import entities.PanierProduit;
import entities.Produit;
import javafx.collections.ObservableList;

import java.util.List;

public interface IServicePanierProduit <T>{
    void ajouterProduitAuPanier(panier Panier, int idProduit);
    ObservableList<T> getProduitsDuPanierUtilisateur(panier Panier);
    void DeleteProduitAuPanier(panier Panier, int idProduit);

    public ObservableList<PanierProduit> getAllProduitsPanier();
    public float facture(panier Panier);

    }
