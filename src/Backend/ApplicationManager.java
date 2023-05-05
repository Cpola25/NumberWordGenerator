package Backend;

import UI.UI;
import javafx.application.Application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ApplicationManager {

    //Declares an instance of WordDatabase object that will let access the word database
    public static WordDatabase wordDatabase;
    static {
        try {
            wordDatabase = new WordDatabase();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // Declares an instance of PhoneNumber
    public static PhoneNumber phoneNumber;
    //Declares an instance of LetterGenerator
    public static LetterGenerator combinations;

    //Stores all the words that can be generated from the number
    public static HashMap<Integer, List<Word>> numberValidWordResults;
    //Stores the size of the numberValidWordResults
    private static int listSize;

    private static LogContent logger;

    public static void main(String[] args) {
        //Launches the Application
        Application.launch(UI.class, args);
    }

    /************************************************** Setters ***************************************************************/

    // queries the database to get the list of words that can be generated from the number and initializes local class variable numberValidWordResults
    public static void setWordList() {

        //queries the database and initializes numberValidWordResults list
        ApplicationManager.numberValidWordResults = wordDatabase.dictionaryQuery(combinations.getLetterCombinations());
        //will add our results to our file
        //will get the starting size of the list so we can display on the congrats screen
        if(ApplicationManager.numberValidWordResults.get(3) != null){
            setListSize(getListSize() + ApplicationManager.numberValidWordResults.get(3).size());
        }
        if(ApplicationManager.numberValidWordResults.get(4) != null){
            setListSize(getListSize() + ApplicationManager.numberValidWordResults.get(4).size());

        }

        if(ApplicationManager.numberValidWordResults.get(7) != null){
            setListSize(getListSize() + ApplicationManager.numberValidWordResults.get(7).size());
        }

    }
    // will be called by the UI class to set the combinations list
    public static void setCombinations() {
        ApplicationManager.combinations = new LetterGenerator(ApplicationManager.phoneNumber);
    }

    //will be called by the UI to set the phoneNumber
    public static void setPhoneNumber(PhoneNumber phoneNumber) {
        ApplicationManager.phoneNumber = phoneNumber;
    }

    public static int getListSize() {
        return listSize;
    }

    public static void setListSize(int listSize) {
        ApplicationManager.listSize = listSize;
    }

    public static void setLogContent(String number) throws IOException {
        logger = new LogContent(number, false);
        logger.writeToResults(numberValidWordResults);
    }

    public static LogContent getLogger() {
        return logger;
    }

    public static void appReset(){
        ApplicationManager.phoneNumber = null;
        listSize = 0;
    }
}


