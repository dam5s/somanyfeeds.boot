package io.damo.hamcrestkotlin

import org.hamcrest.Matcher
import org.hamcrest.collection.*
import org.hamcrest.number.*
import org.hamcrest.text.*
import org.hamcrest.beans.*
import org.hamcrest.xml.*

/**
 * Creates a matcher that matches arrays whose elements are satisfied by the specified matchers.  Matches
 * positively only if the number of matchers specified is equal to the length of the examined array and
 * each matcher[i] is satisfied by array[i].
 *
 *
 * For example:
 * assertThat(new Integer[]{1,2,3}, is(array(equalTo(1), equalTo(2), equalTo(3))))

 * @param elementMatchers
 * *     the matchers that the elements of examined arrays should satisfy
 */
public fun <T> array(vararg elementMatchers: Matcher<in T>): IsArray<T> {
    return IsArray.array(*elementMatchers)
}

/**
 * A shortcut to the frequently used `hasItemInArray(equalTo(x))`.
 *
 *
 * For example:
 * assertThat(hasItemInArray(x))
 * instead of:
 * assertThat(hasItemInArray(equalTo(x)))

 * @param element
 * *     the element that should be present in examined arrays
 */
public fun <T> hasItemInArray(element: T): Matcher<Array<T>> {
    return IsArrayContaining.hasItemInArray(element)
}

/**
 * Creates a matcher for arrays that matches when the examined array contains at least one item
 * that is matched by the specified `elementMatcher`.  Whilst matching, the traversal
 * of the examined array will stop as soon as a matching element is found.
 *
 *
 * For example:
 * assertThat(new String[] {"foo", "bar"}, hasItemInArray(startsWith("ba")))

 * @param elementMatcher
 * *     the matcher to apply to elements in examined arrays
 */
public fun <T> hasItemInArray(elementMatcher: Matcher<in T>): Matcher<Array<T>> {
    return IsArrayContaining.hasItemInArray(elementMatcher)
}

/**
 * Creates a matcher for arrays that matches when each item in the examined array satisfies the
 * corresponding matcher in the specified list of matchers.  For a positive match, the examined array
 * must be of the same length as the specified list of matchers.
 *
 *
 * For example:
 * assertThat(new String[]{"foo", "bar"}, contains(Arrays.asList(equalTo("foo"), equalTo("bar"))))

 * @param itemMatchers
 * *     a list of matchers, each of which must be satisfied by the corresponding item in an examined array
 */
public fun <E> arrayContaining(itemMatchers: List<Matcher<in E>>): Matcher<Array<E>> {
    return IsArrayContainingInOrder.arrayContaining(itemMatchers)
}

/**
 * Creates a matcher for arrays that matcheswhen each item in the examined array is
 * logically equal to the corresponding item in the specified items.  For a positive match,
 * the examined array must be of the same length as the number of specified items.
 *
 *
 * For example:
 * assertThat(new String[]{"foo", "bar"}, contains("foo", "bar"))

 * @param items
 * *     the items that must equal the items within an examined array
 */
public fun <E> arrayContaining(vararg items: E): Matcher<Array<E>> {
    return IsArrayContainingInOrder.arrayContaining(*items)
}

/**
 * Creates a matcher for arrays that matches when each item in the examined array satisfies the
 * corresponding matcher in the specified matchers.  For a positive match, the examined array
 * must be of the same length as the number of specified matchers.
 *
 *
 * For example:
 * assertThat(new String[]{"foo", "bar"}, contains(equalTo("foo"), equalTo("bar")))

 * @param itemMatchers
 * *     the matchers that must be satisfied by the items in the examined array
 */
public fun <E> arrayContaining(vararg itemMatchers: Matcher<in E>): Matcher<Array<E>> {
    return IsArrayContainingInOrder.arrayContaining(*itemMatchers)
}

/**
 * Creates an order agnostic matcher for arrays that matches when each item in the
 * examined array is logically equal to one item anywhere in the specified items.
 * For a positive match, the examined array must be of the same length as the number of
 * specified items.
 *
 *
 * N.B. each of the specified items will only be used once during a given examination, so be
 * careful when specifying items that may be equal to more than one entry in an examined
 * array.
 *
 *
 * For example:
 * assertThat(new String[]{"foo", "bar"}, containsInAnyOrder("bar", "foo"))

 * @param items
 * *     the items that must equal the entries of an examined array, in any order
 */
