import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Will Handle Querying the Database
 * Will Generate the List of Valid words from the letter Combinations
 *I can probably shorten the search by going to a specific line
 * */

public class WordDatabase {

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

       for (Word w:combos.get(3)) {
           if(dictionary.containsKey(w.pre)){
               for (Word wor: dictionary.get(w.pre)) {
                   if(Objects.equals(w.word, wor.word)){
                       results.get(3).add(w);
                   }
               }
           }
       }

       for (Word w:combos.get(4)) {
           if(dictionary.containsKey(w.pre)){
               for (Word wor: dictionary.get(w.pre)) {
                   if(Objects.equals(w.word, wor.word)){
                       results.get(4).add(w);
                   }
               }
           }
       }

       for (Word w:combos.get(7)) {
           if(dictionary.containsKey(w.pre)){
               for (Word wor: dictionary.get(w.pre)) {
                   if(Objects.equals(w.word, wor.word)){
                       results.get(7).add(w);
                   }
               }
           }
       }

       return results;
   }

}
