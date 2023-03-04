import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static String dbUrl = "jdbc:mysql://localhost:3306/bospo?characterEncoding=utf8";
    static String dbUsername = "root";
    static String dbPassword = "Bospo123.";
    static public Connection dbConnection;

    static BranchOffice Tuzla = new BranchOffice("Tuzla",new String []{"Tuzla 1", "Tuzla 2", "Tuzla 3"});
    static BranchOffice Lukavac = new BranchOffice("Lukavac", new String []{"Lukavac 1", "Lukavac 2", "Lukavac 3"});
    static BranchOffice Bijeljina = new BranchOffice("Bijeljina", new String []{"Bijeljina 1", "Bijeljina 2", "Bijeljina 3"});
    static BranchOffice Banja_Luka = new BranchOffice("Banja Luka", new String []{"Banja Luka 1", "Banja Luka 2", "Banja Luka 3"});
    static BranchOffice Sarajevo = new BranchOffice("Sarajevo", new String []{"Sarajevo 1", "Sarajevo 2", "Sarajevo 3"});
    static BranchOffice Ilidza = new BranchOffice("Ilidza",new String []{"Ilidza 1", "Ilidza 2", "Ilidza 3"});

    static Region Region_1 = new Region ("Region 1",new BranchOffice[] {Tuzla,Lukavac});
    static Region Region_2 = new Region ("Region 2",new BranchOffice[] {Bijeljina,Banja_Luka});
    static Region Region_3 = new Region ("Region 3",new BranchOffice[] {Sarajevo,Ilidza});

    static MI_BOSPO Mi_Bospo = new MI_BOSPO(new Region[] {Region_1,Region_2,Region_3});

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("HomeFrame.fxml"));
        primaryStage.setTitle("MI-BOSPO APLIKACIJA");
        primaryStage.setScene(new Scene(root, 620, 400));
        primaryStage.show();
    }

    public static void main(String[] args) { 
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            dbConnection=DriverManager.getConnection(dbUrl, dbUsername, dbPassword);  
            System.out.printf("########DATABASE CONNECTED######## \n \n");  
           
            }
        catch(Exception e){ System.out.println(e);} 
        
        launch(args);
    }
}