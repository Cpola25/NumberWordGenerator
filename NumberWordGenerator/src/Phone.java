
/**
 * Area Core Present: Boolean value [ True or False ]
 * Area Code: 3-digit number [200 … 999] except 911
 * Prefix: 3-digit number not beginning with 0 or 1
 * Suffix: 4-digit number
 * */
//(409)600-1992
//Have a try catch in the UI for throwing the not a phone number exception

//this is a custom exception to flag an invalid number input
 class invalidNumberException extends Exception{
    public invalidNumberException(String str){
        super(str);
    }
}

public class Phone {


    String number = "";
    boolean areaCore;
    int areaCode ;
    int preFix;
    int suffix;

    public Phone(String number) throws invalidNumberException{
        if(number.length() == 10){
            int code = Integer.parseInt(number.substring(0, 3));
            int prefix = Integer.parseInt(number.substring(3, 6));
            int suff = Integer.parseInt(number.substring(6, 10));

            if((code >= 200 && code <= 999) && code !=911){
                this.areaCode = code;
                this.areaCore = true;
            } else {
                throw new invalidNumberException("Invalid Area Code: 200-999 except 911 ");
            }

            if(prefix > 100) {
                this.preFix = prefix;
            }else {
                throw new invalidNumberException("Invalid Prefix: cannot start with 0 or 1 ");
            }

            this.suffix = suff;
            this.number = number;

        }else {
           throw new invalidNumberException("Number is not long enough: Must be 10 Digits");
        }

    }
}
