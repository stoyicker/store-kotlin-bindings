package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.Parser
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.util.KeyParser

/**
 * A fluent builder for Store instantiation.
 * @param fetcher The fetcher for the created instance.
 */
internal class FluentRealStoreBuilder<Raw, Parsed, Key> constructor(
        private val fetcher: Fetcher<Raw, Key>,
        private val persister: Persister<Raw, Key>?,
        private val keyParser: KeyParser<Key, Raw, Parsed>?,
        private val parsers: List<Parser<Raw, Parsed>>?) {
    /**
     * Creates the Store instance.
     * @return The created Store with the parameters passed into the constructor.
     */
    fun open(): Store<Parsed, Key> {
        var builder = StoreBuilder.parsedWithKey<Key, Raw, Parsed>().fetcher(fetcher)
        if (persister != null) {
            builder = builder.persister(persister)
        }
        if (keyParser != null) {
            builder = builder.parser(keyParser)
        } else {
            if (parsers != null) {
                builder = builder.parsers(parsers)
            }
        }
        return builder.open()
    }
}
