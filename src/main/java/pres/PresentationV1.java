package pres;

import dao.DaoImpl;
import extension.DaoImplV2;
import metier.MetierImpl;

public class PresentationV1 {
    public static void main(String[] args) {
        DaoImplV2 dao = new DaoImplV2();
        MetierImpl m = new MetierImpl(dao);//Injection des dépenedances par le constructeur
        //m.setDao(dao);//Utilisation du setter pour effectuer l'injection
        System.out.println("res"+m.calcul());
    }
}
