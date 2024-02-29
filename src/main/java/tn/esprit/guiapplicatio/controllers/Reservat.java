package tn.esprit.guiapplicatio.controllers;
import javax.mail.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.guiapplicatio.Cellule.resevationcell;
import tn.esprit.guiapplicatio.models.Reservation;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

import java.util.Properties;
import java.util.stream.Collectors;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.guiapplicatio.test.HelloApplication;
import tn.esprit.guiapplicatio.utils.MyDatabase;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.Notification;


public class Reservat {
    @FXML
    private ComboBox<Integer> combooo;

    @FXML
    private TextField re;
    private final ReservationService ps = new ReservationService();
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,String> usr;
    @FXML
    private ListView<Reservation> rese;

    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,String> ema;

    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,Integer> idr;
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance,Integer> pho;
    @FXML
    private DatePicker date_debut;

    @FXML
    private DatePicker date_fin;

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
    private String username = "fathimaddeh88@gmail.com";
    private String password = "wxnfnrqwjjcjzjby";
    private ReservationService css =new ReservationService();
    @FXML
    private TextField em;

    @FXML
    private ComboBox<String> is;

    @FXML
    private TextField ph;

    @FXML
    private ListView<String> idse;
    Connection connection=MyDatabase.getInstance().getConnection();
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
        ReservationService programService = new ReservationService();
        try {
            List<Reservation> programs = programService.recuperer();
            rese.setCellFactory(param -> new resevationcell());
            ObservableList<Reservation> observableList = FXCollections.observableList(programs);
            rese.setItems(observableList);

            // Gestionnaire d'événements pour la sélection d'un élément dans la liste
            rese.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) { // Vérifie un simple clic
                    Reservation selectedreservation = rese.getSelectionModel().getSelectedItem();
                    if (selectedreservation != null) {

                        // Remplir les champs de texte avec les coordonnées du programme sélectionné
                        is.getSelectionModel().select(selectedreservation.getType_reservation());
                        us.setText(selectedreservation.getUsername());
                        em.setText(selectedreservation.getEmail());
                        ph.setText(String.valueOf(selectedreservation.getPhone()));
                        ids.setText(String.valueOf(selectedreservation.getId_seance()));
                        iddr.setText(String.valueOf(selectedreservation.getId_reservation()));



                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }



    }

