import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        Phone testPhone = new Phone("4092255669");
        LetterGenerator testGenerator = new LetterGenerator();
        List<Word> testList = testGenerator.letterGeneration(testPhone);
        int counter = 1;
        for (Word w: testList) {
            System.out.print(w.word + " ");
            if(counter % 10 == 0){
                System.out.println();
            }
            counter++;
        }
    }
}
