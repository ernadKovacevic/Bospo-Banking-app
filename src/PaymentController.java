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
import javafx.scene.Node;
import javafx.stage.Stage;

public class PaymentController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Region region;
    private ResultSet result;

    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker endDateField;
    @FXML
    private ChoiceBox<String> groupOneField;

    public void returnToHomeFrame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("HomeFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void doReport(ActionEvent event) throws IOException, SQLException{
        if(startDateField.getValue() != null){
            if(endDateField.getValue() != null){
                if(startDateField.getValue().isBefore(endDateField.getValue())
                || startDateField.getValue().isEqual(endDateField.getValue())){

                   // if(groupOneField.getValue() != null){
                        Statement stmn = Main.dbConnection.createStatement();
                        result = stmn.executeQuery(String.format("SELECT uredIsplata, COUNT(*)"+
                        ",SUM(iznosIsplKredit),MIN(iznosIsplKredit),MAX(iznosIsplKredit),"+
                        "AVG(iznosIsplKredit)"+ 
                        "FROM Kredit WHERE datumIsplata >= \"%s\" AND datumIsplata <= \"%s\""+
                        "GROUP BY uredIsplata;",startDateField.getValue(),endDateField.getValue()));

                   // }else{
                    //    JOptionPane.showMessageDialog(null, "ERROR: UNESITE URED PO KOJEM SE GRUPI??E!");
                   // }
                    openPaymentReportFrame();
                    clearFields();
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR: PO??ETNI DATUM VE??I OD KRAJNJEG!");
                }
            }else{
                JOptionPane.showMessageDialog(null, "ERROR: IZABERITE KRAJNJI DATUM!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "ERROR: IZABERITE PO??ETNI DATUM!");
        }
    }

    public void openPaymentReportFrame() throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentReportFrame.fxml"));
        root = loader.load();
        
        PaymentReportController report = loader.getController();
        report.changeDates(startDateField.getValue().toString(), endDateField.getValue().toString());
        report.addToTable(result);
        Stage thirdStage = new Stage();
        thirdStage.setTitle("Izvje??taj za isplate");
        thirdStage.setScene(new Scene(root,750,500));
        thirdStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] regionNames = Main.Mi_Bospo.getRegions();
        for(int i = 0; i < regionNames.length; i++){
            region = Main.Mi_Bospo.findRegion(regionNames[i]);
            String[] branchOfficeNames = region.getBranchOffices();

            for(int j = 0; j < branchOfficeNames.length-1; j++){
                BranchOffice bOffice = region.findBranchOffice(branchOfficeNames[j]);
                groupOneField.getItems().addAll(bOffice.getOffices());

            }
        } 
    }

    public void clearFields(){
        startDateField.setValue(null);
        endDateField.setValue(null);
    }
}
