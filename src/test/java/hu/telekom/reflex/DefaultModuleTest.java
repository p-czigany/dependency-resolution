package hu.telekom.reflex;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

class DefaultModuleTest {
    @Nested
    class Satisfied {
        @Test
        void unsatisfied() {
            MatcherAssert.assertThat(
                    "not the right Module IDs are returned",
                    new DefaultModule(5, Set.of(0, 2)).satisfied(List.of(
                            new DefaultModule(4, Set.of(0, 1)),
                            new DefaultModule(0, Collections.emptySet())
                    )),
                    Matchers.is(false)
            );
        }

        @Test
        void satisfied() {
            MatcherAssert.assertThat(
                    "not the right Module IDs are returned",
                    new DefaultModule(5, Set.of(0, 2)).satisfied(List.of(
                            new DefaultModule(2, Set.of(3)),
                            new DefaultModule(0, Collections.emptySet())
                    )),
                    Matchers.is(true)
            );
        }
    }
}
