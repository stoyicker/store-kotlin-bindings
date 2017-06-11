package com.nytimes.android.external.store3.base.impl

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import java.util.concurrent.TimeUnit

/**
 * Spec for FluentMemoryPolicyBuilder.
 */
class FluentMemoryPolicyBuilderSpec : StringSpec() {
    init {
        "should build an equivalent object" {
            val expireAfterWriteValue = 10L
            val expireAfterAccessValue = 20L
            val expireAfterTimeUnitValue = TimeUnit.HOURS
            val maxSizeValue = 1000L
            val javaResult = MemoryPolicy.builder()
                    .setExpireAfterWrite(expireAfterWriteValue)
                    .setExpireAfterAccess(expireAfterAccessValue)
                    .setExpireAfterTimeUnit(expireAfterTimeUnitValue)
                    .setMemorySize(maxSizeValue)
                    .build()
            val kotlinResult = FluentMemoryPolicyBuilder.build {
                expireAfterWrite = expireAfterWriteValue
                expireAfterAccess = expireAfterAccessValue
                expireAfterTimeUnit = expireAfterTimeUnitValue
                memorySize = maxSizeValue
            }
            kotlinResult shouldBe javaResult
        }
    }
}
