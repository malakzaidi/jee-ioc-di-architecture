package pres;

import dao.DaoImpl;
import metier.MetierImpl;

public class PresentationV1 {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl m = new MetierImpl(dao);
        m.setDao(dao);//using the setter to do the injection
        System.out.println(m.calcul());
    }
}
