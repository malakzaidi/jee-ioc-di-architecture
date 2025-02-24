package metier;


import dao.IDao;

public class MetierImpl implements IMetier {
    //Couplage faible
    private IDao dao ;
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