public fun <E> arrayContainingInAnyOrder(vararg items: E): Matcher<Array<E>> {
    return IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(*items)
}

/**
 * Creates an order agnostic matcher for arrays that matches when each item in the
 * examined array satisfies one matcher anywhere in the specified matchers.
 * For a positive match, the examined array must be of the same length as the number of
 * specified matchers.
 *
 *
 * N.B. each of the specified matchers will only be used once during a given examination, so be
 * careful when specifying matchers that may be satisfied by more than one entry in an examined
 * array.
 *
 *
 * For example:
 * assertThat(new String[]{"foo", "bar"}, arrayContainingInAnyOrder(equalTo("bar"), equalTo("foo")))

 * @param itemMatchers
 * *     a list of matchers, each of which must be satisfied by an entry in an examined array
 */
public fun <E> arrayContainingInAnyOrder(vararg itemMatchers: Matcher<in E>): Matcher<Array<E>> {
    return IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(*itemMatchers)
}

/**
 * Creates an order agnostic matcher for arrays that matches when each item in the
 * examined array satisfies one matcher anywhere in the specified collection of matchers.
 * For a positive match, the examined array must be of the same length as the specified collection
 * of matchers.
 *
 *
 * N.B. each matcher in the specified collection will only be used once during a given
 * examination, so be careful when specifying matchers that may be satisfied by more than
 * one entry in an examined array.
 *
 *
 * For example:
 * assertThat(new String[]{"foo", "bar"}, arrayContainingInAnyOrder(Arrays.asList(equalTo("bar"), equalTo("foo"))))

 * @param itemMatchers
 * *     a list of matchers, each of which must be satisfied by an item provided by an examined array
 */
public fun <E> arrayContainingInAnyOrder(itemMatchers: Collection<Matcher<in E>>): Matcher<Array<E>> {
    return IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(itemMatchers)
}

/**
 * Creates a matcher for arrays that matches when the `length` of the array
 * satisfies the specified matcher.
 *
 *
 * For example:
 * assertThat(new String[]{"foo", "bar"}, arrayWithSize(equalTo(2)))

 * @param sizeMatcher
 * *     a matcher for the length of an examined array
 */
public fun <E> arrayWithSize(sizeMatcher: Matcher<in Int>): Matcher<Array<E>> {
    return IsArrayWithSize.arrayWithSize(sizeMatcher)
}

/**
 * Creates a matcher for arrays that matches when the `length` of the array
 * equals the specified `size`.
 *
 *
 * For example:
 * assertThat(new String[]{"foo", "bar"}, arrayWithSize(2))

 * @param size
 * *     the length that an examined array must have for a positive match
 */
public fun <E> arrayWithSize(size: Int): Matcher<Array<E>> {
    return IsArrayWithSize.arrayWithSize(size)
}

/**
 * Creates a matcher for arrays that matches when the `length` of the array
 * is zero.
 *
 *
 * For example:
 * assertThat(new String[0], emptyArray())
 */
public fun <E> emptyArray(): Matcher<Array<E>> {
    return IsArrayWithSize.emptyArray()
}

/**
 * Creates a matcher for [java.util.Collection]s that matches when the `size()` method returns
 * a value that satisfies the specified matcher.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), hasSize(equalTo(2)))

 * @param sizeMatcher
 * *     a matcher for the size of an examined [java.util.Collection]
 */
public fun <E> hasSize(sizeMatcher: Matcher<in Int>): Matcher<Collection<E>> {
    return IsCollectionWithSize.hasSize(sizeMatcher)
}

/**
 * Creates a matcher for [java.util.Collection]s that matches when the `size()` method returns
 * a value equal to the specified `size`.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), hasSize(2))

 * @param size
 * *     the expected size of an examined [java.util.Collection]
 */
public fun <E> hasSize(size: Int): Matcher<Collection<E>> {
    return IsCollectionWithSize.hasSize(size)
}

