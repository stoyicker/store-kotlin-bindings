package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.Parser
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.util.KeyParser
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Spec for FluentRealStoreBuilder.
 */
@Suppress("UNCHECKED_CAST")
class FluentRealStoreBuilderSpec {
    @Test
    fun shouldOpenAnEquivalentObject() {
        val fetcher = mock(Fetcher::class.java) as Fetcher<Int, Int>
        val persister = mock(Persister::class.java) as Persister<Int, Int>
        val keyParser = mock(KeyParser::class.java) as KeyParser<Int, Int, Int>
        val parsers = mock(List::class.java) as List<Parser<Int, Int>>
        val memoryPolicy = mock(MemoryPolicy::class.java)
        val stalePolicy = StalePolicy.NETWORK_BEFORE_STALE
        val javaResult = RealStoreBuilder.builder<Int, Int, Int>()
                .fetcher(fetcher)
                .persister(persister)
                .parser(keyParser)
                .parsers(parsers)
                .memoryPolicy(memoryPolicy)
                .networkBeforeStale()
                .open()
        val kotlinResult = FluentRealStoreBuilder(fetcher, persister, keyParser, parsers,
                memoryPolicy, stalePolicy
        ).open()
        assertEquals(javaResult, kotlinResult)
    }
}
