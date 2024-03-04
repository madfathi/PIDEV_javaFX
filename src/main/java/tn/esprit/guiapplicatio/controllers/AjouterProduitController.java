package tn.esprit.guiapplicatio.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.mysql.cj.xdevapi.Session;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tn.esprit.guiapplicatio.models.Categorie;
import tn.esprit.guiapplicatio.models.Offre;
import tn.esprit.guiapplicatio.models.Produit;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tn.esprit.guiapplicatio.test.HelloApplication;
import tn.esprit.guiapplicatio.services.CategorieService;
import tn.esprit.guiapplicatio.services.OffreService;
import tn.esprit.guiapplicatio.services.ProduitService;
import tn.esprit.guiapplicatio.utils.DataSource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.util.List;
import java.util.*;
public class AjouterProduitController implements Initializable {

    @FXML
    private PieChart pieChart;

    private Connection conn;
    private PreparedStatement pst;
    private Statement statement;
    private final ProduitService ps = new ProduitService();
    private ProduitService pss = new ProduitService();
    @FXML
    private TextField RechercherProduit;
    @FXML
    private ComboBox<Offre> comboOffreP;
    @FXML
    private ListView<String> produitlistview;

    @FXML
    private ComboBox<Categorie> ComboProduitC;

    @FXML
    private Button ajouterProduit;

    @FXML
    private Button btImageProduit;

    @FXML
    private Button modifierProduit;

    @FXML
    private Button supprimerProduit;

    @FXML
    private ImageView tfImageP;

    @FXML
    private TextField tfNomProduit;

    @FXML
    private TextField tfPrixProduit;

    @FXML
    private TextField tfQuantiteProduit;
    @FXML
    private TableView<Produit> tabProduit;
    @FXML
    private TableColumn<Produit, String> nomCategorieTab;

    @FXML
    private TableColumn<Produit, Integer> nomPrixTab;

    @FXML
    private TableColumn<Produit, String> nomProduitTab;

    @FXML
    private TableColumn<Produit, Integer> nomQuantiteTab;
    @FXML
    private TableColumn<Produit, String> imageProduitTab;
    @FXML
    private TableColumn<Produit, String> OffreProduitTab;

    String filepath = null, filename = null, fn = null;
    String uploads = "C:/Users/LEGION/Desktop/ProduitCategorie/src/main/java/Images/";

    FileChooser fc = new FileChooser();
    ObservableList<Produit> list = FXCollections.observableArrayList();
    public int idProduit;
    private int selectedProduitId = -1;

    public int getIdProduit() {
        return getIdProduit();
    }

    public void setIdProduit(int id) {
        this.idProduit = id;
    }

    @FXML
    private ComboBox<String> sortProduitBox;
    private List<Produit> temp;

    @FXML
    private ImageView qrcodeProduit;
    @FXML
    private Button pdfProduit;

    @FXML
    private Button excelProduit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance().getCnx();
        sortProduitBox.getItems().removeAll(sortProduitBox.getItems());
        sortProduitBox.getItems().addAll("Trier", "Trier par Prix ↑", "Trier par Prix ↓");
        sortProduitBox.getSelectionModel().select("Trier");
        setCombo();
        setComboOffre();
        showProduit();
        displayEventData();
        produitlistview.setOnMouseClicked(this::setValue);

