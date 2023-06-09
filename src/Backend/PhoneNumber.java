package Backend;

/**
 * This class defines a PhoneNumber number object.
 * A PhoneNumber number has several attributes which include an Area Code, Prefix, and Suffix
 * Each of these attributes are defined by the following specifications:
 * Area Code: 3-digit number [200 … 999] except 911
 * Prefix: 3-digit number not beginning with 0 or 1
 * Suffix: 4-digit number
 *
 * This class also has its own unique Exception which will let us know if we have an issue with the
 * phone number inputted and if so, what the issue is.
 *
 * */

public class PhoneNumber {

    private String number;
    private final int areaCode ;
    private final int preFix;
    private final int suffix;


    /**************************************************  Constructor  ***************************************************************/

    // if input is a valid phone number this constructor will initialize a new PhoneNumber object else it will throw an exception
    public PhoneNumber(String number) throws InvalidNumberException{
        if(number.length() == 10){
            int code = Integer.parseInt(number.substring(0, 3));
            int prefix = Integer.parseInt(number.substring(3, 6));
            int suff = Integer.parseInt(number.substring(6, 10));

            if((code >= 200 && code <= 999) && code !=911){
                this.areaCode = code;
            } else {
                throw new InvalidNumberException("Invalid Area Code: 200-999 except 911 ");
            }

            if(prefix > 100) {
                this.preFix = prefix;
            }else {
                throw new InvalidNumberException("Invalid Prefix: cannot start with 0 or 1 ");
            }
            this.suffix = suff;
            this.number = number;
        }else {
            throw new InvalidNumberException("Number must be 10 Digits with no special symbols");
        }

    }
    /************************************************** Getters ***************************************************************/
    public int getPreFix() {
        return preFix;
    }

    public int getSuffix() {
        return suffix;
    }
    public String getNumber(){
        return number;
    }
    /************************************************** Other Methods ***************************************************************/

    // this will help us put a word in the phone number to display it
    public String generateNumberString(String word){
        String number =" ";
        word = word.toUpperCase();

        switch (word.length()) {
            case 3 -> number = this.areaCode + "-" + word.toUpperCase() + "-" + this.suffix;
            case 4 -> number = this.areaCode + "-" + this.preFix + "-" + word;
            case 7 -> number = this.areaCode + "-" + word.substring(0, 3) + "-" + word.substring(3, 7);
            default -> {
            }
        }

        return number;
    }
    /************************************************** Exception  ***************************************************************/
    //customized exception to track mistakes in users phone number input
    private static class InvalidNumberException extends Exception{
        public InvalidNumberException(String str){
            super(str);
        }
    }
}

