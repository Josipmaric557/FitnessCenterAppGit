package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.*;
import hr.java.FitnessCentar.Services.*;
import hr.java.FitnessCentar.model.entity.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;


public class MenuController {

    private KategorijaService kategorijaService;
    private TrenerService trainerService;
    private ProgramTreningaService programTreningaService;
    private GymService gymService;

    // ================= ROOT =================
    @FXML
    private BorderPane rootPane;

    // ================= DASHBOARD LABELS =================
    @FXML
    private Label statusLabel;

    @FXML
    private MenuItem someButton;

    private AuthService authService;
    private AdminService adminService;





    @FXML
    private void obrisiSve() throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda");
        alert.setHeaderText("Reset baze");
        alert.setContentText("Jeste li sigurni da želite obrisati sve podatke?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        if (!authService.isAdmin()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Zabranjeno");
            error.setHeaderText(null);
            error.setContentText("Samo admin može obrisati bazu!");
            error.showAndWait();
            return;
        }

        adminService.resetDatabase();

        Alert ok = new Alert(Alert.AlertType.INFORMATION);
        ok.setTitle("Uspjeh");
        ok.setContentText("Baza uspješno resetirana!");
        ok.showAndWait();
    }

    // ================= INIT =================
    @FXML
    public void initialize() throws Exception {
        authService = new AuthService(new KorisnikRepositoryImpl());

        adminService = new AdminService(new AdminRepositoryImpl());

        kategorijaService = new KategorijaService(new KategorijaRepositoryImpl());
        trainerService = new TrenerService(new TrenerRepositoryImpl());
        programTreningaService = new ProgramTreningaService(new ProgramTreningaRepositoryImpl());
        gymService = new GymService(new GymRepositoryImpl());
        statusLabel.setText("Dashboard loaded");
    }


    // ================= NAVIGATION =================
    private void loadView(String fxml) {
        try {
            Parent view = FXMLLoader.load(
                    getClass().getResource(fxml)
            );

            // OVDJE možeš mijenjati center dio
            rootPane.setCenter(view);

            statusLabel.setText("Loaded: " + fxml);

        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Error loading view");
        }
    }

    // ================= MENU ACTIONS =================

    @FXML
    private void openGym() {
        loadView("/hr/java/FitnessCenter/View/GymForm.fxml");
    }

    @FXML
    private void openKorisnik() {
        loadView("/hr/java/FitnessCenter/View/KorisnikForm.fxml");
    }

    @FXML
    private void openTrener() {
        loadView("/hr/java/FitnessCenter/View/TrenerForm.fxml");
    }

    @FXML
    private void openProgramTreninga() {
        loadView("/hr/java/FitnessCenter/View/ProgramTreningaForm.fxml");
    }

    @FXML
    private void openOprema() {
        loadView("/hr/java/FitnessCenter/View/OpremaForm.fxml");
    }

    @FXML
    private void openKategorija() {
        loadView("/hr/java/FitnessCenter/View/KategorijaForm.fxml");
    }

    @FXML
    private void openGymKategorija() {
        loadView("/hr/java/FitnessCenter/View/GymKategorijaForm.fxml");
    }

    @FXML
    private void openGymOprema() {
        loadView("/hr/java/FitnessCenter/View/GymOpremaForm.fxml");
    }

    @FXML
    private void openGymProgramTreninga() {
        loadView("/hr/java/FitnessCenter/View/GymProgramTreningaForm.fxml");
    }

    @FXML
    private void openGymTrener() {
        loadView("/hr/java/FitnessCenter/View/GymTrenerForm.fxml");
    }

    @FXML
    private void openTrenerProgramTreninga() {
        loadView("/hr/java/FitnessCenter/View/TrenerProgramTreningaForm.fxml");
    }

    @FXML
    private void openMenuForm() {
        loadView("/hr/java/FitnessCenter/View/Menu2Form.fxml");
    }

    @FXML
    private void openLogin() {

        authService.logout();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/hr/java/FitnessCenter/View/FitnessLoginForm.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) rootPane.getScene().getWindow(); // 👈 BITNO
            stage.setScene(new Scene(root));
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @FXML
    private void importXml() {}
    @FXML
    private void backupDB() {}
}