package tn.esprit.guiapplicatio.controllers;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import  tn.esprit.guiapplicatio.models.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import  tn.esprit.guiapplicatio.services.*;
import java.awt.*;
import java.awt.ScrollPane;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.guiapplicatio.test.HelloApplication;

public class FrontProduitController implements Initializable {
    private final ProduitService ps = new ProduitService();
    String filepath = null, filename = null, fn = null;
    String uploads = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/src/main/resources/tn/esprit/guiapplicatio/Photos";

    FileChooser fc = new FileChooser();
    ObservableList<Produit> list = FXCollections.observableArrayList();
    public int idProduit;
    panierservice pns = new panierservice();

//    public int getIdProduit() {
//        return getIdProduit();
//    }
//
//    public void setIdProduit(int id) {
//        this.idProduit = id;
//    }
//
//   @FXML
//    private TableColumn<Produit, HBox> panierTab;
//    @FXML
//    private TableColumn<PanierProduit, HBox> tabDeletePanierr;
//
//    @FXML
//    private TableColumn<Produit, Integer> nomPrixTab;
//
//    @FXML
//    private TableColumn<Produit, String> imageProduitTab;
//
//    @FXML
//    private TableColumn<Produit, Integer> nomQuantiteTab;
//
//    @FXML
//    private TableView<Produit> tabProduitFront;


    @FXML
    private ComboBox<Categorie> ComboProduitC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {



        setCombo();
        ComboProduitC.setOnAction(this::filtrerProduit);
        // showProduitFront();
        showProduitFrontp();

    }

/*
    public void showProduitFront() {
        imageProduitTab.setCellFactory(column -> new TableCell<Produit, String>() {
            private final javafx.scene.image.ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    // Charger et afficher l'image
                    javafx.scene.image.Image image = new Image("file:///" + uploads + imagePath);
                    imageView.setImage(image);
                    imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                    imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                    setGraphic(imageView);
                }
            }
        });
        imageProduitTab.setCellValueFactory(new PropertyValueFactory<>("imageProduit"));
        nomQuantiteTab.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        nomPrixTab.setCellValueFactory(new PropertyValueFactory<>("prix"));

        list = ps.readProduit();
        tabProduitFront.setItems(list);

    }
*/

    public void setCombo() {
        CategorieService tabC = new CategorieService();
        List<Categorie> tabList = tabC.readCategorie();
        ArrayList<Categorie> cats = new ArrayList<>();
        for (Categorie c : tabList) {
            Categorie cat = new Categorie();
            cat.setIdCategorie(c.getIdCategorie());
            cat.setNomCategorie(c.getNomCategorie());
            cats.add(cat);
        }

        ObservableList<Categorie> choices = FXCollections.observableArrayList(cats);
        ComboProduitC.setItems(choices);

        ComboProduitC.setConverter(new StringConverter<Categorie>() {
            @Override
            public String toString(Categorie categorie) {
                if (categorie == null) {
                    return null;
                } else {
                    return categorie.getNomCategorie();
                }
            }

            @Override
            public Categorie fromString(String string) {
                // Vous pouvez implémenter cette méthode si nécessaire
                return null;
            }
        });
    }

    private List<Produit> temp;
    private List<PanierProduit> temp1;

    @FXML
    public void filtrerProduit(ActionEvent actionEvent) {
        Categorie selectedCategorie = ComboProduitC.getValue();
        int categorieId = selectedCategorie.getIdCategorie();
        temp = ps.readProduitByCategorie(categorieId);
        ObservableList<Produit> updatedList = FXCollections.observableArrayList(temp);

        // Clear the existing content in ListView
        listView.getItems().clear();
        // Add each product to ListView as GridPane
        for (int i = 0; i < updatedList.size(); i += 4) {
            GridPane productGridPane = createProductGridPane(
                    (i < updatedList.size()) ? updatedList.get(i) : null,
                    (i + 1 < updatedList.size()) ? updatedList.get(i + 1) : null,
                    (i + 2 < updatedList.size()) ? updatedList.get(i + 2) : null,
                    (i + 3 < updatedList.size()) ? updatedList.get(i + 3) : null
            );
            listView.getItems().add(productGridPane);
        }
    }

