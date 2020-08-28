/**
 * A class to provide a passenger manifest using realistic
 * first and last names, using popular (ie) frequent first
 * and last names in the US.
 */

import java.util.Random;

public class realisticNameGenerator {

    private static final String[] lastNames = {
            "Johnson",
            "Williams",
            "Brown",
            "Jones",
            "Garcia",
            "Miller",
            "Davis",
            "Rodriguez",
            "Martinez",
            "Hernandez",
            "Lopez",
            "Gonzalez",
            "Wilson",
            "Anderson",
            "Thomas",
            "Taylor",
            "Moore",
            "Jackson",
            "Martin",
            "Lee",
            "Irakliotis"};

    private static final String[] firstNamesMale = {
            "James",
            "John",
            "Robert",
            "Michael",
            "William",
            "David",
            "Richard",
            "Charles",
            "Joseph",
            "Thomas",
            "Christopher",
            "Daniel",
            "Paul",
            "Mark",
            "Donald",
            "George",
            "Kenneth",
            "Steven",
            "Edward",
            "Brian",
            "Leo"
    };

    private static final String[] firstNameFemale = {
            "Mary",
            "Patricia",
            "Linda",
            "Barbara",
            "Elizabeth",
            "Jennifer",
            "Maria",
            "Susan",
            "Margaret",
            "Dorothy",
            "Lisa",
            "Nancy",
            "Karen",
            "Betty",
            "Helen",
            "Sandra",
            "Donna",
            "Carol",
            "Ruth",
            "Sharon",
            "Michelle",
    };

    /**
     * Method realisticName is the principal method of this class. Users
     * may access this class' main service only through this method. It
     * returns a String[2] array, where the first element is a realistic
     * first name and the second element is a realistic last name. The
     * gender for the first name is determined by a coin toss.
     * @return realisticName Contains first and last name
     */
    public String[] realisticName () {

        int lastNamesCount = lastNames.length;
        int maleFirstNamesCount = firstNamesMale.length;
        int femaleFirstNamesCount = firstNameFemale.length;

        int gender;
        String[] name = new String[2];
        Random rand = new Random();

        gender = rand.nextInt(2);       // coin toss to determine gender

        name[1] = lastNames[rand.nextInt(lastNamesCount)];  // last names common to both genders

        if (gender == 0) {                     // female first name
            name[0] = firstNameFemale[rand.nextInt(femaleFirstNamesCount)];
        } else {                               // male first name
            name[0] = firstNamesMale[rand.nextInt(maleFirstNamesCount)];
        }
        /* question for class: how to bias towards female or male first names? */
        return name;
    }

    /* Method main() below is for local testing only */
    public static void main(String[] args) {
        final int N = 20;
        realisticNameGenerator realNames = new realisticNameGenerator();
        String[] realName = new String[2];
        for (int i = 0;  i<N; i++) {
            realName = realNames.realisticName();
            System.out.println(realName[0] + " " + realName[1]);
        }
    }
}

