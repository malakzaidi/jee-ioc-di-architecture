package pres;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;
import dao.IDao;
import metier.IMetier;

public class PresentationV2 {
    public static  void main(String[] args)  {
        try{
            Scanner scanner = new Scanner (new File("config.txt"));
            //DaoImpl d=new DaoImpl();
            String daoClassname = scanner.nextLine();
            Class cDao = Class.forName(daoClassname);
            IDao dao = (IDao) cDao.getConstructor().newInstance();

            //MetierImpl metier = new MetierImpl(d);
            String metierClassname = scanner.nextLine();
            Class cMetier = Class.forName(metierClassname);
            IMetier metier = (IMetier) cMetier.getConstructor().newInstance();

            Method setDao = cMetier.getMethod("setDao", IDao.class);
            setDao.invoke(metier,dao );

            System.out.println("Res"+metier.calcul());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
