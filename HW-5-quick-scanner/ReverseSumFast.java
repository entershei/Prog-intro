import java.util.ArrayList;
import static java.lang.Integer.max;

public class ReverseSumFast {
    public static void main(String[] args) {
        Scanner in = new Scanner();
        ArrayList<String> list = new ArrayList<String>();

        while (in.hasNextLine()) {
            String cur = in.nextLine();

            list.add(cur);
        }
        System.out.println("here");

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
            } else {
                listInt.add(new ArrayList<Integer>());
            }
        }

        ArrayList<Integer> columns = new ArrayList<Integer>();
        ArrayList<Integer> rows = new ArrayList<Integer>();

        for (int i = 0; i < len; ++i) {
            ans.add(new ArrayList<Integer>());
            int sum = 0;

            for (int j = 0; j < listInt.get(i).size(); j++) {
                sum += listInt.get(i).get(j);

                if (columns.size() <= j) {
                    columns.add(listInt.get(i).get(j));
                } else {
                    columns.set(j, listInt.get(i).get(j) + columns.get(j));
                }
            }

            rows.add(sum);
        }

        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < listInt.get(i).size(); j++) {
                ans.get(i).add(rows.get(i) + columns.get(j) - listInt.get(i).get(j));
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
