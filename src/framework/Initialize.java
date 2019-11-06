package framework;

import module.IModule;

public final class Initialize {

    private Initialize() {

    }
    public static Inject getFramework(final IModule module) {
        module.configure();
        return new Inject(module);
    }
}
