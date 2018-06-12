import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Scanner {
    BufferedReader read;
    String s = "";
    boolean fl = true; 


    public Scanner() {
        this.read = new BufferedReader(new InputStreamReader(System.in));
    }

    boolean hasNextLine() {
        if (!fl) {
            return true;
        }
        
        fl = false; 
        try {
            s = read.readLine();
            if (s == null) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (s == null) {
            return false;
        } else {
            return true;
        }
    }

    String nextLine() {
        if (fl) {
            hasNextLine();
        }
        String ans = s;
        fl = true;
            
        return ans;
    }
}
