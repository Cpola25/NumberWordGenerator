import java.util.HashMap;
import java.util.List;

public class Main {



    public static void main(String[] args) throws Exception {

        //initialize our dictionary
        WordDatabase dictionary = new WordDatabase();
        Phone testPhone = new Phone("4093642255");
        LetterGenerator testGenerator = new LetterGenerator(testPhone);
        HashMap<Integer, List<Word>> t = dictionary.dictionaryQuery(testGenerator.getCombinations());
        UI testRun = new UI();
        testRun.printTable(t, testPhone);

    }

}
