package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Services.AuthService;
import hr.java.FitnessCentar.Services.KorisnikService;
import hr.java.FitnessCentar.Repository.Implementation.KorisnikRepositoryImpl;
import hr.java.FitnessCentar.model.Enum.UlogaE;
import hr.java.FitnessCentar.model.entity.Korisnik;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<UlogaE> ulogaCombo;
    @FXML private Button registerButton;

    private AuthService authService;

    @FXML
    public void initialize() throws Exception {
        authService = new AuthService(new KorisnikRepositoryImpl());

        ulogaCombo.setItems(FXCollections.observableArrayList(UlogaE.values()));

        // default role (optional)
        ulogaCombo.setValue(UlogaE.Korisnik);
    }

    @FXML
    private void register() {

        try {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            UlogaE uloga = ulogaCombo.getValue();

            // VALIDATION
            if (username.isBlank() || email.isBlank() || password.isBlank() || uloga == null) {
                showError("Popuni sva polja!");
                return;
            }

            Korisnik k = new Korisnik();
            k.SetkorisnikIme(username);
            k.SetEmail(email);
            k.SetLozinka(password);
            k.SetUlogaE(uloga);

            authService.register(k);

            showInfo("Registracija uspješna!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hr/java/FitnessCenter/View/FitnessLoginForm.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            clearForm();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    private void clearForm() {
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
        ulogaCombo.getSelectionModel().clearSelection();
    }
}