    @FXML
    private ImageView PanierImage;

    public void checkPanier(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/panier.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Votre Panier");
        stage.setScene(new Scene(root1));
        Node source = (Node) mouseEvent.getSource();
        stage.show();
    }



    @FXML
    private ListView<GridPane> listView;


    public void showProduitFrontp() {
        // Clear the existing content in ListView
        listView.getItems().clear();

        // Fetch the list of products from your service
        list = ps.readProduit();

        // Add each product to ListView
        for (int i = 0; i < list.size(); i += 4) {
            GridPane gridPane = createProductGridPane(
                    list.get(i),
                    (i + 1 < list.size()) ? list.get(i + 1) : null,
                    (i + 2 < list.size()) ? list.get(i + 2) : null,
                    (i + 3 < list.size()) ? list.get(i + 3) : null
            );
            listView.getItems().add(gridPane);
            //css
            gridPane.getStyleClass().add("grid-pane-product");

        }

    }

    private GridPane createProductGridPane(Produit produit1, Produit produit2, Produit produit3, Produit produit4) {
        GridPane gridPane = new GridPane();

        // Create and set up UI components for each product
        VBox vbox1 = createProductBox(produit1);
        VBox vbox2 = (produit2 != null) ? createProductBox(produit2) : new VBox(); // Empty VBox if no second product
        VBox vbox3 = (produit3 != null) ? createProductBox(produit3) : new VBox(); // Empty VBox if no third product
        VBox vbox4 = (produit4 != null) ? createProductBox(produit4) : new VBox(); // Empty VBox if no fourth product

        // Add components to GridPane
        gridPane.add(vbox1, 0, 0);
        gridPane.add(vbox2, 1, 0);
        gridPane.add(vbox3, 2, 0);
        gridPane.add(vbox4, 3, 0);

        // Set horizontal gap between columns
        gridPane.setHgap(10);

        return gridPane;
    }
    @FXML
    private ImageView qrcodeProduit;
    private VBox createProductBox(Produit produit) {
        VBox vbox = new VBox();
        float prix = produit.getPrix();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.000"); // Format avec trois chiffres après la virgule
        String prixFormate = decimalFormat.format(prix);

        // Create and set up UI components for each product
        ImageView imageView = new ImageView();
        loadAndSetImage(imageView, produit.getImageProduit());

        Label nameLabel = new Label(produit.getNomProduit());
        Label priceLabel = new Label("Prix: " + prixFormate);
        Label quantityLabel = new Label("Quantité en stock: " + produit.getQuantite());
        Button addButton = new Button("+");
        addButton.getStyleClass().add("addbuttonPanier");
        nameLabel.getStyleClass().add("product-label");
        priceLabel.getStyleClass().add("product-label");
        quantityLabel.getStyleClass().add("product-label");
        Button qrCodeButton = new Button("QR code");
        qrCodeButton.getStyleClass().add("addbuttonPanier");
        // Add components to VBox
        vbox.getChildren().addAll(imageView, nameLabel, priceLabel, quantityLabel, addButton,qrCodeButton);

        // Set spacing and alignment as needed
        vbox.setSpacing(11);
        vbox.setAlignment(Pos.CENTER);


        addButton.setOnAction(event -> {


            panier Panier = null;
            if (Panier != null) {
                PanierProduitService panierProduitService = new PanierProduitService();
                panierProduitService.ajouterProduitAuPanier(Panier, produit.getIdProduit());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText(null);
                alert.setContentText("Produit ajouté dans votre panier.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText(null);
                alert.setContentText("Vous n'avez pas de panier. Veuillez créer un panier d'abord.");
                alert.showAndWait();
            }
//             Rafraîchir la vue du produit
            showProduitFrontp();
        });

        qrCodeButton.setOnAction(event ->
                {
                    String qrData = "Nom: " + produit.getNomProduit() + "\t Quantite: " + produit.getQuantite() + "\n Prix: " + produit.getPrix() + "\t Categorie associée: " + produit.getCategorie().getNomCategorie();

                    // Générez et affichez le QR code
                    generateAndDisplayQRCode(qrData);
                }


        );

        return vbox;
    }
    private void loadAndSetImage(ImageView imageView, String imagePath) {
        // Correct path format for Windows
        String basePath = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/src/main/resources/tn/esprit/guiapplicatio/Photos/";
        String fullPath = basePath + imagePath.replace("\\", "/");

        File imageFile = new File(fullPath);
        if (imageFile.exists()) {
            // Correctly format the file path for the Image constructor
            String imageUrl = imageFile.toURI().toString();
            Image image = new Image(imageUrl);
            imageView.setImage(image);
            imageView.setFitWidth(190);
            imageView.setFitHeight(190);
        } else {
            // Handle the case where the image file does not exist
            System.out.println("Image file does not exist: " + fullPath);
            imageView.setImage(null);
        }
    }


    //generate qrcode et l'afficher
    private void generateAndDisplayQRCode(String qrData) {
        try {
            // Configuration pour générer le QR code
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Générer le QR code avec ZXing
            BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, 184, 199, hints);
// Ajuster la taille de l'ImageView
            qrcodeProduit.setFitWidth(100);
            qrcodeProduit.setFitHeight(100);

            // Convertir la matrice en image JavaFX
            Image qrCodeImage = matrixToImage(matrix);

            // Afficher l'image du QR code dans l'ImageView
            qrcodeProduit.setImage(qrCodeImage);
            Alert a = new Alert(Alert.AlertType.WARNING);

            a.setTitle("Succes");
            a.setContentText("qr code generer");
            a.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour convertir une matrice BitMatrix en image BufferedImage
    private Image matrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixelColor = matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                pixelWriter.setArgb(x, y, pixelColor);
            }
        }

