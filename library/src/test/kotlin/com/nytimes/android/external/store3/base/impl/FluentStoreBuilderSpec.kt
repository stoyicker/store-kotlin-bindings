package com.nytimes.android.external.store3.base.impl

import com.nytimes.android.external.store3.base.Fetcher
import io.kotlintest.matchers.shouldBe
import io.kotlintest.mock.mock
import io.kotlintest.specs.StringSpec

/**
 * Spec for FluentStoreBuilder.
 */
class FluentStoreBuilderSpec : StringSpec() {
    init {
        "equivalent initializations" {
            val fetcher = mock<Fetcher<Any, BarCode>>()
            "barcode" {
                val javaResult = StoreBuilder.barcode<Any>()
                        .fetcher(fetcher)
                        .open()
                val kotlinResult = FluentStoreBuilder.barcode(fetcher)
                kotlinResult shouldBe javaResult
            }
            "key" {
                val javaResult = StoreBuilder.key<BarCode, Any>()
                        .fetcher(fetcher)
                        .open()
                val kotlinResult = FluentStoreBuilder.barcode(fetcher)
                kotlinResult shouldBe javaResult
            }
            "parsedWithKey" {
                val javaResult = StoreBuilder.parsedWithKey<BarCode, Any, Any>()
                        .fetcher(fetcher)
                        .open()
                val kotlinResult = FluentStoreBuilder.parsedWithKey<BarCode, Any, Any>(fetcher)
                kotlinResult shouldBe javaResult
            }
        }
    }
}
