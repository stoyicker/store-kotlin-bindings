package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.Parser
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.util.KeyParser

/**
 * Wraps methods for fluent Store instantiation.
 */
class FluentStoreBuilder<Raw, Parsed, Key> private constructor(
        private val fetcher: Fetcher<Raw, Key>) {
    var persister: Persister<Raw, Key>? = null
    var parser: KeyParser<Key, Raw, Parsed>? = null
        set(value) {
            field = value
            parsers = null
        }
    var parsers: List<Parser<Raw, Parsed>>? = null
        set(value) {
            field = value
            parser = null
        }
    var memoryPolicy: MemoryPolicy? = null

    /**
     * Applies any given configuration for the Store that will be created.
     * @param config Optional configuration for the store to instantiate.
     * @return The created Store with the given configuration, if any.
     */
    internal fun apply(config: (FluentStoreBuilder<Raw, Parsed, Key>.() -> Unit)?)
            : Store<Parsed, Key> {
        if (config != null) {
            this.config()
        }
        return FluentRealStoreBuilder(fetcher, persister, parser, parsers, memoryPolicy).open()
    }

    companion object {
        /**
         * Provides a fluent builder to instantiate a Store that uses BarCode objects as keys.
         * @param fetcher Fetcher for the Store.
         * @param config Optional configuration block.
         */
        fun <Parsed> barcode(
                fetcher: Fetcher<Parsed, BarCode>,
                config: (FluentStoreBuilder<Parsed, Parsed, BarCode>.() -> Unit)? = null)
                = parsedWithKey(fetcher, config)

        /**
         * Provides a fluent builder to instantiate a Store with a custom type for keys.
         * @param fetcher Fetcher for the Store.
         * @param config Optional configuration block.
         */
        fun <Parsed, Key> key(
                fetcher: Fetcher<Parsed, Key>,
                config: (FluentStoreBuilder<Parsed, Parsed, Key>.() -> Unit)? = null)
                = parsedWithKey(fetcher, config)

        /**
         * Provides a fluent builder to instantiate a Store with a custom type for keys and
         * conversion between raw and parsed types.
         * @param fetcher Fetcher for the Store.
         * @param config Optional configuration block.
         */
        fun <Raw, Parsed, Key> parsedWithKey(
                fetcher: Fetcher<Raw, Key>,
                config: (FluentStoreBuilder<Raw, Parsed, Key>.() -> Unit)? = null)
                = FluentStoreBuilder<Raw, Parsed, Key>(fetcher).apply(config)
    }
}