        System.out.println("Matrice convertie en image avec succès");

        return writableImage;
    }

    @FXML
    void checkReclamation(MouseEvent event) {
        try {
            // Load the FXML file for CheckReclamation
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AfficherReclamation.fxml"));
            Parent root = loader.load();

            // Get the current stage or create a new one
            Stage stage = new Stage(); // Create a new stage for demonstration

            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Optional: Set properties on the stage, like title or dimensions
            stage.setTitle("Reclamation");
            stage.initModality(Modality.APPLICATION_MODAL); // To make the new window modal, if needed

            // Show the stage
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, maybe show an alert here
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Loading Page");
            alert.setContentText("Could not load the reclamation check page.");
            alert.showAndWait();
        }

    }
    @FXML
    void checkChatBot(MouseEvent event) {
        try {
            // Load the FXML file for CheckReclamation
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/ChatBot.fxml"));
            Parent root = loader.load();

            // Get the current stage or create a new one
            Stage stage = new Stage(); // Create a new stage for demonstration

            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Optional: Set properties on the stage, like title or dimensions
            stage.setTitle("Chat");
            stage.initModality(Modality.APPLICATION_MODAL); // To make the new window modal, if needed

            // Show the stage
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, maybe show an alert here
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Loading Page");
            alert.setContentText("Could not load the chatbot check page.");
            alert.showAndWait();
        }

    }

    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void ge_coch(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Description.fxml"));
        qrcodeProduit.getScene().setRoot(fxmlLoader.load());
    }

    public void ge_re(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/o.fxml"));
        qrcodeProduit.getScene().setRoot(fxmlLoader.load());

    }

    public void ge_pee(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Panier.fxml"));
        qrcodeProduit.getScene().setRoot(fxmlLoader.load());

    }

    public void ge_produi(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/FrontProduit.fxml"));
        qrcodeProduit.getScene().setRoot(fxmlLoader.load());
    }

    public void ge_ev(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/FrontPage.fxml"));
        qrcodeProduit.getScene().setRoot(fxmlLoader.load());
    }
}






