import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

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

public class KomitentController implements Initializable {
    

    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    private TextField jmbgField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField addressField;
    @FXML
    private ChoiceBox<String> cityField;
    @FXML 
    private DatePicker dateField = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] cities = {"Tuzla", "Lukavac", "Bijeljina", "Banja Luka", "Sarajevo", "Ilidža"};
        cityField.getItems().addAll(cities);
    }

    public void returnToHomeFrame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("HomeFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onInsert(ActionEvent event) throws IOException, SQLException{
        try{
            Long.parseLong(jmbgField.getText());
        
            if(jmbgField.getLength() != 13){
            JOptionPane.showMessageDialog(null, "JMBG MORA IMATI 13 BROJEVA");
            }
            else{
                try{
                    if(dateField.getValue().isBefore(LocalDate.now())){

                        Statement stmn = Main.dbConnection.createStatement();
                        stmn.execute(String.format("INSERT INTO Komitent VALUES (%s,%s,%s,%s,%s,%s)"
                        ,jmbgField.getText()
                        ,!nameField.getText().isEmpty() ? "\""+nameField.getText()+"\"" : "NULL"
                        ,!surnameField.getText().isEmpty() ? "\""+surnameField.getText()+"\"" : "NULL"
                        ,dateField.getValue() != null ? "\""+dateField.getValue()+"\"" : "NULL"
                        ,cityField.getValue() != null ? "\""+cityField.getValue()+"\"" : "NULL"
                        ,!addressField.getText().isEmpty() ? "\""+addressField.getText()+"\"" : "NULL"
                        ));

                        JOptionPane.showMessageDialog(null, "NOVI KOMITENT DODAN U BAZU");

                        jmbgField.clear();
                        nameField.clear();
                        surnameField.clear();
                        dateField.setValue(null);
                        cityField.setValue(null);
                        addressField.clear();
                    }else{
                        JOptionPane.showMessageDialog(null, "DATUM VEĆI OD DANAŠNJEG");
                    }
                }
                catch(MySQLIntegrityConstraintViolationException e){
                    JOptionPane.showMessageDialog(null, "JMBG POSTOJI U BAZI!!!");
                }
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "JMBG NIJE VALIDAN!!!");
        }
    }

    public void onDelete(ActionEvent event){
        if(jmbgField.getLength() !=13 ){
            JOptionPane.showMessageDialog(null, "UNESITE VALIDAN JMBG!!!");
        }else{
            try{
                Statement stmn = Main.dbConnection.createStatement();
                ResultSet results = stmn.executeQuery(String.format("SELECT * FROM Komitent WHERE JMBG = \"%s\"", jmbgField.getText()));
                if(results.next()){
                    stmn.execute(String.format("DELETE FROM Komitent WHERE JMBG = \"%s\"",jmbgField.getText()));
                    JOptionPane.showMessageDialog(null,"KOMITENT OBRISAN IZ BAZE");
                }else{
                    JOptionPane.showMessageDialog(null,"KOMITENT NE POSTOJI U BAZI");
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"ERROR");
            }
        }
    }
    //promjena svih vrijednosti ili samo onih koje unesemo(gubi se mogucnost za NULL) 
    public void onChange(ActionEvent event){
        if(jmbgField.getLength() !=13 ){
            JOptionPane.showMessageDialog(null, "JMBG MORA IMATI 13 BROJEVA!!!");
        }else{
            try{
                Statement stmn = Main.dbConnection.createStatement();
                Statement checkJMBG = Main.dbConnection.createStatement();
                ResultSet results = checkJMBG.executeQuery(String.format("SELECT * FROM Komitent WHERE JMBG = \"%s\"", jmbgField.getText()));
                
                if(results.next()){

                    if(!nameField.getText().isEmpty()){
                        stmn.execute(String.format("UPDATE Komitent SET ime = \"%s\" WHERE JMBG = \"%s\""
                        ,nameField.getText(),jmbgField.getText()));
                    }

                    if(!surnameField.getText().isEmpty()){
                        stmn.execute(String.format("UPDATE Komitent SET prezime = \"%s\" WHERE JMBG = \"%s\""
                        ,surnameField.getText(),jmbgField.getText()));
                    }

                    if(dateField.getValue() != null){
                        if(dateField.getValue().isBefore(LocalDate.now()))
                            stmn.execute(String.format("UPDATE Komitent SET datumRodjenja = \"%s\" WHERE JMBG = \"%s\""
                            ,dateField.getValue(),jmbgField.getText()));
                        else{
                            JOptionPane.showMessageDialog(null,"ERROR: DATUM VEĆI OD DANJAŠNJEG!!!");
                        }
                    }

                    if(cityField.getValue() != null){
                        stmn.execute(String.format("UPDATE Komitent SET grad = \"%s\" WHERE JMBG = \"%s\""
                        ,cityField.getValue(),jmbgField.getText()));
                    }

                    if(!addressField.getText().isEmpty()){
                        stmn.execute(String.format("UPDATE Komitent SET adresaStan = \"%s\" WHERE JMBG = \"%s\""
                        ,addressField.getText(),jmbgField.getText()));
                    }
                 
                    JOptionPane.showMessageDialog(null,"ISPRAVLJENI PODACI O KOMITENTU");
                }else{
                    JOptionPane.showMessageDialog(null,"KOMITENT NE POSTOJI U BAZI");
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"ERROR");
            }

        }
    }





}
