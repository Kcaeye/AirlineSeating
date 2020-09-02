public class Seating {

    /**
     * Method to populate a linear array with realistic passenger
     * names. The method instantiates class RealisticNameGenerator
     * to use its realisticName(). The passenger names are added to
     * array passengers[], over a loop that obtains values from
     * RealisticNameGenerator.realisticName(). The values are passed
     * in the form of a String[2] array.
     *
     * @param numberOfPassengers How many passengers
     * @return String array with names of passengers
     */
    public String[] passengerManifest(int numberOfPassengers) {

        /*
        Instantiate RealisticNameGenerator, to access its
        realisticName() method.

        Question: why do we access .realisticName() through an
        instance of its class, instead of just copying in here?
         */
        RealisticNameGenerator names = new RealisticNameGenerator();

        /*
        String[] passengers is the array that the method will
        return, after it is populated with passenger names.
         */
        String[] passengers = new String[numberOfPassengers];

        /*
        String[] name is a local array that stores the output of
        names.realisticName. Remember that method realisticName()
        is declared (in class RealisticNameGenerator) as a String
        array with two elements.

        Question: we can avoid using this local array if in the
        loop below, we use instead:
            String fname = names.realisticName()[0];
            String lname = names. realisticName()[1];
        Good idea? Bad idea?
         */
        String[] name = new String[2];

        /*
        Loops runs as many times as the number of passengers passed
        as a parameter here. The passengers[] String uses an
        asterisk to delimit first name from last name. (Yeah, we are
        really doing this in the style of 1980s systems).
         */
        for (int i = 0; i < numberOfPassengers; i++) {
            name = names.realisticName();
            passengers[i] = name[0] + "*" + name[1];
        }

        return passengers;
    } // method passengerManifest

    /**
     * Method findLongestName returns the length of the longest component
     * stored in it. This method assumes that the contents of the array
     * passed as parameter, holds delimited information using an asterisk
     * as the delimiter. This is too much to assume, but it works for now.
     * @param inThisArray
     * @return length of longest asterisk delimited string in the array.
     */
    public int findLongestName(String[] inThisArray) {
        int longest = 0;
        /*
        Go over every element of the input array inThisArray[], split
        one element at a time at the components delimited by "*",
        and look for the longest components, using the tertiary operator.
         */
        for ( int i = 0; i < inThisArray.length; i++ ) {
            String components[] = inThisArray[i].split("\\*");
            // Array components[] has two elements
            longest = ( components[0].length() > longest ) ? components[0].length() : longest;
            longest = ( components[1].length() > longest ) ? components[1].length() : longest;
        }
        return longest;
    }

    /**
     * Builds the top border for the seating chart. The size of the the seat is
     * adjusted to accommodate the longest name in the passenger list.
     * @param longestName Length of longest name in the passenger list
     * @param aisleWidth Length of aisle between seats
     * @return The decorate border for the seating chart
     */
    public String buildTopFrame(int longestName, int aisleWidth) {
        return "+" +
                "-".repeat(longestName+3) +
                "+" +
                "-".repeat(longestName+3) +
                "+" +
                " ".repeat(aisleWidth) +
                "+" +
                "-".repeat(longestName+3) +
                "+" +
                "-".repeat(longestName+3) +
                "+";
    }

    /**
     * Converts an integer number to a row/seat designation, e.g.,
     *   0 ---> 1A
     *   1 ---> 1B
     * assuming a single aisle, 2-2 cabin configuration in an airplane.
     * @param sequence Integer to be converted to seat assignment.
     * @return String with seat assignment.
     */
    public static String sequenceToSeat(int sequence) {
        int row = 1 + (sequence/4);
        char col = (char) (65 + (sequence+1)%4);
        return String.valueOf(row) + String.valueOf(col);
    }

    public static String buildFirstLine(String seat, int longest, int sequence, int aisleWidth) {
        String output = "| " + seat + " ".repeat(longest+2-seat.length());
        if ( (sequence+1)%2 == 0) { output = output + "|"; }
        if ( sequence%2 == 1) { output = output + " ".repeat(aisleWidth); }
        return  output;
    }

    public static String buildSecondLine(String name, int longest, int sequence, int aisleWidth) {
        String output = "| " + name + " ".repeat(longest+2-name.length());
        if ( (sequence+1)%2 == 0) { output = output + "|"; }
        if ( sequence%2 == 1) { output = output + " ".repeat(aisleWidth); }
        return  output;
    }

    public static String buildThirdLine(String name, int longest, int sequence, int aisleWidth) {
        String output = "| " + name + " ".repeat(longest+2-name.length());
        if ( (sequence+1)%2 == 0) { output = output + "|"; }
        if ( sequence%2 == 1) { output = output + " ".repeat(aisleWidth); }
        return  output;
    }



    public static void main(String[] args) {

        Seating demo = new Seating();
        int N = 28;
        int aisleWidth = 5;
        String pax[] = demo.passengerManifest(N);
        String topFrame, firstLine="", secondLine="", thirdLine="", seat="";
        int longest = demo.findLongestName(pax);
        topFrame = demo.buildTopFrame(longest, aisleWidth);
        for ( int i = 0; i < N; i++ ) {
            String compoments[] = pax[i].split("\\*");
            seat = sequenceToSeat(i);
            firstLine = firstLine + buildFirstLine(seat, longest, i, aisleWidth);
            secondLine = secondLine + buildSecondLine(compoments[0], longest, i, aisleWidth);
            thirdLine = thirdLine + buildThirdLine(compoments[1], longest, i, aisleWidth);

            if ( (i+1)%4 == 0 ) {
                System.out.println(topFrame);
                System.out.println(firstLine);
                System.out.println(secondLine);
                System.out.println(thirdLine);
                firstLine = "";
                secondLine = "";
                thirdLine = "";
            }
        }
        System.out.println(topFrame);



        /* OLD CODE
        -------------------------------------------------------------------------
        String[] pax = new String[NUMBER_SEATS];
        int row, column;
        for (int i=0; i < NUMBER_SEATS; i++) {
            row = (i/4)+1;  column = i%4;
            System.out.print(pax[i]+" is seated at " + row + (char)(65+column)+"  ");
            if ( (i+1)%4 == 2) { System.out.print(" ".repeat(4)); }
            if ( (i+1)%4 == 0) { System.out.println(); }
        }
        -------------------------------------------------------------------------
         */
    } // method main
}
