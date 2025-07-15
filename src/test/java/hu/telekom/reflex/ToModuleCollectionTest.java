package hu.telekom.reflex;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ToModuleCollectionTest {
    @Test
    void size() {
        MatcherAssert.assertThat(
                "the returned size is not correct",
                new ToModuleCollection(Map.of(
                        5, List.of(0, 2),
                        4, List.of(0, 1),
                        0, Collections.emptyList(),
                        1, Collections.emptyList(),
                        2, List.of(3),
                        3, List.of(1)
                )).size(),
                Matchers.is(Matchers.equalTo(6))
        );
    }

    @Nested
    class IsEmpty {
        @Test
        void trueWhenTheMapIsEmpty() {
            MatcherAssert.assertThat(
                    "emptiness is not answered correctly",
                    new ToModuleCollection(Collections.emptyMap()).isEmpty(),
                    Matchers.is(true)
            );
        }

        @Test
        void falseWhenTheMapIsNotEmpty() {
            MatcherAssert.assertThat(
                    "emptiness is not answered correctly",
                    new ToModuleCollection(Map.of(5, List.of(0, 2))).isEmpty(),
                    Matchers.is(false)
            );
        }
    }

    @Nested
    class ClearAndIsEmpty {
        @Test
        void isEmptyAfterCleare() {
            final Collection<Module> objectUnderTest =
                    new ToModuleCollection(Map.of(5, List.of(0, 2)));
            objectUnderTest.clear();
            MatcherAssert.assertThat(
                    "emptiness is not answered correctly",
                    objectUnderTest.isEmpty(),
                    Matchers.is(true)
            );
        }
    }

    @Nested
    class Contains {
        @Test
        void doesNotContain() {
            MatcherAssert.assertThat(
                    "it answered that the object is contained while it is not",
                    new ToModuleCollection(Map.of(5, List.of(0, 2)))
                            .contains(new DefaultModule(1, Set.of(0, 2))),
                    Matchers.is(false)
            );
        }

        @Test
        void doesContain() {
            MatcherAssert.assertThat(
                    "it answered that the object is not contained while it is",
                    new ToModuleCollection(Map.of(
                            5, List.of(0, 2),
                            4, List.of(0, 1)
                    )).contains(new DefaultModule(4, Set.of(1, 0))),
                    Matchers.is(true)
            );
        }
    }

    @Nested
    class ClearAndContains {
        @Test
        void clearAndContains() {
            final Collection<Module> objectUnderTest =
                    new ToModuleCollection(Map.of(5, List.of(0, 2)));
            objectUnderTest.clear();
            MatcherAssert.assertThat(
                    "emptiness is not answered correctly",
                    objectUnderTest.contains(new DefaultModule(5, Set.of(2, 0))),
                    Matchers.is(false)
            );
        }
    }

    @Nested
    class IteratorMethod {
        @Test
        void testIteratorUnordered() {
            final Collection<Module> objectUnderTest = new ToModuleCollection(Map.of(
                    5, List.of(0, 2),
                    4, List.of(0, 1),
                    0, Collections.emptyList(),
                    1, Collections.emptyList(),
                    2, List.of(3),
                    3, List.of(1)
            ));
            final Set<Module> actualElements = new HashSet<>();
            objectUnderTest.iterator().forEachRemaining(actualElements::add);
            final Set<Module> expectedElements = Set.of(
                    new DefaultModule(5, Set.of(0, 2)),
                    new DefaultModule(4, Set.of(0, 1)),
                    new DefaultModule(0, Collections.emptySet()),
                    new DefaultModule(1, Collections.emptySet()),
                    new DefaultModule(2, Set.of(3)),
                    new DefaultModule(3, Set.of(1))
            );
            MatcherAssert.assertThat(
                    "not matching",
                    actualElements,
                    Matchers.is(Matchers.equalTo(expectedElements))
            );
        }

        @Test
        void testEmptyIterator() {
            final Collection<Module> objectUnderTest =
                    new ToModuleCollection(Collections.emptyMap());
            final Iterator<Module> iterator = objectUnderTest.iterator();
            MatcherAssert.assertThat(
                    "not empty iterator",
                    iterator.hasNext(),
                    Matchers.is(false)
            );
        }

        @Test
        void remove() {
            final Collection<Module> objectUnderTest = new ToModuleCollection(Map.of(
                    5, List.of(0, 2),
                    4, List.of(0, 1)
            ));
            final Iterator<Module> iterator = objectUnderTest.iterator();
            iterator.next();
            iterator.remove();
            MatcherAssert.assertThat(
                    "remove did not do what it should have done",
                    objectUnderTest,
                    Matchers.hasSize(1)
            );
        }
    }

    @Test
    void toArray() {
        MatcherAssert.assertThat(
                "parameterless toArray() did not return correct elements",
                new ToModuleCollection(Map.of(
                        5, List.of(0, 2),
                        4, List.of(0, 1)
                )).toArray(),
                Matchers.is(Matchers.arrayContainingInAnyOrder(
                        new DefaultModule(5, Set.of(0, 2)),
                        new DefaultModule(4, Set.of(0, 1))
                ))
        );
    }

    @Test
    void testToArrayWithParameter() {
        MatcherAssert.assertThat(
                "parametered toArray() did not return correct elements",
                new ToModuleCollection(Map.of(
                        5, List.of(0, 2),
                        4, List.of(0, 1)
                )).toArray(new Object[0]),
                Matchers.is(Matchers.arrayContainingInAnyOrder(
                        new DefaultModule(5, Set.of(0, 2)),
                        new DefaultModule(4, Set.of(0, 1))
                ))
        );
    }

    @Test
    void add() {
        final Collection<Module> objectUnderTest = new ToModuleCollection(Map.of(
                5, List.of(0, 2),
                4, List.of(0, 1)
        ));
        objectUnderTest.add(new DefaultModule(2, Set.of(3)));
        MatcherAssert.assertThat(
                "add did not increase the number of elements",
                objectUnderTest,
                Matchers.hasSize(3)
        );
    }

    @Test
    void remove() {
        final Collection<Module> objectUnderTest = new ToModuleCollection(Map.of(
                5, List.of(0, 2),
                4, List.of(0, 1)
        ));
        objectUnderTest.remove(new DefaultModule(5, Set.of(2, 0)));
        MatcherAssert.assertThat(
                "remove did not decrease the number of elements",
                objectUnderTest,
                Matchers.hasSize(1)
        );
    }

    @Nested
    class ContainsAll {
        @Test
        void containsAllReturnsTrue() {
            MatcherAssert.assertThat(
                    "containsAll() did not return true",
                    new ToModuleCollection(Map.of(
                            5, List.of(0, 2),
                            4, List.of(0, 1)
                    )).containsAll(List.of(
                            new DefaultModule(5, Set.of(2, 0)),
                            new DefaultModule(4, Set.of(1, 0))
                    )),
                    Matchers.is(true)
            );
        }

        @Test
        void containsAllReturnsFalse() {
            MatcherAssert.assertThat(
                    "containsAll() did not return false",
                    new ToModuleCollection(Map.of(
                            5, List.of(0, 2),
                            4, List.of(0, 1)
                    )).containsAll(List.of(
                            new DefaultModule(5, Set.of(2, 0)),
                            new DefaultModule(3, Set.of(1, 0))
                    )),
                    Matchers.is(false)
            );
        }
    }

    @Nested
    class AddAll {
        @Test
        void addAllWorks() {
            final Collection<Module> objectUnderTest = new ToModuleCollection(Map.of(
                    5, List.of(0, 2),
                    4, List.of(0, 1)
            ));
            objectUnderTest.addAll(List.of(
                    new DefaultModule(2, Set.of(3)),
                    new DefaultModule(3, Collections.emptySet())
            ));
            MatcherAssert.assertThat(
                    "addAll() did not increase correctly the number of elements",
                    objectUnderTest,
                    Matchers.hasSize(4)
            );
        }

        @Test
        void addAllReturnsTrue() {
            MatcherAssert.assertThat(
                    "addAll() did not return true",
                    new ToModuleCollection(Map.of(
                            5, List.of(0, 2),
                            4, List.of(0, 1)
                    )).addAll(List.of(
                            new DefaultModule(2, Set.of(3)),
                            new DefaultModule(3, Collections.emptySet())
                    )),
                    Matchers.is(true)
            );
        }

        @Test
        void addAllReturnsFalse() {
            MatcherAssert.assertThat(
                    "addAll() did not return false",
                    new ToModuleCollection(Map.of(
                            5, List.of(0, 2),
                            4, List.of(0, 1)
                    )).addAll(Collections.emptyList()),
                    Matchers.is(false)
            );
        }
    }

    @Nested
    class RemoveAll {
        @Test
        void removeAllIsSuccessful() {
            MatcherAssert.assertThat(
                    "removeAll() did not return true",
                    new ToModuleCollection(Map.of(
                            5, List.of(0, 2),
                            4, List.of(0, 1),
                            0, Collections.emptyList(),
                            1, Collections.emptyList(),
                            2, List.of(3),
                            3, List.of(1)
                    )).removeAll(List.of(
                            new DefaultModule(1, Collections.emptySet()),
                            new DefaultModule(2, Set.of(3)),
                            new DefaultModule(3, Set.of(1))
                    )),
                    Matchers.is(Matchers.equalTo(true))
            );
        }

        @Test
        void removeAllIsNotSuccessful() {
            MatcherAssert.assertThat(
                    "removeAll() did not return false",
                    new ToModuleCollection(Map.of(
                            5, List.of(0, 2),
                            4, List.of(0, 1),
                            0, Collections.emptyList(),
                            1, Collections.emptyList(),
                            2, List.of(3)
                    )).removeAll(List.of(
                            new DefaultModule(3, Set.of(1)),
                            new DefaultModule(6, Collections.emptySet())
                    )),
                    Matchers.is(Matchers.equalTo(false))
            );
        }

        @Test
        void removeAllWorks() {
            final Collection<Module> objectUnderTest = new ToModuleCollection(Map.of(
                    5, List.of(0, 2),
                    4, List.of(0, 1),
                    0, Collections.emptyList(),
                    1, Collections.emptyList(),
                    2, List.of(3)
            ));
            objectUnderTest.removeAll(List.of(
                    new DefaultModule(1, Collections.emptySet()),
                    new DefaultModule(2, Set.of(3)),
                    new DefaultModule(3, Set.of(1))
            ));
            MatcherAssert.assertThat(
                    "removeAll() did not return true",
                    objectUnderTest,
                    Matchers.hasSize(3)
            );
        }
    }

    @Nested
    class RetainAll {
        @Test
        void retainAll() {
            final Collection<Module> objectUnderTest = new ToModuleCollection(Map.of(
                    5, List.of(0, 2),
                    4, List.of(0, 1),
                    0, Collections.emptyList(),
                    1, Collections.emptyList(),
                    2, List.of(3)
            ));
            objectUnderTest.retainAll(List.of(
                    new DefaultModule(1, Collections.emptySet()),
                    new DefaultModule(2, Set.of(3)),
                    new DefaultModule(3, Set.of(1))
            ));
            MatcherAssert.assertThat(
                    "retainAll() did not work as intended",
                    objectUnderTest,
                    Matchers.hasSize(2)
            );
        }

        @Test
        void retainAllReturnsTrue() {
            MatcherAssert.assertThat(
                    "retainAll() did not work as intended",
                    new ToModuleCollection(Map.of(
                            5, List.of(0, 2),
                            4, List.of(0, 1),
                            0, Collections.emptyList(),
                            1, Collections.emptyList(),
                            2, List.of(3)
                    )).retainAll(List.of(
                            new DefaultModule(1, Collections.emptySet()),
                            new DefaultModule(2, Set.of(3)),
                            new DefaultModule(3, Set.of(1))
                    )),
                    Matchers.is(true)
            );
        }

        @Test
        void retainAllReturnsFalse() {
            MatcherAssert.assertThat(
                    "retainAll() did not work as intended",
                    new ToModuleCollection(Map.of(
                            1, Collections.emptyList(),
                            2, List.of(3)
                    )).retainAll(List.of(
                            new DefaultModule(1, Collections.emptySet()),
                            new DefaultModule(2, Set.of(3)),
                            new DefaultModule(3, Set.of(1))
                    )),
                    Matchers.is(false)
            );
        }
    }

    @Test
    void clear() {
        final Collection<Module> objectUnderTest = new ToModuleCollection(Map.of(
                5, List.of(0, 2),
                4, List.of(0, 1),
                0, Collections.emptyList()
        ));
        objectUnderTest.clear();
        MatcherAssert.assertThat(
                "clear() did not make the Collection size 0",
                objectUnderTest,
                Matchers.hasSize(0)
        );
    }
}
