import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        initialisation();
        boucle();
        destruction();
    }

    private static void initialisation(){
        InterfaceUtilisateur.initier();
    }

    private static void boucle(){
        InterfaceUtilisateur.boucleBlocante();
    }

    private static void destruction(){
        InterfaceUtilisateur.detruire();
    }
}