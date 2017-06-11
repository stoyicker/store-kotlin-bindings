package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.Parser
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.util.KeyParser
import io.kotlintest.matchers.shouldBe
import io.kotlintest.mock.mock
import io.kotlintest.specs.StringSpec

/**
 * Spec for FluentRealStoreBuilder.
 */
class FluentRealStoreBuilderSpec : StringSpec() {
    init {
        "should open an equivalent object" {
            val fetcher = mock<Fetcher<Int, Int>>()
            val persister = mock<Persister<Int, Int>>()
            val keyParser = mock<KeyParser<Int, Int, Int>>()
            val parsers = mock<List<Parser<Int, Int>>>()
            val memoryPolicy = mock<MemoryPolicy>()
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
            kotlinResult shouldBe javaResult
        }
    }
}
