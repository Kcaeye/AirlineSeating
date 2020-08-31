
public class ParameterizedPrinting {
    public static void main(String[] args) {

        RealisticNameGenerator rng = new RealisticNameGenerator();

        // How many names to generate?
        int N = 10;

        // Passenger manifest.
        String[] pax = new String[N];

        // The following loop generates the passenger list.
        for ( int i = 0; i < N; i++) {

            // Local array to store output from method realisticName()
            String[] name = new String[2];

            //Obtain a pair of realistic first and last names
            name = rng.realisticName();

            //"Glue" the names together into the pax record using * as delimiter
            pax[i] = name[0] + "*" +name[1];
        }

        // Calculate longest pax record.
        int longestPax = 0;
        for (int i = 0; i < N; i++) {
            if ( pax[i].length() > longestPax ) {
                longestPax = pax[i].length();
            }
        }

        // Now that we know the longest record, we can plan for
        // printing one line at a time, with the proper "box" format

        // How many dashes we need? How much space does the printout take?
        //
        // | FNAME*LNAME |
        // ^   ^  ^  ^   ^
        // 2 spaces for left "| "
        // plus  longestPax
        // plus  2 spaces for right " |"
        // minus 2 for the corner element "+"
        int numberOfDashes = longestPax + 2 + 2 - 2;

        for (int i = 0; i < N; i++) {
            System.out.println("+" + "-".repeat(numberOfDashes) + "+");

            // For pax records shorter than longestPax, we need space padding to the right.
            //    longest pax: xxxxxxxxxxxxxxxxxxxxxx  .... longestPax
            //    current pax: xxxxxxxxxxxx            .... pax[i].length()
            // padding needed:             ##########  .... longestPax - pax[i].length()
            int numberOfSpaces = longestPax-pax[i].length();

            System.out.println("| " + pax[i] + " ".repeat(numberOfSpaces) + " |");
        }
        System.out.println("+" + "-".repeat(numberOfDashes) + "+");


    }
}