/**
 * Creates a matcher for [java.util.Collection]s matching examined collections whose `isEmpty`
 * method returns `true`.
 *
 *
 * For example:
 * assertThat(new ArrayList&lt;String&gt;(), is(empty()))
 */
public fun <E> empty(): Matcher<Collection<E>> {
    return IsEmptyCollection.empty()
}

/**
 * Creates a matcher for [java.util.Collection]s matching examined collections whose `isEmpty`
 * method returns `true`.
 *
 *
 * For example:
 * assertThat(new ArrayList&lt;String&gt;(), is(emptyCollectionOf(String.class)))

 * @param type
 * *     the type of the collection's content
 */
public fun <E> emptyCollectionOf(type: Class<E>): Matcher<Collection<E>> {
    return IsEmptyCollection.emptyCollectionOf(type)
}

/**
 * Creates a matcher for [Iterable]s matching examined iterables that yield no items.
 *
 *
 * For example:
 * assertThat(new ArrayList&lt;String&gt;(), is(emptyIterable()))
 */
public fun <E> emptyIterable(): Matcher<Iterable<E>> {
    return IsEmptyIterable.emptyIterable()
}

/**
 * Creates a matcher for [Iterable]s matching examined iterables that yield no items.
 *
 *
 * For example:
 * assertThat(new ArrayList&lt;String&gt;(), is(emptyIterableOf(String.class)))

 * @param type
 * *     the type of the iterable's content
 */
public fun <E> emptyIterableOf(type: Class<E>): Matcher<Iterable<E>> {
    return IsEmptyIterable.emptyIterableOf(type)
}

/**
 * Creates a matcher for [Iterable]s that matches when a single pass over the
 * examined [Iterable] yields a series of items, each satisfying the corresponding
 * matcher in the specified matchers.  For a positive match, the examined iterable
 * must be of the same length as the number of specified matchers.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), contains(equalTo("foo"), equalTo("bar")))

 * @param itemMatchers
 * *     the matchers that must be satisfied by the items provided by an examined [Iterable]
 */
public fun <E> contains(vararg itemMatchers: Matcher<in E>): Matcher<Iterable<E>> {
    return IsIterableContainingInOrder.contains(*itemMatchers)
}

/**
 * Creates a matcher for [Iterable]s that matches when a single pass over the
 * examined [Iterable] yields a series of items, each logically equal to the
 * corresponding item in the specified items.  For a positive match, the examined iterable
 * must be of the same length as the number of specified items.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), contains("foo", "bar"))

 * @param items
 * *     the items that must equal the items provided by an examined [Iterable]
 */
public fun <E> contains(vararg items: E): Matcher<Iterable<E>> {
    return IsIterableContainingInOrder.contains(*items)
}

/**
 * Creates a matcher for [Iterable]s that matches when a single pass over the
 * examined [Iterable] yields a single item that satisfies the specified matcher.
 * For a positive match, the examined iterable must only yield one item.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo"), contains(equalTo("foo")))

 * @param itemMatcher
 * *     the matcher that must be satisfied by the single item provided by an
 * *     examined [Iterable]
 */
public fun <E> contains(itemMatcher: Matcher<in E>): Matcher<Iterable<E>> {
    return IsIterableContainingInOrder.contains(itemMatcher)
}

/**
 * Creates a matcher for [Iterable]s that matches when a single pass over the
 * examined [Iterable] yields a series of items, each satisfying the corresponding
 * matcher in the specified list of matchers.  For a positive match, the examined iterable
 * must be of the same length as the specified list of matchers.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), contains(Arrays.asList(equalTo("foo"), equalTo("bar"))))

 * @param itemMatchers
 * *     a list of matchers, each of which must be satisfied by the corresponding item provided by
 * *     an examined [Iterable]
 */
public fun <E> contains(itemMatchers: List<Matcher<in E>>): Matcher<Iterable<E>> {
    return IsIterableContainingInOrder.contains(itemMatchers)
}

