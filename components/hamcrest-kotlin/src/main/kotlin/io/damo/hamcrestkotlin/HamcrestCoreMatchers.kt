package io.damo.hamcrestkotlin

import org.hamcrest.Matcher
import org.hamcrest.core.*

/**
 * Creates a matcher that matches if the examined object matches **ALL** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", allOf(startsWith("my"), containsString("Val")))
 */
public fun <T> allOf(matchers: Iterable<Matcher<in T>>): Matcher<T> {
    return AllOf.allOf(matchers)
}

/**
 * Creates a matcher that matches if the examined object matches **ALL** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", allOf(startsWith("my"), containsString("Val")))
 */
public fun <T> allOf(vararg matchers: Matcher<in T>): Matcher<T> {
    return AllOf.allOf(*matchers)
}

/**
 * Creates a matcher that matches if the examined object matches **ALL** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", allOf(startsWith("my"), containsString("Val")))
 */
public fun <T> allOf(first: Matcher<in T>, second: Matcher<in T>): Matcher<T> {
    return AllOf.allOf(first, second)
}

/**
 * Creates a matcher that matches if the examined object matches **ALL** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", allOf(startsWith("my"), containsString("Val")))
 */
public fun <T> allOf(first: Matcher<in T>, second: Matcher<in T>, third: Matcher<in T>): Matcher<T> {
    return AllOf.allOf(first, second, third)
}

/**
 * Creates a matcher that matches if the examined object matches **ALL** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", allOf(startsWith("my"), containsString("Val")))
 */
public fun <T> allOf(first: Matcher<in T>, second: Matcher<in T>, third: Matcher<in T>, fourth: Matcher<in T>): Matcher<T> {
    return AllOf.allOf(first, second, third, fourth)
}

/**
 * Creates a matcher that matches if the examined object matches **ALL** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", allOf(startsWith("my"), containsString("Val")))
 */
public fun <T> allOf(first: Matcher<in T>, second: Matcher<in T>, third: Matcher<in T>, fourth: Matcher<in T>, fifth: Matcher<in T>): Matcher<T> {
    return AllOf.allOf(first, second, third, fourth, fifth)
}

/**
 * Creates a matcher that matches if the examined object matches **ALL** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", allOf(startsWith("my"), containsString("Val")))
 */
public fun <T> allOf(first: Matcher<in T>, second: Matcher<in T>, third: Matcher<in T>, fourth: Matcher<in T>, fifth: Matcher<in T>, sixth: Matcher<in T>): Matcher<T> {
    return AllOf.allOf(first, second, third, fourth, fifth, sixth)
}

/**
 * Creates a matcher that matches if the examined object matches **ANY** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))
 */
public fun <T> anyOf(matchers: Iterable<Matcher<in T>>): AnyOf<T> {
    return AnyOf.anyOf(matchers)
}

/**
 * Creates a matcher that matches if the examined object matches **ANY** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))
 */
public fun <T> anyOf(first: Matcher<T>, second: Matcher<in T>, third: Matcher<in T>): AnyOf<T> {
    return AnyOf.anyOf(first, second, third)
}

/**
 * Creates a matcher that matches if the examined object matches **ANY** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))
 */
public fun <T> anyOf(first: Matcher<T>, second: Matcher<in T>, third: Matcher<in T>, fourth: Matcher<in T>): AnyOf<T> {
    return AnyOf.anyOf(first, second, third, fourth)
}

/**
 * Creates a matcher that matches if the examined object matches **ANY** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))
 */
public fun <T> anyOf(first: Matcher<T>, second: Matcher<in T>, third: Matcher<in T>, fourth: Matcher<in T>, fifth: Matcher<in T>): AnyOf<T> {
    return AnyOf.anyOf(first, second, third, fourth, fifth)
}

/**
 * Creates a matcher that matches if the examined object matches **ANY** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))
 */
public fun <T> anyOf(first: Matcher<T>, second: Matcher<in T>, third: Matcher<in T>, fourth: Matcher<in T>, fifth: Matcher<in T>, sixth: Matcher<in T>): AnyOf<T> {
    return AnyOf.anyOf(first, second, third, fourth, fifth, sixth)
}

/**
 * Creates a matcher that matches if the examined object matches **ANY** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))
 */
