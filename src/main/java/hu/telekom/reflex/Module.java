package hu.telekom.reflex;

import java.util.Collection;

public interface Module {
    Integer id();

    boolean satisfied(Collection<Module> modules);
}
