package com.nytimes.android.external.store3.base.impl

import io.kotlintest.matchers.shouldBe
import io.kotlintest.mock.mock
import io.kotlintest.specs.StringSpec

/**
 * Spec for StoreParameters.
 */
open class StoreParametersSpec : StringSpec() {
    init {
        "defaults" {
            val sut = StoreParameters<Any, Any>(mock())
            "should have persister be null" {
                sut.persister shouldBe null
            }
            "should have memoryPolicy be null" {
                sut.memoryPolicy shouldBe null
            }
            "should have stalePolicy be ${StalePolicy.UNSPECIFIED}" {
                sut.stalePolicy shouldBe null
            }
        }
    }
}

/**
 * Spec for ParsableStoreParameters.
 */
class ParsableStoreParametersSpec : StoreParametersSpec() {
    init {
        "defaults" {
            val sut = ParsableStoreParameters<Any, Any, Any>(mock())
            "should have parser be null" {
                sut.parser shouldBe null
            }
            "should have parsers be null" {
                sut.parsers shouldBe null
            }
        }
        "property relationships" {
            "should make parser null when parsers is assigned" {
                val sut = ParsableStoreParameters<Any, Any, Any>(mock())
                sut.parser = mock()
                sut.parsers = mock()
                sut.parser shouldBe null
            }
            "should make parsers null when parser is assigned" {
                val sut = ParsableStoreParameters<Any, Any, Any>(mock())
                sut.parsers = mock()
                sut.parser = mock()
                sut.parsers shouldBe null
            }
        }
    }
}
