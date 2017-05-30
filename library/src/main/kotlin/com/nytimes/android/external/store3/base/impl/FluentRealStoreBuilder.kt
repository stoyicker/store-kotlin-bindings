package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store.base.Fetcher
import com.nytimes.android.external.store.base.Persister
import com.nytimes.android.external.store.base.impl.Store
import com.nytimes.android.external.store.base.impl.StoreBuilder

/**
 * A fluent builder for Store instantiation.
 * @param fetcher The fetcher for the created instance.
 */
class FluentRealStoreBuilder<Raw, Parsed, Key> internal constructor(
        private val fetcher: Fetcher<Raw, Key>) {
    var persister: Persister<Raw, Key>? = null

    /**
     * Applies any given configuration for the Store that will be created.
     * @param config Optional configuration for the store to instantiate.
     * @return The created Store with the given configuration, if any.
     */
    internal fun apply(config: (FluentRealStoreBuilder<Raw, Parsed, Key>.() -> Unit)?)
            : Store<Parsed, Key> {
        if (config != null) {
            this.config()
        }
        return InternalBuilder<Raw, Parsed, Key>(fetcher, persister).open()
    }

    /**
     * A builder to be able to send an immutable snapshot of configuration for the Store.
     * @param fetcher The fetcher for the created instance.
     * @param persister An optional persister for the Store.
     */
    private class InternalBuilder<Raw, Parsed, Key>(
            private val fetcher: Fetcher<Raw, Key>,
            private val persister: Persister<Raw, Key>?) {
        /**
         * Creates the Store instance.
         * @return The created Store with the parameters passed into the constructor.
         */
        fun open(): Store<Parsed, Key> {
            var builder = StoreBuilder.parsedWithKey<Key, Raw, Parsed>().fetcher(fetcher)
            if (persister != null) {
                builder = builder.persister(persister)
            }
            return builder.open()
        }
    }
}
