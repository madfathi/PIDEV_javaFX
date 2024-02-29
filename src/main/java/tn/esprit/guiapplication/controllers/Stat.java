package tn.esprit.guiapplication.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import tn.esprit.guiapplication.test.HelloApplication;
import tn.esprit.guiapplication.utils.MyDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Comparator;

public class Stat {
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Label total_c;

    @FXML
    private Label total_p;

    @FXML
    private Connection connection;

    @FXML
    public void initialize() {
        totalClient();
        totalPrograms();
        //OrderProducts();

    }

    @FXML
    public void totalClient() {
        String query = "SELECT COUNT(*) FROM client";
        connection = MyDatabase.getInstance().getConnection();
        int countData = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countData = resultSet.getInt("COUNT(*)");
            }
            total_c.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void totalPrograms() {
        String query = "SELECT COUNT(*) FROM program";
        connection = MyDatabase.getInstance().getConnection();
        int countData = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countData = resultSet.getInt("COUNT(*)");
            }
            total_p.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OrderPrograms() {
        barChart.getData().clear();
        String query = "SELECT nom, COUNT(id_p) AS count, AVG(prix_p) AS avgPrice FROM program GROUP BY nom";

        connection = MyDatabase.getInstance().getConnection();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(2)));

            }
            barChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ClientB(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterclient.fxml"));
        try {
            total_c.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void ProgramB(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterprogram.fxml"));
        try {
            total_p.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
