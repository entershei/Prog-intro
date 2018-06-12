import java.util.Map;
import java.util.HashMap;
import java.lang.String;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class WordStatInput {
    public static void main(String[] args) {
        try (
                Scanner in = new Scanner(new File(args[0]), "utf8");
                PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
        ) {
            Map<String, Integer> CntW = new HashMap<>();
            ArrayList<String> AllStr = new ArrayList<String>();

            while (in.hasNextLine()) {
                String s = in.nextLine();
                String[] cur = s.split("[^\\p{L}\\p{Pd}']+");

                for (int i = 0; i < cur.length; ++i) {
                    String word = cur[i].toLowerCase();
                    if (CntW.containsKey(word)) {
                        CntW.put(word, CntW.get(word) + 1);
                    } else {
                        AllStr.add(word);
                        CntW.put(word, 1);
                    }
                }
            }

            for (int i = 0; i < AllStr.size(); ++i) {
                Integer value = CntW.get(AllStr.get(i));
                if (AllStr.get(i).length() > 0) {
                    out.println(AllStr.get(i) + " " + value);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}