import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class PaymentReportController{
    
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private ChoiceBox<String> groupOneFiled;

    public void changeDates(String startDate, String endDate){
        startDateLabel.setText(startDate);
        endDateLabel.setText(endDate);
    }
}
