/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.LookupVar;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.Doctors;
import static controllers.Doctors.loadDoctorTV;
import controllers.PatientsRequest;
import controllers.RefereeCommission;
import controllers.Services;
import controllers.ServicesCommision;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class AddReferralsController implements Initializable {

    @FXML
    private Button saveReferees;
    @FXML
    private Button resetRef;

    @FXML
    private TextArea servicesOffered;
    @FXML
    private TextField commissionDue;
    RefereeCommission refcom;
    /**
     * Initializes the controller class.
     */
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private HBox refereeDoctorid;
    @FXML
    private Button selectDoctor;
    @FXML
    private TextField doctorsName;
    Doctors lookupDoctors;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refcom = new RefereeCommission();
        saveReferees.setOnAction(onActionListener);
        selectDoctor.setOnAction(onActionListener);

    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            if (saveReferees == e.getSource()) {
                RefereeCommission refCom = new RefereeCommission();
                refCom.setPatientId(PatientsRequest.globalPatientId);
                refCom.setDoctorId("" + lookupDoctors.getDoctorid());
                refCom.setServices(servicesOffered.getText());
                refCom.setTotalCom(Double.parseDouble(commissionDue.getText()));
                refCom.setCount(refCom.maxCount(PatientsRequest.globalPatientId) + 1);
                if (refCom.save()) {

                    Stage stage = (Stage) saveReferees.getScene().getWindow();
                    stage.close();
                    DISPLAY_MESSAGE.showInfoDialog(saveReferees.getScene().getWindow(), "Successfully Saved Referee Commission", NotificationType.INFORMATION);
                    ((Node) (e.getSource())).getScene().getWindow().hide();

//              Patients_RecordController prc=new Patients_RecordController();
//              prc.loadDoctorsComTv(PatientsRequest.globalPatientId,lookupDoctors.getDoctorid());
                } else {
                    DISPLAY_MESSAGE.showInfoDialog(saveReferees.getScene().getWindow(), "Failed to save Referee Commission", NotificationType.ERROR);
                }

            }

            if (selectDoctor == e.getSource()) {

                Stage utility = new Stage();
                LookupVar.SEARCH = LookupVar.LOOK_UP.LOOKUP_CONTR;

                utility.setTitle("SELECT DOCTOR");
                LookupVar.SEARCH.setTbl_items(loadDoctorTV());
                SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());

                LookupVar.SEARCH.getTxt_search().setOnKeyReleased((KeyEvent event) -> {
                    LookupVar.SEARCH.getTbl_items().getItems().clear();
                    // LookupVar.SEARCH.getTbl_items().getItems().addAll(clientTV(LookupVar.SEARCH.getTxt_search().getText()).getItems());
                    SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());
                });
                LookupVar.SEARCH.getTbl_items().setOnMouseClicked((MouseEvent event) -> {

                    if (event.getClickCount() == 2) {
                        lookupDoctors = (Doctors) LookupVar.SEARCH.getTbl_items().getSelectionModel().getSelectedItem();

                        RefereeCommission refCom = new RefereeCommission();
                        refCom.setDoctorId("" + lookupDoctors.getDoctorid());
                        doctorsName.setText(lookupDoctors.getName());
                        Doctors.setGlobalDoctorName(lookupDoctors.getName());

                        utility.close();

                        refCom.fetchService("" + lookupDoctors.getDoctorid(), PatientsRequest.globalPatientId);
                        Doctors dr = new Doctors();
                        Doctors.setSelectedDoctorId("" + lookupDoctors.getDoctorid());

                        Services serv = new Services();
                        servicesOffered.setText(null);
                        ArrayList<String> servOffered = serv.fetchServicesOffered(PatientsRequest.globalPatientId, serv.maxServiceCount(PatientsRequest.globalPatientId));
                        for (String servce : servOffered) {
                            servicesOffered.appendText(servce + "\n");
                        }
                        commissionDue.setText("" + serv.getTotalCom(PatientsRequest.globalPatientId, serv.maxServiceCount(PatientsRequest.globalPatientId)));
                   }
                     

                });

                utility.initModality(Modality.WINDOW_MODAL);
                utility.initOwner(selectDoctor.getScene().getWindow());
                utility.setScene(LookupVar.LOOK_UP.lookupScene);
                utility.setIconified(false);
                utility.show();

            }
            
        }
    }

    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }

    }

}
