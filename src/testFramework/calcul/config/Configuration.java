package testFramework.calcul.config;

import module.AbstractModule;
import testFramework.calcul.metier.*;

public class Configuration extends AbstractModule {
    @Override
    public void configure() {
        createMapping(IDao.class, DaoImpl.class);
        createMapping(IDao.class,DaoImplv2.class);
    }
}
