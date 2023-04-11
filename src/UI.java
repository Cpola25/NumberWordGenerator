

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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

public class UI extends Application {
//4093642255

public static void main(String[] args){
    launch(args);
}

    @Override
    public void start(Stage stage) throws Exception {
        //Display Window
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 500, 500);

        //Phone Number input Text Box
        Label label = new Label("Enter Number");
        TextField phoneInput = new TextField();
        phoneInput.setPrefColumnCount(10);
        pane.addRow(0, label, phoneInput);

        //this is so we can display the table results in the display window
        Text printedTable = new Text();

        //Submit Button
        Button submit = new Button("Sumbit");
        pane.addRow(1, submit);
        submit.setOnAction(event -> {
            try{
                Phone number =  new Phone(phoneInput.getText());
                LetterGenerator combos  = new LetterGenerator(number);
                WordDatabase r = new WordDatabase();
                printedTable.setText(printTable( r.dictionaryQuery(combos.getCombinations()), number));
                pane.addRow(2, printedTable);
            }catch (Exception e){

                printedTable.setText(e.toString());
                pane.addRow(2, printedTable);
                System.out.println(e);
            }

        });


        stage.setTitle("Find the Right Word!");
        stage.setScene(scene);
        stage.show();
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
