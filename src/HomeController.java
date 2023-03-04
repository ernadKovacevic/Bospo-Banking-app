import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Scene scene;
    private Stage stage;
    private Parent root;
 //   private Button defineKomitent;
 //   private Button defineCredit;


    public void switchToAddKomitentFrame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("AddKomitentFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddCreditFrame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("AddCreditFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 

    public void switchToReportFrame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("ReportFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToPaymentFrame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("PaymentFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 






}