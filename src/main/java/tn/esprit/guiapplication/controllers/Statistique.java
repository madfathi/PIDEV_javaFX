package tn.esprit.guiapplication.controllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

    public class Statistique {

        @FXML
        private ListView<String> ListPie; // Utilisez String pour ListView au lieu de ?

        @FXML
        private PieChart adressePie;

        private ClientService clientService = new ClientService();

        @FXML
        public void initialize() {
            try {
                afficherListeAdresse();
                afficherPieChart();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void afficherListeAdresse() throws SQLException {
            List<Client> clients = clientService.recuperer();
            ObservableList<String> adresses = FXCollections.observableArrayList();

            for (Client client : clients) {
                adresses.add(String.valueOf(client.getAge()));
            }

            ListPie.setItems(adresses);
        }

        private void afficherPieChart() throws SQLException {
            List<Client> clients = clientService.recuperer();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            for (Client client : clients) {
                String adresse = String.valueOf(client.getAge());
                boolean adresseExistante = false;

                // Vérifier si l'adresse existe déjà dans le PieChart
                for (PieChart.Data data : pieChartData) {
                    if (data.getName().equals(adresse)) {
                        adresseExistante = true;
                        data.setPieValue(data.getPieValue() + 1);
                        break;
                    }
                }

                // Si l'adresse n'existe pas, l'ajouter dans le PieChart
                if (!adresseExistante) {
                    pieChartData.add(new PieChart.Data(adresse, 1));
                }
            }

            adressePie.setData(pieChartData);
        }

        public void retour3(ActionEvent actionEvent) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterprogram.fxml"));
            try {
                adressePie.getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

