package com.nytimes.android.external.store3.base.impl

import java.util.concurrent.TimeUnit

/**
 * A parameter box for MemoryPolicy instantiation.
 */
class MemoryPolicyParameters {
    var expireAfterWrite = MemoryPolicy.DEFAULT_POLICY
    var expireAfterAccess = MemoryPolicy.DEFAULT_POLICY
    var expireAfterTimeUnit = TimeUnit.SECONDS
    var maxSize = 1L
}
