package UI;

import Backend.ApplicationManager;
import Backend.PhoneNumber;
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

    private static final ArrayList<String> finalTopTenWords = new ArrayList<>();

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
            ApplicationManager.setPhoneNumber(new PhoneNumber(number.getText())); //Sets PhoneNumber Object in the ApplicationManager class
            ApplicationManager.setCombinations(); // Sets the combinations object in the ApplicationManager class
            ApplicationManager.setWordList(); // Sets the wordList object in the ApplicationManager class
            ApplicationManager.setLogContent(number.getText());
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
        numberOfResults.setText( Integer.toString(ApplicationManager.getListSize()));
    }

    public void continueToDisplay(ActionEvent event){
        try{
            if((ApplicationManager.getListSize()) < 11){
                for (Word t: ApplicationManager.numberValidWordResults.get(3)) {
                    finalTopTenWords.add(t.getWord());
                }
                for (Word f: ApplicationManager.numberValidWordResults.get(4)) {
                    finalTopTenWords.add(f.getWord());
                }
                for (Word s: ApplicationManager.numberValidWordResults.get(7)) {
                    finalTopTenWords.add(s.getWord());
                }
                continueTopTen(event);
            }else{
                switchToDisplayResults(event); //Switches screen to the Display page
            }
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

    private List<Node> threeLetterVboxNodes;
    private List<Node> fourLetterVboxNodes;
    private List<Node> sevenLetterVboxNodes;

    //Looks through our list of words so it can refill the checkboxes
    public void loadMoreWords(){
        ArrayList<Word> threeLetterValidWords = (ArrayList<Word>) ApplicationManager.numberValidWordResults.get(3);
        ArrayList<Word> fourLetterValidWords = (ArrayList<Word>) ApplicationManager.numberValidWordResults.get(4);
        ArrayList<Word> sevenLetterValidWords = (ArrayList<Word>) ApplicationManager.numberValidWordResults.get(7);

        threeLetterVboxNodes = threeLetter.getChildren();
        fourLetterVboxNodes = fourLetter.getChildren();
        sevenLetterVboxNodes = sevenLetter.getChildren();

        for(int i = 0; i < 10; i++){
            //gets child checkboxes from the VBox parent
            CheckBox currentThree = (CheckBox) threeLetterVboxNodes.get(i);
            if(i < threeLetterValidWords.size()){
                currentThree.setText( ApplicationManager.phoneNumber.generateNumberString(threeLetterValidWords.get(i).getWord()));
                currentThree.setAccessibleText(threeLetterValidWords.get(i).getWord());
            }
            CheckBox currentFour = (CheckBox) fourLetterVboxNodes.get(i);
            if(i < fourLetterValidWords.size()){
                currentFour.setText( ApplicationManager.phoneNumber.generateNumberString( fourLetterValidWords.get(i).getWord()));
                currentFour.setAccessibleText(fourLetterValidWords.get(i).getWord());
            }
            CheckBox currentSeven = (CheckBox) sevenLetterVboxNodes.get(i);
            if(i < sevenLetterValidWords.size()){
                currentSeven.setText( ApplicationManager.phoneNumber.generateNumberString( sevenLetterValidWords.get(i).getWord()));
                currentSeven.setAccessibleText(sevenLetterValidWords.get(i).getWord());
            }
        }
    }

    public void refreshList(ActionEvent event){

        //we are garuanteed to have words at this stage to process.
        //When this button is push it means that we have to search all the checkboxes to see which ones have been selected.
        if((ApplicationManager.getListSize()) < 11){

            continueTopTen(event);

        }else{

            for(int i = 0; i < 10; i++){
                //gets child checkboxes from the VBox parent
                CheckBox currentThreeLetterCheckBox = (CheckBox) threeLetterVboxNodes.get(i);
                CheckBox currentFourLetterCheckBox = (CheckBox) fourLetterVboxNodes.get(i);
                CheckBox currentSevenLetterCheckBox = (CheckBox) sevenLetterVboxNodes.get(i);

                //if the box is not selected and it has text remove it from word list
                if(!currentThreeLetterCheckBox.isSelected() && !Objects.equals(currentThreeLetterCheckBox.getText(), "")){
                    ApplicationManager.numberValidWordResults.get(3).removeIf(w -> Objects.equals(currentThreeLetterCheckBox.getAccessibleText(), w.getWord()));
                    ApplicationManager.setListSize(ApplicationManager.getListSize() - 1);
                    //currentThreeLetterCheckBox.setText("");
                }else if (currentThreeLetterCheckBox.isSelected()){

                    currentThreeLetterCheckBox.setSelected(false);
                }
                if(!currentFourLetterCheckBox.isSelected() && !Objects.equals(currentFourLetterCheckBox.getText(), "")){
                    ApplicationManager.numberValidWordResults.get(4).removeIf(w -> Objects.equals(currentFourLetterCheckBox.getAccessibleText(), w.getWord()));
                    ApplicationManager.setListSize(ApplicationManager.getListSize() - 1);
                    //currentFourLetterCheckBox.setText("");
                }else{
                    currentFourLetterCheckBox.setSelected(false);
                }


                if(!currentSevenLetterCheckBox.isSelected() && !Objects.equals(currentSevenLetterCheckBox.getText(), "")){
                    ApplicationManager.numberValidWordResults.get(7).removeIf(w -> Objects.equals(currentSevenLetterCheckBox.getAccessibleText(), w.getWord()));
                    ApplicationManager.setListSize(ApplicationManager.getListSize() - 1);
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
        ApplicationManager.getLogger().writeToResults(finalTopTenWords);

        for(int i = 0; i < topTenVBoxNodes.size(); i++){
            Text currentTextField = (Text) topTenVBoxNodes.get(i);
            if(i < finalTopTenWords.size()) {

                currentTextField.setText(ApplicationManager.phoneNumber.generateNumberString(finalTopTenWords.get(i)));
            }
        }
    }


    /**************************************************      Credits     ***************************************************************/

    public void exit(){
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
