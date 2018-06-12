import java.util.ArrayList;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        while (in.hasNextLine()) {
            String cur = in.nextLine();
                        
            list.add(cur);
        }

        int len = list.size();

        for (int i = len - 1; i >= 0; --i) {                
            String[] cur = list.get(i).split("\\p{javaWhitespace}");

            for (int j = cur.length - 1; j >= 0; --j) {
                System.out.print(cur[j] + " ");
            }

            System.out.println();
        }
    }
}