/**
 * Creates an order agnostic matcher for [Iterable]s that matches when a single pass over
 * the examined [Iterable] yields a series of items, each logically equal to one item
 * anywhere in the specified items. For a positive match, the examined iterable
 * must be of the same length as the number of specified items.
 *
 *
 * N.B. each of the specified items will only be used once during a given examination, so be
 * careful when specifying items that may be equal to more than one entry in an examined
 * iterable.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), containsInAnyOrder("bar", "foo"))

 * @param items
 * *     the items that must equal the items provided by an examined [Iterable] in any order
 */
public fun <T> containsInAnyOrder(vararg items: T): Matcher<Iterable<T>> {
    return IsIterableContainingInAnyOrder.containsInAnyOrder(*items)
}

/**
 * Creates an order agnostic matcher for [Iterable]s that matches when a single pass over
 * the examined [Iterable] yields a series of items, each satisfying one matcher anywhere
 * in the specified collection of matchers.  For a positive match, the examined iterable
 * must be of the same length as the specified collection of matchers.
 *
 *
 * N.B. each matcher in the specified collection will only be used once during a given
 * examination, so be careful when specifying matchers that may be satisfied by more than
 * one entry in an examined iterable.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), containsInAnyOrder(Arrays.asList(equalTo("bar"), equalTo("foo"))))

 * @param itemMatchers
 * *     a list of matchers, each of which must be satisfied by an item provided by an examined [Iterable]
 */
public fun <T> containsInAnyOrder(itemMatchers: Collection<Matcher<in T>>): Matcher<Iterable<T>> {
    return IsIterableContainingInAnyOrder.containsInAnyOrder(itemMatchers)
}

/**
 * Creates an order agnostic matcher for [Iterable]s that matches when a single pass over
 * the examined [Iterable] yields a series of items, each satisfying one matcher anywhere
 * in the specified matchers.  For a positive match, the examined iterable must be of the same
 * length as the number of specified matchers.
 *
 *
 * N.B. each of the specified matchers will only be used once during a given examination, so be
 * careful when specifying matchers that may be satisfied by more than one entry in an examined
 * iterable.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), containsInAnyOrder(equalTo("bar"), equalTo("foo")))

 * @param itemMatchers
 * *     a list of matchers, each of which must be satisfied by an item provided by an examined [Iterable]
 */
public fun <T> containsInAnyOrder(vararg itemMatchers: Matcher<in T>): Matcher<Iterable<T>> {
    return IsIterableContainingInAnyOrder.containsInAnyOrder(*itemMatchers)
}

/**
 * Creates a matcher for [Iterable]s that matches when a single pass over the
 * examined [Iterable] yields a single item that satisfies the specified matcher.
 * For a positive match, the examined iterable must only yield one item.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo"), containsInAnyOrder(equalTo("foo")))

 * @param itemMatcher
 * *     the matcher that must be satisfied by the single item provided by an
 * *     examined [Iterable]
 */
deprecated("use contains(Matcher<? super E> itemMatcher) instead\n    ")
public fun <E> containsInAnyOrder(itemMatcher: Matcher<in E>): Matcher<Iterable<E>> {
    return IsIterableContainingInAnyOrder.containsInAnyOrder(itemMatcher)
}

/**
 * Creates a matcher for [Iterable]s that matches when a single pass over the
 * examined [Iterable] yields an item count that satisfies the specified
 * matcher.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), iterableWithSize(equalTo(2)))

 * @param sizeMatcher
 * *     a matcher for the number of items that should be yielded by an examined [Iterable]
 */
public fun <E> iterableWithSize(sizeMatcher: Matcher<in Int>): Matcher<Iterable<E>> {
    return IsIterableWithSize.iterableWithSize(sizeMatcher)
}

/**
 * Creates a matcher for [Iterable]s that matches when a single pass over the
 * examined [Iterable] yields an item count that is equal to the specified
 * `size` argument.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), iterableWithSize(2))

 * @param size
 * *     the number of items that should be yielded by an examined [Iterable]
 */
public fun <E> iterableWithSize(size: Int): Matcher<Iterable<E>> {
    return IsIterableWithSize.iterableWithSize(size)
}

