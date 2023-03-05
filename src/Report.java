import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Report {
    private SimpleStringProperty officeName;
    private SimpleIntegerProperty numOfLoans;
    private SimpleDoubleProperty sumOfLoans;
    private SimpleDoubleProperty minLoan;
    private SimpleDoubleProperty maxLoan;
    private SimpleDoubleProperty avgLoan;

    public Report(String name, int loans, double sum, double min, double max, double avg ){
        this.officeName = new SimpleStringProperty(name);
        this.numOfLoans = new SimpleIntegerProperty(loans);
        this.sumOfLoans = new SimpleDoubleProperty(sum);
        this.minLoan = new SimpleDoubleProperty(min);
        this.maxLoan = new SimpleDoubleProperty(max);
        this.avgLoan = new SimpleDoubleProperty(avg);
    }

    public String getOfficeName(){
        return this.officeName.get();
    }

    public void setOfficeName(String name){
        this.officeName = new SimpleStringProperty(name);
    }

    public int getNumOfLoans(){
        return this.numOfLoans.get();
    }

    public void setNumOfLoans(int loans){
        this.numOfLoans = new SimpleIntegerProperty(loans);
    }

    public double getSumOfLoans(){
        return this.sumOfLoans.get();
    }

    public void setSumOfLoans(double sum){
        this.sumOfLoans = new SimpleDoubleProperty(sum);
    }

    public double getMinLoan(){
        return this.minLoan.get();
    }

    public void setMinLoan(double min){
        this.minLoan = new SimpleDoubleProperty(min);
    }

    public double getMaxLoan(){
        return this.maxLoan.get();
    }

    public void setMaxLoan(double max){
        this.maxLoan = new SimpleDoubleProperty(max);
    }

    public double getAvgLoan(){
        return this.avgLoan.get();
    }

    public void setAvgLoan(double avg){
        this.avgLoan = new SimpleDoubleProperty(avg);
    }
}
