package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store3.base.Fetcher
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

/**
 * Spec for FluentStoreBuilder.
 */
class FluentStoreBuilderSpec {
    @Test
    fun equivalentBarcode() {
        @Suppress("UNCHECKED_CAST")
        val fetcher = Mockito.mock(Fetcher::class.java) as Fetcher<Any, BarCode>
        val javaResult = StoreBuilder.barcode<Any>()
                .fetcher(fetcher)
                .open()
        val kotlinResult = FluentStoreBuilder.barcode(fetcher)
        assertEquals(javaResult, kotlinResult)
    }
    @Test
    fun equivalentKey() {
        @Suppress("UNCHECKED_CAST")
        val fetcher = Mockito.mock(Fetcher::class.java) as Fetcher<Any, BarCode>
        val javaResult = StoreBuilder.key<BarCode, Any>()
                .fetcher(fetcher)
                .open()
        val kotlinResult = FluentStoreBuilder.key(fetcher)
        assertEquals(javaResult, kotlinResult)
    }
    @Test
    fun equivalentParsedWithKey() {
        @Suppress("UNCHECKED_CAST")
        val fetcher = Mockito.mock(Fetcher::class.java) as Fetcher<Any, BarCode>
        val javaResult = StoreBuilder.parsedWithKey<BarCode, Any, Any>()
                .fetcher(fetcher)
                .open()
        val kotlinResult = FluentStoreBuilder.parsedWithKey<BarCode, Any, Any>(fetcher)
        assertEquals(javaResult, kotlinResult)
    }
}
