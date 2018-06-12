public class Sum {
    public static void main(String[] args) {
        int sum = 0;
                
        for (int i = 0; i < args.length; ++i) {
             String cur = "";

             for (int j = 0; j < args[i].length(); ++j) {
                char c = args[i].charAt(j);

                if (Character.isWhitespace(c)) {
                    if (cur.length() > 0) {
                        sum += Integer.parseInt(cur);
                    }
                    cur = "";
                } else {
                    cur += c;
                }
             }
             
             if (cur.length() > 0) {
                 sum += Integer.parseInt(cur);
             }
        }

        System.out.println(sum);
    }
}
