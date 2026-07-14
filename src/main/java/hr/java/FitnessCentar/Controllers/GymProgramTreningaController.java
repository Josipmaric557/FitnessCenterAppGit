package hr.java.FitnessCentar.Controllers;

import java.math.BigDecimal;
import java.util.List;

import hr.java.FitnessCentar.Repository.Implementation.GymProgramTreningaRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.GymRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.ProgramTreningaRepositoryImpl;
import hr.java.FitnessCentar.Services.GymProgramTreningaService;
import hr.java.FitnessCentar.Services.GymService;
import hr.java.FitnessCentar.Services.ProgramTreningaService;
import hr.java.FitnessCentar.model.entity.Gym;
import hr.java.FitnessCentar.model.entity.programTreninga;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class GymProgramTreningaController {

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
    private ComboBox<programTreninga> programComboBox;

    @FXML
    private ListView<programTreninga> gymProgramListView;

    private GymProgramTreningaService gymProgramService;
    private GymService gymService;
    private ProgramTreningaService programService;


    private Gym selectedGym;

    private final ObservableList<Gym> gymList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try {

            gymProgramService =
                    new GymProgramTreningaService(
                            new GymProgramTreningaRepositoryImpl()
                    );

            gymService =
                    new GymService(
                            new GymRepositoryImpl()
                    );

            programService =
                    new ProgramTreningaService(
                            new ProgramTreningaRepositoryImpl()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }

        setUpTable();
        loadGyms();
        loadProgrami();
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
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    public void loadProgrami() {

        try {

            programComboBox.setItems(
                    FXCollections.observableArrayList(
                            programService.findAll()
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
                        loadGymProgrami();
                    }
                });

        searchGymField.textProperty()
                .addListener((obs, oldVal, newVal) -> {
                    filterGyms(newVal);
                });
    }

    private void loadGymProgrami() {

        if (selectedGym == null) return;

        try {

            List<programTreninga> list =
                    gymProgramService.getProgramTreningaByGymId(
                            selectedGym.GetId()
                    );

            gymProgramListView.setItems(
                    FXCollections.observableArrayList(list)
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

        programTreninga program =
                programComboBox.getValue();

        if (program == null) {
            showError("Odaberi program!");
            return;
        }

        try {

            gymProgramService.addProgramTreningaToGym(
                    selectedGym.GetId(),
                    program.Getid()
            );

            loadGymProgrami();

            showInfo("Program dodijeljen!");

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

        programTreninga program =
                gymProgramListView
                        .getSelectionModel()
                        .getSelectedItem();

        if (program == null) {
            showError("Odaberi program iz liste!");
            return;
        }

        try {

            gymProgramService.removeProgramTreningaFromGym(
                    selectedGym.GetId(),
                    program.Getid()
            );

            loadGymProgrami();

            showInfo("Program uklonjen!");

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
