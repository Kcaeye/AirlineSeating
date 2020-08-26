public class Seating {

    public static void main(String[] args) {

        final int NUMBER_SEATS = 28;

        // let's say that we have the pax array populated.
        String[] pax = new String[NUMBER_SEATS];

        int row, column;

        for (int i=0; i < NUMBER_SEATS; i++) {
            row = (i/4)+1;
            column = i%4;
            System.out.print(pax[i]+" is seated at " + row + (char)(65+column)+"  ");
            if ( (i+1)%4 == 2) {
                System.out.print(" ".repeat(4));
            }
            if ( (i+1)%4 == 0) {
                System.out.println();
            }
        }
    }
}
