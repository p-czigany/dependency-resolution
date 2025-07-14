package hu.telekom.reflex;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class DefaultModule implements Module {
    private final Integer id;
    private final Set<Integer> dependencies;

    public DefaultModule(final Integer id, final Set<Integer> dependencies) {
        this.id = id;
        this.dependencies = dependencies;
    }

    @Override
    public Integer id() {
        return this.id;
    }

    @Override
    public boolean satisfied(final Collection<Module> modules) {
        return modules.stream()
                .map(Module::id).collect(Collectors.toSet())
                .containsAll(this.dependencies);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DefaultModule that = (DefaultModule) o;
        return Objects.equals(id, that.id)
                && Objects.equals(dependencies, that.dependencies);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(dependencies);
        return result;
    }
}
