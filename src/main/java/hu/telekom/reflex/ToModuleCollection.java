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
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.size();
    }

    @Override
    public boolean isEmpty() {
        return this.dataMap.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return this.dataMap.entrySet().stream().anyMatch(
                e -> new DefaultModule(
                        e.getKey(),
                        new HashSet<>(e.getValue())
                ).equals(o)
        );
    }

    @Override
    public Iterator<Module> iterator() {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.iterator();
    }

    @Override
    public Object[] toArray() {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.toArray(a);
    }

    @Override
    public boolean add(final Module module) {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.add(module);
    }

    @Override
    public boolean remove(final Object o) {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends Module> c) {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(),
                            new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        return this.cachedModules.retainAll(c);
    }

    @Override
    public void clear() {
        if (!this.cached) {
            this.cachedModules = this.dataMap.entrySet().stream()
                    .map(e -> (Module) new DefaultModule(
                            e.getKey(), new HashSet<>(e.getValue())
                    ))
                    .collect(Collectors.toList());
            this.cached = true;
        }
        this.cachedModules.clear();
    }
}
