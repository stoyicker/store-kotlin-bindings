package com.nytimes.android.external.store3.base.impl

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Spec for FluentMemoryPolicyBuilder.
 */
class FluentMemoryPolicyBuilderSpec {
    @Test
    fun shouldBuildAnEquivalentObject() {
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
        assertEquals(javaResult, kotlinResult)
    }
}

