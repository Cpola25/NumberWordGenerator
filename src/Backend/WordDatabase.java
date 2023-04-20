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
 * dictioraryQuery method which will return a smalled hashmap composed of the letter combinations that
 * are valid words
 *
 *
 * What I still want to do:
 *
 * I want to use a singleton pattern on the dictionary hashmap so we don't read the entire database everytime a
 * new instance of WordDatabase is created
 *
 *
 * */

public class WordDatabase{

    // This is where we link our database
    private final File database = new File("./database.txt");
    //create a scanner object to read the text file
    private final Scanner dataReader = new Scanner(database);
    //This is where we will store the words from the database
    private final HashMap<String, List<Word>> dictionary;

    //This will be the list with all of the valid words that can be generated from the number
    private HashMap<Integer, List<Word>> results = new HashMap<>(){{
        put(3, new ArrayList<>());
        put(4, new ArrayList<>());
        put(7, new ArrayList<>());
    }};

    //Constructor that initializes the dictionary map
    public WordDatabase() throws FileNotFoundException {
        dictionary = new HashMap<>();
        while(dataReader.hasNextLine()){
            String test = dataReader.nextLine();
            String[] words = test.split("\\s+");
            for (String word: words) {
                String pre = word.substring(0, 3);

               if(!dictionary.containsKey(pre)) {
                   dictionary.put(pre, new ArrayList<>());
               }

                dictionary.get(pre).add(new Word(word));

            }
        }
    }

    //This will take the map generated from the LetterGenerator class to filter out the non-valid words
   public HashMap<Integer, List<Word>> dictionaryQuery(HashMap<Integer, List<Word>> combos){

       for (Word letterCombination:combos.get(3)) {
           String currentWordPrefix = letterCombination.getPre();
           if(dictionary.containsKey(currentWordPrefix)){
               for (Word dictionaryWord : dictionary.get(currentWordPrefix)) {
                   if(Objects.equals(letterCombination.getWord(), dictionaryWord.getWord())){
                       results.get(3).add(letterCombination);
                   }
               }
           }
       }

       for (Word letterCombination:combos.get(4)) {
           String currentWordPrefix = letterCombination.getPre();
           if(dictionary.containsKey(currentWordPrefix)){
               for (Word dictionaryWord : dictionary.get(currentWordPrefix)) {
                   if(Objects.equals(letterCombination.getWord(), dictionaryWord.getWord())){
                       results.get(4).add(letterCombination);
                   }
               }
           }
       }

       for (Word letterCombination:combos.get(7)) {
           String currentWordPrefix = letterCombination.getPre();
           if(dictionary.containsKey(currentWordPrefix)){
               for (Word dictionaryWord : dictionary.get(currentWordPrefix)) {
                   if(Objects.equals(letterCombination.getWord(), dictionaryWord.getWord())){
                       results.get(7).add(letterCombination);
                   }
               }
           }
       }

       return results;
   }

}
