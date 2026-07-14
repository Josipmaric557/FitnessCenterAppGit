package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.OpremaRepositoryImpl;
import hr.java.FitnessCentar.Services.OpremaService;
import hr.java.FitnessCentar.model.Enum.OpremaE;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Oprema;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class OpremaController {

    @FXML private TableView<Oprema> opremaTable;

    @FXML private TableColumn<Oprema, Integer> idColumn;
    @FXML private TableColumn<Oprema, String> nazivColumn;
    @FXML private TableColumn<Oprema, String> proizvodacColumn;
    @FXML private TableColumn<Oprema, Integer> kolicinaColumn;
    @FXML private TableColumn<Oprema, String> tipColumn;
    @FXML private TableColumn<Oprema, String> vrstaGymColumn;

    @FXML private TextField searchField;
    @FXML private TextField nazivField;
    @FXML private TextField proizvodacField;
    @FXML private TextField kolicinaField;

    @FXML private ComboBox<OpremaE> opremaTypeCombo;
    @FXML private ComboBox<vrstaGymaE> vrstaGymCombo;

    private OpremaService opremaService;

    public void setOpremaService(OpremaService service) {
        this.opremaService = service;
    }

    private final ObservableList<Oprema> opremaList = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws Exception {

        opremaService = new OpremaService(new OpremaRepositoryImpl());
        setUpColumns();
        loadEnums();
        loadData();
        tableListener();
    }







    private void loadEnums() {
        opremaTypeCombo.setItems(FXCollections.observableArrayList(OpremaE.values()));
        vrstaGymCombo.setItems(FXCollections.observableArrayList(vrstaGymaE.values()));
    }



    private void tableListener() {
        opremaTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, oprema) -> {
                    if (oprema != null) {

                        nazivField.setText(oprema.GetNaziv());
                        proizvodacField.setText(oprema.GetProizvodac());
                        kolicinaField.setText(String.valueOf(oprema.GetKolicina()));

                        opremaTypeCombo.getSelectionModel().select(oprema.GetOpremaE());
                        vrstaGymCombo.getSelectionModel().select(oprema.GetVrstaGyma());
                    }
                });
    }



    public void loadData() {
        try {


            opremaList.setAll(opremaService.findAll());
            opremaTable.setItems(opremaList);

        } catch (Exception e) {
            showError("Greška pri dohvaćanju podataka: " + e.getMessage());
        }
    }



    private void setUpColumns() {

        idColumn.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().GetId()).asObject());

        nazivColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().GetNaziv()));

        proizvodacColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().GetProizvodac()));

        kolicinaColumn.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().GetKolicina()).asObject());

        tipColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().GetOpremaE() != null
                                ? data.getValue().GetOpremaE().name()
                                : ""
                ));

        vrstaGymColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().GetVrstaGyma() != null
                                ? data.getValue().GetVrstaGyma().name()
                                : ""
                ));
    }



    @FXML
    private void search() {

        String rijec = searchField.getText();

        if (rijec == null || rijec.isBlank()) {
            opremaTable.setItems(opremaList);
            return;
        }

        rijec = rijec.toLowerCase();

        ObservableList<Oprema> filtered = FXCollections.observableArrayList();

        for (Oprema o : opremaList) {
            if (o.GetNaziv().toLowerCase().contains(rijec)
                    || o.GetProizvodac().toLowerCase().contains(rijec)) {
                filtered.add(o);
            }
        }

        opremaTable.setItems(filtered);
    }



    @FXML
    private void save() {

        try {


            if (!validate()) return;

            Oprema oprema = new Oprema();

            oprema.Setnaziv(nazivField.getText());
            oprema.SetProizvodac(proizvodacField.getText());
            oprema.SetKolicina(Integer.parseInt(kolicinaField.getText()));
            oprema.SetOpremaE(opremaTypeCombo.getValue());
            oprema.SetvrstaGymaE(vrstaGymCombo.getValue());

            opremaService.save(oprema);

            loadData();
            clearForm();

            showInfo("Oprema spremljena");

        } catch (NumberFormatException e) {
            showError("Količina mora biti broj");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    @FXML
    private void update() {

        Oprema selected = opremaTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Odaberi opremu");
            return;
        }

        try {


            if (!validate()) return;

            selected.Setnaziv(nazivField.getText());
            selected.SetProizvodac(proizvodacField.getText());
            selected.SetKolicina(Integer.parseInt(kolicinaField.getText()));
            selected.SetOpremaE(opremaTypeCombo.getValue());
            selected.SetvrstaGymaE(vrstaGymCombo.getValue());

            opremaService.update(selected.GetId(), selected);

            loadData();

            showInfo("Oprema izmijenjena");

        } catch (NumberFormatException e) {
            showError("Količina mora biti broj");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    @FXML
    private void delete() {

        Oprema selected = opremaTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Odaberi opremu");
            return;
        }

        try {


            opremaService.delete(selected.GetId());

            loadData();
            clearForm();

            showInfo("Oprema obrisana");

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    private boolean validate() {

        if (nazivField.getText().isBlank()
                || proizvodacField.getText().isBlank()
                || kolicinaField.getText().isBlank()
                || opremaTypeCombo.getValue() == null
                || vrstaGymCombo.getValue() == null) {

            showError("Popuni sva polja");
            return false;
        }

        return true;
    }



    private void clearForm() {

        nazivField.clear();
        proizvodacField.clear();
        kolicinaField.clear();

        opremaTypeCombo.getSelectionModel().clearSelection();
        vrstaGymCombo.getSelectionModel().clearSelection();
    }
}