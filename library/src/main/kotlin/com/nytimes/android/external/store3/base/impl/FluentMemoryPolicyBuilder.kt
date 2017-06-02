package com.nytimes.android.external.store3.base.impl

/**
 * Wraps methods for fluent MemoryPolicy instantiation.
 */
class FluentMemoryPolicyBuilder private constructor() {
    companion object {
        /**
         * Builds a MemoryPolicy.
         * @param config The desired configuration for the memory policy.
         * @return A MemoryPolicy with the desired parameters or default ones if none were provided.
         */
        fun build(config: (MemoryPolicyParameters.() -> Unit)? = null): MemoryPolicy =
            MemoryPolicyParameters().apply { config?.invoke(this) }.let {
                MemoryPolicy.builder()
                        .setExpireAfterWrite(it.expireAfterWrite)
                        .setExpireAfterAccess(it.expireAfterAccess)
                        .setExpireAfterTimeUnit(it.expireAfterTimeUnit)
                        .setMemorySize(it.maxSize)
                        .build()
            }
    }
}
