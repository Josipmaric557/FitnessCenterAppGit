package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.GymOpremaRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.GymRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.OpremaRepositoryImpl;
import hr.java.FitnessCentar.Services.GymOpremaService;
import hr.java.FitnessCentar.Services.GymService;
import hr.java.FitnessCentar.Services.OpremaService;
import hr.java.FitnessCentar.model.entity.Gym;
import hr.java.FitnessCentar.model.entity.Oprema;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.util.List;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class GymOpremaController {

    @FXML
    private TableView<Gym> gymTableView;

    @FXML
    private TableColumn<Gym, Integer> idColumn;

    @FXML
    private TableColumn<Gym, String> nazivColumn;

    @FXML
    private TableColumn<Gym, String> adresaColumn;

    @FXML
    private TableColumn<Gym, String> gradColumn;

    @FXML
    private TableColumn<Gym, String> radnoVrijemeColumn;

    @FXML
    private TableColumn<Gym, BigDecimal> cijenaClanarineColumn;

    @FXML
    private TableColumn<Gym, Double> ukupnaPovrsinaColumn;

    @FXML
    private TableColumn<Gym, String> fotoColumn;

    @FXML
    private TextField searchGymField;

    @FXML
    private ComboBox<Oprema> opremaComboBox;

    @FXML
    private ListView<Oprema> gymOpremaListView;

    private GymOpremaService gymOpremaService;
    private GymService gymService;
    private OpremaService opremaService;

    private Gym selectedGym;

    private final ObservableList<Gym> gymList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try {

            gymOpremaService =
                    new GymOpremaService(
                            new GymOpremaRepositoryImpl()
                    );

            gymService =
                    new GymService(
                            new GymRepositoryImpl()
                    );

            opremaService =
                    new OpremaService(
                            new OpremaRepositoryImpl()
                    );

        } catch (Exception e) {

            e.printStackTrace();
            showError(e.getMessage());
        }

        setUpTable();

        loadGyms();

        loadOprema();

        setUpListeners();
    }

    private void setUpTable() {

        idColumn.setCellValueFactory(
                data -> new SimpleIntegerProperty(
                        data.getValue().GetId()
                ).asObject()
        );

        nazivColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().GetNaziv()
                )
        );

        adresaColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().GetAdresa()
                )
        );

        gradColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().GetGrad()
                )
        );

        radnoVrijemeColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().GetRadnoVrijeme()
                )
        );

        cijenaClanarineColumn.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue().GetCijenaClanarine()
                )
        );

        ukupnaPovrsinaColumn.setCellValueFactory(
                data -> new SimpleDoubleProperty(
                        data.getValue().GetUkupnaPovrsina()
                ).asObject()
        );

        fotoColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().GetFotoPath()
                )
        );
    }

    public void loadGyms() {

        try {

            gymList.clear();

            gymList.addAll(
                    gymService.findAll()
            );

            gymTableView.setItems(gymList);

        } catch (Exception e) {


            showError(e.getMessage());
        }
    }

    public void loadOprema() {

        try {

            opremaComboBox.setItems(
                    FXCollections.observableArrayList(
                            opremaService.findAll()
                    )
            );

        } catch (Exception e) {

            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    private void setUpListeners() {

        gymTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, gym) -> {

                    if (gym != null) {

                        selectedGym = gym;

                        loadGymOprema();
                    }
                });

        searchGymField.textProperty()
                .addListener((obs, oldVal, newVal) -> {
                    filterGyms(newVal);
                });
    }

    private void loadGymOprema() {

        if (selectedGym == null) {
            return;
        }

        try {

            List<Oprema> opremaList =
                    gymOpremaService.getOpremaByGymId(
                            selectedGym.GetId()
                    );

            gymOpremaListView.setItems(
                    FXCollections.observableArrayList(opremaList)
            );

        } catch (Exception e) {

            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    @FXML
    private void assignButtonA() {

        if (selectedGym == null) {
            showError("Odaberi gym!");
            return;
        }

        Oprema oprema = opremaComboBox.getValue();

        if (oprema == null) {
            showError("Odaberi opremu!");
            return;
        }

        try {

            gymOpremaService.assignOpremaToGym(
                    selectedGym.GetId(),
                    oprema.GetId()
            );

            loadGymOprema();

            showInfo("Oprema uspješno dodijeljena!");

        } catch (Exception e) {

            e.printStackTrace();

            showError(e.getMessage());
        }
    }

    @FXML
    private void removeButtonA() {

        if (selectedGym == null) {
            showError("Odaberi gym!");
            return;
        }

        Oprema oprema =
                gymOpremaListView
                        .getSelectionModel()
                        .getSelectedItem();

        if (oprema == null) {
            showError("Odaberi opremu iz liste!");
            return;
        }

        try {

            gymOpremaService.removeOpremaFromGym(
                    selectedGym.GetId(),
                    oprema.GetId()
            );

            loadGymOprema();

            showInfo("Oprema uspješno uklonjena!");

        } catch (Exception e) {

            e.printStackTrace();

            showError(e.getMessage());
        }
    }

    private void filterGyms(String text) {

        if (text == null || text.isBlank()) {

            gymTableView.setItems(gymList);

            return;
        }

        ObservableList<Gym> filtered =
                FXCollections.observableArrayList();

        for (Gym g : gymList) {

            if (g.GetNaziv()
                    .toLowerCase()
                    .contains(text.toLowerCase())) {

                filtered.add(g);
            }
        }

        gymTableView.setItems(filtered);
    }
}
