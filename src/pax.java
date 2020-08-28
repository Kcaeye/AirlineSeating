import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

// Sal Pecoraro's code

public class pax {

    public static String method(BufferedReader read, int txt_Size) throws Exception {

        Random rand = new Random();
        int counter = 0;
        int r = rand.nextInt(txt_Size);
        while (counter != r) {
            counter++;
            read.readLine();
        }
        return read.readLine();
    }

    public static void main(String[] args) throws Exception {

        final int NUMBER_SEATS = 32;
        String[] pax = new String[NUMBER_SEATS];
        for (int h = 0; h < NUMBER_SEATS; h++) {

            FileReader fr = new FileReader("/Users/salpecoraro/Downloads/first_names.txt");
            BufferedReader firstName = new BufferedReader(fr);
            FileReader fr2 = new FileReader("/Users/salpecoraro/Downloads/names 2/last_Names.txt");
            BufferedReader lastName = new BufferedReader(fr2);
            String store = method(lastName,162253);
            String copy = "";

            for (int i = 0; i < store.length(); i++) {

                if (store.charAt(i) == ',') {
                    break;
                }
                copy = copy + store.charAt(i);
            }
            pax[h] = copy.substring(0,1) + copy.substring(1).toLowerCase();
            store = method(firstName,32952);
            pax[h] = store + " " + pax[h];
        }

        int row, column;
        String space = " ";
        for (int i = 0; i < NUMBER_SEATS; i++) {
            row = (i / 4) + 1;
            column = i % 4;
            System.out.print(pax[i] + " is seated at " + row + (char) (65 + column) + "  ");
            System.out.print((i + 1) % 4 == 2 ? space.repeat(4):"");
            if ((i + 1) % 4 == 0) {
                System.out.println();
            }
        }
    }
}