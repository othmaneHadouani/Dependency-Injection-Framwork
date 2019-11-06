package framework;

import annotation.MyAutowired;
import module.IModule;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class Inject {
    private final IModule module;

    public Inject(final IModule module) {
        this.module = module;
    }

    public Object inject(final Class<?> classToInject) throws Exception {
        if (classToInject == null) {
            return null;
        }
        return injectFieldsIntoClass(classToInject);
    }

    private Object injectFieldsIntoClass(final Class<?> classToInject) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (final Constructor<?> constructor : classToInject.getConstructors()) {
            if (constructor.isAnnotationPresent(MyAutowired.class)) {
                return injectFieldsViaConstructor(classToInject, constructor);
            } else {
                return injectFields(classToInject);
            }
        }
        return null;
    }

    private Object injectFields(Class<?> classToInject) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Object o = classToInject.newInstance();
        for (Field field : classToInject.getDeclaredFields()) {
            if(field.isAnnotationPresent(MyAutowired.class)) {
                final Class<?> dependency = module.getMapping(field.getType());
                field.setAccessible(true);
                field.set(o, dependency.getConstructor().newInstance());
            }
        }
        return o;
    }
    private Object injectFieldsViaConstructor(Class<?> classToInject, Constructor<?> constructor) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        final Object[] objArr = new Object[parameterTypes.length];
        int i = 0;
        for (final Class<?> c : parameterTypes) {
            final Class<?> dependency = module.getMapping(c);
            if (c.isAssignableFrom(dependency)) {
                objArr[i++] = dependency.getConstructor().newInstance();
            }
        }
        return classToInject.getConstructor(parameterTypes).newInstance(objArr);
    }
}
