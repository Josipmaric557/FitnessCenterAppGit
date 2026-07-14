package hr.java.FitnessCentar.Util;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void showError(String poruka) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Greška");
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    public static void showInfo(String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(poruka);

        alert.showAndWait();
    }
}
