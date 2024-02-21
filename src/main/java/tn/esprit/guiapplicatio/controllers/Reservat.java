package tn.esprit.guiapplicatio.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import tn.esprit.guiapplicatio.models.Reservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.services.SeanceService;
import tn.esprit.guiapplicatio.services.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.guiapplicatio.test.HelloApplication;
import tn.esprit.guiapplicatio.utils.MyDatabase;



public class Reservat {
    @FXML
    private ComboBox<Integer> combooo;
    private final ReservationService ps = new ReservationService();
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,String> usr;

    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,String> ema;

    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,Integer> idr;
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,Integer> pho;

    @FXML
    private TextField ty;
    @FXML
    private TextField ids;
    @FXML
    private TextField id_r1;

    @FXML
    private ListView<String> reserv;
    @FXML
    private ListView<String> user;
    @FXML
    private ListView<String> emai;
    @FXML
    private ListView<String> phon;
    @FXML
    private ListView<String> id_r;


    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,String> typ;

    @FXML
    private TextField iddr;
    @FXML
    private TableView<Reservation> tableview;

    private ReservationService css =new ReservationService();
    @FXML
    private TextField em;

    @FXML
    private ComboBox<String> is;

    @FXML
    private TextField ph;

    @FXML
    private ListView<String> idse;


    @FXML
    private TextField us;

    @FXML
    void initialize() throws SQLException {

        ReservationService SeanceService = new ReservationService();

        List<Reservation> reListe = css.recuperer();

        for (Reservation re : reListe) {
            combooo.getItems().add(re.getId_reservation()); // Supposons que getId() retourne l'ID de la séance
        }


        List<Reservation> seances = null;
            try {
                seances = SeanceService.recuperer();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ObservableList<String> type_r= FXCollections.observableArrayList();
            ObservableList<String> usrz = FXCollections.observableArrayList();
            ObservableList<String> email = FXCollections.observableArrayList();
            ObservableList<String> phone = FXCollections.observableArrayList();
        ObservableList<String> id = FXCollections.observableArrayList();
        ObservableList<String> id_r1 = FXCollections.observableArrayList();
            //   ObservableList<String> iss = FXCollections.observableArrayList();

            for ( Reservation s : seances) {
                type_r.add(s.getType_reservation());
                usrz.add(s.getUsername());
                email.add(s.getEmail());
                phone.add(String.valueOf(s.getPhone()));
                     id.add(String.valueOf(s.getId_seance()));
                     id_r1.add(String.valueOf(s.getId_reservation()));
                System.out.println(s.getId_seance());
            }


            reserv.setItems(type_r);
            user.setItems(usrz);
            emai.setItems(email);
            phon.setItems(phone);
            idse.setItems(id);
            id_r.setItems(id_r1);




            setCombo();
        ReservationService cs2 = new ReservationService();

        // tableview.setItems(observableList);
        idr.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        typ.setCellValueFactory(new PropertyValueFactory<>("type_reservation"));
        usr.setCellValueFactory(new PropertyValueFactory<>("username"));
        ema.setCellValueFactory(new PropertyValueFactory<>("email"));
        pho.setCellValueFactory(new PropertyValueFactory<>("phone"));
       /* ca.setCellFactory(column -> new TableCell<Seance, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    // Charger et afficher l'image
                    Image image = new Image("file:///" + uploads + imagePath);
                    imageView.setImage(image);
                    imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                    imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                    setGraphic(imageView);
                }
            }
        });

        ca.setCellValueFactory(new PropertyValueFactory<>("categorie"));*/
        TableColumn<Reservation, Boolean> btnCol = new TableColumn<>("Action");
        btnCol.setSortable(false); // Désactive le tri sur cette colonne

        // Définissez la cellule de bouton personnalisée pour cette colonne
        btnCol.setCellFactory(col -> new ButtonCell());

        // Ajoutez la colonne de bouton à votre TableView
        tableview.getColumns().add(btnCol);
       ObservableList<Reservation> list1 = css.readCategorie();
        tableview.setItems(list1);

    }

    public void rowClicked1(MouseEvent mouseEvent) {
        int selectedIndex = reserv.getSelectionModel().getSelectedIndex();
        String ty = reserv.getItems().get(selectedIndex);
        String use = user.getItems().get(selectedIndex);
        String email = emai.getItems().get(selectedIndex);
        String pho = phon.getItems().get(selectedIndex);
       String id_s = idse.getItems().get(selectedIndex);
        String id_r11 = id_r.getItems().get(selectedIndex);

        is.setValue(ty);
        us.setText(use);
        em.setText(email);
        ph.setText(pho);
        ids.setText(id_s);
        id_r1.setText(id_r11);



    }

    public void suppt(ActionEvent actionEvent) throws SQLException {
ReservationService s=new ReservationService();
        Integer selectedId = combooo.getSelectionModel().getSelectedItem();
        s.supprimer(selectedId);
        combooo.getItems().remove(selectedId);

        //combooo.getSelectionModel().getSelectedItem();

        ObservableList<Reservation> updatedList;

// Initialisation de la liste observable
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Reservation> seancesList = css.recuperer();

// Ajout des données à la liste observable
        updatedList.addAll(seancesList);
        tableview.setItems(updatedList);
        ObservableList<String> typeSeanceList = updatedList.stream()
                .map(Reservation::getType_reservation)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String>   usernameSeanceList   = updatedList.stream()
                .map(Reservation::getUsername)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));


