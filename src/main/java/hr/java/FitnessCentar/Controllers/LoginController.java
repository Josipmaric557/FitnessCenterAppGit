package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.KorisnikRepositoryImpl;
import hr.java.FitnessCentar.Services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private AuthService authService;

    @FXML
    public void initialize() throws Exception {
        authService = new AuthService(new KorisnikRepositoryImpl());
    }

    @FXML
    private void login() {
        try {

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isBlank() || password.isBlank()) {
                showError("Unesi username i password");
                return;
            }

            boolean success = authService.login(username, password);

            if (!success) {
                showError("Pogrešan login");
                return;
            }



            showInfo("Login uspješan!");

            openDashboard();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void openDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/hr/java/FitnessCenter/View/MenuForm.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            showError("Greška pri otvaranju dashboarda: " + e.getMessage());
        }
    }

    @FXML
    private void openRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/hr/java/FitnessCenter/View/FitnessRegisterForm.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            showError("Greška pri register forme: " + e.getMessage());
        }
    }
}
