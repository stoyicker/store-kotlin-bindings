package org.jorge.store.kotlin.sample

import android.app.Activity
import android.os.Bundle
import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.Parser
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.FluentMemoryPolicyBuilder
import com.nytimes.android.external.store3.base.impl.FluentStoreBuilder
import com.nytimes.android.external.store3.base.impl.StalePolicy
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.util.KeyParser
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.concurrent.TimeUnit

internal class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO Call the method whose Store instantiation you want to test
    }

    private fun barcodeBasic(): Store<ByteArray, BarCode> {
        val myFetcher = Fetcher<ByteArray, BarCode> { Single.just(ByteArray(0)) }
        return FluentStoreBuilder.barcode(myFetcher)
    }

    private fun barcodeComplete(): Store<ByteArray, BarCode> {
        val myFetcher = Fetcher<ByteArray, BarCode> { Single.just(ByteArray(0)) }
        val myPersister = object : Persister<ByteArray, BarCode> {
            override fun read(key: BarCode) = Maybe.just(ByteArray(0))

            override fun write(key: BarCode, raw: ByteArray) = Single.just(true)
        }
        val myMemoryPolicy = FluentMemoryPolicyBuilder.build {
            expireAfterAccess = 28
            expireAfterWrite = 9
            expireAfterTimeUnit = TimeUnit.MILLISECONDS
            maxSize = 100
        }
        return FluentStoreBuilder.barcode(myFetcher) {
            persister = myPersister
            memoryPolicy = myMemoryPolicy
            stalePolicy = StalePolicy.REFRESH_ON_STALE
        }
      }

    private fun keyBasic(): Store<ByteArray, Int> {
        val myFetcher = Fetcher<ByteArray, Int> { Single.just(ByteArray(0)) }
        return FluentStoreBuilder.key(myFetcher)
    }

    private fun keyComplete(): Store<ByteArray, Int> {
        val myFetcher = Fetcher<ByteArray, Int> { Single.just(ByteArray(0)) }
        val myPersister = object : Persister<ByteArray, Int> {
            override fun read(key: Int) = Maybe.just(ByteArray(0))

            override fun write(key: Int, raw: ByteArray) = Single.just(true)
        }
        val myMemoryPolicy = FluentMemoryPolicyBuilder.build {
            expireAfterAccess = 28
            expireAfterWrite = 9
            expireAfterTimeUnit = TimeUnit.MILLISECONDS
            maxSize = 100
        }
        return FluentStoreBuilder.key(myFetcher) {
            persister = myPersister
            memoryPolicy = myMemoryPolicy
            stalePolicy = StalePolicy.REFRESH_ON_STALE
        }
      }

    private fun parsedWithKeyBasic(): Store<DummyModel, Int> {
        val myFetcher = Fetcher<ByteArray, Int> { Single.just(ByteArray(0)) }
        return FluentStoreBuilder.parsedWithKey(myFetcher)
    }

    private fun parsedWithKeyComplete(): Store<DummyModel, Int> {
        val myFetcher = Fetcher<ByteArray, Int> { Single.just(ByteArray(0)) }
        val myPersister = object : Persister<ByteArray, Int> {
            override fun read(key: Int) = Maybe.just(ByteArray(0))

            override fun write(key: Int, raw: ByteArray) = Single.just(true)
        }
        val myKeyParser = KeyParser<Int, ByteArray, DummyModel> { _, _ -> DummyModel() }
        val myParser = Parser<ByteArray, DummyModel> { DummyModel() }
        val myMemoryPolicy = FluentMemoryPolicyBuilder.build()
        return FluentStoreBuilder.parsedWithKey(myFetcher) {
            persister = myPersister
            parser = myKeyParser
            parsers = listOf(myParser, myParser)
            memoryPolicy = myMemoryPolicy
            stalePolicy = StalePolicy.REFRESH_ON_STALE
        }
    }

    data class DummyModel(private val id: Long = System.currentTimeMillis())
}