public fun <T> anyOf(first: Matcher<T>, second: Matcher<in T>): AnyOf<T> {
    return AnyOf.anyOf(first, second)
}

/**
 * Creates a matcher that matches if the examined object matches **ANY** of the specified matchers.
 *
 *
 * For example:
 * assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))
 */
public fun <T> anyOf(vararg matchers: Matcher<in T>): AnyOf<T> {
    return AnyOf.anyOf(*matchers)
}

/**
 * Creates a matcher that matches when both of the specified matchers match the examined object.
 *
 *
 * For example:
 * assertThat("fab", both(containsString("a")).and(containsString("b")))
 */
public fun <LHS> both(matcher: Matcher<in LHS>): CombinableMatcher.CombinableBothMatcher<LHS> {
    return CombinableMatcher.both(matcher)
}

/**
 * Creates a matcher that matches when either of the specified matchers match the examined object.
 *
 *
 * For example:
 * assertThat("fan", either(containsString("a")).and(containsString("b")))
 */
public fun <LHS> either(matcher: Matcher<in LHS>): CombinableMatcher.CombinableEitherMatcher<LHS> {
    return CombinableMatcher.either(matcher)
}

/**
 * Wraps an existing matcher, overriding its description with that specified.  All other functions are
 * delegated to the decorated matcher, including its mismatch description.
 *
 *
 * For example:
 * describedAs("a big decimal equal to %0", equalTo(myBigDecimal), myBigDecimal.toPlainString())

 * @param description
 * *     the new description for the wrapped matcher
 * *
 * @param matcher
 * *     the matcher to wrap
 * *
 * @param values
 * *     optional values to insert into the tokenised description
 */
public fun <T> describedAs(description: String, matcher: Matcher<T>, vararg values: Any): Matcher<T> {
    return DescribedAs.describedAs(description, matcher, *values)
}

/**
 * Creates a matcher for [Iterable]s that only matches when a single pass over the
 * examined [Iterable] yields items that are all matched by the specified
 * `itemMatcher`.
 *
 *
 * For example:
 * assertThat(Arrays.asList("bar", "baz"), everyItem(startsWith("ba")))

 * @param itemMatcher
 * *     the matcher to apply to every item provided by the examined [Iterable]
 */
public fun <U> everyItem(itemMatcher: Matcher<U>): Matcher<Iterable<U>> {
    return Every.everyItem(itemMatcher)
}

/**
 * A shortcut to the frequently used `is(equalTo(x))`.
 *
 *
 * For example:
 * assertThat(cheese, is(smelly))
 * instead of:
 * assertThat(cheese, is(equalTo(smelly)))
 */
public fun <T> `is`(value: T): Matcher<T> {
    return Is.`is`(value)
}

/**
 * Decorates another Matcher, retaining its behaviour, but allowing tests
 * to be slightly more expressive.
 *
 *
 * For example:
 * assertThat(cheese, is(equalTo(smelly)))
 * instead of:
 * assertThat(cheese, equalTo(smelly))
 */
public fun <T> `is`(matcher: Matcher<T>): Matcher<T> {
    return Is.`is`(matcher)
}

/**
 * A shortcut to the frequently used `is(instanceOf(SomeClass.class))`.
 *
 *
 * For example:
 * assertThat(cheese, is(Cheddar.class))
 * instead of:
 * assertThat(cheese, is(instanceOf(Cheddar.class)))

 */
deprecated("use isA(Class<T> type) instead.")
public fun <T> `is`(type: Class<T>): Matcher<T> {
    return Is.`is`(type)
}

/**
 * A shortcut to the frequently used `is(instanceOf(SomeClass.class))`.
 *
 *
 * For example:
 * assertThat(cheese, isA(Cheddar.class))
 * instead of:
 * assertThat(cheese, is(instanceOf(Cheddar.class)))
 */
public fun <T> isA(type: Class<T>): Matcher<T> {
    return Is.isA(type)
}

/**
 * Creates a matcher that always matches, regardless of the examined object.
 */
public fun anything(): Matcher<Any> {
    return IsAnything.anything()
}

/**
 * Creates a matcher that always matches, regardless of the examined object, but describes
 * itself with the specified [String].

 * @param description
 * *     a meaningful [String] used when describing itself
 */
