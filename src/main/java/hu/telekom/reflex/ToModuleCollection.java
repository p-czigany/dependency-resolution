package hu.telekom.reflex;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ToModuleCollection implements Collection<Module> {
    private final Map<Integer, List<Integer>> dataMap;
    private boolean cached;
    private Collection<Module> cachedModules;

    public ToModuleCollection(final Map<Integer, List<Integer>> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public int size() {
        this.cacheModules();
        return this.cachedModules.size();
    }

    @Override
    public boolean isEmpty() {
        this.cacheModules();
        return this.cachedModules.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        this.cacheModules();
        return this.cachedModules.contains(o);
    }

    @Override
    public Iterator<Module> iterator() {
        this.cacheModules();
        return this.cachedModules.iterator();
    }

    @Override
    public Object[] toArray() {
        this.cacheModules();
        return this.cachedModules.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        this.cacheModules();
        return this.cachedModules.toArray(a);
    }

    @Override
    public boolean add(final Module module) {
        this.cacheModules();
        return this.cachedModules.add(module);
    }

    @Override
    public boolean remove(final Object o) {
        this.cacheModules();
        return this.cachedModules.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        this.cacheModules();
        return this.cachedModules.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends Module> c) {
        this.cacheModules();
        return this.cachedModules.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        this.cacheModules();
        return this.cachedModules.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        this.cacheModules();
        return this.cachedModules.retainAll(c);
    }

    @Override
    public void clear() {
        this.cacheModules();
        this.cachedModules.clear();
    }

    private void cacheModules() {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
    }
}
