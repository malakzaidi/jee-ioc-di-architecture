package extension;

import dao.IDao;

public class DaoImplV2 implements IDao {

    @Override
    public double getData() {
        System.out.println("Version web service");
        double x=20;
        return x;
    }
}
