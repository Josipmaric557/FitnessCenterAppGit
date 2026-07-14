package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.ProgramTreningaRepositoryImpl;
import hr.java.FitnessCentar.model.Enum.TezinaTreningaE;
import hr.java.FitnessCentar.model.Enum.programTreningaE;
import hr.java.FitnessCentar.model.entity.programTreninga;
import hr.java.FitnessCentar.Services.ProgramTreningaService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import static hr.java.FitnessCentar.Util.AlertUtil.showError;
import static hr.java.FitnessCentar.Util.AlertUtil.showInfo;


public class ProgramTreningaController {

    //////////////////neradi tezinatreninga pise da je null treba provjerit
    ///

    @FXML
    private TableView<programTreninga> programTable;

    @FXML
    private TableColumn<programTreninga, Integer> idColumn;

    @FXML
    private TableColumn<programTreninga, String> nazivColumn;

    @FXML
    private TableColumn<programTreninga, String> opisColumn;

    @FXML
    private TableColumn<programTreninga, Integer> trajanjeColumn;

    @FXML
    private TableColumn<programTreninga, String>  tipColumn;

    @FXML
    private TableColumn<programTreninga, String> tezinaColumn;

    @FXML
    private TextField nazivField;

    @FXML
    private TextField opisField;

    @FXML
    private TextField trajanjeField;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<programTreningaE> tipCombo;

    @FXML
    private ComboBox<TezinaTreningaE>  tezinaCombo1;

    private ProgramTreningaService programTreningaService;

    private final ObservableList<programTreninga> programTreningaList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try{

            programTreningaService = new ProgramTreningaService(new ProgramTreningaRepositoryImpl());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        setUpColumns();
        loadData();
        tableListener();



        tipCombo.setItems(
                FXCollections.observableArrayList(programTreningaE.values())
        );

        tezinaCombo1.setItems(
                FXCollections.observableArrayList(TezinaTreningaE.values())
        );

    }

    private void setUpColumns() {

        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().Getid()).asObject());
        nazivColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().GetNaziv()));
        opisColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().GetOpis()));
        trajanjeColumn.setCellValueFactory(data-> new SimpleIntegerProperty(data.getValue().GetTrajanjeMin()).asObject());
        tipColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().GetProgramTreningaE())));
        tezinaColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().GetTezinaTreningaE())));
    }

    public void loadData() {
        try{

            programTreningaList.clear();
            programTreningaList.addAll(programTreningaService.findAll());
            programTable.setItems(programTreningaList);


        }catch(Exception e){
            showError(e.getMessage());
        }

    }

    private void tableListener() {

        programTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, programTreninga) -> {
            if(programTreninga != null){
                nazivField.setText(programTreninga.GetNaziv());
                opisField.setText(programTreninga.GetOpis());
                trajanjeField.setText(String.valueOf(programTreninga.GetTrajanjeMin()));
                tipCombo.getSelectionModel().select(programTreninga.GetProgramTreningaE());
                tezinaCombo1.getSelectionModel().select(programTreninga.GetTezinaTreningaE());
            }
        });

    }

    @FXML
    private void save(){
        try{
            programTreninga pt = new  programTreninga();

            pt.Setnaziv(nazivField.getText());
            pt.SetOpis(opisField.getText());
            pt.SettrajanjeMin(Integer.parseInt(trajanjeField.getText()));
            pt.SetProgramTreningaE(tipCombo.getValue());
            pt.SetTezinaTreningaE(tezinaCombo1.getValue());

            programTreningaService.save(pt);

            loadData();
            clearForm();

            showInfo("Program treninga spremljen");

        }catch(Exception e){
            showError(e.getMessage());
        }

    }

    private void clearForm() {
        nazivField.clear();
        opisField.clear();
        trajanjeField.clear();
        tipCombo.getSelectionModel().clearSelection();
        tezinaCombo1.getSelectionModel().clearSelection();
    }

    @FXML
    private void update(){

        programTreninga selectedPT = programTable.getSelectionModel().getSelectedItem();
        if(selectedPT == null){
            showError("Izaberi program treninga");
            return;
        }

        try{
            selectedPT.Setnaziv(nazivField.getText());
            selectedPT.SetOpis(opisField.getText());
            selectedPT.SettrajanjeMin(Integer.parseInt(trajanjeField.getText()));
            selectedPT.SetProgramTreningaE(tipCombo.getValue());
            selectedPT.SetTezinaTreningaE(tezinaCombo1.getValue());

            programTreningaService.update(selectedPT.Getid(), selectedPT);

            loadData();

            showInfo("Program treninga izmjenjen");

        }catch(Exception e){
            showError(e.getMessage());
        }


    }

    @FXML
    private void delete(){

        programTreninga selectedPT = programTable.getSelectionModel().getSelectedItem();
        if(selectedPT == null){
            showError("Izaberi program treninga");
            return;
        }
        try{
            programTreningaService.delete(selectedPT.Getid());
            loadData();
            clearForm();
            showInfo("Program treninga izbrisan");

        }catch(Exception e){
            showError(e.getMessage());

        }

    }

    @FXML
    private void search(){

        String rijec = searchField.getText().toLowerCase();

        if(rijec.isBlank()){
            programTable.setItems(programTreningaList);

            return;
        }

        ObservableList<programTreninga> filtered = FXCollections.observableArrayList();

        for(programTreninga pt: programTreningaList){
            if(pt.GetNaziv().toLowerCase().contains(rijec)
            || pt.GetOpis().toLowerCase().contains(rijec)){
                filtered.add(pt);
            }
        }

        programTable.setItems(filtered);
    }

}
