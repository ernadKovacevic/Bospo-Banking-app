import com.mysql.jdbc.ResultSet;

import javafx.fxml.FXML;

import javafx.scene.control.Label;


public class FinalReportController {

    @FXML
    Label startDateLabel;
    @FXML
    Label endDateLabel;


    public void changeDateLabels(String startDate, String endDate){
        startDateLabel.setText(startDate);
        endDateLabel.setText(endDate);
    }

    public void addToTable(ResultSet rows){
        
    }

}
