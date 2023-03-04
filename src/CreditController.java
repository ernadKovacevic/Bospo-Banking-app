import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CreditController implements Initializable{

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Region region;

    @FXML
    private TextField idCreditField;
    @FXML
    private TextField jmbgKomitentField;
    @FXML
    private DatePicker datePaymentField;
    @FXML
    private TextField loanAmountField;
    @FXML
    private TextField repaymentMonthsField;
    @FXML
    private TextField interestRateField;
    @FXML
    private ChoiceBox<String> regionField;
    @FXML
    private ChoiceBox<String> branchOfficeField;
    @FXML
    private ChoiceBox<String> officeField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regionField.getItems().addAll(Main.Mi_Bospo.getRegions());
    }


    public void returnToHomeFrame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("HomeFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onInsert(ActionEvent event) throws SQLException{
        try{
            Integer.parseInt(idCreditField.getText());
            try{
                Long.parseLong(jmbgKomitentField.getText()); 
        
                if(jmbgKomitentField.getText().length() != 13){
                    JOptionPane.showMessageDialog(null, "JMBG MORA IMATI 13 BROJEVA");
                }else{
                    if(idCreditField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "UNESITE BROJ KREDITA");
                    }else{

                        try{
                            if((Double.parseDouble(interestRateField.getText()) > 0 && 
                                Double.parseDouble(interestRateField.getText()) <= 100)){

                                try{

                                    if((Double.parseDouble(repaymentMonthsField.getText()) > 0 && 
                                        Double.parseDouble(repaymentMonthsField.getText()) <= 100)){

                                        try{

                                            Statement stmn = Main.dbConnection.createStatement();
                           
                                            stmn.execute(String.format("INSERT INTO Kredit VALUES(%s,%s,%s,%s,%s,%s,%s)"
                                            ,"\""+jmbgKomitentField.getText()+"\""
                                            ,"\""+idCreditField.getText()+"\""
                                            ,datePaymentField.getValue() != null ? "\""+datePaymentField.getValue() + "\"" : "NULL"
                                            ,!loanAmountField.getText().isEmpty() ? Double.parseDouble(loanAmountField.getText()) : "NULL"
                                            ,Double.parseDouble(interestRateField.getText())   
                                            ,Double.parseDouble(repaymentMonthsField.getText())
                                            ,officeField.getValue() != null ? "\""+officeField.getValue()+"\"" : "NULL"
                                            ));
            
                                            JOptionPane.showMessageDialog(null, "NOVI KREDIT DODAN U BAZU");

                                            jmbgKomitentField.clear();
                                            idCreditField.clear();
                                            datePaymentField.setValue(null);
                                            loanAmountField.clear();
                                            interestRateField.clear();
                                            repaymentMonthsField.clear();
                                            regionField.setValue(null);
                                            branchOfficeField.setValue(null);
                                            officeField.setValue(null);
                                        }
                                        catch(MySQLIntegrityConstraintViolationException e){
                                            JOptionPane.showMessageDialog(null, "BROJ KREDITA POSTOJI U BAZI");   
                                        } 
        
                                    }else{
                                        JOptionPane.showMessageDialog(null, "ERROR : ROK OTPLATE : 1-100 mjeseci");
                                    }
                                }
                                catch(NumberFormatException e){
                                    JOptionPane.showMessageDialog(null, "ERROR: UNESITE BROJ MJESECI");
                                }   
                            }else{
                                JOptionPane.showMessageDialog(null, "ERROR : KAMATNA STOPA : 1-100%");
                            }   
                        }
                        catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "ERROR : UNESITE KAMATNU STOPU");
                        }
                    }
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "JMBG NIJE VALIDAN"); 
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "BROJ KREDITA NIJE VALIDAN"); 
        }
    }

    public void onDelete(ActionEvent event){
        try{
            Integer.parseInt(idCreditField.getText());
    
            try{
                Statement stmn = Main.dbConnection.createStatement();
                ResultSet result = stmn.executeQuery(String.format("SELECT * FROM Kredit WHERE idKredit = \"%s\"", idCreditField.getText()));
                if(result.next()){
                    stmn.execute(String.format("DELETE FROM Kredit WHERE idKredit = \"%s\"",idCreditField.getText()));
                    JOptionPane.showMessageDialog(null, "KREDIT OBRISAN");
                }else{
                    JOptionPane.showMessageDialog(null, "KREDIT NE POSTOJI U BAZI");
                }
            }
            catch(Exception e){
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "BROJ KREDITA NIJE VALIDAN");
        }
    }

    public void onChange(ActionEvent event){

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

}
