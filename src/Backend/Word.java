package Backend;

/**
 * This class defines a Word Object
 * a Word object will have two main attributes
 * String word will store the actual word value
 * String pre will store the first 3 characters of the word (for faster reference)
 * */

public class Word {

    private final String word;
    private final String pre;

    public Word(String word) {
        this.word = word;
        pre = word.substring(0, 3);
    }

    /************************************************** Getters ***************************************************************/

    public String getWord() {
        return word;
    }

    public String getPre() {
        return pre;
    }
}

