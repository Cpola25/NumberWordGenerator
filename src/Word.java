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
