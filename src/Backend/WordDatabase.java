package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Will be the class responsible for direct querying of the database
 * Will store database into a hashmap with keys representing every unique 3 letter prefix and
 * the values representing an ArrayList of words starting with that prefix
 *
 * Using this class we can pass the resulting combination list from the LetterGenerator class to the
 * dictioraryQuery method which will return a smaller hashmap composed of the letter combinations that
 * are valid words
 *
 * */

public class WordDatabase{

    //This is where we will store the words from the database
    private HashMap<String, List<Word>> dictionaryDatabase;

    //Constructor that initializes the dictionary map
    public WordDatabase() throws FileNotFoundException {
        if(ApplicationManager.wordDatabase == null) {
            System.out.println("New Dictionary Initialized");
            // This is where we link our database
            File database = new File("./database.txt");
            dictionaryDatabase = new HashMap<>();
            //create a scanner object to read the text file
            Scanner dataReader = new Scanner(database);
            while (dataReader.hasNextLine()) {
                String test = dataReader.nextLine();
                String[] words = test.split("\\s+");
                for (String word : words) {
                    String pre = word.substring(0, 3);

                    if (!dictionaryDatabase.containsKey(pre)) {
                        dictionaryDatabase.put(pre, new ArrayList<>());
                    }
                    dictionaryDatabase.get(pre).add(new Word(word));
                }
            }
        }else{
            System.out.println("Dictionary Already Initialized");
        }
    }

    //This will take the map generated from the LetterGenerator class to filter out the non-valid words
   public HashMap<Integer, List<Word>> dictionaryQuery(HashMap<Integer, List<Word>> combos){

       //This will be the list with all the valid words that can be generated from the number
       HashMap<Integer, List<Word>> wordsFoundInDictionary = new HashMap<>() {{
           put(3, new ArrayList<>());
           put(4, new ArrayList<>());
           put(7, new ArrayList<>());
       }};

       for (Word letterCombination:combos.get(3)) {
           String currentWordPrefix = letterCombination.getPre();
           if(dictionaryDatabase.containsKey(currentWordPrefix)){
               for (Word dictionaryWord : dictionaryDatabase.get(currentWordPrefix)) {
                   if(Objects.equals(letterCombination.getWord(), dictionaryWord.getWord())){
                       wordsFoundInDictionary.get(3).add(letterCombination);
                   }
               }
           }
       }

       for (Word letterCombination:combos.get(4)) {
           String currentWordPrefix = letterCombination.getPre();
           if(dictionaryDatabase.containsKey(currentWordPrefix)){
               for (Word dictionaryWord : dictionaryDatabase.get(currentWordPrefix)) {
                   if(Objects.equals(letterCombination.getWord(), dictionaryWord.getWord())){
                       wordsFoundInDictionary.get(4).add(letterCombination);
                   }
               }
           }
       }
       for (Word letterCombination:combos.get(7)) {
           String currentWordPrefix = letterCombination.getPre();
           if(dictionaryDatabase.containsKey(currentWordPrefix)){
               for (Word dictionaryWord : dictionaryDatabase.get(currentWordPrefix)) {
                   if(Objects.equals(letterCombination.getWord(), dictionaryWord.getWord())){
                       wordsFoundInDictionary.get(7).add(letterCombination);
                   }
               }
           }
       }

       return wordsFoundInDictionary;
   }

}