/**
 * Creates a matcher for [java.util.Map]s matching when the examined [java.util.Map] contains
 * at least one entry whose key equals the specified `key` **and** whose value equals the
 * specified `value`.
 *
 *
 * For example:
 * assertThat(myMap, hasEntry("bar", "foo"))

 * @param key
 * *     the key that, in combination with the value, must be describe at least one entry
 * *
 * @param value
 * *     the value that, in combination with the key, must be describe at least one entry
 */
public fun <K, V> hasEntry(key: K, value: V): Matcher<Map<out K, V>> {
    return IsMapContaining.hasEntry(key, value)
}

/**
 * Creates a matcher for [java.util.Map]s matching when the examined [java.util.Map] contains
 * at least one entry whose key satisfies the specified `keyMatcher` **and** whose
 * value satisfies the specified `valueMatcher`.
 *
 *
 * For example:
 * assertThat(myMap, hasEntry(equalTo("bar"), equalTo("foo")))

 * @param keyMatcher
 * *     the key matcher that, in combination with the valueMatcher, must be satisfied by at least one entry
 * *
 * @param valueMatcher
 * *     the value matcher that, in combination with the keyMatcher, must be satisfied by at least one entry
 */
public fun <K, V> hasEntry(keyMatcher: Matcher<in K>, valueMatcher: Matcher<in V>): Matcher<Map<out K, V>> {
    return IsMapContaining.hasEntry(keyMatcher, valueMatcher)
}

/**
 * Creates a matcher for [java.util.Map]s matching when the examined [java.util.Map] contains
 * at least one key that satisfies the specified matcher.
 *
 *
 * For example:
 * assertThat(myMap, hasKey(equalTo("bar")))

 * @param keyMatcher
 * *     the matcher that must be satisfied by at least one key
 */
public fun <K> hasKey(keyMatcher: Matcher<in K>): Matcher<Map<out K, *>> {
    return IsMapContaining.hasKey(keyMatcher)
}

/**
 * Creates a matcher for [java.util.Map]s matching when the examined [java.util.Map] contains
 * at least one key that is equal to the specified key.
 *
 *
 * For example:
 * assertThat(myMap, hasKey("bar"))

 * @param key
 * *     the key that satisfying maps must contain
 */
public fun <K> hasKey(key: K): Matcher<Map<out K, *>> {
    return IsMapContaining.hasKey(key)
}

/**
 * Creates a matcher for [java.util.Map]s matching when the examined [java.util.Map] contains
 * at least one value that is equal to the specified value.
 *
 *
 * For example:
 * assertThat(myMap, hasValue("foo"))

 * @param value
 * *     the value that satisfying maps must contain
 */
public fun <V> hasValue(value: V): Matcher<Map<*, V>> {
    return IsMapContaining.hasValue(value)
}

/**
 * Creates a matcher for [java.util.Map]s matching when the examined [java.util.Map] contains
 * at least one value that satisfies the specified valueMatcher.
 *
 *
 * For example:
 * assertThat(myMap, hasValue(equalTo("foo")))

 * @param valueMatcher
 * *     the matcher that must be satisfied by at least one value
 */
public fun <V> hasValue(valueMatcher: Matcher<in V>): Matcher<Map<*, V>> {
    return IsMapContaining.hasValue(valueMatcher)
}

/**
 * Creates a matcher that matches when the examined object is found within the
 * specified collection.
 *
 *
 * For example:
 * assertThat("foo", isIn(Arrays.asList("bar", "foo")))

 * @param collection
 * *     the collection in which matching items must be found
 */
public fun <T> isIn(collection: Collection<T>): Matcher<T> {
    return IsIn.isIn(collection)
}

public fun <T> isIn(param1: Array<T>): Matcher<T> {
    return IsIn.isIn(param1)
}

/**
 * Creates a matcher that matches when the examined object is equal to one of the
 * specified elements.
 *
 *
 * For example:
 * assertThat("foo", isIn("bar", "foo"))

 * @param elements
 * *     the elements amongst which matching items will be found
 */
public fun <T> isOneOf(vararg elements: T): Matcher<T> {
    return IsIn.isOneOf(*elements)
}