public fun anything(description: String): Matcher<Any> {
    return IsAnything.anything(description)
}

/**
 * Creates a matcher for [Iterable]s that only matches when a single pass over the
 * examined [Iterable] yields at least one item that is equal to the specified
 * `item`.  Whilst matching, the traversal of the examined [Iterable]
 * will stop as soon as a matching item is found.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), hasItem("bar"))

 * @param item
 * *     the item to compare against the items provided by the examined [Iterable]
 */
public fun <T> hasItem(item: T): Matcher<Iterable<*>> {
    return IsCollectionContaining.hasItem(item)
}

/**
 * Creates a matcher for [Iterable]s that only matches when a single pass over the
 * examined [Iterable] yields at least one item that is matched by the specified
 * `itemMatcher`.  Whilst matching, the traversal of the examined [Iterable]
 * will stop as soon as a matching item is found.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar"), hasItem(startsWith("ba")))

 * @param itemMatcher
 * *     the matcher to apply to items provided by the examined [Iterable]
 */
public fun <T> hasItem(itemMatcher: Matcher<in T>): Matcher<Iterable<*>> {
    return IsCollectionContaining.hasItem(itemMatcher)
}

/**
 * Creates a matcher for [Iterable]s that matches when consecutive passes over the
 * examined [Iterable] yield at least one item that is equal to the corresponding
 * item from the specified `items`.  Whilst matching, each traversal of the
 * examined [Iterable] will stop as soon as a matching item is found.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar", "baz"), hasItems("baz", "foo"))

 * @param items
 * *     the items to compare against the items provided by the examined [Iterable]
 */
public fun <T> hasItems(vararg items: T): Matcher<Iterable<T>> {
    return IsCollectionContaining.hasItems(*items)
}

/**
 * Creates a matcher for [Iterable]s that matches when consecutive passes over the
 * examined [Iterable] yield at least one item that is matched by the corresponding
 * matcher from the specified `itemMatchers`.  Whilst matching, each traversal of
 * the examined [Iterable] will stop as soon as a matching item is found.
 *
 *
 * For example:
 * assertThat(Arrays.asList("foo", "bar", "baz"), hasItems(endsWith("z"), endsWith("o")))

 * @param itemMatchers
 * *     the matchers to apply to items provided by the examined [Iterable]
 */
public fun <T> hasItems(vararg itemMatchers: Matcher<in T>): Matcher<Iterable<T>> {
    return IsCollectionContaining.hasItems(*itemMatchers)
}

/**
 * Creates a matcher that matches when the examined object is logically equal to the specified
 * `operand`, as determined by calling the [Object.equals] method on
 * the **examined** object.

 *
 * If the specified operand is `null` then the created matcher will only match if
 * the examined object's `equals` method returns `true` when passed a
 * `null` (which would be a violation of the `equals` contract), unless the
 * examined object itself is `null`, in which case the matcher will return a positive
 * match.

 *
 * The created matcher provides a special behaviour when examining `Array`s, whereby
 * it will match if both the operand and the examined object are arrays of the same length and
 * contain items that are equal to each other (according to the above rules) **in the same
 * indexes**.
 *
 *
 * For example:
 *
 * assertThat("foo", equalTo("foo"));
 * assertThat(new String[] {"foo", "bar"}, equalTo(new String[] {"foo", "bar"}));
 *
 */
public fun <T> equalTo(operand: T): Matcher<T> {
    return IsEqual.equalTo(operand)
}

/**
 * Creates a matcher that matches when the examined object is an instance of the specified `type`,
 * as determined by calling the [Class.isInstance] method on that type, passing the
 * the examined object.

 *
 * The created matcher forces a relationship between specified type and the examined object, and should be
 * used when it is necessary to make generics conform, for example in the JMock clause
 * `with(any(Thing.class))`
 *
 *
 * For example:
 * assertThat(new Canoe(), instanceOf(Canoe.class));
 */
public fun <T> any(type: Class<T>): Matcher<T> {
    return IsInstanceOf.any(type)
}

/**
 * Creates a matcher that matches when the examined object is an instance of the specified `type`,
 * as determined by calling the [Class.isInstance] method on that type, passing the
 * the examined object.

 *
 * The created matcher assumes no relationship between specified type and the examined object.
 *
 *
 * For example:
 * assertThat(new Canoe(), instanceOf(Paddlable.class));
 */
