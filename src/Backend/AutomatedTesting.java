package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class will run a number of automated scripts which test each of the programs classes
 * */


public class AutomatedTesting {

    public static void main(String[] args) throws FileNotFoundException{
            File bulkLoadFile = new File("./BulkLoading/BulkLoadingDocument.txt");
             Scanner fileReader = new Scanner(bulkLoadFile);

             while(fileReader.hasNextLine()){
                 String test = fileReader.nextLine();
                 String[] words = test.split("\\s+");
                 for (String word : words) {
                     //word = each phoneNumber
                     PhoneNumber currentNumber = null;
                     LogContent testingLogger;
                     try {
                         currentNumber = new PhoneNumber(word);
                         testingLogger = new LogContent(word);
                     } catch (Exception e) {
                         e.printStackTrace();
                         continue;
                     }
                     ApplicationManager.appReset(); // if there is any previous information in the initialized variables this will clear it
                     ApplicationManager.setPhoneNumber(currentNumber);
                     ApplicationManager.setCombinations();
                     ApplicationManager.setWordList();
                    testingLogger.writeToTesting(ApplicationManager.numberValidWordResults);
                 }
             }
    }
}
