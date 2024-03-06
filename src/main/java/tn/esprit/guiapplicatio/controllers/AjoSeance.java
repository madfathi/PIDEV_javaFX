package tn.esprit.guiapplicatio.controllers;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.*;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.controlsfx.control.Notifications;
import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.services.SeanceService;
import javafx.scene.image.Image;
import javafx.scene.Parent;


import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.guiapplicatio.test.HelloApplication;
import tn.esprit.guiapplicatio.utils.MyDatabase;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class AjoSeance {
    public Connection conn = MyDatabase.getInstance().getConnection();
    @FXML
    private ListView<String> idsf;
    private PreparedStatement pst;
    private Statement statement;
    String filepath = null, filename = null, fn = null;
    @FXML
    private PieChart pieChart;
    @FXML
    private ListView<String> nbco;
    @FXML
    private ListView<String> typeco;
    @FXML
    private ListView<String> dureeco;
    @FXML
    private ListView<String> categorieco;
    @FXML
    private TextField searchField;
    @FXML
    private PieChart stat;
    @FXML
    private ListView<String> ides;
    @FXML
    private Label erreurLabel;
    @FXML
    private TextField suppr;
    @FXML
    private ComboBox<Integer> cobos;
    private String username = "fathimaddeh88@gmail.com";
    private String password = "wxnfnrqwjjcjzjby";
    int j = 0;
    @FXML
    private Button btnImageC;
    @FXML
    private Label nerreurLabel;
    @FXML
    private Label derreurLabel;

    @FXML
    private TextField searchTextField;
    @FXML
    private ListView<?> suppra;

    @FXML

    private List<Seance> temp;

    private SeanceService css = new SeanceService();
    @FXML
    private ComboBox<String> sortCategorieBox;

    @FXML
    private TextField RechercherCategorie;
    @FXML
    private TableColumn<Seance, Integer> id;
    @FXML
    private TableColumn<Seance, String> ca;
    ObservableList<String> dure = FXCollections.observableArrayList();
    @FXML
    private TextField categorieTF;

    @FXML
    private Label dashboardText;

    @FXML
    private ImageView tfImage;
    @FXML
    private TableColumn<Seance, Integer> du;

    @FXML
    private TextField dureeTF;

    @FXML
    private TextField searchField1;
    @FXML
    private TableColumn<Seance, Integer> nb;
    Seance seance = new Seance();
    @FXML
    private TextField nbTF;
    @FXML
    private ListView<String> date;
    @FXML
    private TableView<Seance> tableview;
    FileChooser fc = new FileChooser();
    @FXML
    private TableColumn<Seance, String> ty;
    ObservableList<String> duree = FXCollections.observableArrayList();
    String to;
    String co;
    String Do;
    String No;

    @FXML
    private TextField typeTF;
    @FXML
    private TableColumn<Seance, String> editCOL;
    SeanceService seanceService = new SeanceService();
    List<Seance> seances = seanceService.readCategorie();
    ObservableList<String> items = FXCollections.observableArrayList();

    int i = 0;
    ObservableList<Seance> list = FXCollections.observableArrayList();

    ObservableList<Seance> datalist;
    String uploads = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/img/";

    public void setTypee(String type) {
        to = type;
    }

    public void setIde(String ide) {
        id.setText(ide);
    }

    public void setCategoriee(String categorie) {
        co = categorie;
    }

    public void setDuree(String duree) {
        Do = duree;
    }

    public void setNbMaximale(String nbMaximal) {
        No = nbMaximal;

    }


    private ObservableList<Seance> originalList;
    private FilteredList<Seance> filteredList;
// Ajouter la colonne à votre TableVieweditCOL;

    public void updateTableView() throws SQLException {
        SeanceService SeanceService = new SeanceService();
        try {
            List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> type = FXCollections.observableArrayList();
            ObservableList<String> categorie = FXCollections.observableArrayList();
            ObservableList<String> duree = FXCollections.observableArrayList();
            ObservableList<String> nb = FXCollections.observableArrayList();
            ObservableList<String> idf = FXCollections.observableArrayList();
            //ObservableList<String> iss = FXCollections.observableArrayList();

            for (Seance s : seances) {
                type.add(s.getType_seance());
                categorie.add(s.getCategorie());
                duree.add(s.getDuree_seance());
                nb.add(String.valueOf(s.getNb_maximal()));
                idf.add(String.valueOf(s.getId_seance()));

            }


            typeco.setItems(type);
            dureeco.setItems(duree);
            nbco.setItems(nb);
            idsf.setItems(idf);
            // ides.setItems(iss);


            // Afficher les images dans la ListView
            categorieco.setCellFactory(list -> new ListCell<String>() {
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


        List<Seance> reListe = css.recuperer();

        for (Seance re : reListe) {
            cobos.getItems().add(re.getId_seance()); // Supposons que getId() retourne l'ID de la séance
        }
    }

    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void supprimerseance(ActionEvent actionEvent) {
        String idUtilisateurStr = suppr.getText();

        // Vérifier si l'ID est un entier valide
        try {
            int idUtilisateur = Integer.parseInt(idUtilisateurStr);

            // Appeler la méthode de service pour supprimer l'utilisateur par son ID
            css.supprimer(idUtilisateur);

            addDataToChart();
            // Vous pouvez ajouter ici des actions supplémentaires après la suppression de l'utilisateur
        } catch (NumberFormatException e) {
            System.err.println("L'ID de l'utilisateur doit être un entier valide.");
            // Gérer l'erreur si l'utilisateur entre un ID non valide (par exemple, afficher un message d'erreur à l'utilisateur)
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            // Gérer l'erreur (afficher un message à l'utilisateur, journaliser l'erreur, etc.)
        }

        // Afficher un message de confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("L'utilisateur a été supprimé avec succès.");

        alert.showAndWait();

        SeanceService SeanceService = new SeanceService();
        try {
            List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> typez = FXCollections.observableArrayList();
            ObservableList<String> categoriee = FXCollections.observableArrayList();
            ObservableList<String> dureee = FXCollections.observableArrayList();
            ObservableList<String> nbe = FXCollections.observableArrayList();

            for (Seance s : seances) {
                typez.add(s.getType_seance());
                categoriee.add(s.getCategorie());
                dureee.add(String.valueOf(s.getDuree_seance()));
                nbe.add(String.valueOf(s.getNb_maximal()));
            }


            typeco.setItems(typez);
            dureeco.setItems(dureee);
            nbco.setItems(nbe);


            // Afficher les images dans la ListView
            categorieco.setCellFactory(list -> new ListCell<String>() {
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        addDataToChart();

    }

    public void modifiee(ActionEvent actionEvent) {


    }

    public void rowclicked(MouseEvent mouseEvent) throws IOException {
       /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        typeTF.getScene().setRoot(fxmlLoader.load());
        Seance clickedseance = tableview.getSelectionModel().getSelectedItem();
        typeTF.setText(String.valueOf(clickedseance.getType_seance()));
        categorieTF.setText(String.valueOf(clickedseance.getCategorie()));
        dureeTF.setText(String.valueOf(clickedseance.getDuree_seance()));
        nbTF.setText(String.valueOf(clickedseance.getNb_maximal()));
*/
      /*  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        Parent root = fxmlLoader.load();
        ModifierSeance controller = fxmlLoader.getController();
        Seance clickedSeance = tableview.getSelectionModel().getSelectedItem();
        ObservableList<Seance> Seances = tableview.getItems();
        controller.setSeances(Seances);
        controller.setId(String.valueOf(clickedSeance.getId_seance()));
        controller.setType(String.valueOf(clickedSeance.getType_seance()));
        controller.setCategorie(String.valueOf(clickedSeance.getCategorie()));
        controller.setDuree(String.valueOf(clickedSeance.getDuree_seance()));
        controller.setNbMaximal(String.valueOf(clickedSeance.getNb_maximal()));

        typeTF.getScene().setRoot(root);
*/

    }

    public void row(MouseEvent mouseEvent) throws IOException, SQLException {
      /*   ObservableList<Seance> seances = FXCollections.observableArrayList();
SeanceService SeanceService=new SeanceService();
        List<Seance> seancess = SeanceService.recuperer();
        ObservableList<Seance> seancesss = FXCollections.observableArrayList(seancess);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        Parent root = fxmlLoader.load();
        ModifierSeance controller = fxmlLoader.getController();
        String clickedSeance = typeco.getSelectionModel().getSelectedItem();
        System.out.println("vcdvdv");
        Optional<Seance> seanceOptional = seancess.stream()
                .filter(seance -> seance.getType_seance().contains(clickedSeance))
                .findFirst();


        // Vérifier si la séance a été trouvée
        if (seanceOptional.isPresent()) {
            Seance seance = seanceOptional.get();
            controller.setSeances(seancesss);
            // Mettre à jour les autres attributs dans le contrôleur ModifierSeance
            controller.setId(String.valueOf(seance.getId_seance()));
            controller.setType(seance.getType_seance());
            controller.setCategorie(seance.getCategorie());
            controller.setDuree(String.valueOf(seance.getDuree_seance()));
            controller.setNbMaximal(String.valueOf(seance.getNb_maximal()));

            // Remplacer la scène actuelle par la scène de modification
            typeco.getScene().setRoot(root);
        } else {
            System.out.println("Aucune séance trouvée avec l'ID : " );
        }

        typeco.getScene().setRoot(root);



*/

    }

    public void row1(MouseEvent mouseEvent) throws SQLException, IOException {

     /*   ObservableList<Seance> seances = FXCollections.observableArrayList();
        SeanceService SeanceService = new SeanceService();
        List<Seance> seancess = SeanceService.recuperer();
        ObservableList<Seance> seancesss = FXCollections.observableArrayList(seancess);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        Parent root = fxmlLoader.load();
        ModifierSeance controller = fxmlLoader.getController();
        String clickedSeance = typeco.getSelectionModel().getSelectedItem();
        System.out.println("vcdvdv");
        Optional<Seance> seanceOptional = seancess.stream()
                .filter(seance -> seance.getType_seance().contains(clickedSeance))
                .findFirst();


        // Vérifier si la séance a été trouvée
        if (seanceOptional.isPresent()) {
            Seance seance = seanceOptional.get();
            controller.setSeances(seancesss);
            // Mettre à jour les autres attributs dans le contrôleur ModifierSeance
            controller.setId(String.valueOf(seance.getId_seance()));
            controller.setType(seance.getType_seance());
            controller.setCategorie(seance.getCategorie());
            controller.setDuree(String.valueOf(seance.getDuree_seance()));
            controller.setNbMaximal(String.valueOf(seance.getNb_maximal()));

            // Remplacer la scène actuelle par la scène de modification

            typeco.getScene().setRoot(root);
        } else {
            System.out.println("Aucune séance trouvée avec l'ID : ");
        }

        typeco.getScene().setRoot(root);

        addDataToChart();
    }

    public void refreshing(ActionEvent actionEvent) {
      /*  categorieco.setCellFactory(list -> new ListCell<String>() {
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
*/
    }

    public void ge_re(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Ajouterprogram.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());


    }

    public void excel(ActionEvent actionEvent) throws SQLException, IOException {
        String req = "SELECT id_seance,type_seance,duree_seance,nb_maximal FROM seance ";
        statement = conn.createStatement();
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(req);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Détails Seance");
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("id_Seance");
        header.createCell(1).setCellValue("type_Seance");
        header.createCell(2).setCellValue("duree_Seance");
        header.createCell(3).setCellValue("nb_maximal");
        int index = 1;
        while (rs.next()) {
            HSSFRow row = sheet.createRow(index);

            row.createCell(0).setCellValue(rs.getInt("id_seance"));
            row.createCell(1).setCellValue(rs.getString("type_seance"));
            row.createCell(3).setCellValue(rs.getString("nb_maximal"));
            index++;
        }
        FileOutputStream file = new FileOutputStream("C:/Users/Lenovo/IdeaProjects/guiapplicatio/src/PDF/projet.xls");
        wb.write(file);
        file.close();

        JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");

        pst.close();
        rs.close();

    }

    public void go(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Ajo.fxml"));
        Parent root = fxmlLoader.load();

        typeco.getScene().setRoot(root);


    }


    @FXML
    public void ajoSeance(ActionEvent actionEvent) throws SQLException {


    }

    public int getTotalSeanceCount() {
        try {
            String query = "SELECT COUNT(*) FROM seance";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addDataToChart() {
        // Efface les données existantes

        pieChart.getData().clear();


        // Récupère les statistiques des prix
        int seanceEntre10000Et20000 = getCountByRange(1, 5);
        int seanceEntre30000Et50000 = getCountByRange(5, 10);
        int totalProduits = getTotalSeanceCount();

        // Ajoute les données au PieChart
        PieChart.Data data1 = new PieChart.Data("seance entre 1 et 5", seanceEntre10000Et20000);
        PieChart.Data data2 = new PieChart.Data("seance entre 5 et 10", seanceEntre30000Et50000);
        PieChart.Data data3 = new PieChart.Data("Autres seances", totalProduits - seanceEntre10000Et20000 - seanceEntre30000Et50000);
        pieChart.getData().addAll(data1, data2, data3);
        //  addLabelsToChart(pieChart);

        // Ajoute les annotations

        //addArrowLabel(pieChart, data3, totalProduits - seanceEntre10000Et20000 - seanceEntre30000Et50000, totalProduits, (Pane) pieChart.getParent());

    }

    /*  private void addLabelsToChart(PieChart pieChart) {
          ObservableList<PieChart.Data> data = pieChart.getData();
          for (PieChart.Data slice : data) {
              int value = (int) slice.getPieValue();
              int total = getTotalSeanceCount(); // Assuming this method returns the total count of sessions

              double percentage = (double) value / total * 100.0;
              String text = String.format("%.1f%%", percentage);
              Label label = new Label(text);
              label.getStyleClass().add("pie-label");

              label.setTranslateX(pieChart.getTranslateX() + pieChart.getRadius() * Math.cos(Math.toRadians(slice.getStartAngle() + slice.getAngle() / 2)));
              label.setTranslateY(pieChart.getTranslateY() + pieChart.getRadius() * Math.sin(Math.toRadians(slice.getStartAngle() + slice.getAngle() / 2)));

              pieChart.getScene().getRoot().getChildrenUnmodifiable().add(label);
          }
      }
  */
    public int getCountByRange(int minPrice, int maxPrice) {
        try {
            String query = "SELECT COUNT(*) FROM seance WHERE nb_maximal BETWEEN ? AND ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, minPrice);
            pst.setInt(2, maxPrice);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void updateDuree(ObservableList<String> duree1) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            for (int i = 0; i < duree1.size(); i++) {

                String[] parts = duree1.get(i).split(", ");
                int days = Integer.parseInt(parts[0].split(" ")[0]);
                int hours = Integer.parseInt(parts[1].split(" ")[0]);
                int minutes = Integer.parseInt(parts[2].split(" ")[0]);
                int seconds = Integer.parseInt(parts[3].split(" ")[0]);

                if (seconds > 0) {
                    seconds--;
                } else {
                    seconds = 59;
                    if (minutes > 0) {
                        minutes--;
                    } else {
                        minutes = 59;
                        if (hours > 0) {
                            hours--;
                        } else {
                            hours = 23;
                            if (days > 0) {
                                days--;
                            } else {
                                // La durée est écoulée, vous pouvez prendre une action appropriée ici
                            }
                        }
                    }
                }

                String newDuration = String.format("%d jours, %d heures, %d minutes, %d secondes", days, hours, minutes, seconds);

                duree1.set(i, newDuration);
                String ty = typeco.getItems().get(i);
                String duu = dureeco.getItems().get(i);
                String nbc = nbco.getItems().get(i);
                String ca = categorieco.getItems().get(i);
                String idco = idsf.getItems().get(i);
                seance.setDuree_seance(duu);
                seance.setType_seance(ty);
                seance.setCategorie(ca);
                int nbm = Integer.parseInt(nbc);
                seance.setNb_maximal(nbm);
                seance.setId_seance(Integer.parseInt(idco));
                if (seconds == 0 && minutes == 0 && hours == 0 && days == 0) {

                    try {
                        //envoyer("maddeh.fathi@esprit.tn");
                        String query = "SELECT * FROM reservation WHERE id_seance = ?";
                        pst = conn.prepareStatement(query);
                        pst.setInt(1, Integer.parseInt(idsf.getItems().get(i)));
                        ResultSet ResultSet = pst.executeQuery();

                        if (ResultSet.next()) {
                            String mail = ResultSet.getString("email");
                            envoyer(mail);
                        }
                        Image image = new Image("C:/Users/Lenovo/IdeaProjects/guiapplicatio/img/avatar.png");
                        Notifications notification = Notifications.create();
                        notification.graphic(new ImageView(image));
                        notification.text("seance supprimee");
                        notification.title("success message");
                        notification.hideAfter(Duration.seconds(4));
                        notification.show();
                        css.supprimer(Integer.parseInt(idco));





                       /* alert.setHeaderText(null);
                        alert.setContentText("La Seance "+ ty +" n'est plus disponible");
                        alert.showAndWait();*/
                        categorieco.getItems().remove(i);
                        nbco.getItems().remove(i);
                        duree1.remove(i);
                        // Supprimer les éléments correspondants des autres listes
                        typeco.getItems().remove(i);
                        dureeco.getItems().remove(i);
                        nbco.getItems().remove(i);

                        categorieco.getItems().remove(i);
                        ObservableList<String> categorie = FXCollections.observableArrayList();
                        for (Seance s : seances) {

                            categorie.add(s.getCategorie());

                        }
                        categorieco.setItems(categorie);


                        idsf.getItems().remove(i);

                        css.supprimer(Integer.parseInt(idco));

                        //  envoyer("maddeh.fathi@esprit.tn");

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Séance supprimée");
                        alert2.setHeaderText(null);
                        alert2.setContentText("La séance " + ty + " a été supprimée.");

                        alert2.showAndWait();
                        return;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
                try {
                    css.modifier2(seance);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
            dureeco.setItems(duree1);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    @FXML
    void initialize() throws SQLException {
       // addToPieChartData(pieChartData, repas.getType());

        addDataToChart();
        updateTableView();
        SeanceService SeanceService = new SeanceService();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        try {
            List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> type = FXCollections.observableArrayList();
            ObservableList<String> categorie = FXCollections.observableArrayList();
            ObservableList<String> duree1 = FXCollections.observableArrayList();
            ObservableList<String> nb = FXCollections.observableArrayList();
            ObservableList<String> idf = FXCollections.observableArrayList();
            ObservableList<String> da = FXCollections.observableArrayList();

            //   ObservableList<String> iss = FXCollections.observableArrayList();
            // Creating ObservableList for PieChart


            for (Seance s : seances) {
                type.add(s.getType_seance());
                categorie.add(s.getCategorie());

                nb.add(String.valueOf(s.getNb_maximal()));
                idf.add(String.valueOf(s.getId_seance()));
                da.add(String.valueOf(s.getDate_fin()));
                duree1.add(s.getDuree_seance());


                addToPieChartData(pieChartData, String.valueOf(s.getNb_maximal()));
            }

            updateDuree(duree1);
            typeco.setItems(type);
            date.setItems(da);
            nbco.setItems(nb);
            idsf.setItems(idf);
            //ides.setItems(iss);


            // Afficher les images dans la ListView
            categorieco.setCellFactory(list -> new ListCell<String>() {
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ObservableList<Seance> updatedList;
        stat.setData(pieChartData);

// Initialisation de la liste observable
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Seance> seancesList = css.recuperer();

// Ajout des données à la liste observable
        updatedList.addAll(seancesList);


        ObservableList<String> categorieSeanceList = updatedList.stream()
                .map(Seance::getCategorie)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));


        categorieco.setItems(categorieSeanceList);


        ObservableList<String> list = FXCollections.observableArrayList("Trier par Nom ↑", "Trier par Nom ↓");

        sortCategorieBox.setItems(list);


    }


    public void refresh(ActionEvent actionEvent) throws IOException {

    }


    @FXML
    void addSeanceSearch(KeyEvent event) throws SQLException {
        FilteredList<String> filteredList = new FilteredList<>(items);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(item -> {
                // Si le champ de recherche est vide, afficher tous les éléments
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convertir la valeur en minuscules pour une recherche insensible à la casse
                String lowerCaseFilter = newValue.toLowerCase();
                System.out.println(lowerCaseFilter);
                // Comparer le type de séance avec le texte de recherche
                if (item.toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Correspondance trouvée
                }

                return false; // Pas de correspondance trouvée
            });
        });

// Attachez la liste filtrée à la ListView
      /*  typeco.setItems(filteredList);

        ObservableList<Seance> seancesFiltrees = FXCollections.observableArrayList();
        String typeSeance = searchField.getText();

        // Utilisez le type_seance filtré pour récupérer les séances correspondantes dans la base de données
        String query = "SELECT * FROM seance WHERE type_seance = ?";
        Connection connection = MyDatabase.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query) ;

            // Remplacer le placeholder (?) dans la requête avec la valeur du champ de recherche
            preparedStatement.setString(1, typeSeance);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Traiter les résultats de la requête
                while (resultSet.next()) {
                    // Récupérer les valeurs des colonnes pour chaque séance
                    String categorie = resultSet.getString("categorie");
                    String type = resultSet.getString("type_seance");
                    String duree = resultSet.getString("duree_seance");
                    int nbMaximal = resultSet.getInt("nb_maximal");
                    Date dateee = resultSet.getDate("date_fin");

                    // Créer une nouvelle instance de Seance avec les valeurs récupérées et l'ajouter à la liste à afficher
                    Seance seance = new Seance(duree, nbMaximal,type,categorie,dateee);
                    seancesFiltrees.add(seance);
                }
            }

           //List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> type = FXCollections.observableArrayList();
            ObservableList<String> categorie = FXCollections.observableArrayList();
            ObservableList<String> duree = FXCollections.observableArrayList();
            ObservableList<String> nb = FXCollections.observableArrayList();
            //   ObservableList<String> iss = FXCollections.observableArrayList();

            for (Seance s : seancesFiltrees) {
                type.add(s.getType_seance());
                categorie.add(s.getCategorie());
                duree.add(String.valueOf(s.getDuree_seance()));
                nb.add(String.valueOf(s.getNb_maximal()));
                //     iss.add(String.valueOf(s.getId_seance()));

            }


            typeco.setItems(type);
            dureeco.setItems(duree);
            nbco.setItems(nb);
            //ides.setItems(iss);


*/


    }


    public void sortCategorie(ActionEvent actionEvent) {
        String selected = sortCategorieBox.getSelectionModel().getSelectedItem();
        if (selected.equals("Trier par Nom ↑")) {
            temp = css.sortCategorieAsc();

        } else if (selected.equals("Trier par Nom ↓")) {
            temp = css.sortCategorieDesc();

        }

        ObservableList<Seance> updatedList = FXCollections.observableArrayList(temp);


        ObservableList<String> typeSeanceList = updatedList.stream()
                .map(Seance::getType_seance)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String> categorieSeanceList = updatedList.stream()
                .map(Seance::getCategorie)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));


        ObservableList<String> duree_Senace = updatedList.stream()
                .map(Seance::getDuree_seance)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String> nb_maximal = updatedList.stream()
                .map(s -> String.valueOf(s.getNb_maximal()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        typeco.setItems(typeSeanceList);
        categorieco.setItems(categorieSeanceList);
        dureeco.setItems(duree_Senace);
        nbco.setItems(nb_maximal);

    }

    public void btnImageC(ActionEvent actionEvent) throws IOException {
        File file = fc.showOpenDialog(null);
        // Shows a new file open dialog.
        if (file != null) {
            // URI that represents this abstract pathname
            tfImage.setImage(new Image(file.toURI().toString()));

            filename = file.getName();
            filepath = file.getAbsolutePath();

            fn = filename;

            FileChannel source = new FileInputStream(filepath).getChannel();
            FileChannel dest = new FileOutputStream(uploads + filename).getChannel();
            dest.transferFrom(source, 0, source.size());
        } else {
            System.out.println("Fichier invalide!");
        }
    }

    public void supprimer(ActionEvent actionEvent) throws SQLException {
        int selected=typeco.getSelectionModel().getSelectedIndex();
        String catego = categorieco.getItems().get(selected);
        String Dispo =dureeco.getItems().get(selected);
        String id= idsf.getItems().get(selected);


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
                    css.supprimer(Integer.parseInt(id));
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

    public void pdf(ActionEvent actionEvent) {
        ObservableList<String> data = typeco.getItems();
        ObservableList<String> data2 = nbco.getItems();
        ObservableList<String> data3 = dureeco.getItems();
        int count = data.size();
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

            for (int i = 0; i < count; i++) {
                // Ajouter l'image
            /*    String imagePath = "C:/path/to/your/image/" + seance.getCategorie() + ".jpg"; // Replace with the actual path to your image
                PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);

                // Position the image
                contentStream.drawImage(pdImage, 100, 650, 100, 100); // Adjust the position and size as needed
*/
                // Ajouter les informations de la séance
                String ligne = "ID : " + data.get(i) + "     Type_Seance : " + data2.get(i) + "  NB_Maximal : " + data3.get(i);
                contentStream.showText(ligne);

                contentStream.newLine();
                contentStream.newLineAtOffset(0, -15);
            }

            contentStream.endText();

            // Fermez le contenu de la page
            contentStream.close();

            String outputPath = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/src/PDF/seance.pdf";
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

    public void envoyer(String b) {
// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.ciphersuites", "TLS_AES_256_GCM_SHA384");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fathimaddeh88@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(b));
            message.setSubject("Failed RESERVATION");
            message.setText("votre reservation a ete annulee");

            // Enable debugging
            session.setDebug(true);

            Transport.send(message);
            System.out.println("Message envoyé avec succès");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void roww(MouseEvent mouseEvent) throws SQLException {
        int nb_maximale = 0;
        int nombreReservations = 0;
        int selectedIndex = nbco.getSelectionModel().getSelectedIndex();
        String id_s = idsf.getItems().get(selectedIndex);
        String query = "SELECT nb_maximal FROM seance WHERE id_seance = ?";
        int idd = Integer.parseInt(id_s);
        Connection connection = MyDatabase.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idd);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {

                nb_maximale = resultSet.getInt("nb_maximal");
            }

        }

        String sql = "SELECT * FROM reservation WHERE id_seance = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idd);
        ResultSet resultSe = preparedStatement.executeQuery();
        while (resultSe.next()) {
            nombreReservations++;
        }

        int difference = nb_maximale - nombreReservations;

        // Afficher la différence dans une fenêtre contextuelle
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Différence de réservations");
        alert.setHeaderText(null);
        alert.setContentText("Le Nombre Des Reservation Disponible : " + difference);
        alert.showAndWait();


    }
    private void addToPieChartData(ObservableList<PieChart.Data> pieChartData, String type) {
        // Searching for existing PieChart data with the same type
        for (PieChart.Data data : pieChartData) {
            if (data.getName().equals(type)) {
                data.setPieValue(data.getPieValue() + 1);
                return;
            }
        }
        // Adding new data for new types
        pieChartData.add(new PieChart.Data(type, 1));
    }


    public void addSeancSearch(KeyEvent keyEvent) throws SQLException {

        FilteredList<String> filteredList = new FilteredList<>(items);
        searchField1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(item -> {
                // If the search field is empty, show all items
                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }

                // Convert the value to lowercase for case-insensitive search
                String lowerCaseFilter = newValue.toLowerCase();

                System.out.println(lowerCaseFilter);
                // Compare the item with the search text
                if (item.toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Match found
                }

                return false; // No match found
            });
        });

// Attach the filtered list to the ListView
        typeco.setItems(filteredList);


    }

    public void ge_co(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Affichecommande.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());


    }


    public void ge_pe(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/afficherpanier.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());



    }

    public void modifiar(ActionEvent actionEvent) throws SQLException, IOException {

        ObservableList<Seance> seances = FXCollections.observableArrayList();
        SeanceService SeanceService=new SeanceService();
        List<Seance> seancess = SeanceService.recuperer();
        ObservableList<Seance> seancesss = FXCollections.observableArrayList(seancess);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        Parent root = fxmlLoader.load();
        ModifierSeance controller = fxmlLoader.getController();
        String clickedSeance = typeco.getSelectionModel().getSelectedItem();
        System.out.println("vcdvdv");
        Optional<Seance> seanceOptional = seancess.stream()
                .filter(seance -> seance.getType_seance().contains(clickedSeance))
                .findFirst();


        // Vérifier si la séance a été trouvée
        if (seanceOptional.isPresent()) {
            Seance seance = seanceOptional.get();
            controller.setSeances(seancesss);
            // Mettre à jour les autres attributs dans le contrôleur ModifierSeance
            controller.setId(String.valueOf(seance.getId_seance()));
            controller.setType(seance.getType_seance());
            controller.setCategorie(seance.getCategorie());
            controller.setDuree(String.valueOf(seance.getDuree_seance()));
            controller.setNbMaximal(String.valueOf(seance.getNb_maximal()));

            // Remplacer la scène actuelle par la scène de modification
            typeco.getScene().setRoot(root);
        } else {
            System.out.println("Aucune séance trouvée avec l'ID : " );
        }

        typeco.getScene().setRoot(root);




    }

    public void ge_us(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AfficherUsers.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());





    }

    public void ge_coach(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Ajouterprogram.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());

    }

    public void ge_po(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/MenuProduitCategorie.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());



    }

    public void ge_ev(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AdminEvenment.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());




    }

    public void logo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/LOGIN.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());


    }
}



