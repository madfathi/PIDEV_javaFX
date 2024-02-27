package tn.esprit.applictiongui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import tn.esprit.applictiongui.model.commande;
import tn.esprit.applictiongui.service.commandeservice;
import javafx.scene.input.MouseEvent;
import tn.esprit.applictiongui.test.HelloApplication;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Affichecommande {
    @FXML
    private ListView<commande> listview;
    @FXML
    private TextField re;
    @FXML
    private TextField ID_c;
    @FXML
    private ListView<String> addd;
    @FXML
    private ListView<String> mmail;
    @FXML
    private ListView<String> nnom;

    @FXML
    private ListView<String> ppani;

    @FXML
    private ListView<String> ppre;
    @FXML
    private ListView<String> tell;
    @FXML
    private TextField rc;

    @FXML
    private TextField ma;

    @FXML
    private TextField mm;

    @FXML
    private TextField mn;

    @FXML
    private TextField mp;

    @FXML
    private TextField mt;

    ObservableList<commande> list = FXCollections.observableArrayList();
    private final commandeservice cs =new commandeservice();
    private commandeservice css =new commandeservice();

    @FXML
    private TableView<commande> tab;

    @FXML
    private TableColumn<commande, String> taba;

    @FXML
    private TableColumn<commande, String> tabe;

    @FXML
    private TableColumn<commande, String> tabn;

    @FXML
    private TableColumn<commande, String> tabp;
    @FXML
    private TableColumn<commande, String> tabr;
    @FXML
    private TableColumn<commande, Integer> tabt;
    @FXML
    private TableColumn<commande, Integer> tabi;
public int idc,tel;
public String nom,pre,mail,addr,pani;
    public String getPani() {
        return pani;
    }


    public int getIdc() {
        return idc;
    }

    public int getTel() {
        return tel;
    }

    public String getAddr() {
        return addr;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public String getPre() {
        return pre;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setPani(String pani) {
        this.pani = pani;
    }


    public void SetValue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        commande selected =listview.getSelectionModel().getSelectedItem();

        if (selected != null) {

            mt.setText(String.valueOf(selected.getTel()));
            mn.setText(selected.getNom());
            mm.setText(selected.getMail());
            ma.setText(selected.getAddr());
            mp.setText(selected.getPre());
            idc = selected.getIdc();
            pani = selected.getPani().toString();


        }
    }
    @FXML
    void suppcommande(ActionEvent event) {
        commande selected=listview.getSelectionModel().getSelectedItem();


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Voulez-Vous Supprimer cet Categorie?");
            alert.setContentText("Supprimer?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    try {
                        css.supprimer(selected.getIdc());
                        initialize();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type == noButton) {

                    try {
                        initialize();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                } else {

                    try {
                        initialize();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                }
            });


    }


    @FXML
    void updatecommande(ActionEvent event) throws SQLException {

        String nomC = mn.getText();
        String pree = mp.getText();
        String maa = mm.getText();
        String ad = ma.getText();
        int te;

// Contrôle de saisie pour nomC
        if (nomC.isEmpty()) {
            // Afficher un message d'erreur si le champ est vide
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de saisie");
            al.setContentText("Veuillez entrer un nom.");
            al.showAndWait();
            return; // Arrêter l'exécution de la méthode
        }

// Contrôle de saisie pour pree
        if (pree.isEmpty()) {
            // Afficher un message d'erreur si le champ est vide
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de saisie");
            al.setContentText("Veuillez entrer un prénom.");
            al.showAndWait();
            return; // Arrêter l'exécution de la méthode
        }

// Contrôle de saisie pour te
        try {
            te = Integer.parseInt(mt.getText());
        } catch (NumberFormatException e) {
            // Afficher un message d'erreur si te n'est pas un entier
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de saisie");
            al.setContentText("Veuillez entrer un nombre valide pour te.");
            al.showAndWait();
            return; // Arrêter l'exécution de la méthode
        }

// Contrôle de saisie pour maa
        if (maa.isEmpty() && !maa.contains("@")) {
            // Afficher un message d'erreur si le champ est vide
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de saisie");
            al.setContentText("Veuillez entrer une valeur pour maa.");
            al.showAndWait();
            return; // Arrêter l'exécution de la méthode
        }

// Contrôle de saisie pour ad
        if (ad.isEmpty()) {
            // Afficher un message d'erreur si le champ est vide
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de saisie");
            al.setContentText("Veuillez entrer une adresse.");
            al.showAndWait();
            return; // Arrêter l'exécution de la méthode
        }

// Si tous les champs sont correctement saisis, poursuivre avec la création de la commande
// (Vous pouvez ajouter le code de création de commande ici)

// Afficher un message de succès
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Succès");
        al.setContentText("Produit Modifié avec succès.");
        al.showAndWait();

        initialize(); //
       commande c =new commande(idc,te,nomC,pree,maa,ad, Collections.singletonList(pani));
        css.modifier(c);

        //Alert al = new Alert(Alert.AlertType.WARNING);

        al.setTitle("Succes");
        al.setContentText("Produit Modifiée");
        al.showAndWait();
        initialize();
    }

    @FXML
    void initialize( ) throws SQLException {
        /*rc.getItems().removeAll(rc.getItems());
        rc.getItems().addAll("Trier", "Trier par Nom ↑", "Trier par Nom ↓");
        rc.getSelectionModel().select("Trier");*/
        /*commandeservice cs=new commandeservice();
        try {
            List<commande> co=cs.recuperer();
            ObservableList<commande> ob= FXCollections.observableList(co);
            tab.setItems(ob);
            tabi.setCellValueFactory(new PropertyValueFactory<>("idc"));
            tabt.setCellValueFactory(new PropertyValueFactory<>("tel"));
            tabn.setCellValueFactory(new PropertyValueFactory<>("mail"));
            tabp.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tabe.setCellValueFactory(new PropertyValueFactory<>("pre"));
            taba.setCellValueFactory(new PropertyValueFactory<>("addr"));
            tabr.setCellValueFactory(new PropertyValueFactory<>("pani"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        commandeservice co = null;
        co = new commandeservice();
        try {
            List<commande> coo = co.recuperer();
            ObservableList<String> telList = FXCollections.observableArrayList();
            ObservableList<String> emailList = FXCollections.observableArrayList();
            ObservableList<String> mdpList = FXCollections.observableArrayList();
            ObservableList<String> nomList = FXCollections.observableArrayList();
            ObservableList<String> prenomList = FXCollections.observableArrayList();
            ObservableList<String> adresseList = FXCollections.observableArrayList();

            for (commande co : coo) {
                telList.add(String.valueOf(user.getTel()));
                emailList.add(user.getMail());
                mdpList.add(String.valueOf(user.getPani()));
                nomList.add(user.getNom());
                prenomList.add(user.getPre());
                adresseList.add(user.getAddr());
            }
            tell.setItems(telList);
            mmtail.setItems(emailList);
            ppani.setItems(mdpList);
            nnom.setItems(nomList);
            ppre.setItems(prenomList);
            addd.setItems(adresseList);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/

        try {
            List<commande> co = cs.recuperer();
            ObservableList<commande> ob = FXCollections.observableList(co);
            listview.setItems(ob);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void recherchecommande(KeyEvent event) {
// Create a new FilteredList with the original list as the source
        FilteredList<commande> filter = new FilteredList<>(listview.getItems(), ev -> true);

        // Add a listener on the text property of the search field to update the predicate of the FilteredList
        re.textProperty().addListener((observable, oldValue, searchText) -> {
            // Update the predicate based on the search text
            filter.setPredicate(commande -> {
                if (searchText == null || searchText.isEmpty()) {
                    return true; // Show all items when the filter text is empty.
                }

                String lowerCaseFilter = searchText.toLowerCase();

                if (commande.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches nom.
                }

                return false; // Does not match.
            });
        });

        // Create a new SortedList and attach it to the FilteredList
        SortedList<commande> sortedList = new SortedList<>(filter);

        // Set the comparator for the sorted list
        sortedList.setComparator(Comparator.comparing(commande::getNom));

        // Set the items of the ListView to the sorted list
        listview.setItems(sortedList);

        // Refresh the ListView to update the filtered items
        listview.refresh();

    }




   /* void recherchecommande(KeyEvent event) {


        FilteredList<commande> filter = new FilteredList<>(list, ev -> true);


        // Add a listener on the text property of the search field to update the predicate of the FilteredList
       rc.textProperty().addListener((observable, oldValue, newValue) -> {
           filter.setPredicate(commande -> {
               if (newValue == null || newValue.isEmpty()) {
                   return true; // Show all items when the filter text is empty.
               }

               String lowerCaseFilter = newValue.toLowerCase();

               if (commande.getNom().toLowerCase().contains(lowerCaseFilter)) {
                   return true; // Filter matches nom.
               }

               return false; // Does not match.
           });
       });

        // Create a SortedList from the FilteredList to sort the results
        SortedList<commande> sort = new SortedList<>(filter);
        // Bind the comparator of the SortedList with the comparator of the TableView
        sort.comparatorProperty().bind(tab.comparatorProperty());
        // Set the items of the TableView to the SortedList
        tab.setItems(sort);

    }*/
    /*@FXML
    void trier(ActionEvent event) throws SQLException {
        String selected = sc.getSelectionModel().getSelectedItem();
         List<commande> temp;
        temp = null;
        if (selected.equals("Trier par Nom asc ")) {
            temp = css.tri_par_nom();

        } else if (selected.equals("Trier par Nom des")) {
            temp = css.tri_par_nom2();

        }
        // Mettez à jour la liste observable utilisée par votre TableView (par exemple, 'list')
        ObservableList<commande> updatedList = FXCollections.observableArrayList(temp);

        // Mettre à jour la TableView
        tab.setItems(updatedList);

    }*/
    @FXML
    void triasc(ActionEvent event) {
        List<commande> temp;
        temp = null;
        try {
            temp = css.tri_par_nom();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<commande> updatedList = FXCollections.observableArrayList(temp);


        //tell.setItems(updatedList);
      listview.setItems(updatedList);
    }

    @FXML
    void tridesc(ActionEvent event) {
        List<commande> temp;
        temp = null;
        try {
            temp = css.tri_par_nom2();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<commande> updatedList = FXCollections.observableArrayList(temp);


        listview.setItems(updatedList);
    }
    public void pdf(ActionEvent actionEvent) throws IOException {
        ObservableList<commande> data = listview.getItems();

        try {
            // Créez un nouveau document PDF
            PDDocument document = new PDDocument();

            // Créez une page dans le document
            PDPage page = new PDPage();
            document.addPage(page);

            // Obtenez le contenu de la page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Écrivez du texte dans le document
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);


            for (commande ca : data) {


                String ligne = "ID : " + ca.getIdc() + "     Nom : " + ca.getNom()
                        +"     tel : " + ca.getTel()+"     mail : " + ca.getMail()
                        +"    addresse : " + ca.getAddr()+"     Votre Panier : " + ca.getPani();
                contentStream.showText(ligne);

                contentStream.newLine();;
                contentStream.newLineAtOffset(0, -17);


            }

            contentStream.endText();

            // Fermez le contenu de la page
            contentStream.close();

            String outputPath ="C:/Users/user/Desktop/az/a.pdf";
            File file = new File(outputPath);
            document.save(file);

            // Fermez le document
            document.close();

            System.out.println("Le PDF a été généré avec succès.");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    void back(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applictiongui/back.fxml"));
        try {
            mt.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

