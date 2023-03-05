import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ReportController implements Initializable{

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Region region;
    private ResultSet result;

    @FXML
    private DatePicker dateFromField;
    @FXML
    private DatePicker dateToField;
    @FXML
    private ChoiceBox<String> regionField;
    @FXML
    private ChoiceBox<String> branchOfficeField;
    @FXML
    private ChoiceBox<String> officeField;

    //final report frame fields

    public void returnToHomeFrame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("HomeFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regionField.getItems().add("Sve regije");
        regionField.getItems().addAll(Main.Mi_Bospo.getRegions());
        regionField.setValue("Sve regije");
    }

    public void chooseBranchOffice(){
       
        region = Main.Mi_Bospo.findRegion(regionField.getValue());
        branchOfficeField.getItems().removeAll(branchOfficeField.getItems());
        branchOfficeField.getItems().addAll(region.getBranchOffices());

    }

    public void chooseOffice(){

        BranchOffice branchOffice = region.findBranchOffice(branchOfficeField.getValue());
        officeField.getItems().removeAll(officeField.getItems());
        officeField.getItems().addAll(branchOffice.getOffices());

    }

    public void doReport() throws SQLException, IOException{
        if(dateFromField.getValue() != null){
            if(dateToField.getValue() != null){
                if(dateFromField.getValue().isBefore(dateToField.getValue()) 
                   || dateFromField.getValue().equals(dateToField.getValue())){

                    Statement stmn = Main.dbConnection.createStatement();

                    if(regionField.getValue().equals("Sve regije")){
                        result = stmn.executeQuery(String.format(
                        "SELECT * FROM Kredit where datumIsplata >= \"%s\" AND datumIsplata <= \"%s\"",
                        dateFromField.getValue(),dateToField.getValue()));   
                    }else{
                        if(branchOfficeField.getValue() != null){
                            if(officeField.getValue() != null){
                                result = stmn.executeQuery(String.format(
                                "SELECT * FROM Kredit where datumIsplata >= \"%s\" AND datumIsplata <= \"%s\" AND uredIsplata = \"%s\"",
                                dateFromField.getValue(),dateToField.getValue(),officeField.getValue()));
                            }else{
                                JOptionPane.showMessageDialog(null,"ERROR: ODABERITE URED");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"ERROR: ODABERITE PODRUŽNICU");
                        }
                    }             
                    JOptionPane.showMessageDialog(null,"IZVJEŠTAJ URAĐEN");

                    openFinalReportFrame();

                    clearFields();
                }else{
                    JOptionPane.showMessageDialog(null,"ERROR: POČETNI DATUM VEĆI OD KRAJNJEG");
                }
            }else{
                JOptionPane.showMessageDialog(null,"ERROR: KRAJNJI DATUM NIJE VALIDAN");
            }
        }else{
            JOptionPane.showMessageDialog(null,"ERROR: POČETNI DATUM NIJE VALIDAN");
        }
    }

    public void clearFields(){
        dateFromField.setValue(null);
        dateToField.setValue(null);
        regionField.setValue("Sve regije");
        branchOfficeField.setValue(null);
        officeField.setValue(null);
    }

    public void openFinalReportFrame() throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinalReportFrame.fxml"));
        root = loader.load();
        FinalReportController report = loader.getController();
        report.changeDateLabels(dateFromField.getValue().toString(),dateToField.getValue().toString());
        report.addToTable(result);
        //report.startReportFrame();
        Stage secondStage = new Stage();
        secondStage.setTitle("IZVJEŠTAJ");
        secondStage.setScene(new Scene(root, 750, 500));
        secondStage.show();
    }
    

}