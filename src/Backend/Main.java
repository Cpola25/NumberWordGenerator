package Backend;

import UI.UI;
import javafx.application.Application;

import java.io.FileNotFoundException;
import java.util.*;

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




    public static void main(String[] args) throws Exception {


        UI runApplication = new UI();
        Application.launch(UI.class, args);
        //This is setting the Number Object

       // runApplication.printTable(t, testPhone);
        RelevanceGenerator getRelevant = new RelevanceGenerator();


    }

    public String printTable(HashMap<Integer, List<Word>> valid, Phone number){

        StringBuilder printTable = new StringBuilder();

        //43 spaces. 15 spaces 16 spaces
        printTable.append("----------------------------------------------------------------------------" + "\n" +
                "|                  3 Letter                 |                  4 Letter                 |                  7 Letter                 |" + "\n" +
                "----------------------------------------------------------------------------" + "\n"
        );
        // System.out.println("----------------------------------------------------------------------------" + "\n");
        // System.out.println("|        3 Letter        |        4 Letter        |        7 Letter        |");
        // System.out.println("----------------------------------------------------------------------------");

        for(int i = 0; i < valid.size(); i ++){
            String begining = number.number.substring(0, 3) + "-";
            String space = "               ";
            String first = "            ";
            String second ="            ";
            String third = "            ";

            if(i < valid.get(3).size()) {
                first = (begining + valid.get(3).get(i).word.toUpperCase(Locale.ROOT) + "-" + number.suffix) ;
            }
            if(i < valid.get(4).size()) {
                second = (begining + number.preFix + "-" + valid.get(4).get(i).word.toUpperCase(Locale.ROOT));
            }
            if(i < valid.get(7).size()) {
                String w = valid.get(7).get(i).word.toUpperCase(Locale.ROOT);
                third = (begining + w.substring(0, 3) + "-" + w.substring(3, 7));
            }

            printTable.append("|" + space + first + space + "|"  + space + second+ space + "|" + space + third + space + "|"  + "\n");
            //System.out.println("|      " + first + "      |      " + second + "      |      " + third +"      |");
        }
        printTable.append("----------------------------------------------------------------------------" + "\n");
        //System.out.println("----------------------------------------------------------------------------");

        return printTable.toString();
    }


}
