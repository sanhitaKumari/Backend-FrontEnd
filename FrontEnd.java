public class FrontEnd {
    private String FrontEnd_DB;
    private boolean accuracy;
    private String[] counter;
    private static final double ACC_PERC = 0.75;

    public FrontEnd(String FrontEnd_DB) {
        this.FrontEnd_DB = FrontEnd_DB;
        countFrontEnd_DB();
    }

    private void countFrontEnd_DB() {
        String fedb = onlyLetters(FrontEnd_DB);
        this.counter = fedb.split(" ");
    }

    public void setAcc (boolean accuracy){
        this.accuracy = accuracy;
    }

    public void setFedb(String FrontEnd_DB){
        this.FrontEnd_DB = FrontEnd_DB;
        countFrontEnd_DB(); 
    }

    public boolean ifAcc() {
        return accuracy;
    }

    public String getFrontEnd_DB() {
        return FrontEnd_DB;
    }

    public boolean match(String input) {
        if(!input.isEmpty()) {
            int acc = 0;
            input = onlyLetters(input);
            String[] inputCount = input.split(" ");

            int totAcc = Math.max(inputCount.length, counter.length);
            acc = getMatch(inputCount);
            double perc = ((double)acc/totAcc);

            return perc >= ACC_PERC;
        }
        return false;
    }

    private int getMatch(String[] inputCount) {
        int acc = 0;

        for(int i = 0; i < counter.length; i++) {
            String currCount = counter[i];
            for(int j = 0; j < inputCount.length; j++) {
                String currInputCount = inputCount[j];

                if(currCount.equalsIgnoreCase(currInputCount)) {
                    acc ++;
                    break;
                }
                else{

                }
            }
        }
        return acc;
    }

    private String onlyLetters(String line) {
        line = line.replace(".", " ");
        line = line.replace(",", " ");
        line = line.replace("!", " ");
        line = line.replace(",", " ");

        return line;
    }

    public void score() {
        accuracy = true;
    }

}