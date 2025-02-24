package dao;

public class DaoImpl implements IDao {
    @Override
    public double getData(){
        System.out.println("Un message from DaoImpl");
        double c = Math.random() ;
        return c ;
    }
}
