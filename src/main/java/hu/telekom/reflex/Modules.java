package hu.telekom.reflex;

import java.util.List;

public interface Modules {
    List<Integer> sortedDependencyIds() throws CircularDependenciesException;

    class CircularDependenciesException extends Exception {
    }
}
