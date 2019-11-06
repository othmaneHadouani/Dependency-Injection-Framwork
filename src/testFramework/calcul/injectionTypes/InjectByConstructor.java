package testFramework.calcul.injectionTypes;

import annotation.MyAutowired;
import testFramework.calcul.metier.IDao;

public class InjectByConstructor {

    private IDao iDao;

    @MyAutowired
    public InjectByConstructor(IDao iDao) {
        this.iDao = iDao;
    }

    public double calcul(int a,int b) {
        return(a*iDao.getValue()+b);
    }
}
