package Backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class handles writing to our result files
 * It creates a new file for each unique phone number entered by the user
 * It also logs all test data to the Test folder in an output file.
 *
 *
 * */
public class LogContent {
    private final  File results;
    private final File testing = new File("./Results/Tests/testingLogs.txt");

    public LogContent(String number) throws IOException {
        //if there is no data for the number then we make a new file to log the results
        results = new File("./Results//Numbers/" + number +".txt");

        if(!results.exists()){
            results.createNewFile();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        writeToResults("Number:  " + number + "                                            Date:  " + dtf.format(now)+ "\n" + "\n");

        if(!testing.exists()){
            testing.createNewFile();
        }
    }

    public void writeToResults(String input) {
        try {
            FileWriter logToResults = new FileWriter(results);
            logToResults.write(input);
            logToResults.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeToResults(HashMap<Integer, List<Word>> validWords) {

        try {
            FileWriter logToResults = new FileWriter(results, true);
            StringBuilder input = new StringBuilder();
            input.append("""
                    ----------------------------All Words Found for Your Number------------------------------

                    """);
            int counter = 1;
            for (Word w:validWords.get(3)) {
                input.append(ApplicationManager.phoneNumber.generateNumberString(w.getWord())).append(" ");
                if(counter %7 == 0){
                    input.append("\n");
                }
                counter ++;
            }

            for (Word w:validWords.get(4)) {
                input.append(ApplicationManager.phoneNumber.generateNumberString(w.getWord())).append(" ");
                if(counter %7 == 0){
                    input.append("\n");
                }
                counter++;
            }

            for (Word w:validWords.get(7)) {
                input.append(ApplicationManager.phoneNumber.generateNumberString(w.getWord())).append(" ");
                if(counter %7 == 0){
                    input.append("\n");
                }
                counter++;
            }

            input.append("""


                    """);
            logToResults.write(input.toString());
            logToResults.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToResults(ArrayList<String> finalTopTen) {
        try {
            FileWriter logToResults = new FileWriter(results, true);
            StringBuilder input = new StringBuilder();
            int counter = 1;
            input.append("""
                    -------------------------------Your Favorite Words------------------------------------

                    """);
            for (String phone:finalTopTen) {
                input.append(ApplicationManager.phoneNumber.generateNumberString(phone)).append("  ");
                if(counter %7 == 0){
                    input.append("\n");
                }
                counter ++;
            }

            logToResults.write(input.toString());
            logToResults.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToTesting(HashMap<Integer, List<Word>> list){
        try {
            FileWriter logToResults = new FileWriter(results, true);
            StringBuilder input = new StringBuilder();
            input.append("""
                    ----------------------------All Words Found for Your Number------------------------------

                    """);
            int counter = 1;
            for (Word w:list.get(3)) {
                input.append(ApplicationManager.phoneNumber.generateNumberString(w.getWord())).append(" ");
                if(counter %7 == 0){
                    input.append("\n");
                }
                counter ++;
            }

            for (Word w:list.get(4)) {
                input.append(ApplicationManager.phoneNumber.generateNumberString(w.getWord())).append(" ");
                if(counter %7 == 0){
                    input.append("\n");
                }
                counter++;
            }

            for (Word w:list.get(7)) {
                input.append(ApplicationManager.phoneNumber.generateNumberString(w.getWord())).append(" ");
                if(counter %7 == 0){
                    input.append("\n");
                }
                counter++;
            }

            input.append("""

                    """);
            logToResults.write(input.toString());
            logToResults.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
