package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.*;
import hr.java.FitnessCentar.Services.*;
import hr.java.FitnessCentar.Util.ImageUtil;
import hr.java.FitnessCentar.model.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import hr.java.FitnessCentar.Util.Session;

public class GymController implements Initializable {



    @FXML private TableView<Gym> gymTableView;
    @FXML private TableColumn<Gym, Integer> idColumn;
    @FXML private TableColumn<Gym, String> nazivColumn;
    @FXML private TableColumn<Gym, String> gradColumn;
    @FXML private TableColumn<Gym, String> adresaColumn;



    @FXML private TextField searchField;
    @FXML private TextField nazivField;
    @FXML private TextField adresaField;
    @FXML private TextField gradField;
    @FXML private TextField radnoVrijemeField;
    @FXML private TextField cijenaField;
    @FXML private TextField povrsinaField;
    @FXML private ImageView imageGymView;

    @FXML private Label selectedGymLabel;



    @FXML private ListView<String> kategorijaListView;
    @FXML private ListView<String> trenerListView;
    @FXML private ListView<String> programListView;
    @FXML private ListView<String> opremaListView;



    private GymService gymService;

    private GymTrenerService gymTrenerService;
    private GymOpremaService gymOpremaService;
    private GymProgramTreningaService gymProgramTreningaService;
    private GymKategorijaService gymKategorijaService;

    private String currentImagePath;

    private ObservableList<Gym> gyms = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            gymService = new GymService(new GymRepositoryImpl());
            gymTrenerService = new GymTrenerService(new GymTrenerRepositoryImpl());
            gymOpremaService = new GymOpremaService(new GymOpremaRepositoryImpl());
            gymProgramTreningaService = new GymProgramTreningaService(new GymProgramTreningaRepositoryImpl());
            gymKategorijaService = new GymKategorijaService(new GymKategorijaRepositoryImpl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            loadGyms();
            setupTable();
            setupSelection();
            setupSearch();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    private void loadGyms() throws Exception {
        gyms.setAll(gymService.findAll());
        gymTableView.setItems(gyms);
    }



    private void setupTable() {

        idColumn.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(d.getValue().GetId()));

        nazivColumn.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().GetNaziv()));

        gradColumn.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().GetGrad()));

        adresaColumn.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().GetAdresa()));
    }



    private void setupSelection() {

        gymTableView.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, gym) -> {

                    if (gym == null) {
                        imageGymView.setImage(null);
                        return;
                    }

                    fillFields(gym);
                    loadRelations(gym);


                });
    }



    private void setupSearch() {

        searchField.textProperty().addListener((obs, oldVal, text) -> {

            if (text == null || text.isBlank()) {
                gymTableView.setItems(gyms);
                return;
            }

            gymTableView.setItems(
                    FXCollections.observableArrayList(
                            gyms.stream()
                                    .filter(g -> g.GetNaziv().toLowerCase()
                                            .contains(text.toLowerCase()))
                                    .toList()
                    )
            );
        });
    }


    private void fillFields(Gym gym) {

        selectedGymLabel.setText(gym.GetNaziv());

        nazivField.setText(gym.GetNaziv());
        adresaField.setText(gym.GetAdresa());
        gradField.setText(gym.GetGrad());
        radnoVrijemeField.setText(gym.GetRadnoVrijeme());
        cijenaField.setText(gym.GetCijenaClanarine().toString());
        povrsinaField.setText(String.valueOf(gym.GetUkupnaPovrsina()));

        currentImagePath = gym.GetFotoPath();



        if (currentImagePath != null && !currentImagePath.isBlank()) {

            imageGymView.setImage(
                    new Image(new File(currentImagePath).toURI().toString())
            );

        } else {

            imageGymView.setImage(null);
        }
    }


    private void loadRelations(Gym gym) {

        kategorijaListView.getItems().clear();
        trenerListView.getItems().clear();
        programListView.getItems().clear();
        opremaListView.getItems().clear();

        try {


            gymKategorijaService.getKategorijaByGymId(gym.GetId())
                    .forEach(
                            k -> kategorijaListView.getItems().add(k.GetNaziv())
                    );


            gymTrenerService.getTreneriByGymId(gym.GetId())
                    .forEach(gt ->
                            trenerListView.getItems().add(
                                    gt.getIme()+ " " + gt.GetPrezime()
                            )
                    );


            gymProgramTreningaService.getProgramTreningaByGymId(gym.GetId())
                    .forEach(gp ->
                            programListView.getItems().add(
                                    gp.GetNaziv()
                            )
                    );


            gymOpremaService.getOpremaByGymId(gym.GetId())
                    .forEach(go ->
                            opremaListView.getItems().add(
                                    go.GetNaziv()
                            )
                    );

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    @FXML
    private void createGym() {

        try {
            Gym gym = buildGym();
            gym.SetFotoPath(currentImagePath);
            gymService.save(gym);
            loadGyms();
            clear();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    @FXML
    private void updateGym() {

        try {

            Gym selected = gymTableView.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showError("Odaberi gym!");
                return;
            }

            String oldImagePath = selected.GetFotoPath();

            Gym updatedGym = buildGym();

            gymService.update(selected.GetId(), updatedGym);

            if (oldImagePath != null
                    && !oldImagePath.isBlank()
                    && !oldImagePath.equals(updatedGym.GetFotoPath())) {

                ImageUtil.deleteImage(oldImagePath);
            }

            loadGyms();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    @FXML
    private void deleteGym() {

        try {
            Gym selected = gymTableView.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showError("Odaberi gym!");
                return;
            }



            gymService.delete(selected.GetId());

            loadGyms();
            clear();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }


    private Gym buildGym() {

        Gym gym = new Gym();

        gym.SetNaziv(nazivField.getText());
        gym.SetAdresa(adresaField.getText());
        gym.SetGrad(gradField.getText());
        gym.SetRadnoVrijeme(radnoVrijemeField.getText());
        gym.SetCijenaClanarine(new BigDecimal(cijenaField.getText()));
        gym.SetUkupnaPovrsina(Integer.parseInt(povrsinaField.getText()));

        gym.SetFotoPath(currentImagePath);

        return gym;
    }

    @FXML
    private void uploadImage() {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {

                currentImagePath = ImageUtil.saveImage(file);



                imageGymView.setImage(
                        new Image(new File(currentImagePath).toURI().toString())
                );

            } catch (Exception e) {
                showError(e.getMessage());
            }
        }
    }

    @FXML
    private void clear() {

        nazivField.clear();
        adresaField.clear();
        gradField.clear();
        radnoVrijemeField.clear();
        cijenaField.clear();
        povrsinaField.clear();

        currentImagePath = null;

        imageGymView.setImage(null);

        selectedGymLabel.setText("-- none --");
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }

    @FXML
    private void exportXml(Gym gym, String user) throws Exception {

    }

    public void importGym(String user) throws Exception {

    }


}