        addDataToChart();

    }
    public void setItems(ObservableList<Categorie> categories) {
    }
    public void btn_image_produit_action(ActionEvent actionEvent) throws SQLException, FileNotFoundException, IOException {
        File file = fc.showOpenDialog(null);
        // Shows a new file open dialog.
        if (file != null) {
            // URI that represents this abstract pathname
            tfImageP.setImage(new Image(file.toURI().toString()));

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

    @FXML
    public void AjouterProduit(ActionEvent actionEvent) throws SQLException {
        String nomProd = tfNomProduit.getText().trim();
        Categorie cat = ComboProduitC.getValue();
        Offre of = comboOffreP.getValue();
        String prixText = tfPrixProduit.getText().trim();
        String quantiteText = tfQuantiteProduit.getText().trim();

        if (nomProd.isEmpty() || cat == null || of == null || prixText.isEmpty() || quantiteText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; // Stop execution if any field is empty
        }

        int prix;
        int quantite;
        try {
            prix = Integer.parseInt(prixText);
            quantite = Integer.parseInt(quantiteText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Format incorrect");
            alert.setContentText("Veuillez saisir des nombres valides pour les champs 'Prix' et 'Quantité'.");
            alert.showAndWait();
            return; // Stop execution if parsing fails
        }

        if (prix <= 0 || quantite <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valeurs invalides");
            alert.setContentText("Les valeurs des champs 'Prix' et 'Quantité' doivent être supérieures à zéro.");
            alert.showAndWait();
            return; // Stop execution if prix or quantite is not positive
        }

        // If all validations pass, proceed to add the product
        ps.addProduit(new Produit(nomProd, quantite, prix, cat, filename, of));

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Succès");
        a.setContentText("Produit ajouté avec succès.");
        a.showAndWait();
    }

//    @FXML
//    public void AjouterProduit(ActionEvent actionEvent) throws SQLException {
//        String nomProd = tfNomProduit.getText();
//        Categorie cat = ComboProduitC.getValue();
//        Offre of = comboOffreP.getValue();
//        int prix = Integer.parseInt(tfPrixProduit.getText());
//        int quantite = Integer.parseInt(tfQuantiteProduit.getText());
//        ps.addProduit(new Produit(nomProd, quantite, prix, cat, filename, of));
//        Alert a = new Alert(Alert.AlertType.WARNING);
//        a.setTitle("Succes");
//        a.setContentText("Produit Ajoutée");
//        a.showAndWait();
//    }

    public void ModifierProduit(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nomProd = tfNomProduit.getText();
        Categorie cat = ComboProduitC.getValue();
        Offre of = comboOffreP.getValue();
        int prix = Integer.parseInt(tfPrixProduit.getText());
        int quantite = Integer.parseInt(tfQuantiteProduit.getText());
        Produit p = new Produit(idProduit, nomProd, quantite, prix, cat, fn, of);
        pss.modifyProduit(p);
        Alert a = new Alert(Alert.AlertType.WARNING);

        a.setTitle("Succes");
        a.setContentText("Produit Modifiée");
        a.showAndWait();
        showProduit();


    }


    @FXML
    public void SupprimerProduit(ActionEvent actionEvent) {
        // Get the selected string from the ListView
        String selectedString = produitlistview.getSelectionModel().getSelectedItem();
        if (selectedString != null && !selectedString.trim().isEmpty()) {
            // Confirm deletion with the user
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir supprimer ce produit ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText(null);
            Optional<ButtonType> response = confirmationAlert.showAndWait();

            if (response.isPresent() && response.get() == ButtonType.YES) {
                // Parse the identifier from the selected string
                String identifier = parseIdentifierFromSelectedString(selectedString);

                // Find the corresponding Produit object
                Produit toDelete = findProduitByIdentifier(identifier);

                if (toDelete != null) {
                    // Delete the product from your data source
                    pss.deleteProduit(toDelete.getIdProduit());

                    // Remove the selected string from the ListView
                    produitlistview.getItems().remove(selectedString);

                    // Optionally, show a confirmation message
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, "Produit supprimé avec succès.", ButtonType.OK);
                    infoAlert.setHeaderText(null);
                    infoAlert.showAndWait();
                } else {
                    // Handle the case where the product wasn't found
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Produit introuvable.", ButtonType.OK);
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }
        } else {
            // Handle the case where no product was selected
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un produit à supprimer.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    // Helper method to parse the identifier from the selected string
    private String parseIdentifierFromSelectedString(String selectedString) {
        // Implement this based on how your string is formatted and how you can uniquely identify a product from it
        return selectedString.split(" ")[0]; // Example: assuming the first part is an ID or name
    }

    // Helper method to find a Produit by its identifier (name, ID, etc.)
    private Produit findProduitByIdentifier(String identifier) {
        // Implement this to search for and return the corresponding Produit object
        // This could involve searching through a list, a database query, etc.
        for (Produit produit : ps.readProduit()) { // Assuming ps.readProduit() returns all products
            if (produit.getNomProduit().equals(identifier) || String.valueOf(produit.getIdProduit()).equals(identifier)) {
                return produit;
            }
        }
        return null;
    }


    public static class ColumnListViewCell extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null); // Clear graphic if cell is empty
        } else {
            // Split the data into columns
            String[] columns = item.split("\\s{2,}"); // Split by 2 or more spaces

            // Create a HBox to hold the columns
            HBox rowBox = new HBox();

            // Add labels for each column except the last one (image path)
            for (int i = 0; i < columns.length - 1; i++) {
                Label label = new Label(columns[i]);
                label.setPrefWidth(150); // Set the width as needed
                rowBox.getChildren().add(label);
            }

            // Add the image view for the last column (image path)
            ImageView imageView = new ImageView();
            String imagePath = columns[columns.length - 1];
            try {
                // Load image from classpath resources
                InputStream stream = getClass().getResourceAsStream("/Photos/" + imagePath);
                if (stream != null) {
                    Image image = new Image(stream);
                    imageView.setImage(image);
                    imageView.setFitWidth(100); // Adjust width of the image view
                    imageView.setPreserveRatio(true);
                    rowBox.getChildren().add(imageView);
                } else {
                    System.err.println("Image not found: " + imagePath);
                }
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
            }

            // Set the HBox as the cell's graphic
            setGraphic(rowBox);
        }
    }

}
    private void displayEventData() {
        ProduitService evenmentService = new ProduitService();
        List<Produit> evenments = evenmentService.readProduit();

        // Create an ObservableList to store the event data
        ObservableList<String> eventDataList = FXCollections.observableArrayList();

        // Add column titles
        String columnTitles = String.format("%-20s %-20s %-20s %-20s %-20s", "NomProduit", "Quantite", "Prix", "NomCategorie", "Image Path");
        eventDataList.add(columnTitles);

        // Iterate through the list of events and add their details to the eventDataList
        for (Produit produit : evenments) {
            String eventData = String.format("%-20s %-20s %-20s %-20s %-20s",
                    produit.getNomProduit(),
                    produit.getQuantite(),
                    produit.getPrix(),
                    produit.getCategorie().getNomCategorie(),
                    produit.getImageProduit());
            eventDataList.add(eventData);
        }

        // Set items for the table ListView
        produitlistview.setItems(eventDataList);

        // Set custom ListCell to format the data in columns
        produitlistview.setCellFactory(listView -> new tn.esprit.guiapplicatio.controllers.ProduitListView.ColumnListViewCell());
    }



    public void showProduit() {
        ProduitService produitService = new ProduitService();
        List<Produit> produits = produitService.readProduit(); // Assuming this returns a List of all products

        // Create an ObservableList to store the product data in a formatted string manner
        ObservableList<String> produitDataList = FXCollections.observableArrayList();

        // Iterate through the list of products and format each product's details into a string
        for (Produit produit : produits) {
            String formattedData = String.format("%-20s %-20s %-20s %-20s %-20s",
                    produit.getNomProduit(),
                    produit.getQuantite(),
                    produit.getPrix(),
                    produit.getCategorie().getNomCategorie(),
                    produit.getImageProduit());
            produitDataList.add(formattedData);
        }

        // Set items for the ListView
        produitlistview.setItems(produitDataList);

        // Set the custom ListCell to format the data in columns if not already set
        // This can be skipped if it's already done in the `initialize` or `displayEventData` method
        // produitlistview.setCellFactory(listView -> new ColumnListViewCell());
    }


    public void setValue(MouseEvent mouseEvent) {
        // Get the selected string from the ListView
        String selectedItem = produitlistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null && !selectedItem.isEmpty()) {
            // Assuming your string format is "NomProduit Quantite Prix NomCategorie ImagePath"
            // and splitting it to extract the product name as a unique identifier
            String[] parts = selectedItem.split("\\s{2,}", 5); // Adjust the regex as needed for your actual format
            if (parts.length > 0) {
                String nomProduit = parts[0]; // Assuming the product name is the first part

                // Now, find the corresponding Produit object by the product name
                Produit correspondingProduit = findProduitByName(nomProduit);

                if (correspondingProduit != null) {
                    // Update UI components with the details of the found Produit
                    ComboProduitC.setValue(correspondingProduit.getCategorie());
                    tfNomProduit.setText(correspondingProduit.getNomProduit());
                    tfPrixProduit.setText(String.valueOf(correspondingProduit.getPrix()));
                    tfQuantiteProduit.setText(String.valueOf(correspondingProduit.getQuantite()));
                    comboOffreP.setValue(correspondingProduit.getOffre());
                    idProduit = correspondingProduit.getIdProduit();
                    fn = correspondingProduit.getImageProduit();
                    Image im = new Image("file:" + uploads + correspondingProduit.getImageProduit());
                    tfImageP.setImage(im);
                }
            }
        }
    }

    // Helper method to find a Produit by its name
    private Produit findProduitByName(String nomProduit) {
        ProduitService produitService = new ProduitService();
        List<Produit> produits = produitService.readProduit(); // Assuming this method returns all products
        for (Produit produit : produits) {
            if (produit.getNomProduit().equals(nomProduit)) {
                return produit;
            }
        }
        return null; // If no matching product is found
    }


    public void setComboOffre() {
        OffreService tabO = new OffreService();
        List<Offre> tabListOffre = tabO.readOffre();
        ArrayList<Offre> Offres = new ArrayList<>();
        for (Offre o : tabListOffre) {
            Offre of = new Offre();
            of.setIdOffre(o.getIdOffre());
            of.setNomOffre(o.getNomOffre());
            Offres.add(of);
        }
        ObservableList<Offre> choices = FXCollections.observableArrayList(Offres);
        comboOffreP.setItems(choices);
        comboOffreP.setConverter(new StringConverter<Offre>() {
            @Override
            public String toString(Offre offre) {
                if (offre == null) {
                    return null;
                } else {
                    return offre.getNomOffre();
                }
            }

            @Override
            public Offre fromString(String string) {
                // Vous pouvez implémenter cette méthode si nécessaire
                return null;
            }
        });
    }


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

    public void backProduit(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MenuProduitCategorie.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Node source = (Node) actionEvent.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();
        stage.show();

    }

    @FXML
    public void sortProduit(ActionEvent actionEvent) {
        String selected = sortProduitBox.getSelectionModel().getSelectedItem();
        List<Produit> sortedProduits = null;

        if (selected.equals("Trier par Prix ↑")) {
            sortedProduits = pss.sortProduitPrixAsc();
        } else if (selected.equals("Trier par Prix ↓")) {
            sortedProduits = pss.sortProduitPrixDesc();
        }

        // Convert the sorted list of Produit objects into a list of formatted strings
        ObservableList<String> sortedListStrings = FXCollections.observableArrayList();
        if (sortedProduits != null) {
            for (Produit produit : sortedProduits) {
                String produitString = formatProduitAsString(produit);
                sortedListStrings.add(produitString);
            }
        }

        // Update the ListView with the sorted list of strings
        produitlistview.setItems(sortedListStrings);
    }

    private String formatProduitAsString(Produit produit) {
        // Adjust this format to match how you've formatted the strings in your ListView
        return String.format("%-20s %-20s %-20s %-20s %-20s",
                produit.getNomProduit(),
                produit.getQuantite(),
                produit.getPrix(),
                produit.getCategorie().getNomCategorie(),
                produit.getImageProduit());
    }


    //qrcode produit
    @FXML
    public void generateQrCodeProduit(ActionEvent actionEvent) {
        Produit selected = tabProduit.getSelectionModel().getSelectedItem();
        // Vérifiez si un élément est sélectionné
        if (selected != null) {
            // Générez la chaîne de données pour le QR code
            String qrData = "Nom: " + selected.getNomProduit() + "Quantite: " + selected.getQuantite() + "Prix: " + selected.getPrix() + "Categorie associée: " + selected.getCategorie();

            // Générez et affichez le QR code
            generateAndDisplayQRCode(qrData);
        } else {
            // Affichez un message d'erreur ou prenez une autre action appropriée
            System.out.println("Aucun Produit sélectionnée.");
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
            qrcodeProduit.setFitWidth(184);
            qrcodeProduit.setFitHeight(199);

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
    public void searchProduit(KeyEvent keyEvent) {
        // Assume 'originalItems' is the original unfiltered list of product strings
        ObservableList<String> originalItems = FXCollections.observableArrayList(produitlistview.getItems());

        // Filter the list based on the search text
        FilteredList<String> filteredList = new FilteredList<>(originalItems, s -> true);

        // Update the predicate whenever the filter changes
        RechercherProduit.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(produitString -> {
                // If filter text is empty, display all products
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare lower case strings
                String lowerCaseFilter = newValue.toLowerCase();
                return produitString.toLowerCase().contains(lowerCaseFilter);
            });
        });

        // Update the ListView items
        produitlistview.setItems(filteredList);
    }



    @FXML
    public void generatePdfProduit(ActionEvent actionEvent) {
        ObservableList<Produit> data = tabProduit.getItems();

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


            for (Produit produit : data) {
                String ligne = "ID : " + produit.getIdProduit() + "        Nom : " + produit.getNomProduit() + "     Quantité : " + produit.getQuantite() + "        Prix : " + produit.getPrix();
                contentStream.showText(ligne);
                contentStream.newLine();
                contentStream.newLineAtOffset(0, -15);
            }

            contentStream.endText();

            // Fermez le contenu de la page
            contentStream.close();

            // Déterminez le chemin du fichier PDF
            String outputPath = "C:/Users/LEGION/Desktop/ProduitCategorie/src/main/java/PDF/produits.pdf";
            File file = new File(outputPath);

            // Sauvegardez le document PDF
            document.save(file);

            // Fermez le document
            document.close();

            System.out.println("Le PDF a été généré avec succès.");

            // Envoyez le PDF par e-mail
//            envoyer("recipient@example.com", outputPath);

            // Ouvrez le fichier PDF
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void generateExcelProduit(ActionEvent actionEvent) throws SQLException, FileNotFoundException, IOException {

        String req = "SELECT idProduit,nomProduit,quantite,prix FROM produit ";
        statement = conn.createStatement();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Détails produit");
        HSSFRow header = sheet.createRow(0);


        header.createCell(0).setCellValue("idProduit");
        header.createCell(1).setCellValue("nomProduit");
        header.createCell(2).setCellValue("quantite");
        header.createCell(3).setCellValue("prix");


        int index = 1;
        while (rs.next()) {
            HSSFRow row = sheet.createRow(index);

            row.createCell(0).setCellValue(rs.getInt("idProduit"));
            row.createCell(1).setCellValue(rs.getString("nomProduit"));
            row.createCell(2).setCellValue(rs.getInt("quantite"));
            row.createCell(3).setCellValue(rs.getInt("prix"));

            index++;
        }

        FileOutputStream file = new FileOutputStream("C:/Users/LEGION/Desktop/ProduitCategorie/src/main/java/EXCEL/produit.xls");
        wb.write(file);
        file.close();

        JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");

        pst.close();
        rs.close();

    }


    private void addDataToChart() {
        // Efface les données existantes
        pieChart.getData().clear();

        // Récupère les statistiques des prix
        int produitsEntre100Et200 = getProduitsCountByPriceRange(0, 100);
        int produitsEntre300Et500 = getProduitsCountByPriceRange(200, 1000);
        int totalProduits = getTotalProduitsCount();

        // Ajoute les données au PieChart
        PieChart.Data data1 = new PieChart.Data("Produits entre 100 et 200", produitsEntre100Et200);
        PieChart.Data data2 = new PieChart.Data("Produits entre 300 et 500", produitsEntre300Et500);
        PieChart.Data data3 = new PieChart.Data("Autres Produits", totalProduits - produitsEntre100Et200 - produitsEntre300Et500);
        pieChart.getData().addAll(data1, data2, data3);

    }

    private int getProduitsCountByPriceRange(int minPrice, int maxPrice) {
        try {
            String query = "SELECT COUNT(*) FROM produit WHERE prix BETWEEN ? AND ?";
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

    private int getTotalProduitsCount() {
        try {
            String query = "SELECT COUNT(*) FROM produit";
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


    @FXML
    public void refreshProduit(ActionEvent actionEvent) {
    showProduit();
    }

    public void PageAfficher(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/ProduitListView.fxml"));
        try {
            tfNomProduit.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }



}
    /*

    private void updateTextField (String newT){
        Platform.runLater(() -> {
            tfNomProduit.setText(newT);
            tfQuantiteProduit.setText(newT);
            tfPrixProduit.setText(newT);
        });
    }*/