/**
 * Creates a matcher of [Double]s that matches when an examined double is equal
 * to the specified `operand`, within a range of +/- `error`.
 *
 *
 * For example:
 * assertThat(1.03, is(closeTo(1.0, 0.03)))

 * @param operand
 * *     the expected value of matching doubles
 * *
 * @param error
 * *     the delta (+/-) within which matches will be allowed
 */
public fun closeTo(operand: Double, error: Double): Matcher<Double> {
    return IsCloseTo.closeTo(operand, error)
}

/**
 * Creates a matcher of [java.math.BigDecimal]s that matches when an examined BigDecimal is equal
 * to the specified `operand`, within a range of +/- `error`. The comparison for equality
 * is done by BigDecimals [java.math.BigDecimal.compareTo] method.
 *
 *
 * For example:
 * assertThat(new BigDecimal("1.03"), is(closeTo(new BigDecimal("1.0"), new BigDecimal("0.03"))))

 * @param operand
 * *     the expected value of matching BigDecimals
 * *
 * @param error
 * *     the delta (+/-) within which matches will be allowed
 */
public fun closeTo(operand: java.math.BigDecimal, error: java.math.BigDecimal): Matcher<java.math.BigDecimal> {
    return BigDecimalCloseTo.closeTo(operand, error)
}

/**
 * Creates a matcher of [Comparable] object that matches when the examined object is
 * equal to the specified value, as reported by the `compareTo` method of the
 * **examined** object.
 *
 *
 * For example:
 * assertThat(1, comparesEqualTo(1))

 * @param value
 * *     the value which, when passed to the compareTo method of the examined object, should return zero
 */
public fun <T : Comparable<T>> comparesEqualTo(value: T): Matcher<T> {
    return OrderingComparison.comparesEqualTo(value)
}

/**
 * Creates a matcher of [Comparable] object that matches when the examined object is
 * greater than the specified value, as reported by the `compareTo` method of the
 * **examined** object.
 *
 *
 * For example:
 * assertThat(2, greaterThan(1))

 * @param value
 * *     the value which, when passed to the compareTo method of the examined object, should return greater
 * *     than zero
 */
public fun <T : Comparable<T>> greaterThan(value: T): Matcher<T> {
    return OrderingComparison.greaterThan(value)
}

/**
 * Creates a matcher of [Comparable] object that matches when the examined object is
 * greater than or equal to the specified value, as reported by the `compareTo` method
 * of the **examined** object.
 *
 *
 * For example:
 * assertThat(1, greaterThanOrEqualTo(1))

 * @param value
 * *     the value which, when passed to the compareTo method of the examined object, should return greater
 * *     than or equal to zero
 */
public fun <T : Comparable<T>> greaterThanOrEqualTo(value: T): Matcher<T> {
    return OrderingComparison.greaterThanOrEqualTo(value)
}

/**
 * Creates a matcher of [Comparable] object that matches when the examined object is
 * less than the specified value, as reported by the `compareTo` method of the
 * **examined** object.
 *
 *
 * For example:
 * assertThat(1, lessThan(2))

 * @param value
 * *     the value which, when passed to the compareTo method of the examined object, should return less
 * *     than zero
 */
public fun <T : Comparable<T>> lessThan(value: T): Matcher<T> {
    return OrderingComparison.lessThan(value)
}

/**
 * Creates a matcher of [Comparable] object that matches when the examined object is
 * less than or equal to the specified value, as reported by the `compareTo` method
 * of the **examined** object.
 *
 *
 * For example:
 * assertThat(1, lessThanOrEqualTo(1))

 * @param value
 * *     the value which, when passed to the compareTo method of the examined object, should return less
 * *     than or equal to zero
 */
public fun <T : Comparable<T>> lessThanOrEqualTo(value: T): Matcher<T> {
    return OrderingComparison.lessThanOrEqualTo(value)
}

/**
 * Creates a matcher of [String] that matches when the examined string is equal to
 * the specified expectedString, ignoring case.
 *
 *
 * For example:
 * assertThat("Foo", equalToIgnoringCase("FOO"))

 * @param expectedString
 * *     the expected value of matched strings
 */
