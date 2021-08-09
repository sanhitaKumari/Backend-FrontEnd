import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Learn {
    
    private static final String QUIT = "x quit";
    public static final String LINE_SEP = "\n-------------------------------\n";

    private static final HashMap<String, FrontEnd> learn = new HashMap<>();
    private static int obtScore;

    public static void main(String[] args) {
        fillLearn();
        rules();
        doIt();
    }

    private static void doIt() {
        Scanner inp = new Scanner(System.in);
        String userInp;
        int uScore = 0;
        int go = 1;

        userInp = "startInConsole";

        while(!uQuit(userInp, uScore) && uScore < obtScore ) {
            final Set<String> mySets = learn.keySet();

            for(String mySet : mySets) {
                FrontEnd currFedb = learn.get(mySet);

                if(currFedb == null) {
                    learn.remove(mySet);
                    continue;
                }

                if(!currFedb.ifAcc()) {
                    System.out.printf("\n\t\t" + LINE_SEP + "\n\n> What is %s\n\n\n", mySet);

                    if(inp.hasNextLine()) {
                        userInp = inp.nextLine();
                    }

                    if (!uQuit(userInp, uScore)) {
                        if (currFedb.match(userInp)) {
                            currFedb.score();
                            uScore++;
                            System.out.printf("\n> Yes! %s is to: %s", mySet, currFedb.getFrontEnd_DB());
                        }
                        else{
                            System.out.printf("\n> No! %s is to: %s", mySet, currFedb.getFrontEnd_DB());
                        }
                    }
                }
            }

            System.out.println(LINE_SEP);
            System.out.printf("\n You have %d of %d obtainable score at the end of go %d. ", uScore, obtScore, go);

            if(uScore < obtScore) {
                System.out.println("\n> Type anything to continue " + " OR remove to remove a concept \"x\" or \"quit\" to quit? ");

                if(inp.hasNextLine()) {
                    userInp = inp.nextLine();
                    if(userInp.toLowerCase().equals("remove")) {
                        System.out.println("\n> Remove what set?");

                        if(inp.hasNextLine()) {
                            removeSet(inp.nextLine());
                        }
                    }
                }
            }
            
            else break;
        }
        
        System.out.println(LINE_SEP);
        System.out.println("AWESOME! YOU HAVE LEARNT ALL SETS.");

    }

    private static boolean uQuit(String userInp, int uScore) {
        if(userInp != null && QUIT.toLowerCase().contains(userInp)) {
            System.out.println ("You quit with " + uScore + " of " + obtScore + " obtainable score. " + "Practice later dumbass!");

            System.exit(100);
        }

        return false;
    }

    private static void rules() {
        System.out.println("> Get them right ya dumbass");
        System.out.printf("Here ya goooo...\n%s\n", LINE_SEP);
    }

    private static void removeSet(String mySet) {
        FrontEnd setF = learn.get(mySet);

        if(setF != null) {
            if(!setF.ifAcc()){
                learn.remove(mySet);
                System.out.println("Removed \"" + mySet);
                incScore();
            }
            else {
                System.out.println("You know it already");
            }
        }

        else {
            System.out.print("New set not in HashMap");
        }
    }

    private static void incScore() {
        obtScore = learn.size();
        System.out.println("There are " + obtScore + " sets in total.");
    }

    private static void fillLearn() {
        learn.put("Java", new FrontEnd("JavaScript HTML MySQL"));
        learn.put("Ruby", new FrontEnd("React HTML CSS PostgressSQL"));
        learn.put("Python", new FrontEnd("AngularJS HTML MySQL"));
        learn.put("PHP", new FrontEnd("JQuery HTML DbSchema"));
        learn.put("C++", new FrontEnd("TypeScript HTML DbVisualizer"));

        incScore();
    }

}
