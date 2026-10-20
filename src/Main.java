
import java.util.ArrayList; // Import the ArrayList class



public class Main{

    private final static Sauvegarder sauvegarde = new Sauvegarder();

    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<String>();
        test.add("a");
        test.add("1");
        test.add("F");
        test.add("M");
        
        sauvegarde.ecrire(test);
        test.clear();
 
    }

}
