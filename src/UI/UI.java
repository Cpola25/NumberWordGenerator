package UI;

import Backend.Main;
import Backend.Phone;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class will Define the UI
 *
 * First Screen:
 *
 * We need to ask the user for their number
 * and keep asking until a valid number is entered
 *
 * Second Screen:
 *
 * Should display once we have a valid number
 * We could have a message that informs the user of how many words match thier number
 * Ex: "Congrats we found 364 words that match your number!"
 * Maybe a button n
 * */

public class UI extends Application{

        private Stage stage;
        private Scene scene;
        private Parent root;



    /************************************************** Switch Screen Methods ***************************************************************/

    public void switchToEnterNumber(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./Screens/EnterNumber.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCongrats(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./Screens/Congrats.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToDisplayResults(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./Screens/DisplayResults.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTopTen(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./Screens/TopTen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   /************************************************** Enter Number Screen ***************************************************************/

   @FXML
    private TextField number;
    @FXML
    private Button enterNumber;
    @FXML
    private Text errorMessage;


    public void submitNumber(ActionEvent event){
        try{
            Main.setPhoneNumber(new Phone(number.getText()));
            Main.setCombinations();
            Main.setWordList();

            switchToCongrats(event);
        }catch(Exception e){
            errorMessage.setText(String.valueOf(e));
        }
    }

    /************************************************** Congrats Screen ***************************************************************/
    @FXML
    private Text resultNumber;

    public void setTotal(){
        resultNumber.setText(Integer.toString(Main.listSize));
    }
    public void continueToDisplay(ActionEvent event){
        try{
            switchToDisplayResults(event);
        }catch(Exception e){
            System.out.println(e);
        }
    }


    /************************************************** Display Screen ***************************************************************/



    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("./Screens/StartUp.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

public static void main(String[] args){
        launch(args);
}

}
