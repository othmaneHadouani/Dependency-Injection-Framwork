package testFramework.calcul.injectionTypes;

import annotation.MyAutowired;
import testFramework.calcul.metier.IDao;

public class InjectByField {

    @MyAutowired
    private IDao iDao;

    public  double calcul(int a,int b) {
        return(a*iDao.getValue()+b);
    }
}
