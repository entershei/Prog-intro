import java.lang.reflect.Array;
import java.lang.reflect.WildcardType;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class WordStatCount {
    private static class Word implements Comparable {
        int cnt, firstEnter;
        String s;

        public Word(int cnt, int firstEnter, String s) {
            this.cnt = cnt;
            this.firstEnter = firstEnter;
            this.s = s;
        }

        @Override
        public int compareTo(Object o) {
            Word b = (Word)o;

            if((this.cnt < b.cnt) || (this.cnt == b.cnt && this.firstEnter < b.firstEnter)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        try (
                Scanner in = new Scanner(new File(args[0]), "utf8");
                PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
        ) {
            Map<String, Integer> CntW = new HashMap<>();
            Map<String, Integer> FirstEnter = new HashMap<>();
            int counter = 0;

            while (in.hasNextLine()) {
                String s = in.nextLine();
                String[] cur = s.split("[^\\p{L}\\p{Pd}']+");

                for (int i = 0; i < cur.length; ++i) { 
                    if (cur[i].length() == 0) {
                        continue;
                    }
                    String word = cur[i].toLowerCase();
                    ++counter;

                    if (CntW.containsKey(word)) {
                        CntW.put(word, CntW.get(word) + 1);
                    } else {
                        CntW.put(word, 1);
                        FirstEnter.put(word, counter);
                    }
                }
            }

            Word[] vector = new Word[CntW.size()];
            int cnt = 0;

            for (Map.Entry<String, Integer> cur : CntW.entrySet()) {
                vector[cnt++] = new Word(cur.getValue(), FirstEnter.get(cur.getKey()), cur.getKey());
         //       System.err.println(cur.getKey() + " " + cur.getValue() + " " + FirstEnter.get(cur.getKey()));
            }

            Arrays.sort(vector);

            for (Word i : vector) {
                out.println(i.s + " " + i.cnt);
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