public fun equalToIgnoringCase(expectedString: String): Matcher<String> {
    return IsEqualIgnoringCase.equalToIgnoringCase(expectedString)
}

/**
 * Creates a matcher of [String] that matches when the examined string is equal to
 * the specified expectedString, when whitespace differences are (mostly) ignored.  To be
 * exact, the following whitespace rules are applied:
 *
 *  * all leading and trailing whitespace of both the expectedString and the examined string are ignored
 *  * any remaining whitespace, appearing within either string, is collapsed to a single space before comparison
 *
 *
 *
 * For example:
 * assertThat("   my\tfoo  bar ", equalToIgnoringWhiteSpace(" my  foo bar"))

 * @param expectedString
 * *     the expected value of matched strings
 */
public fun equalToIgnoringWhiteSpace(expectedString: String): Matcher<String> {
    return IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace(expectedString)
}

/**
 * Creates a matcher of [String] that matches when the examined string has zero length.
 *
 *
 * For example:
 * assertThat("", isEmptyString())
 */
public fun isEmptyString(): Matcher<String> {
    return IsEmptyString.isEmptyString()
}

/**
 * Creates a matcher of [String] that matches when the examined string is `null`, or
 * has zero length.
 *
 *
 * For example:
 * assertThat(((String)null), isEmptyString())
 */
public fun isEmptyOrNullString(): Matcher<String> {
    return IsEmptyString.isEmptyOrNullString()
}

/**
 * Creates a matcher of [String] that matches when the examined string contains all of
 * the specified substrings, regardless of the order of their appearance.
 *
 *
 * For example:
 * assertThat("myfoobarbaz", stringContainsInOrder(Arrays.asList("bar", "foo")))

 * @param substrings
 * *     the substrings that must be contained within matching strings
 */
public fun stringContainsInOrder(substrings: Iterable<String>): Matcher<String> {
    return StringContainsInOrder.stringContainsInOrder(substrings)
}

/**
 * Creates a matcher that matches any examined object whose `toString` method
 * returns a value that satisfies the specified matcher.
 *
 *
 * For example:
 * assertThat(true, hasToString(equalTo("TRUE")))

 * @param toStringMatcher
 * *     the matcher used to verify the toString result
 */
public fun <T> hasToString(toStringMatcher: Matcher<in String>): Matcher<T> {
    return org.hamcrest.`object`.HasToString.hasToString(toStringMatcher)
}

/**
 * Creates a matcher that matches any examined object whose `toString` method
 * returns a value equalTo the specified string.
 *
 *
 * For example:
 * assertThat(true, hasToString("TRUE"))

 * @param expectedToString
 * *     the expected toString result
 */
public fun <T> hasToString(expectedToString: String): Matcher<T> {
    return org.hamcrest.`object`.HasToString.hasToString(expectedToString)
}

/**
 * Creates a matcher of [Class] that matches when the specified baseType is
 * assignable from the examined class.
 *
 *
 * For example:
 * assertThat(Integer.class, typeCompatibleWith(Number.class))

 * @param baseType
 * *     the base class to examine classes against
 */
public fun <T> typeCompatibleWith(baseType: Class<T>): Matcher<Class<*>> {
    return org.hamcrest.`object`.IsCompatibleType.typeCompatibleWith(baseType)
}

/**
 * Creates a matcher of [java.util.EventObject] that matches any object
 * derived from eventClass announced by source.
 *
 * For example:
 * assertThat(myEvent, is(eventFrom(PropertyChangeEvent.class, myBean)))

 * @param eventClass
 * *     the class of the event to match on
 * *
 * @param source
 * *     the source of the event
 */
public fun eventFrom(eventClass: Class<out java.util.EventObject>, source: Any): Matcher<java.util.EventObject> {
    return org.hamcrest.`object`.IsEventFrom.eventFrom(eventClass, source)
}

/**
 * Creates a matcher of [java.util.EventObject] that matches any EventObject
 * announced by source.
 *
 * For example:
 * assertThat(myEvent, is(eventFrom(myBean)))

 * @param source
 * *     the source of the event
 */
public fun eventFrom(source: Any): Matcher<java.util.EventObject> {
    return org.hamcrest.`object`.IsEventFrom.eventFrom(source)
}

