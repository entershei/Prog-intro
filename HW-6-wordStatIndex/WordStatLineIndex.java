import javax.sql.rowset.spi.SyncResolver;
import java.util.*;
import java.lang.String;
import java.io.*;
import java.util.Scanner;

public class WordStatLineIndex {
    public static void main(String[] args) {
        try (
                Scanner in = new Scanner(new File(args[0]), "utf8");
                PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
        ) {
            Map<String, ArrayList<String> > CntW = new TreeMap<>();
            int line = 0;
            while (in.hasNextLine()) {
                ++line;
                String s = in.nextLine();
                String[] cur = s.split("[^\\p{L}\\p{Pd}']+");
                int x = 0;

                for (int i = 0; i < cur.length; ++i) {
                    String word = cur[i].toLowerCase();
                    
                    if (word.isEmpty()) {
                        ++x;
                        continue;
                    }
                    
                    if (CntW.containsKey(word)) {
                        CntW.get(word).add(Integer.toString(line) + ":" + Integer.toString(i + 1 - x));
                    } else {
                        CntW.put(word, new ArrayList<String>());
                        CntW.get(word).add(Integer.toString(line) + ":" + Integer.toString(i + 1 - x));
                    }
                }
            }

            for (Map.Entry<String, ArrayList<String> > i : CntW.entrySet()) {
                out.print(i.getKey() + " " + Integer.toString(i.getValue().size()));

                for (String j : i.getValue()) {
                    out.print(" " + j);
                }

                out.println();
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