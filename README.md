# Dependency Resolution

This small algorithm practice project provides the solution for the following question:

> Figure out a resolution order for a given set of dependencies given in the format below:
>
> ```java
> Map<Integer, List<Integer> dependencies = Map.of(
>                5, List.of(0, 2),
>                4, List.of(0, 1),
>                0, Collections.emptyList(),
>                1, Collections.emptyList(),
>                2, List.of(3),
>                3, List.of(1)
> );
> ```
>
> The answer should be given as a `List<Integer>`.

## Use

```java
new DefaultModules(dependencies).sortedDependencyIds();
```