package hr.java.FitnessCentar.Controllers;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;

import hr.java.FitnessCentar.Repository.Implementation.KorisnikRepositoryImpl;
import hr.java.FitnessCentar.model.Enum.UlogaE;
import hr.java.FitnessCentar.model.entity.Korisnik;
import hr.java.FitnessCentar.Services.KorisnikService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class KorisnikController {

    @FXML
    private TableView<Korisnik> korisnikTable;

    @FXML
    private TableColumn<Korisnik, Integer> idColumn;

    @FXML
    private TableColumn<Korisnik, String> imeColumn;

    @FXML
    private TableColumn<Korisnik, String> passwordColumn;

    @FXML
    private TableColumn<Korisnik, String> emailColumn;



    @FXML
    private TableColumn<Korisnik, String> ulogaColumn;


    @FXML
    private TextField imeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField searchField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<UlogaE> ulogaComboBox;

    private KorisnikService korisnikService;

    private final ObservableList<Korisnik> korisnikList = FXCollections.observableArrayList();



    @FXML
    private void initialize() {

        try{
            korisnikService = new KorisnikService(new KorisnikRepositoryImpl());

        }catch(Exception e){
            throw new RuntimeException(e);
        }


        setUpColumns();
        loadData();
        tableListener();
        ulogaComboBox.setItems(FXCollections.observableArrayList(UlogaE.values()));
    }
    private void setUpColumns() {

        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().GetId()).asObject());
        imeColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().GetKorisnikIme()));
        emailColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getEmail()));
        passwordColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLozinka()));
        ulogaColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().GetUlogaE())));

    }
    public void loadData() {

        try{

            korisnikList.clear();
            korisnikList.addAll(korisnikService.findAll());
            korisnikTable.setItems(korisnikList);

        }catch(Exception e){
            showError(e.getMessage());
        }

    }
    private void tableListener() {

        korisnikTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, Korisnik) -> {
            if (Korisnik != null) {
                imeField.setText(Korisnik.GetKorisnikIme());
                emailField.setText(Korisnik.getEmail());
                passwordField.setText(Korisnik.getLozinka());
                ulogaComboBox.getSelectionModel().select(Korisnik.GetUlogaE());
            }
        });
    }

    @FXML
    private void save(){

        try{
             Korisnik kor = new Korisnik();
             kor.SetkorisnikIme(imeField.getText());
             kor.SetEmail(emailField.getText());
             kor.SetLozinka(passwordField.getText());
             kor.SetUlogaE(ulogaComboBox.getValue());

             korisnikService.save(kor);

             loadData();
             clearForm();

             showInfo("Korisnik spremljen!");

        }catch(Exception e){
            showError(e.getMessage());
        }
    }

    private void clearForm() {
        imeField.clear();
        emailField.clear();
        passwordField.clear();
        ulogaComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void update(){

        Korisnik selectedKorisnik = korisnikTable.getSelectionModel().getSelectedItem();

        if(selectedKorisnik == null){
            showError("Korisnik ne postoji!");
            return;
        }

        try{

            selectedKorisnik.SetkorisnikIme(imeField.getText());
            selectedKorisnik.SetLozinka(passwordField.getText());
            selectedKorisnik.SetEmail(emailField.getText());
            selectedKorisnik.SetUlogaE(ulogaComboBox.getValue());
            korisnikService.update(selectedKorisnik.GetId(), selectedKorisnik);

            loadData();

            showInfo("Korisnik izmjenjen!");


        }catch(Exception e){
            showError(e.getMessage());
        }

    }
    @FXML
    private void delete(){

        Korisnik selectedKorisnik = korisnikTable.getSelectionModel().getSelectedItem();
        if(selectedKorisnik == null){
            showError("Korisnik ne postoji!");
            return;
        }

        try{

            korisnikService.delete(selectedKorisnik.GetId());
            loadData();
            clearForm();
            showInfo("Korisnik izbrisan!");

        }catch(Exception e){
            showError(e.getMessage());

        }

    }

    @FXML
    private void search(){
        String rijec  = searchField.getText().toLowerCase();
        if(rijec.isBlank()){
            korisnikTable.setItems(korisnikList);
            return;
        }

        ObservableList<Korisnik> filtered = FXCollections.observableArrayList();

        for(Korisnik k : korisnikList){
            if(k.GetKorisnikIme().toLowerCase().contains(rijec)
            || k.getEmail().toLowerCase().contains(rijec)
            || k.getLozinka().toLowerCase().contains(rijec)){
                filtered.add(k);
            }
        }
        korisnikTable.setItems(filtered);
    }
}
