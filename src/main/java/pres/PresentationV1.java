package pres;

import metier.MetierImpl;

public class PresentationV1 {
    public static void main(String[] args) {
        MetierImpl m = new MetierImpl();
        System.out.println(m.calcul());
    }
}
