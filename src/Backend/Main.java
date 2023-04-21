package Backend;

import UI.UI;
import javafx.application.Application;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class Main{

    public static WordDatabase dictionary;
    static {
        try {
            dictionary = new WordDatabase();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Phone phoneNumber;
    public static LetterGenerator combinations;
    public static HashMap<Integer, List<Word>> wordList;
    public static int listSize;

    public static void main(String[] args) throws Exception {

        UI runApplication = new UI();
        Application.launch(UI.class, args);
        //This is setting the Number Object
        //get number and only retrieve the integers

    }

    public Main() throws FileNotFoundException {
    }

    public static void setWordList() {
        Main.wordList = dictionary.dictionaryQuery(combinations.getCombinations());

        if(Main.wordList.get(3) != null){
            listSize += Main.wordList.get(3).size();
        }
        if(Main.wordList.get(4) != null){
            listSize += Main.wordList.get(4).size();
        }
        if(Main.wordList.get(7) != null){
            listSize += Main.wordList.get(7).size();
        }

    }

    public static void setCombinations() {
        Main.combinations = new LetterGenerator(Main.phoneNumber);
    }

    public static void setPhoneNumber(Phone phoneNumber) {
        Main.phoneNumber = phoneNumber;
    }


}
