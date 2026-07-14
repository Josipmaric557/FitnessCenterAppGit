package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.GymRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.GymTrenerRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.TrenerRepositoryImpl;
import hr.java.FitnessCentar.Services.GymService;
import hr.java.FitnessCentar.Services.GymTrenerService;
import hr.java.FitnessCentar.Services.TrenerService;
import hr.java.FitnessCentar.model.entity.Gym;
import hr.java.FitnessCentar.model.entity.Trener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class GymTrenerController {

    @FXML
    private TableView<Gym> gymTableView;

    @FXML
    private TableColumn<Gym, Integer> gymIdColumn;

    @FXML
    private TableColumn<Gym, String> gymNameColumn;

    @FXML
    private TableColumn<Gym, String> gymCityColumn;

    @FXML
    private TextField searchGymField;

    @FXML
    private ComboBox<Trener> trenerComboBox;

    @FXML
    private ListView<Trener> trenerListView;

    private GymTrenerService gymTrenerService;
    private GymService gymService;
    private TrenerService trenerService;

    private Gym selectedGym;

    private final ObservableList<Gym> gymList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try {

            gymTrenerService =
                    new GymTrenerService(
                            new GymTrenerRepositoryImpl()
                    );

            gymService =
                    new GymService(
                            new GymRepositoryImpl()
                    );

            trenerService =
                    new TrenerService(
                            new TrenerRepositoryImpl()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }

        setUpTable();
        loadGyms();
        loadTreneri();
        setUpListeners();
    }

    private void setUpTable() {

        gymIdColumn.setCellValueFactory(
                data -> new SimpleIntegerProperty(
                        data.getValue().GetId()
                ).asObject()
        );

        gymNameColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().GetNaziv()
                )
        );

        gymCityColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().GetGrad()
                )
        );
    }

    public void loadGyms() {

        try {

            gymList.clear();
            gymList.addAll(gymService.findAll());

            gymTableView.setItems(gymList);

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    public void loadTreneri() {

        try {

            trenerComboBox.setItems(
                    FXCollections.observableArrayList(
                            trenerService.findAll()
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
                        loadGymTreneri();
                    }
                });

        searchGymField.textProperty()
                .addListener((obs, oldVal, newVal) -> {
                    filterGyms(newVal);
                });
    }

    private void loadGymTreneri() {

        if (selectedGym == null) return;

        try {

            List<Trener> list =
                    gymTrenerService.getTreneriByGymId(
                            selectedGym.GetId()
                    );

            trenerListView.setItems(
                    FXCollections.observableArrayList(list)
            );

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    @FXML
    private void assignTrener() {

        if (selectedGym == null) {
            showError("Odaberi gym!");
            return;
        }

        Trener trener = trenerComboBox.getValue();

        if (trener == null) {
            showError("Odaberi trenera!");
            return;
        }

        try {

            gymTrenerService.addTrenerToGym(
                    selectedGym.GetId(),
                    trener.GetId()
            );

            loadGymTreneri();

            showInfo("Trener dodijeljen gymu!");

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    @FXML
    private void removeTrener() {

        if (selectedGym == null) {
            showError("Odaberi gym!");
            return;
        }

        Trener trener =
                trenerListView.getSelectionModel()
                        .getSelectedItem();

        if (trener == null) {
            showError("Odaberi trenera iz liste!");
            return;
        }

        try {

            gymTrenerService.removeTrenerFromGym(
                    selectedGym.GetId(),
                    trener.GetId()
            );

            loadGymTreneri();

            showInfo("Trener uklonjen!");

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

            if (g.GetNaziv().toLowerCase()
                    .contains(text.toLowerCase())) {
                filtered.add(g);
            }
        }

        gymTableView.setItems(filtered);
    }
}
