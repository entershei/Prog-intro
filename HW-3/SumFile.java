import java.io.*;
import java.util.Scanner;

public class SumFile {
    public static void main(String[] args) {
        try (
                Scanner in = new Scanner(new File(args[0]), "utf8");
                PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        ) {
            int sum = 0;

            while (in.hasNextInt()) {
                sum += in.nextInt();
            }

            out.println(sum);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}