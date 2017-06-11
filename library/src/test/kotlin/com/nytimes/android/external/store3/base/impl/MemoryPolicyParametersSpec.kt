package com.nytimes.android.external.store3.base.impl

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import java.util.concurrent.TimeUnit

/**
 * Spec for MemoryPolicyParameters.
 */
class MemoryPolicyParametersSpec : StringSpec() {
    init {
        "defaults" {
            val sut = MemoryPolicyParameters()
            "should have expireAfterWrite be ${MemoryPolicy.DEFAULT_POLICY}" {
                sut.expireAfterWrite shouldBe MemoryPolicy.DEFAULT_POLICY
            }
            "should have expireAfterAccess be ${MemoryPolicy.DEFAULT_POLICY}" {
                sut.expireAfterAccess shouldBe MemoryPolicy.DEFAULT_POLICY
            }
            "should have expireAfterTimeUnit be ${TimeUnit.SECONDS}" {
                sut.expireAfterTimeUnit shouldBe TimeUnit.SECONDS
            }
            "should have memorySize be ${1}" {
                sut.memorySize shouldBe 1
            }
        }
        "domain restrictions" {
            val sut = MemoryPolicyParameters()
            "expireAfterWrite >= 0" {
                val validValue = 82L
                val invalidValue = -1L
                sut.expireAfterWrite = validValue
                sut.expireAfterWrite shouldBe validValue
                sut.expireAfterWrite = invalidValue
                sut.expireAfterWrite shouldBe validValue
            }
            "expireAfterAccess >= 0" {
                val validValue = 5L
                val invalidValue = -1L
                sut.expireAfterAccess = validValue
                sut.expireAfterAccess shouldBe validValue
                sut.expireAfterAccess = invalidValue
                sut.expireAfterAccess shouldBe validValue
            }
            "memorySize >= 1" {
                val validValue = 3L
                val invalidValue = -1L
                sut.memorySize = validValue
                sut.memorySize shouldBe validValue
                sut.memorySize = invalidValue
                sut.memorySize shouldBe validValue
            }
        }
    }
}
