import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PaymentReportController implements Initializable{
    
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private ChoiceBox<String> groupOneFiled;
    @FXML 
    private TableView<Report> table;
    @FXML
    private TableColumn<Report,String> colOffice;
    @FXML
    private TableColumn<Report,Integer> colNumOfLoans;
    @FXML
    private TableColumn<Report,Double> colSumOfLoans;
    @FXML
    private TableColumn<Report,Double> colMinLoan;
    @FXML
    private TableColumn<Report,Double> colMaxLoan;
    @FXML 
    private TableColumn<Report,Double> colAvgLoan;

    public void changeDates(String startDate, String endDate){
        startDateLabel.setText(startDate);
        endDateLabel.setText(endDate);
    }

    public void addToTable(ResultSet result) throws SQLException{
        while(result.next()){
            Report newReport = new Report(result.getString(1),
            result.getInt(2), result.getDouble(3), result.getDouble(4),
             result.getDouble(5), result.getDouble(6));

            table.getItems().add(newReport);

        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOffice.setCellValueFactory(new PropertyValueFactory<>("OfficeName"));
        colNumOfLoans.setCellValueFactory(new PropertyValueFactory<>("NumOfLoans"));
        colSumOfLoans.setCellValueFactory(new PropertyValueFactory<>("SumOfLoans"));
        colMinLoan.setCellValueFactory(new PropertyValueFactory<>("MinLoan"));
        colMaxLoan.setCellValueFactory(new PropertyValueFactory<>("MaxLoan"));
        colAvgLoan.setCellValueFactory(new PropertyValueFactory<>("AvgLoan"));

    }
}
