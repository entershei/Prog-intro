import java.math.BigInteger;

public class SumBigInteger {
    public static void main(String[] args) {
        BigInteger sum = BigInteger.valueOf(0);
                
        for (int i = 0; i < args.length; ++i) {
             int it = 0;     
             boolean fl = false;

             for (int j = 0; j < args[i].length(); ++j) {
                char c = args[i].charAt(j);

                if (Character.isWhitespace(c)) {
                    if (j - it > 0) {
                        sum = sum.add(new BigInteger(args[i].substring(it, j)));                            
                    }
                    
                    it = j + 1;
                    fl = false;
                } else {
                    fl = true;
                }
             }
             
            if (fl && (args[i].length() - it > 0)) {
                sum = sum.add(new BigInteger(args[i].substring(it, args[i].length())));                            
            }                 
        }

        System.out.println(sum);
    }
}
