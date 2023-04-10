public class Word {
    String word;
    int relevance = 0;
    boolean isWord;

    public Word(String word) {
        this.word = word;
    }

    public void incRelevance(){
        this.relevance += 1;
    }
}