    public void rowClicked1(MouseEvent mouseEvent) throws SQLException {
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
        //s.supprimer(selectedId);
        combooo.getItems().remove(selectedId);
        ReservationService reservationService = new ReservationService();
        Reservation reserv = new Reservation();
        reserv.setId_reservation(Integer.parseInt(iddr.getText()));
        try {
            reservationService.supprimer(reserv.getId_reservation());
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText(" supprimée");
            alert.showAndWait();
            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

        //combooo.getSelectionModel().getSelectedItem();




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

    public void mail(ActionEvent actionEvent) {
      // envoyer();





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
    @FXML
    void  rechercheseance(KeyEvent event) {

        FilteredList<Reservation> filter = new FilteredList<>(rese.getItems(), ev -> true);

        // Add a listener on the text property of the search field to update the predicate of the FilteredList
        re.textProperty().addListener((observable, oldValue, searchText) -> {
            // Update the predicate based on the search text
            filter.setPredicate(reservation -> {
                if (searchText == null || searchText.isEmpty()) {
                    return true; // Show all items when the filter text is empty.
                }

                String lowerCaseFilter = searchText.toLowerCase();

                if (reservation.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches nom.
                }

                return false; // Does not match.
            });
        });

        // Create a new SortedList and attach it to the FilteredList
        SortedList<Reservation> sortedList = new SortedList<>(filter);

        // Set the comparator for the sorted list
        sortedList.setComparator(Comparator.comparing(Reservation::getUsername));

        // Set the items of the ListView to the sorted list
        rese.setItems(sortedList);

        // Refresh the ListView to update the filtered items
        rese.refresh();



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
   //     int phone = Integer.parseInt(ph.getText());
        String phoneStr = ph.getText();
        if (username.isEmpty() || cat.isEmpty() ||email.isEmpty()||phoneStr.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            // Sortir de la méthode si un champ
            if (username.isEmpty()) {
                alert.setTitle("Erreur");
                alert.setContentText("champs username est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ
            }
            if (cat.isEmpty()) {
                alert.setTitle("Erreur");
                alert.setContentText("champs type est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ
            }
            if (email.isEmpty()) {
                alert.setTitle("Erreur");
                alert.setContentText("champs email est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ


            }
            if (phoneStr.isEmpty()) {
                alert.setTitle("Erreur");
                alert.setContentText("champs phone est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ


            }
return;
        }
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
        int phone = Integer.parseInt(ph.getText());
        String query = "SELECT id_seance,nb_maximal FROM seance WHERE type_seance = ?";
        Connection connection = MyDatabase.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cat);
            try (ResultSet resultSet = statement.executeQuery()) {

                // Vérifier s'il y a un résultat
                if (resultSet.next()) {
                    int nombreReservations=0;
                    int nb_maximale=resultSet.getInt("nb_maximal");
                    // Récupérer l'ID de la séance
                    id_seance = resultSet.getInt("id_seance");
                    String sql = "SELECT * FROM reservation WHERE id_seance = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id_seance);
                    ResultSet resultSe = preparedStatement.executeQuery();

                    while(resultSe.next()) {
                        nombreReservations++;
                    }
                    System.out.println(nombreReservations);
                        if(nombreReservations > nb_maximale-1)
                        {
                            System.out.println(nombreReservations);
                            System.out.println(nombreReservations);
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur de saisie");
                            alert.setHeaderText(null);
                            alert.setContentText("Le nombre maximale de la reservation est "+String.valueOf(nb_maximale));
                            alert.showAndWait();
                            return; // Sortir de la méthode si la valid


                        }




                    ids.setText(String.valueOf(id_seance));
                    Image image=new Image("C:/Users/Lenovo/IdeaProjects/guiapplicatio/img/avatar.png");
                    Notifications notification=Notifications.create();
                    notification.graphic(new ImageView(image));
                    notification.text(" thank you for your reservation");
                    notification.title("success message");
                    notification.hideAfter(Duration.seconds(4));
                    notification.show();

                    ps.ajouter(new Reservation(cat,username,email,phone,id_seance));

                    ReservationService SeanceService = new ReservationService();
                    combooo.getItems().clear();

                    List<Reservation> reListe = css.recuperer();

                    for (Reservation re : reListe) {
                        combooo.getItems().add(re.getId_reservation()); // Supposons que getId() retourne l'ID de la séance
                    }

                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Succes");
                    a.setContentText("Reservation Ajoutée");
                    a.showAndWait();
                    System.out.println("L'ID de la séance est : " + id_seance);
                    envoyer(email);
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



initialize();


/*
        ps.ajouter(new Reservation(cat,username,email,phone,id_seance));
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("Succes");
        a.setContentText("Produit Ajoutée");
        a.showAndWait();*/



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




        public void envoyer(String b) {
// Etape 1 : Création de la session
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.ssl.ciphersuites", "TLS_AES_256_GCM_SHA384");
            props.put("mail.smtp.socketFactory.port","465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("fathimaddeh88@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(b));
                message.setSubject("SUCCESSFUL RESERVATION");
                message.setText("votre reservation a ete faite avec succes");

                // Enable debugging
                session.setDebug(true);

                Transport.send(message);
                System.out.println("Message envoyé avec succès");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        //Etape 4 : Tester la méthode



    public void modii(ActionEvent actionEvent) throws SQLException {
       ObservableList<Reservation> updatedList;
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Reservation> seancesListe = css.recuperer();

// Ajout des données à la liste observable
        updatedList.addAll(seancesListe);


        int currentAnimalId = Integer.parseInt(iddr.getText());
        String username = us.getText();
        System.out.println(username);
        String cat = is.getSelectionModel().getSelectedItem();
        int id_seancee=0 ;
        String query = "SELECT id_seance FROM seance WHERE type_seance = ?";
        Connection connection = MyDatabase.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cat);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("feeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                    id_seancee = resultSet.getInt("id_seance");
                    System.out.println(id_seancee);

                }
            }
        }

        String email = em.getText();
        String phoneStr = ph.getText();
        if (username.isEmpty() || email.isEmpty() || phoneStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            // Sortir de la méthode si un champ
            if (username.isEmpty()) {
                alert.setTitle("Erreur");
                alert.setContentText("champs username est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ
            }

            if (email.isEmpty()) {
                alert.setTitle("Erreur");
                alert.setContentText("champs email est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ


            }
            if (phoneStr.isEmpty()) {
                alert.setTitle("Erreur");
                alert.setContentText("champs phone est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ


            }

        }

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
            if (animal.getId_reservation() == currentAnimalId) {
              animal.setId_seance(id_seancee);
                    animal.setType_reservation(is.getSelectionModel().getSelectedItem());
                    animal.setUsername(us.getText());
                    animal.setEmail(em.getText());
                    int phone = Integer.parseInt(ph.getText());
                    animal.setPhone(phone);
                    animal.setId_reservation(currentAnimalId);
                    int idss = Integer.parseInt(ids.getText());
                  //  animal.setId_seance(idss);
                    ReservationService service = new ReservationService();
                    service.modifier(animal);


                }
            }


// Initialisation de la liste observable

initialize();

    }}