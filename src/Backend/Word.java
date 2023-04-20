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
    private String word;
    private String pre;
    private int relevance = 0;

    public Word(String word) {
        this.word = word;
        pre = word.substring(0, 3);
    }

    /************************************************** Getters ***************************************************************/

    public String getPre() {
        return pre;
    }

    public int getRelevance() {
        return relevance;
    }

    public String getWord() {
        return word;
    }
    /*************************************************** Other Methods ********************************************************/
    public void incRelevance(){
        this.relevance += 1;
    }

}
