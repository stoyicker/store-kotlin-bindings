package org.jorge.store.kotlin.sample

import android.app.Activity
import android.os.Bundle
import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.FluentStoreBuilder
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Maybe
import io.reactivex.Single

internal class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO Call the method whose Store instantiation you want to test
    }

    private fun barcodeBasic(): Store<Any, BarCode> {
        val myFetcher = Fetcher<Any, BarCode> { Single.just(Any()) }
        return FluentStoreBuilder.barcode(myFetcher)
    }

    private fun barcodeComplete(): Store<Any, BarCode> {
       val myFetcher = Fetcher<Any, BarCode> { Single.just(Any()) }
        val myPersister = object : Persister<Any, BarCode> {
            override fun read(key: BarCode) = Maybe.just(Any())

            override fun write(key: BarCode, raw: Any) = Single.just(true)
        }
        return FluentStoreBuilder.barcode(myFetcher) {
            persister = myPersister
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
        return FluentStoreBuilder.key(myFetcher) {
            persister = myPersister
        }
      }

    private fun parsedWithKeyBasic(): Store<ByteArray, Int> {
        val myFetcher = Fetcher<ByteArray, Int> { Single.just(ByteArray(0)) }
        return FluentStoreBuilder.parsedWithKey(myFetcher)
    }

    private fun parsedWithKeyComplete(): Store<ByteArray, Int> {
        val myFetcher = Fetcher<ByteArray, Int> { Single.just(ByteArray(0)) }
        val myPersister = object : Persister<ByteArray, Int> {
            override fun read(key: Int) = Maybe.just(ByteArray(0))

            override fun write(key: Int, raw: ByteArray) = Single.just(true)
        }
        return FluentStoreBuilder.parsedWithKey(myFetcher) {
            persister = myPersister
        }
      }
}
