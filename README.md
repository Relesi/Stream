Stream-API


[![](http://javadoc-badge.appspot.com/com.annimon/stream.svg?label=JavaDocs)](http://www.javadoc.io/doc/com.annimon/stream/)

### Inclue

 + Functional interfaces (`Supplier`, `Function`, `Consumer` etc);
 + `Stream`/`IntStream`/`LongStream`/`DoubleStream` (without parallel processing, but with a variety of additional methods and with custom operators);
 + `Optional`/`OptionalBoolean`/`OptionalInt`/`OptionalLong`/`OptionalDouble` classes;
 + `Exceptional` class - functional way to deal with exceptions;
 + `Objects` from Java 7.


### Uso

```java
Stream.of(/* array | list | set | map | anything based on Iterator/Iterable interface */)
    .filter(..)
    .map(..)
    ...
    .sorted()
    .forEach(..);
Stream.of(value1, value2, value3)...
IntStream.range(0, 10)...
```
Example project: https://github.com/aNNiMON/Android-Java-8-Stream-Example


## Caracteristicas principais

### Operadores personalizados

Ao contrário dos fluxos Java 8, Stream-API oferece a capacidade de aplicar operadores personalizados.

```java
Stream.of(...)
    .custom(new Reverse<>())
    .forEach(...);

public final class Reverse<T> implements UnaryOperator<Stream<T>> {

    @Override
    public Stream<T> apply(Stream<T> stream) {
        final Iterator<? extends T> iterator = stream.getIterator();
        final ArrayDeque<T> deque = new ArrayDeque<T>();
        while (iterator.hasNext()) {
            deque.addFirst(iterator.next());
        }
        return Stream.of(deque.iterator());
    }
}
```
