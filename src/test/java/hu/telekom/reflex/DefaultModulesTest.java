package hu.telekom.reflex;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class DefaultModulesTest {
    @RepeatedTest(100)
    void sortedDependencyIds() throws Modules.CircularDependenciesException {
        final List<Integer> actualResult = new DefaultModules(Map.of(
                5, List.of(0, 2),
                4, List.of(0, 1),
                0, Collections.emptyList(),
                1, Collections.emptyList(),
                2, List.of(3),
                3, List.of(1)
        )).sortedDependencyIds();
        System.out.println(actualResult);
        MatcherAssert.assertThat(
                actualResult.indexOf(5),
                Matchers.allOf(
                        Matchers.is(Matchers.greaterThan(actualResult.indexOf(2))),
                        Matchers.is(Matchers.greaterThan(actualResult.indexOf(0)))
                )
        );
        MatcherAssert.assertThat(
                actualResult.indexOf(4),
                Matchers.allOf(
                        Matchers.is(Matchers.greaterThan(actualResult.indexOf(1))),
                        Matchers.is(Matchers.greaterThan(actualResult.indexOf(0)))
                )
        );
        MatcherAssert.assertThat(
                actualResult.indexOf(2),
                Matchers.is(Matchers.greaterThan(actualResult.indexOf(3)))
        );
        MatcherAssert.assertThat(
                actualResult.indexOf(3),
                Matchers.is(Matchers.greaterThan(actualResult.indexOf(1)))
        );
    }

    @Test
    void ifThereAreCircularDependenciesThenThrowsAnException() {
        Assertions.assertThrows(
                Modules.CircularDependenciesException.class,
                () -> new DefaultModules(Map.of(
                        1, Collections.emptyList(),
                        2, List.of(3),
                        3, List.of(2)
                )).sortedDependencyIds(),
                "did not throw CircularDependenciesException"
        );
    }

    @Test
    void sortedDependencyIdsDoesntMessUpDependencies()
            throws Modules.CircularDependenciesException {
        final Modules myModules = new DefaultModules(Map.of(
                5, List.of(0, 2),
                4, List.of(0, 1),
                0, Collections.emptyList(),
                1, Collections.emptyList(),
                2, List.of(3),
                3, List.of(1)
        ));
        myModules.sortedDependencyIds();
        MatcherAssert.assertThat(
                "size has changed!!",
                myModules.sortedDependencyIds(),
                Matchers.hasSize(6)
        );
    }
}
