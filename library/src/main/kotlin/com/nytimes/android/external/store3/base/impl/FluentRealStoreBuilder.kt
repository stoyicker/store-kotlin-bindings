package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.Persister

/**
 * A fluent builder for Store instantiation.
 * @param fetcher The fetcher for the created instance.
 */
internal class FluentRealStoreBuilder<Raw, Parsed, Key> constructor(
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
