import framework.Initialize;
import framework.Inject;
import testFramework.calcul.injectionTypes.InjectByConstructor;
import testFramework.calcul.config.Configuration;
import testFramework.calcul.injectionTypes.InjectByField;

public class Main {


    public static void main(String[] args) throws Exception{
        final Inject inject = Initialize.getFramework(new Configuration());


       final InjectByField injectByField = (InjectByField) inject.inject(InjectByField.class);
        final InjectByConstructor injectByConstructor = (InjectByConstructor) inject.inject(InjectByConstructor.class);

       System.out.println("Injection champ : Rest  ="+injectByField.calcul(100,100));
       System.out.println("Injection Constructeurs : Rest ="+injectByConstructor.calcul(100,100));
    }
}
