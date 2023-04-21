package UI;

import Backend.Main;
import Backend.Phone;
import Backend.Word;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        static ArrayList<String> finalTopTenWords = new ArrayList<>();

    /************************************************** Switch Screen Methods ***************************************************************/

    public void switchToCongrats(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./Screens/Congrats.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
    public void switchToDisplayResults(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./Screens/DisplayResults.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTopTen(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./Screens/TopChoices.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchTeam(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./Screens/Credits.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   /************************************************** Enter Number Screen ***************************************************************/

   @FXML
    private TextField number;
    @FXML
    private Text errorMessage;


    public void submitNumber(ActionEvent event){
        try{
            Main.setPhoneNumber(new Phone(number.getText())); //Sets Phone Object in the Main class
            Main.setCombinations(); // Sets the combinations object in the Main class
            Main.setWordList(); // Sets the wordList object in the Main class
            switchToCongrats(event); //Switched the screen to the Congrats page
        }catch(Exception e){
            errorMessage.setText(String.valueOf(e)); // if there is an error then it will be displayed to help the user update their input
        }
    }

    /************************************************** Congrats Screen ***************************************************************/
    @FXML
    private Text numberOfResults = new Text();

    //This will let us get the total number of matched that were found so we can display
    public void setTotal(){
        numberOfResults.setText(Integer.toString(Main.listSize));
    }
    public void continueToDisplay(ActionEvent event){
        try{
            switchToDisplayResults(event); //Switches screen to the Display page
        }catch(Exception e){
            System.out.println(e);
        }
    }
    /************************************************** Display Screen ***************************************************************/
    @FXML
    private VBox threeLetter;
    @FXML
    private VBox fourLetter;
    @FXML
    private VBox sevenLetter;

    ArrayList<Word> threeLetterValidWords;
    ArrayList<Word> fourLetterValidWords;
    ArrayList<Word> sevenLetterValidWords;

    List<Node> threeLetterVboxNodes;
    List<Node> fourLetterVboxNodes;
    List<Node> sevenLetterVboxNodes;


    public void loadMoreWords(){
        threeLetterValidWords = (ArrayList<Word>) Main.wordList.get(3);
        fourLetterValidWords = (ArrayList<Word>) Main.wordList.get(4);
        sevenLetterValidWords = (ArrayList<Word>) Main.wordList.get(7);

        threeLetterVboxNodes = threeLetter.getChildren();
        fourLetterVboxNodes = fourLetter.getChildren();
        sevenLetterVboxNodes = sevenLetter.getChildren();

        for(int i = 0; i < 10; i++){
            //gets child checkboxes from the VBox parent
            CheckBox currentThree = (CheckBox) threeLetterVboxNodes.get(i);
            if(i < threeLetterValidWords.size()){
                currentThree.setText( Main.phoneNumber.generateNumberString(3, threeLetterValidWords.get(i).getWord().toUpperCase()));
                currentThree.setAccessibleText(threeLetterValidWords.get(i).getWord());
            }
            CheckBox currentFour = (CheckBox) fourLetterVboxNodes.get(i);
            if(i < fourLetterValidWords.size()){
                currentFour.setText( Main.phoneNumber.generateNumberString(4, fourLetterValidWords.get(i).getWord().toUpperCase()));
                currentFour.setAccessibleText(fourLetterValidWords.get(i).getWord());
            }
            CheckBox currentSeven = (CheckBox) sevenLetterVboxNodes.get(i);
            if(i < sevenLetterValidWords.size()){
                currentSeven.setText( Main.phoneNumber.generateNumberString(7, sevenLetterValidWords.get(i).getWord().toUpperCase()));
                currentSeven.setAccessibleText(sevenLetterValidWords.get(i).getWord());
            }
        }
    }
    public void refreshList(ActionEvent event){
        int firstListSize = Main.wordList.get(3).size();
        int secondListSize = Main.wordList.get(4).size();
        int thirdListSize = Main.wordList.get(7).size();

        if(( firstListSize + secondListSize + thirdListSize) < 11 || Main.listSize < 11){

            if(firstListSize !=0)
            for (Node currentCheckBox: threeLetterVboxNodes) {
                CheckBox checkBox = (CheckBox) currentCheckBox;
                if(!Objects.equals(checkBox.getText(), ""))
                    UI.finalTopTenWords.add(checkBox.getText());
            }

            //selection disappeared check again
            if(secondListSize !=0)
            for (Node currentCheckBox: fourLetterVboxNodes) {
                CheckBox checkBox = (CheckBox) currentCheckBox;
                if(!Objects.equals(checkBox.getText(), ""))
                    UI.finalTopTenWords.add(checkBox.getText());
            }

            if(thirdListSize != 0)
            for (Node currentCheckBox: sevenLetterVboxNodes) {
                CheckBox checkBox = (CheckBox) currentCheckBox;
                if(!Objects.equals(checkBox.getText(), ""))
                    UI.finalTopTenWords.add(checkBox.getText());
            }
            continueTopTen(event);
        }else{
            for(int i = 0; i < 10; i++){
                //gets child checkboxes from the VBox parent
                CheckBox currentThreeLetterCheckBox = (CheckBox) threeLetterVboxNodes.get(i);
                //if the box is not selected and it has text remove it from word list
                if(!currentThreeLetterCheckBox.isSelected() && !Objects.equals(currentThreeLetterCheckBox.getText(), "")){
                    Main.wordList.get(3).removeIf(w -> Objects.equals(currentThreeLetterCheckBox.getAccessibleText(), w.getWord()));
                    //currentThreeLetterCheckBox.setText("");
                }else{
                    currentThreeLetterCheckBox.setSelected(false);
                }

                CheckBox currentFourLetterCheckBox = (CheckBox) fourLetterVboxNodes.get(i);
                if(!currentFourLetterCheckBox.isSelected() && !Objects.equals(currentFourLetterCheckBox.getText(), "")){
                    Main.wordList.get(4).removeIf(w -> Objects.equals(currentFourLetterCheckBox.getAccessibleText(), w.getWord()));
                    //currentFourLetterCheckBox.setText("");
                }else{
                    currentFourLetterCheckBox.setSelected(false);
                }

                CheckBox currentSevenLetterCheckBox = (CheckBox) sevenLetterVboxNodes.get(i);
                if(!currentSevenLetterCheckBox.isSelected() && !Objects.equals(currentSevenLetterCheckBox.getText(), "")){
                    Main.wordList.get(7).removeIf(w -> Objects.equals(currentSevenLetterCheckBox.getAccessibleText(), w.getWord()));
                    //currentFourLetterCheckBox.setText("");
                }else{
                    currentSevenLetterCheckBox.setSelected(false);
                }
                currentThreeLetterCheckBox.setText("");
                currentFourLetterCheckBox.setText("");
                currentSevenLetterCheckBox.setText("");
            }

            loadMoreWords();
        }

    }
    //move to next screen
    public void continueTopTen(ActionEvent event){
        try{
            switchToTopTen(event);
        }catch(Exception e){
            System.out.println(e);
        }
    }


    /************************************************** Top Ten Screen ***************************************************************/

    @FXML
    private VBox topTen;

    public void displayTen(){
       List<Node> topTenVBoxNodes = topTen.getChildren();
        for(int i = 0; i < topTenVBoxNodes.size(); i++){
            Text currentTextField = (Text) topTenVBoxNodes.get(i);
            if(i < finalTopTenWords.size()) {
                currentTextField.setText(finalTopTenWords.get(i));
            }
        }
    }


    /**************************************************      Credits     ***************************************************************/




    public void exit(ActionEvent event){
        Platform.exit();
    }
    /**************************************************      Other     ***************************************************************/
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./Screens/EnterNumber.fxml")));
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
