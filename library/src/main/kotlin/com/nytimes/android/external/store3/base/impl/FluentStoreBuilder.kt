package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store.base.Fetcher
import com.nytimes.android.external.store.base.impl.BarCode

/**
 * Provides a fluent builder to instantiate a Store that uses BarCode objects as keys.
 * @param fetcher Fetcher for the Store.
 * @param config Optional configuration block.
 */
fun <Parsed> barcode(
        fetcher: Fetcher<Parsed, BarCode>,
        config: (FluentRealStoreBuilder<Parsed, Parsed, BarCode>.() -> Unit)? = null)
        = parsedWithKey(fetcher, config)

/**
 * Provides a fluent builder to instantiate a Store with a custom type for keys.
 * @param fetcher Fetcher for the Store.
 * @param config Optional configuration block.
 */
fun <Parsed, Key> key(
        fetcher: Fetcher<Parsed, Key>,
        config: (FluentRealStoreBuilder<Parsed, Parsed, Key>.() -> Unit)? = null)
        = parsedWithKey(fetcher, config)

/**
 * Provides a fluent builder to instantiate a Store with a custom type for keys and
 * conversion between raw and parsed types.
 * @param fetcher Fetcher for the Store.
 * @param config Optional configuration block.
 */
fun <Raw, Parsed, Key> parsedWithKey(
        fetcher: Fetcher<Raw, Key>,
        config: (FluentRealStoreBuilder<Raw, Parsed, Key>.() -> Unit)? = null)
        = FluentRealStoreBuilder<Raw, Parsed, Key>(fetcher).apply(config)
