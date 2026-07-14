package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.KategorijaRepositoryImpl;
import hr.java.FitnessCentar.Services.KategorijaService;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Kategorija;
import hr.java.FitnessCentar.model.entity.Oprema;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

public class KategorijaController {

    @FXML
    private TableView<Kategorija> kategorijaTable;

    @FXML
    private TableColumn<Kategorija, Integer> idColumn;

    @FXML
    private TableColumn<Kategorija, String> nazivColumn;

    @FXML
    private TableColumn<Kategorija, String> opisColumn;

    @FXML
    private TableColumn<Kategorija, String> vrstaColumn;

    @FXML
    private TextField searchField;

    @FXML
    private TextField nazivField;

    @FXML
    private TextField opisField;

    @FXML
    private ComboBox<vrstaGymaE> vrstaComboBox;

    private KategorijaService kategorijaService;



    private final ObservableList<Kategorija> kategorijaList = FXCollections.observableArrayList();




    @FXML
    private void initialize() {

        try {
            kategorijaService =
                   new KategorijaService(new KategorijaRepositoryImpl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        vrstaComboBox.setItems(
                FXCollections.observableArrayList(vrstaGymaE.values())
        );

        setUpColumns();
        loadData();
        tableListener();


    }



    private void tableListener() {
        kategorijaTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, kategorija) -> {
                    if(kategorija != null){
                        nazivField.setText(kategorija.GetNaziv());
                        opisField.setText(kategorija.GetOpis());
                        vrstaComboBox.getSelectionModel().select(kategorija.GetVrstaGymaE());

                    }
                });
    }

    public void loadData() {
        try{

            kategorijaList.clear();
            kategorijaList.addAll(kategorijaService.findAll());
            kategorijaTable.setItems(kategorijaList);




        }catch(Exception e){
            showError(e.getMessage());
        }

    }

    private void setUpColumns() {

        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().GetId()).asObject());
        nazivColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().GetNaziv()));
        opisColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().GetOpis()));
        vrstaColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().GetVrstaGymaE())));



    }

    @FXML
    private void search() {

        String rijec = searchField.getText().toLowerCase();

        if (rijec.isBlank()) {

            kategorijaTable.setItems(kategorijaList);

            return;
        }

        ObservableList<Kategorija> filtered = FXCollections.observableArrayList();

        for(Kategorija k : kategorijaList){
            if(k.GetNaziv().toLowerCase().contains(rijec)
            || k.GetOpis().toLowerCase().contains(rijec)) {
                filtered.add(k);
            }
        }
        kategorijaTable.setItems(filtered);

    }

    @FXML
    private void save() {

        try{

            Kategorija kat = new Kategorija();

            kat.SetNaziv(nazivField.getText());
            kat.SetOpis(opisField.getText());
            kat.SetVrstagymaE(vrstaComboBox.getValue());

            kategorijaService.save(kat);

            loadData();
            clearForm();

            showInfo("Kategorija spremljena");




        }catch(Exception e){
            showError(e.getMessage());
        }

    }

    private void clearForm() {

        nazivField.clear();
        opisField.clear();
        vrstaComboBox.getSelectionModel().clearSelection();
        kategorijaTable.getSelectionModel().clearSelection();

    }



    @FXML
    private void update() {

        Kategorija selectedKategorija = kategorijaTable.getSelectionModel().getSelectedItem();
        if(selectedKategorija == null){
            showError("izaberi Kategoriju!");
            return;
        }

        try{



            selectedKategorija.SetNaziv(nazivField.getText());
            selectedKategorija.SetOpis(opisField.getText());
            selectedKategorija.SetVrstagymaE(vrstaComboBox.getValue());

            kategorijaService.update(selectedKategorija.GetId(), selectedKategorija);

            loadData();

            showInfo("Kategorija izmjenjena");

        }catch(Exception e){
            showError(e.getMessage());
        }

    }

    @FXML
    private void delete() {

        Kategorija selectedKategorija = kategorijaTable.getSelectionModel().getSelectedItem();
        if(selectedKategorija == null){
            showError("Izaberi kategoriju!");
            return;
        }

        try{

            kategorijaService.delete(selectedKategorija.GetId());

            loadData();

            clearForm();

            showInfo("Kategorija izbrisana");

        }catch(Exception e){
            showError(e.getMessage());
        }

    }


}
