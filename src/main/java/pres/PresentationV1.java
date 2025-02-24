package pres;

import dao.DaoImpl;
import metier.MetierImpl;

public class PresentationV1 {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl m = new MetierImpl();
        m.setDao(dao);
        System.out.println(m.calcul());
    }
}
