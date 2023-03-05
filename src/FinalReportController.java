import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class FinalReportController implements Initializable{

    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private TableView<Credit> table;
    @FXML
    private TableColumn<Credit,String> jmbgColumn;
    @FXML
    private TableColumn<Credit,String> idCreditColumn;
    @FXML
    private TableColumn<Credit,String> paymentDateColumn;
    @FXML
    private TableColumn<Credit,Double> amountColumn;
    @FXML
    private TableColumn<Credit,Double> interestRateColumn;
    @FXML
    private TableColumn<Credit,Double> repaymentColumn;
    @FXML
    private TableColumn<Credit,String> officeColumn;
    


    public void changeDateLabels(String startDate, String endDate){
        startDateLabel.setText(startDate);
        endDateLabel.setText(endDate);
    }

    public void addToTable(ResultSet rows) throws SQLException{
        while(rows.next()){
            System.out.println(rows.getString(1)+ " | " +rows.getString(2) + " | "
                        + rows.getDate(3)+ " | " +rows.getDouble(4) + " | " 
                        + rows.getDouble(5)+ " | " +rows.getDouble(6) + " | "
                        + rows.getString(7));
            Credit newCredit = new Credit(
            rows.getString(1), rows.getString(2), rows.getDate(3),
            rows.getDouble(4), rows.getDouble(5), rows.getDouble(6),rows.getString(7));
            newCredit.print();
            table.getItems().add(newCredit);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jmbgColumn.setCellValueFactory(new PropertyValueFactory<>("JMBGKomitent"));
        idCreditColumn.setCellValueFactory(new PropertyValueFactory<>("IdCredit"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("PaymentDate"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
       
        interestRateColumn.setCellValueFactory(new PropertyValueFactory<>("InterestRate"));
        
        repaymentColumn.setCellValueFactory(new PropertyValueFactory<>("RepaymentMonths"));
        
        officeColumn.setCellValueFactory(new PropertyValueFactory<>("Office"));
    
    }

}
