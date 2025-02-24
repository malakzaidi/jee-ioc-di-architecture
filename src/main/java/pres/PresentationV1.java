package pres;

import dao.DaoImpl;
import metier.MetierImpl;

public class PresentationV1 {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl m = new MetierImpl(dao);//Injection des dépenedances par le constructeur
        //m.setDao(dao);//Utilisation du setter pour effectuer l'injection
        System.out.println("res"+m.calcul());
    }
}
