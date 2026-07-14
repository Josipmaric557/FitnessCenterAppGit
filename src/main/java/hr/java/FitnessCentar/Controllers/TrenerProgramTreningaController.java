package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.ProgramTreningaRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.TrenerProgramTreningaRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.TrenerRepositoryImpl;
import hr.java.FitnessCentar.Services.TrenerProgramTreningaService;
import hr.java.FitnessCentar.Repository.interfaces.TrenerProgramTreningaRepository;
import hr.java.FitnessCentar.Services.ProgramTreningaService;
import hr.java.FitnessCentar.Services.TrenerProgramTreningaService;
import hr.java.FitnessCentar.Services.TrenerService;
import hr.java.FitnessCentar.model.entity.Trener;
import hr.java.FitnessCentar.model.entity.programTreninga;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.control.TableView;

import java.util.List;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class TrenerProgramTreningaController {

    @FXML
    private TableView<Trener> trenerTable;
    @FXML
    private TableColumn<Trener, Integer> trenerIdColumn;
    @FXML
    private TableColumn<Trener, String> trenerImeColumn;
    @FXML
    private TableColumn<Trener, String> trenerSpecColumn;
    @FXML
    private TextField searchTrenerField;

    // PROGRAMS
    @FXML
    private TableView<programTreninga> programTable;
    @FXML
    private TableColumn<programTreninga, Integer> programIdColumn;
    @FXML
    private TableColumn<programTreninga, String> programNazivColumn;
    @FXML
    private TableColumn<programTreninga, String> programTezinaColumn;
    @FXML
    private TextField searchProgramField;

    // RIGHT PANEL
    @FXML
    private Label selectedTrenerLabel;
    @FXML
    private Label selectedProgramLabel;
    @FXML
    private ListView<programTreninga> assignedProgramList;

    private Trener selectedTrener;
    private programTreninga selectedProgram;

    private TrenerProgramTreningaService service;
    private TrenerService trenerService;
    private ProgramTreningaService programService;


    private final ObservableList<Trener> trenerList =
            FXCollections.observableArrayList();

    private final ObservableList<programTreninga> programList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try {

            service =
                    new TrenerProgramTreningaService(
                            new TrenerProgramTreningaRepositoryImpl()
                    );

            trenerService =
                    new TrenerService(
                            new TrenerRepositoryImpl()
                    );

            programService =
                    new ProgramTreningaService(
                            new ProgramTreningaRepositoryImpl()
                    );


        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }

        trenerTable.setOnDragDetected(event -> {

            Trener trener =
                    trenerTable
                            .getSelectionModel()
                            .getSelectedItem();

            if (trener == null) {
                return;
            }

            Dragboard dragboard =
                    trenerTable.startDragAndDrop(
                            TransferMode.MOVE);

            ClipboardContent content =
                    new ClipboardContent();

            content.putString(
                    String.valueOf(trener.GetId()));

            dragboard.setContent(content);

            event.consume();
        });

        programTable.setOnDragOver(event -> {

            if (event.getDragboard().hasString()) {

                event.acceptTransferModes(
                        TransferMode.MOVE);
            }

            event.consume();
        });

        programTable.setOnDragDropped(event -> {

            Dragboard dragboard = event.getDragboard();

            if (!dragboard.hasString()) {
                return;
            }

            if (programTable.getSelectionModel().getSelectedItem() == null) {
                showError("Klikni program prije dropa!");
                return;
            }

            int trainerId = Integer.parseInt(dragboard.getString());

            programTreninga program =
                    programTable.getSelectionModel().getSelectedItem();

            try {
                service.addProgramTreningaToTrainer(
                        trainerId,
                        program.Getid()
                );

                showInfo("Trener dobio svoj Program!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            event.setDropCompleted(true);
            event.consume();
        });

        setUpTables();
        loadData();
        setUpListeners();
    }

    private void setUpTables() {

        trenerIdColumn.setCellValueFactory(
                d -> new SimpleIntegerProperty(
                        d.getValue().GetId()
                ).asObject()
        );

        trenerImeColumn.setCellValueFactory(
                d -> new SimpleStringProperty(
                        d.getValue().getIme()
                )
        );

        trenerSpecColumn.setCellValueFactory(
                d -> new SimpleStringProperty(
                        d.getValue().GetSpecijalizacije()
                )
        );

        programIdColumn.setCellValueFactory(
                d -> new SimpleIntegerProperty(
                        d.getValue().Getid()
                ).asObject()
        );

        programNazivColumn.setCellValueFactory(
                d -> new SimpleStringProperty(
                        d.getValue().GetNaziv()
                )
        );

        programTezinaColumn.setCellValueFactory(
                d -> new SimpleStringProperty(
                        d.getValue().GetTezinaTreningaE().toString()
                )
        );
    }

    private void loadData() {

        try {

            trenerList.clear();
            trenerList.addAll(trenerService.findAll());
            trenerTable.setItems(trenerList);

            programList.clear();
            programList.addAll(programService.findAll());
            programTable.setItems(programList);

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    private void setUpListeners() {

        trenerTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, trener) -> {

                    if (trener != null) {

                        selectedTrener = trener;
                        selectedTrenerLabel.setText(trener.getIme());

                        loadAssignedPrograms();
                    }
                });

        programTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, program) -> {

                    if (program != null) {

                        selectedProgram = program;
                        selectedProgramLabel.setText(program.GetNaziv());
                    }
                });

        searchTrenerField.textProperty()
                .addListener((obs, o, n) -> filterTreneri(n));

        searchProgramField.textProperty()
                .addListener((obs, o, n) -> filterProgrami(n));
    }

    private void loadAssignedPrograms() {

        if (selectedTrener == null) return;

        try {

            List<programTreninga> list =
                    service.getProgramTreningaByTrener(
                            selectedTrener.GetId()
                    );

            assignedProgramList.setItems(
                    FXCollections.observableArrayList(list)
            );

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    @FXML
    private void assignProgram() {

        if (selectedTrener == null) {
            showError("Odaberi trenera!");
            return;
        }

        if (selectedProgram == null) {
            showError("Odaberi program!");
            return;
        }

        try {

            service.addProgramTreningaToTrainer(
                    selectedTrener.GetId(),
                    selectedProgram.Getid()
            );

            loadAssignedPrograms();
            showInfo("Program dodijeljen treneru!");

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    @FXML
    private void removeProgram() {

        if (selectedTrener == null) {
            showError("Odaberi trenera!");
            return;
        }

        programTreninga program =
                assignedProgramList.getSelectionModel()
                        .getSelectedItem();

        if (program == null) {
            showError("Odaberi program iz liste!");
            return;
        }

        try {

            service.removeProgramTreningaFromTrainer(
                    selectedTrener.GetId(),
                    program.Getid()
            );

            loadAssignedPrograms();
            showInfo("Program uklonjen!");

        } catch (Exception e) {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    private void filterTreneri(String text) {

        if (text == null || text.isBlank()) {
            trenerTable.setItems(trenerList);
            return;
        }

        ObservableList<Trener> filtered = FXCollections.observableArrayList();

        for (Trener t : trenerList) {
            if (t.getIme().toLowerCase().contains(text.toLowerCase())) {
                filtered.add(t);
            }
        }

        trenerTable.setItems(filtered);
    }

    private void filterProgrami(String text) {

        if (text == null || text.isBlank()) {
            programTable.setItems(programList);
            return;
        }

        ObservableList<programTreninga> filtered = FXCollections.observableArrayList();

        for (programTreninga p : programList) {
            if (p.GetNaziv().toLowerCase().contains(text.toLowerCase())) {
                filtered.add(p);
            }
        }

        programTable.setItems(filtered);
    }


}