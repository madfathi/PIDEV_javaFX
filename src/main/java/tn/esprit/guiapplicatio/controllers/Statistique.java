package tn.esprit.guiapplicatio.controllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import tn.esprit.guiapplicatio.models.Client;
import tn.esprit.guiapplicatio.services.ClientService;
import tn.esprit.guiapplicatio.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

    public class Statistique {

        @FXML
        private ListView<String> ListPie;

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

                for (PieChart.Data data : pieChartData) {
                    if (data.getName().equals(adresse)) {
                        adresseExistante = true;
                        data.setPieValue(data.getPieValue() + 1);
                        break;
                    }
                }

                if (!adresseExistante) {
                    pieChartData.add(new PieChart.Data(adresse, 1));
                }
            }

            adressePie.setData(pieChartData);
        }

        public void retour3(ActionEvent actionEvent) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Ajouterprogram.fxml"));
            try {
                adressePie.getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

