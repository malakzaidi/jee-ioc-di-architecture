package metier;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import dao.IDao;

@Component("metier")
public class MetierImpl implements IMetier {

    @Autowired
    private IDao dao=null ;
    // Constructeur sans paramètres
    public MetierImpl() {

    }
    //Constructeur avec paramètres
    public MetierImpl(@Qualifier ("dao2") IDao dao) {
        this.dao = dao;
    }
    @Override
    public double calcul() {
        double x = dao.getData();
        double result=x*25;
        return result;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

}
