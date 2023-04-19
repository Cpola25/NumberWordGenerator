package Backend;
/**
 * This class defines a Phone number object.
 * A Phone number has several attributes which include an Area Code, Prefix, and Suffix
 * Each of these attributes are defined by the following specifications:
 * Area Code: 3-digit number [200 … 999] except 911
 * Prefix: 3-digit number not beginning with 0 or 1
 * Suffix: 4-digit number
 *
 * This class also has its own unique Exception which will let us know if we have an issue with the
 * phone number inputted and if so, what the issue is.
 *
 * */

public class Phone {

    String number = "";
    boolean areaCore;
    int areaCode ;
    int preFix;
    int suffix;

    public Phone(String number) throws InvalidNumberException{
        if(number.length() == 10){
            int code = Integer.parseInt(number.substring(0, 3));
            int prefix = Integer.parseInt(number.substring(3, 6));
            int suff = Integer.parseInt(number.substring(6, 10));

            if((code >= 200 && code <= 999) && code !=911){
                this.areaCode = code;
                this.areaCore = true;
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
           throw new InvalidNumberException("Number is not long enough: Must be 10 Digits");
        }

    }



     class InvalidNumberException extends Exception{
        public InvalidNumberException(String str){
            super(str);
        }
    }
}
