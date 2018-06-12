import java.lang.*;
import java.io.*;
import java.util.Scanner;

public class SumAbcFile {
    public static void main(String[] args) {
        try (
                Scanner in = new Scanner(new File(String.valueOf(args[0])), "utf8");
                PrintWriter out = new PrintWriter(new FileWriter(String.valueOf(args[1])));
        ) {
            int sum = 0;

            while (in.hasNextLine()) {
                String lc = new String(in.nextLine().toLowerCase());

                String[] curs = lc.split("\n\\s");

                if (curs[0].length() > 0) {
                    for (int h = 0; h < curs.length; ++h) {
                        StringBuilder cur = new StringBuilder();

                        for (int i = 0; i < curs[h].length(); ++i) {
                            if (curs[h].charAt(i) != '-') {
                                cur.append((char) (curs[h].charAt(i) - 'a' + '0'));
                            } else {
                                cur.append('-');
                            }
                        }          
                                                              
                        sum += Integer.parseInt(cur.toString());
                    }
                }
            }

            out.println(sum);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}