public fun <T> instanceOf(type: Class<*>): Matcher<T> {
    return IsInstanceOf.instanceOf<T>(type)
}

/**
 * Creates a matcher that wraps an existing matcher, but inverts the logic by which
 * it will match.
 *
 *
 * For example:
 * assertThat(cheese, is(not(equalTo(smelly))))

 * @param matcher
 * *     the matcher whose sense should be inverted
 */
public fun <T> not(matcher: Matcher<T>): Matcher<T> {
    return IsNot.not(matcher)
}

/**
 * A shortcut to the frequently used `not(equalTo(x))`.
 *
 *
 * For example:
 * assertThat(cheese, is(not(smelly)))
 * instead of:
 * assertThat(cheese, is(not(equalTo(smelly))))

 * @param value
 * *     the value that any examined object should **not** equal
 */
public fun <T> not(value: T): Matcher<T> {
    return IsNot.not(value)
}

/**
 * Creates a matcher that matches if examined object is `null`.
 *
 *
 * For example:
 * assertThat(cheese, is(nullValue())
 */
public fun nullValue(): Matcher<Any> {
    return IsNull.nullValue()
}

/**
 * Creates a matcher that matches if examined object is `null`. Accepts a
 * single dummy argument to facilitate type inference.
 *
 *
 * For example:
 * assertThat(cheese, is(nullValue(Cheese.class))

 * @param type
 * *     dummy parameter used to infer the generic type of the returned matcher
 */
public fun <T> nullValue(type: Class<T>): Matcher<T> {
    return IsNull.nullValue(type)
}

/**
 * A shortcut to the frequently used `not(nullValue())`.
 *
 *
 * For example:
 * assertThat(cheese, is(notNullValue()))
 * instead of:
 * assertThat(cheese, is(not(nullValue())))
 */
public fun notNullValue(): Matcher<Any> {
    return IsNull.notNullValue()
}

/**
 * A shortcut to the frequently used `not(nullValue(X.class)). Accepts a
 * single dummy argument to facilitate type inference.`.
 *
 *
 * For example:
 * assertThat(cheese, is(notNullValue(X.class)))
 * instead of:
 * assertThat(cheese, is(not(nullValue(X.class))))

 * @param type
 * *     dummy parameter used to infer the generic type of the returned matcher
 */
public fun <T> notNullValue(type: Class<T>): Matcher<T> {
    return IsNull.notNullValue(type)
}

/**
 * Creates a matcher that matches only when the examined object is the same instance as
 * the specified target object.

 * @param target
 * *     the target instance against which others should be assessed
 */
public fun <T> sameInstance(target: T): Matcher<T> {
    return IsSame.sameInstance(target)
}

/**
 * Creates a matcher that matches only when the examined object is the same instance as
 * the specified target object.

 * @param target
 * *     the target instance against which others should be assessed
 */
public fun <T> theInstance(target: T): Matcher<T> {
    return IsSame.theInstance(target)
}

/**
 * Creates a matcher that matches if the examined [String] contains the specified
 * [String] anywhere.
 *
 *
 * For example:
 * assertThat("myStringOfNote", containsString("ring"))

 * @param substring
 * *     the substring that the returned matcher will expect to find within any examined string
 */
public fun containsString(substring: String): Matcher<String> {
    return StringContains.containsString(substring)
}

/**
 * Creates a matcher that matches if the examined [String] starts with the specified
 * [String].
 *
 *
 * For example:
 * assertThat("myStringOfNote", startsWith("my"))

 * @param prefix
 * *      the substring that the returned matcher will expect at the start of any examined string
 */
public fun startsWith(prefix: String): Matcher<String> {
    return StringStartsWith.startsWith(prefix)
}

/**
 * Creates a matcher that matches if the examined [String] ends with the specified
 * [String].
 *
 *
 * For example:
 * assertThat("myStringOfNote", endsWith("Note"))

 * @param suffix
 * *      the substring that the returned matcher will expect at the end of any examined string
 */
public fun endsWith(suffix: String): Matcher<String> {
    return StringEndsWith.endsWith(suffix)
}
