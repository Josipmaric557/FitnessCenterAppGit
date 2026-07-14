package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.TrenerRepositoryImpl;
import hr.java.FitnessCentar.Services.TrenerService;
import hr.java.FitnessCentar.model.entity.Trener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import hr.java.FitnessCentar.Util.AlertUtil;

import java.util.Locale;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class TrenerController {

    @FXML
    private TableView<Trener> trenerTable;

    @FXML
    private TableColumn<Trener, Integer> idColumn;

    @FXML
    private TableColumn<Trener, String> imeColumn;

    @FXML
    private TableColumn<Trener, String> prezimeColumn;

    @FXML
    private TableColumn<Trener, String> specColumn;

    @FXML
    private TableColumn<Trener, String> certColumn;


    @FXML
    private TextField imeField;

    @FXML
    private TextField prezimeField;

    @FXML
    private TextField specField;

    @FXML
    private TextField certField;

    @FXML
    private TextField photoField;

    @FXML
    private TextField searchField;

    private final ObservableList<Trener> trenerList = FXCollections.observableArrayList();

    private TrenerService trenerService;

    public void setTrenerService(TrenerService trenerService) {
        this.trenerService = trenerService;
    }

    @FXML
    private void initialize() {
        try {
            trenerService = new TrenerService(new TrenerRepositoryImpl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setUpColumns();
        loadData();
        tableListener();
    }

    private void tableListener() {
        trenerTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, trener) -> {
                    if(trener != null){
                        imeField.setText(trener.getIme());
                        prezimeField.setText(trener.GetPrezime());
                        specField.setText(trener.GetSpecijalizacije());
                        certField.setText(trener.GetCertifikacije());
                        photoField.setText(trener.GetPhotoPath());
                    }
                });
    }

    public void loadData() {

        try{
            trenerList.clear();
            trenerList.addAll(trenerService.findAll());
            trenerTable.setItems(trenerList);

        }catch(Exception e){
            showError(e.getMessage());
        }
    }

    private void setUpColumns() {
        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().GetId()).asObject());
        imeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIme()));
        prezimeColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().GetPrezime()));
        specColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().GetSpecijalizacije()));
        certColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().GetCertifikacije()));
    }


    public void save() {
        try{

            Trener trener = new Trener();

            trener.SetIme(imeField.getText());
            trener.SetPrezime(prezimeField.getText());
            trener.SetSpecijalizacije(specField.getText());
            trener.SetCertifikacije(certField.getText());
            trener.SetPhotoPath(photoField.getText());

            trenerService.save(trener);

            loadData();
            clearForm();

            showInfo("Trener uspiješno spremljen");

        }catch(Exception e){
            showError(e.getMessage());
        }
    }






    public void update() {

        Trener selectedTrener = trenerTable.getSelectionModel().getSelectedItem();

        if(selectedTrener == null){
            showError("Izaberi trenera");
            return;
        }
        try{

            selectedTrener.SetIme(imeField.getText());
            selectedTrener.SetPrezime(prezimeField.getText());
            selectedTrener.SetSpecijalizacije(specField.getText());
            selectedTrener.SetCertifikacije(certField.getText());
            selectedTrener.SetPhotoPath(photoField.getText());

            trenerService.update(selectedTrener.GetId(), selectedTrener);

            loadData();

            showInfo("Trener izmjenjen!!");



        }catch(Exception e){
            showError(e.getMessage());
        }
    }

    public void delete() {
        Trener selectedTrener = trenerTable.getSelectionModel().getSelectedItem();
        if(selectedTrener == null){
            showError("Izaberi trenera");
            return;
        }

        try{
            trenerService.delete(selectedTrener.GetId());

            loadData();

            clearForm();

            showInfo("Trener uspijesno izbrisan!!");

        }catch(Exception e){
            showError(e.getMessage());
        }
    }
    private void clearForm() {

        imeField.clear();
        prezimeField.clear();
        specField.clear();
        certField.clear();
        photoField.clear();
    }



    public void search() {
        String rijec = searchField.getText().toLowerCase();

        ObservableList<Trener> filtered = FXCollections.observableArrayList();

        for(Trener t : trenerList){
            if(t.getIme().toLowerCase().contains(rijec)
            || t.GetPrezime().toLowerCase().contains(rijec)
            || t.GetSpecijalizacije().toLowerCase().contains(rijec)){

                filtered.add(t);
            }
        }
        trenerTable.setItems(filtered);
    }
}
