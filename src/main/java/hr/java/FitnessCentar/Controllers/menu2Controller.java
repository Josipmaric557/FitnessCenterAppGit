package hr.java.FitnessCentar.Controllers;

import hr.java.FitnessCentar.Repository.Implementation.GymRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.OpremaRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.ProgramTreningaRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.TrenerRepositoryImpl;
import hr.java.FitnessCentar.Repository.interfaces.GymRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

// import tvojih modela (prilagodi paketu)
import hr.java.FitnessCentar.model.entity.Gym;
import hr.java.FitnessCentar.model.entity.Trener;
import hr.java.FitnessCentar.model.entity.programTreninga;
import hr.java.FitnessCentar.model.entity.Oprema;

// import tvojih servisa/DAO (PRILAGODI)
import hr.java.FitnessCentar.Services.GymService;
import hr.java.FitnessCentar.Services.TrenerService;
import hr.java.FitnessCentar.Services.ProgramTreningaService;
import hr.java.FitnessCentar.Services.OpremaService;

public class menu2Controller {

        @FXML
        private Label gymsCountLabel;

        @FXML
        private Label treneriCountLabel;

        @FXML
        private Label programiCountLabel;

        @FXML
        private Label opremaCountLabel;

        private GymService gymService;
        private TrenerService trenerService;
        private ProgramTreningaService programService;
        private OpremaService opremaService;

        @FXML
        public void initialize() throws Exception {

            // 1. INIT SERVICES FIRST
            gymService = new GymService(new GymRepositoryImpl());
            trenerService = new TrenerService(new TrenerRepositoryImpl());
            programService = new ProgramTreningaService(new ProgramTreningaRepositoryImpl());
            opremaService = new OpremaService(new OpremaRepositoryImpl());

            // 2. THEN LOAD DATA
            loadStats();
        }

        public void loadStats() {
            try {
                List<Gym> gyms = gymService.findAll();
                List<Trener> treneri = trenerService.findAll();
                List<programTreninga> programi = programService.findAll();
                List<Oprema> oprema = opremaService.findAll();

                gymsCountLabel.setText(String.valueOf(gyms.size()));
                treneriCountLabel.setText(String.valueOf(treneri.size()));
                programiCountLabel.setText(String.valueOf(programi.size()));
                opremaCountLabel.setText(String.valueOf(oprema.size()));

            } catch (Exception e) {
                gymsCountLabel.setText("ERR");
                treneriCountLabel.setText("ERR");
                programiCountLabel.setText("ERR");
                opremaCountLabel.setText("ERR");

                e.printStackTrace();
            }
        }

        @FXML
        public void refreshDashboard() {
            loadStats();
        }
}