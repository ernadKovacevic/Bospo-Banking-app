import java.sql.Date;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Credit {
    public SimpleStringProperty jmbgKomitent;
    public SimpleStringProperty idCredit;
    public SimpleStringProperty paymentDate;
    public SimpleDoubleProperty amount;
    public SimpleDoubleProperty interestRate;
    public SimpleDoubleProperty repaymentMonths;
    public SimpleStringProperty office;

    public Credit(String jmbg, String id, Date date, double amount_,
    double interestRate_, double repaymentMonths_,String office_ ){
        this.jmbgKomitent = new SimpleStringProperty(jmbg);
        this.idCredit = new SimpleStringProperty(id);
        this.paymentDate = new SimpleStringProperty(date.toString());
        this.amount = new SimpleDoubleProperty(amount_);
        this.interestRate = new SimpleDoubleProperty(interestRate_);
        this.repaymentMonths = new SimpleDoubleProperty(repaymentMonths_);
        this.office = new SimpleStringProperty(office_);
    }
    
    public void print(){
        System.out.println(jmbgKomitent);
        System.out.println(idCredit);
        System.out.println(paymentDate);
        System.out.println(amount);
        System.out.println(interestRate);
        System.out.println(repaymentMonths);
        System.out.println(office);
    }



    public String getJMBGKomitent(){
        return jmbgKomitent.get();
    }

    public void setJMBGKomitent(String s){
        this.jmbgKomitent = new SimpleStringProperty(s);
    }

    public String getIdCredit(){
        return idCredit.get();
    }

    public void setIdCredit(String s){
        this.idCredit = new SimpleStringProperty(s);
    }


    public String getPaymentDate(){
        return paymentDate.get();
    }

    public void setPaymentDate(String s){
        this.paymentDate = new SimpleStringProperty(s);
    }

    public double getAmount(){
        return amount.get();
    }
    
    public void setAmount(double d){
        this.amount = new SimpleDoubleProperty(d);
    }

    public double getInterestRate(){
        return interestRate.get();
    }
    
    public void setInterestRate(double d){
        this.interestRate = new SimpleDoubleProperty(d);
    }

    public double getRepaymentMonths(){
        return repaymentMonths.get();
    }
    
    public void setRepaymentMonths(double d){
        this.repaymentMonths = new SimpleDoubleProperty(d);
    }


    public String getOffice(){
        return office.get();
    }
    
    public void setOffice(String s){
        this.office = new SimpleStringProperty(s);
    }


}
