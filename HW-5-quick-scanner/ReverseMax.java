import java.util.ArrayList;
import java.util.Scanner;

public class ReverseMax {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        while (in.hasNextLine()) {
            String cur = in.nextLine();
                        
            list.add(cur);
        }

        int len = list.size();

        ArrayList<ArrayList<Integer>> listInt = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < len; ++i) {                
            if (list.get(i).length() > 0) {
                String[] curs = list.get(i).split("\\p{javaWhitespace}");

                ArrayList<Integer> curint = new ArrayList<Integer>();

                for (int j = 0; j < curs.length; j++) {
                    curint.add(Integer.parseInt(curs[j]));
                }

                listInt.add(curint);

                ArrayList<Integer> forans = new ArrayList<Integer>();

                for (int j = 0; j < curint.size(); ++j) {
                    forans.add(0);
                }
                                                      
                ans.add(forans);
            } else {
                ArrayList<Integer> cur = new ArrayList<Integer>();
                
                listInt.add(cur);
                ans.add(cur);
            }
        }
                                  
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < listInt.get(i).size(); j++) {
                int max = listInt.get(i).get(j);

                for (int h = 0; h < listInt.get(i).size(); ++h) {
                     max = Math.max(max, listInt.get(i).get(h));
                }

                for (int h = 0; h < len; ++h) {
                    if (listInt.get(h).size() > j) {
                       max = Math.max(max, listInt.get(h).get(j));
                    }
                }
                
                ArrayList<Integer> forch = ans.get(i);
                forch.set(j, max);

               // ans.set(i, forch);
            }
        }

        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < ans.get(i).size(); ++j) {
                System.out.print(ans.get(i).get(j) + " ");
            }

            System.out.println();
        }
    }
}
