package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.GymKategorijaRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.GymRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.KategorijaRepositoryImpl;
import hr.java.FitnessCentar.Services.GymKategorijaService;
import hr.java.FitnessCentar.Services.GymService;
import hr.java.FitnessCentar.Services.KategorijaService;
import hr.java.FitnessCentar.model.entity.Gym;
import hr.java.FitnessCentar.model.entity.Kategorija;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.util.List;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class GymKategorijaController {

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
    private ComboBox<Kategorija> kategorijaComboBox;

    @FXML
    private ListView<Kategorija> gymKategorijaListView;

    @FXML
    private Button assignButton;

    @FXML
    private Button removeButton;

    private GymKategorijaService gymKategorijaService;
    private GymService gymService;
    private KategorijaService kategorijaService;

    private Gym selectedGym;

    private final ObservableList<Gym> gymList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try {

            gymKategorijaService =
                    new GymKategorijaService(
                            new GymKategorijaRepositoryImpl()
                    );

            gymService =
                    new GymService(
                            new GymRepositoryImpl()
                    );

            kategorijaService =
                    new KategorijaService(
                            new KategorijaRepositoryImpl()
                    );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setUpTable();

        loadGyms();

        loadKategorije();

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

            gymList.addAll(gymService.findAll());

            gymTableView.setItems(gymList);

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void loadKategorije() {

        try {

            kategorijaComboBox.setItems(
                    FXCollections.observableArrayList(
                            kategorijaService.findAll()
                    )
            );

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void setUpListeners() {

        gymTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, gym) -> {

                    if (gym != null) {

                        selectedGym = gym;

                        loadGymCategories();
                    }
                });

        searchGymField.textProperty()
                .addListener((obs, oldVal, newVal) -> {
                    filterGyms(newVal);
                });
    }

    private void loadGymCategories() {

        try {

            List<Kategorija> kategorije =
                    gymKategorijaService.getKategorijaByGymId(
                            selectedGym.GetId()
                    );

            gymKategorijaListView.setItems(
                    FXCollections.observableArrayList(kategorije)
            );

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    private void assignButtonA() {

        if (selectedGym == null) {
            showError("Odaberi gym!");
            return;
        }

        Kategorija kategorija =
                kategorijaComboBox.getValue();

        if (kategorija == null) {
            showError("Odaberi kategoriju!");
            return;
        }

        try {

            gymKategorijaService.assignKategorijaToGym(
                    selectedGym.GetId(),
                    kategorija.GetId()
            );

            loadGymCategories();

            showInfo("Kategorija dodijeljena!");

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    private void removeButtonA() {

        if (selectedGym == null) {
            showError("Odaberi gym!");
            return;
        }

        Kategorija kategorija =
                gymKategorijaListView
                        .getSelectionModel()
                        .getSelectedItem();

        if (kategorija == null) {
            showError("Odaberi kategoriju iz liste!");
            return;
        }

        try {

            gymKategorijaService.removeKategorijaFromGym(
                    selectedGym.GetId(),
                    kategorija.GetId()
            );

            loadGymCategories();

            showInfo("Kategorija uklonjena!");

        } catch (Exception e) {
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