        ObservableList<String> emailre = updatedList.stream()
                .map(r -> String.valueOf(r.getEmail()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String> pho  = updatedList.stream()
                .map(r-> String.valueOf(r.getPhone()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        reserv.setItems(typeSeanceList);
        user.setItems(usernameSeanceList);
        emai.setItems(emailre);
        phon.setItems(pho);




    }

    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void ge_re(ActionEvent actionEvent) {
    }

    public void ge_se(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjoSeance.fxml"));
        user.getScene().setRoot(fxmlLoader.load());
        initialize();
    }

    private class ButtonCell extends TableCell<Reservation, Boolean> {
        final Button cellButton = new Button("Supprimer");

        ButtonCell() {
            cellButton.setOnAction(event -> {
                Reservation reservation = getTableView().getItems().get(getIndex());
                // Supprimer la réservation de votre liste

                try {
                    css.supprimer(reservation.getId_reservation());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                tableview.getItems().remove(reservation);
                // Si vous souhaitez également supprimer la réservation de la base de données, utilisez le service approprié ici
                // cs2.supprimer(reservation.getId_reservation());
            });
        }

        // Affiche le bouton uniquement pour les lignes non vides
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(cellButton);
            }
        }
    }

    public void setCombo() {
        List<String> type = new ArrayList<>();
        SeanceService tabC = new SeanceService();
        List<Seance> tabList = tabC.readCategorie();
        ArrayList<Seance> cats = new ArrayList<>();
        for (Seance c : tabList) {
            Seance cat = new Seance();
            cat.setType_seance(c.getType_seance());
            cat.setCategorie(c.getCategorie());
            cat.setDuree_seance(c.getDuree_seance());
            cat.setNb_maximal(c.getNb_maximal());
            cats.add(cat);
        }

        for (Seance c : cats) {
            type.add(c.getType_seance());
        }


        ObservableList<String> choices = FXCollections.observableArrayList(type);
        is.setItems(choices);
        /*is.setConverter(new StringConverter<Seance>() {
            @Override
            public String toString(Seance categorie) {
                if (categorie == null) {
                    return null;
                } else {
                    return categorie.getType_seance();
                }
            }

            @Override
            public Seance fromString(String string) {
                // Vous pouvez implémenter cette méthode si nécessaire
                return null;
            }
        });*/
    }



    @FXML
    public void ajouter(ActionEvent actionEvent) throws SQLException {

        int id_seance = 0;
        String username = us.getText();
        String cat = is.getSelectionModel().getSelectedItem();
        String email = em.getText();
        int phone = Integer.parseInt(ph.getText());
        String phoneStr = ph.getText();
       if (!username.matches("[a-zA-Z]+")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le nom d'utilisateur doit contenir uniquement des lettres.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }

// Vérification du format de l'email
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("L'email doit être au format example@gmail.com.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }

// Vérification du format du téléphone (8 chiffres)
        if (phoneStr.length() != 8 || !phoneStr.matches("[0-9]+")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de téléphone doit contenir exactement 8 chiffres.");
            alert.showAndWait();
            return; // Sortir de la méthode si la valid
        }
        String query = "SELECT id_seance FROM seance WHERE type_seance = ?";
        Connection connection = MyDatabase.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cat);
            try (ResultSet resultSet = statement.executeQuery()) {

                // Vérifier s'il y a un résultat
                if (resultSet.next()) {

                    // Récupérer l'ID de la séance
                    id_seance = resultSet.getInt("id_seance");
                    ids.setText(String.valueOf(id_seance));
                    ps.ajouter(new Reservation(cat,username,email,phone,id_seance));
                    ReservationService SeanceService = new ReservationService();
                    combooo.getItems().clear();

                    List<Reservation> reListe = css.recuperer();

                    for (Reservation re : reListe) {
                        combooo.getItems().add(re.getId_reservation()); // Supposons que getId() retourne l'ID de la séance
                    }

                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setTitle("Succes");
                    a.setContentText("Produit Ajoutée");
                    a.showAndWait();
                    System.out.println("L'ID de la séance est : " + id_seance);
                } else {
                    System.out.println("Aucune séance trouvée avec le type_seance : " + cat);
                }
            }
        }
        catch(SQLException e)

        {
            e.printStackTrace();
        }
        SeanceService cs2 = new SeanceService();
        try {
            List<tn.esprit.guiapplicatio.models.Seance> seances = cs2.recuperer();
            ObservableList<tn.esprit.guiapplicatio.models.Seance> observableList = FXCollections.observableList(seances);

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        ObservableList<Reservation> updatedList;

// Initialisation de la liste observable
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Reservation> seancesList = css.recuperer();

// Ajout des données à la liste observable
        updatedList.addAll(seancesList);
        tableview.setItems(updatedList);
        ObservableList<String> typeSeanceList = updatedList.stream()
                .map(Reservation::getType_reservation)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String>   usernameSeanceList   = updatedList.stream()
                .map(Reservation::getUsername)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));


        ObservableList<String> emailre = updatedList.stream()
                .map(s -> String.valueOf(s.getEmail()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String> pho  = updatedList.stream()
                .map(s -> String.valueOf(s.getPhone()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        reserv.setItems(typeSeanceList);
        user.setItems(usernameSeanceList);
        emai.setItems(emailre);
        phon.setItems(pho);





/*
        ps.ajouter(new Reservation(cat,username,email,phone,id_seance));
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("Succes");
        a.setContentText("Produit Ajoutée");
        a.showAndWait();*/


        idr.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        typ.setCellValueFactory(new PropertyValueFactory<>("type_reservation"));
        usr.setCellValueFactory(new PropertyValueFactory<>("username"));
        ema.setCellValueFactory(new PropertyValueFactory<>("email"));
      //  pho.setCellValueFactory(new PropertyValueFactory<>("phone"));
       /* ca.setCellFactory(column -> new TableCell<Seance, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    // Charger et afficher l'image
                    Image image = new Image("file:///" + uploads + imagePath);
                    imageView.setImage(image);
                    imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                    imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                    setGraphic(imageView);
                }
            }
        });
        ca.setCellValueFactory(new PropertyValueFactory<>("categorie"));*/
        ObservableList<Reservation> list1 = css.readCategorie();
        tableview.setItems(list1);
    }
  /*  public void setValue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Reservation selected = tableview.getSelectionModel().getSelectedItem();
        SeanceService tabC = new SeanceService();
        if (selected != null) {
            typ.setValue(selected.getType_reservation());
            tfNomProduit.setText(selected.getNomProduit());
            tfPrixProduit.setText(String.valueOf(selected.getPrix()));
            tfQuantiteProduit.setText(String.valueOf(selected.getQuantite()));
            idProduit = selected.getIdProduit();
            fn = selected.getImageProduit();
            Image im = new Image("file:" + uploads + selected.getImageProduit());
            tfImageP.setImage(im);
        }
    }*/
    @FXML
    public void rowClicked(MouseEvent mouseEvent) {



    }

    public void modii(ActionEvent actionEvent) throws SQLException {
        ObservableList<Reservation> updatedList;
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Reservation> seancesListe = css.recuperer();

// Ajout des données à la liste observable
        updatedList.addAll(seancesListe);

        /*ObservableList<Reservation> currentTableData = tableview.getItems();
        int currentAnimalId = Integer.parseInt(iddr.getText());*/
        int currentAnimalId = Integer.parseInt(id_r1.getText());
        String username = us.getText();
        //String cat = is.getSelectionModel().getSelectedItem();
        String email = em.getText();
        String phoneStr = ph.getText();
        if (!username.matches("[a-zA-Z]+")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le nom d'utilisateur doit contenir uniquement des lettres.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }

// Vérification du format de l'email
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("L'email doit être au format example@gmail.com.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }

// Vérification du format du téléphone (8 chiffres)
        if (phoneStr.length() != 8 || !phoneStr.matches("[0-9]+")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de téléphone doit contenir exactement 8 chiffres.");
            alert.showAndWait();
            return; // Sortir de la méthode si la valid
        }


        for (Reservation animal : updatedList) {
            if(animal.getId_reservation() == currentAnimalId) {
                animal.setType_reservation(is.getSelectionModel().getSelectedItem());
                animal.setUsername(us.getText());
                animal.setEmail(em.getText());
                int phone = Integer.parseInt(ph.getText());
                animal.setPhone(phone);
                animal.setId_reservation(currentAnimalId);
                int idss = Integer.parseInt(ids.getText());
                animal.setId_seance(idss);
                ReservationService service = new ReservationService();
                service.modifier(animal);


            }
        }




// Initialisation de la liste observable
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Reservation> seancesList = css.recuperer();

// Ajout des données à la liste observable
        updatedList.addAll(seancesList);
        tableview.setItems(updatedList);
        ObservableList<String> typeSeanceList = updatedList.stream()
                .map(Reservation::getType_reservation)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String>   usernameSeanceList   = updatedList.stream()
                .map(Reservation::getUsername)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));


        ObservableList<String> emailre = updatedList.stream()
                .map(s -> String.valueOf(s.getEmail()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String> pho  = updatedList.stream()
                .map(s -> String.valueOf(s.getPhone()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        reserv.setItems(typeSeanceList);
        user.setItems(usernameSeanceList);
        emai.setItems(emailre);
        phon.setItems(pho);





    }


}
