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
            String lname = names.realisticName()[1];
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
    } // method findLongestName

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
    } // method buildTopFrame

    /**
     * Converts an integer number to a row/seat designation, e.g.,
     *   0 ---> 1A
     *   1 ---> 1B
     * assuming a single aisle, 2-2 cabin configuration in an airplane.
     * @param sequence Integer to be converted to seat assignment.
     * @return String with seat assignment.
     */
    public static String sequenceToSeat(int sequence) {
        int row = 1 + (sequence/4); // 1+ so that 0/4, 1/4, 2/4, and 3/4 ---> yield row 1
        char col = (char) (65 + (sequence+1)%4); // ascii 65 = 'A', 65+1= 'B', etc
        return String.valueOf(row) + String.valueOf(col); // 'cause string x = row+col no-no.
                                                          // but there's a way 'round it.
                                                          // exam question?
    } // method sequenceToSeat

    /**
     * Builds the first line of the seating chart. The line contains the
     * seat numbers across a single row, e.g., 3A, 3B, 3C, 3D. The method
     * takes into consideration the length of the longest name in the
     * passenger list, to produce the necessary padding after the seat
     * assignment. Also, based on the sequence value, the method determines
     * if a right "armrest", i.e., a vertical delimited needs to be placed.
     * And finally, also based on sequence, when to place the necessary
     * spacing for the aisle.
     * @param seat Seat assignment
     * @param longest length of longest name in passenger list
     * @param sequence sequence of current passenger
     * @param aisleWidth width of aisle
     * @return A string to be used in concatenating the first line of the output.
     */
    public static String buildFirstLine(String seat, int longest, int sequence, int aisleWidth) {
        String output = "| " + seat + " ".repeat(longest+2-seat.length());
        // Is this the rightmost seat of the group? If so, add a "|".
        if ( (sequence+1)%2 == 0) { output = output + "|"; }
        // Is this the aisle seat of the left group? If so, add the aisle.
        if ( sequence%2 == 1) { output = output + " ".repeat(aisleWidth); }
        return  output;
    } // method buildFirstLine

    public static String buildSecondLine(String name, int longest, int sequence, int aisleWidth) {
        String output = "| " + name + " ".repeat(longest+2-name.length());
        if ( (sequence+1)%2 == 0) { output = output + "|"; }
        if ( sequence%2 == 1) { output = output + " ".repeat(aisleWidth); }
        return  output;
    } // method buildSecondLine

    public static String buildThirdLine(String name, int longest, int sequence, int aisleWidth) {
        String output = "| " + name + " ".repeat(longest+2-name.length());
        if ( (sequence+1)%2 == 0) { output = output + "|"; }
        if ( sequence%2 == 1) { output = output + " ".repeat(aisleWidth); }
        return  output;
    } // method buildThirdLine

    /*
    MAIN
     */
    public static void main(String[] args) {

        Seating demo = new Seating();
        // Number of passengers; needs to be multiple of 4 for now.
        int N = 28;
        // Width of aisle; Robert Bacon's proportional width to longest name is good idea
        // for future revision.
        int aisleWidth = 5;

        // Obtain passenger list.
        String pax[] = demo.passengerManifest(N);

        // Initialize strings we'll be using to deliver output.
        String topFrame, firstLine="", secondLine="", thirdLine="", seat="";

        // Find longest name in passenger list
        int longest = demo.findLongestName(pax);
        // aisleWidth in proportion to longest can be assigned here -- future rev.

        // Build top frame
        topFrame = demo.buildTopFrame(longest, aisleWidth);

        // Go through every passenger record
        for ( int i = 0; i < N; i++ ) {

            // Split record for first and last name assuming * delimiter
            String compoments[] = pax[i].split("\\*");

            // Calcuate seat assignment based on sequence (i)
            seat = sequenceToSeat(i);

            // Build the three strings for output
            firstLine  = firstLine  + buildFirstLine(seat, longest, i, aisleWidth);
            secondLine = secondLine + buildSecondLine(compoments[0], longest, i, aisleWidth);
            thirdLine  = thirdLine  + buildThirdLine(compoments[1], longest, i, aisleWidth);

            // Every 4 passenger records, print and reset strings
            if ( (i+1)%4 == 0 ) {
                // print ...
                System.out.println(topFrame);
                System.out.println(firstLine);
                System.out.println(secondLine);
                System.out.println(thirdLine);
                // reset ...
                firstLine  = "";
                secondLine = "";
                thirdLine  = "";
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
} // class Seating
