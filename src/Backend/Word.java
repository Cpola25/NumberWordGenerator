package Backend;

/**
 * This class defines a Word Object
 * a Word object will have three main attributes
 * String word will store the actual word value
 *
 * String pre will store the first 3 characters of the word (for faster reference)
 * Int relevance which will allow us to track which words the user likes more.
 *
 * */

public class Word {
    String word;
    String pre;
    int relevance = 0;

    public Word(String word) {
        this.word = word;
        pre = word.substring(0, 3);
    }

    public void incRelevance(){
        this.relevance += 1;
    }
}
