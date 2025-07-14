package hu.telekom.reflex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class DefaultModules implements Modules {
    private final Collection<Module> modules;

    public DefaultModules(final Map<Integer, List<Integer>> dependencies) {
        this(new ToModuleCollection(dependencies));
    }

    public DefaultModules(final Collection<Module> modules) {
        this.modules = modules;
    }

    private List<Module> sortedDependencies()
            throws CircularDependenciesException {
        final List<Module> sortedModules = new ArrayList<>();
        final List<Module> unsortedModules = new ArrayList<>(this.modules);
        while (!unsortedModules.isEmpty()) {
            for (int i = 0; i < unsortedModules.size(); i++) {
                final Module module = unsortedModules.get(i);
                if (module.satisfied(sortedModules)) {
                    sortedModules.add(module);
                    unsortedModules.remove(module);
                    break;
                }
                if (i == unsortedModules.size() - 1) {
                    throw new CircularDependenciesException();
                }
            }
        }
        return sortedModules;
    }

    @Override
    public List<Integer> sortedDependencyIds()
            throws CircularDependenciesException {
        return this.sortedDependencies().stream()
                .map(Module::id).collect(Collectors.toList());
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DefaultModules that = (DefaultModules) o;
        return Objects.equals(modules, that.modules);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(modules);
    }
}
