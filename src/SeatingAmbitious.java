public class SeatingAmbitious {

    private static final int DEFAULT_NUMBER_OF_SEATS = 28;

    private String[] seatingChart;
    private int numberOfSeats;
    private int numberOfPassengers;

    public void setNumberOfPassengers( int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Default constructor
     */
    public SeatingAmbitious() {
        seatingChart = new String[DEFAULT_NUMBER_OF_SEATS];
        numberOfSeats = DEFAULT_NUMBER_OF_SEATS;
        numberOfPassengers = 0;
    } // default constructor

    /**
     * Parameterized constructor
     */
    public SeatingAmbitious(int n) {
        seatingChart = new String[n];
        numberOfSeats = n;
        numberOfPassengers = 0;
    } // parameterized constructor

    /**
     * Assigns a passenger record in the first available seat, and returns
     * true if assignment is successful. If all seats occupied, it returns false.
     * @param passengerRecord
     * @return true if seat assignment successful, false if flight full.
     */
    public boolean assignSeat(String passengerRecord) {
        boolean success = false;
        // If flight not full
        if ( numberOfPassengers < numberOfSeats ) {
            int index = 0;
            // Start from the beginning and keep looking for
            // the first empty seating record.
            while (index < numberOfSeats && !success) {

                if ( seatingChart[index] == null ) {

                    // Empty seat found.

                    // Declare a success (so that we can exit the loop).
                    success = true;

                    // Assign passenger to that seat.
                    seatingChart[index] = passengerRecord;

                    // Increase passenger count
                    numberOfPassengers++;
                }
            }
        }
        return success;
    } // method assignSeat(String)

    /**
     * Another version of method assignSeat, that places a passenger to
     * a specified seat.
     * @param passengerRecord
     * @param sequence Request seat.
     * @return true if seat assignment successful, false if flight full.
     */
    public boolean assignSeat(String passengerRecord, int sequence) {
        boolean success = false;
        // If flight not full and seat requested is within range
        if ( numberOfPassengers <= numberOfSeats && sequence < numberOfSeats ) {
            // Is requested seat available?
            if ( seatingChart[sequence] == null ) {

                // Assign passenger to the requested seat.
                seatingChart[sequence] = passengerRecord;

                // Update (increase) passenger count.
                numberOfPassengers++;

                // Declare success!
                success = true;
            }
        }
        return success;
    } // method assignSeat(String, int)

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
        String[] passengers = new String[numberOfSeats];

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
        for (int i = 0; i < numberOfSeats; i++) {
            name = names.realisticName();
            passengers[i] = name[0] + "*" + name[1];
        }

        return passengers;
    } // method passengerManifest

    /**
     * Method sequenceToRow convers an integer to the corresponding
     * seat row in a given cabin configuration. The method assumes
     * that parameter sequence is legal (ie, greater than 0 and less
     * that the number of seats on the aircraft).
     * @param sequence
     * @return The row number
     */
    public int sequenceToRow(int sequence) {
        // Assume a 2-2 cabin configuration, ie 4 seats per row.
        return 1+(sequence/4);
    }

    /**
     * Method sequenceToCol converns an integer to a letter corresponding to
     * a seat in a row; beginning with seat A at the leftmost.
     * @param sequence
     * @return
     */
    public String sequenceToCol(int sequence) {
        return Character.toString((char) (65+sequence%4));
        //                                   ^^ ASCII 65 : upper case A
    }

    /**
     * Populates class array seatingChart
     */
    public void mapManifestToSeats() {
        // Obtain a manifest
        String pax[] = passengerManifest(numberOfSeats);

        // Go through the manifest, and assign seats
        for (int i = 0; i < numberOfSeats; i++) {
            int row = sequenceToRow(i);
            String col = sequenceToCol(i);
            seatingChart[i] = row + col + "*" + pax[i];
        }
    } // method mapManifestToSeats

    public void printManifest() {
        // Find longest name in seatingChart
        int longest = 0;
        for (int i = 0; i < numberOfSeats; i++) {
            String passengerInfo[] = seatingChart[i].split("\\*");
            int fNameLength = passengerInfo[1].length();
            int lNameLength = passengerInfo[2].length();
            if ( fNameLength > longest ) { longest = fNameLength; }
            if ( lNameLength > longest ) { longest = lNameLength; }
        }
        System.out.println(longest);

        // Printing loop
        for (int i = 0; i < numberOfSeats; i++) {

        }
    }

    public static void main(String[] args) {

        SeatingAmbitious demo = new SeatingAmbitious();
        demo.mapManifestToSeats();
        demo.printManifest();



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