/**
 * Creates a matcher that matches when the examined object has a JavaBean property
 * with the specified name.
 *
 *
 * For example:
 * assertThat(myBean, hasProperty("foo"))

 * @param propertyName
 * *     the name of the JavaBean property that examined beans should possess
 */
public fun <T> hasProperty(propertyName: String): Matcher<T> {
    return HasProperty.hasProperty(propertyName)
}

/**
 * Creates a matcher that matches when the examined object has a JavaBean property
 * with the specified name whose value satisfies the specified matcher.
 *
 *
 * For example:
 * assertThat(myBean, hasProperty("foo", equalTo("bar"))

 * @param propertyName
 * *     the name of the JavaBean property that examined beans should possess
 * *
 * @param valueMatcher
 * *     a matcher for the value of the specified property of the examined bean
 */
public fun <T> hasProperty(propertyName: String, valueMatcher: Matcher<*>): Matcher<T> {
    return HasPropertyWithValue.hasProperty(propertyName, valueMatcher)
}

/**
 * Creates a matcher that matches when the examined object has values for all of
 * its JavaBean properties that are equal to the corresponding values of the
 * specified bean.
 *
 *
 * For example:
 * assertThat(myBean, samePropertyValuesAs(myExpectedBean))

 * @param expectedBean
 * *     the bean against which examined beans are compared
 */
public fun <T> samePropertyValuesAs(expectedBean: T): Matcher<T> {
    return SamePropertyValuesAs.samePropertyValuesAs(expectedBean)
}

/**
 * Creates a matcher of [org.w3c.dom.Node]s that matches when the examined node contains a node
 * at the specified `xPath` within the specified namespace context, with any content.
 *
 *
 * For example:
 * assertThat(xml, hasXPath("/root/something[2]/cheese", myNs))

 * @param xPath
 * *     the target xpath
 * *
 * @param namespaceContext
 * *     the namespace for matching nodes
 */
public fun hasXPath(xPath: String, namespaceContext: javax.xml.namespace.NamespaceContext): Matcher<org.w3c.dom.Node> {
    return HasXPath.hasXPath(xPath, namespaceContext)
}

/**
 * Creates a matcher of [org.w3c.dom.Node]s that matches when the examined node contains a node
 * at the specified `xPath`, with any content.
 *
 *
 * For example:
 * assertThat(xml, hasXPath("/root/something[2]/cheese"))

 * @param xPath
 * *     the target xpath
 */
public fun hasXPath(xPath: String): Matcher<org.w3c.dom.Node> {
    return HasXPath.hasXPath(xPath)
}

/**
 * Creates a matcher of [org.w3c.dom.Node]s that matches when the examined node has a value at the
 * specified `xPath`, within the specified `namespaceContext`, that satisfies
 * the specified `valueMatcher`.
 *
 *
 * For example:
 * assertThat(xml, hasXPath("/root/something[2]/cheese", myNs, equalTo("Cheddar")))

 * @param xPath
 * *     the target xpath
 * *
 * @param namespaceContext
 * *     the namespace for matching nodes
 * *
 * @param valueMatcher
 * *     matcher for the value at the specified xpath
 */
public fun hasXPath(xPath: String, namespaceContext: javax.xml.namespace.NamespaceContext, valueMatcher: Matcher<String>): Matcher<org.w3c.dom.Node> {
    return HasXPath.hasXPath(xPath, namespaceContext, valueMatcher)
}

/**
 * Creates a matcher of [org.w3c.dom.Node]s that matches when the examined node has a value at the
 * specified `xPath` that satisfies the specified `valueMatcher`.
 *
 *
 * For example:
 * assertThat(xml, hasXPath("/root/something[2]/cheese", equalTo("Cheddar")))

 * @param xPath
 * *     the target xpath
 * *
 * @param valueMatcher
 * *     matcher for the value at the specified xpath
 */
public fun hasXPath(xPath: String, valueMatcher: Matcher<String>): Matcher<org.w3c.dom.Node> {
    return HasXPath.hasXPath(xPath, valueMatcher)
}
