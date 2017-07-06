This repository is now deprecated. Use [this](https://github.com/NYTimes/Store/pull/229) instead.

# Store bindings for Kotlin

A small collection of helper functions for idiomatic use of [NYTimes/Store](https://github.com/NYTimes/Store) when developing in Kotlin.

## Setup

### Do I need this library?

No, you don't *need* it. All Store functionality can be fully accessed from both Java and Kotlin. This library just aims to provide a nicer way to interact with Store in case you are using Kotlin.

### Installation

```groovy
repositories {
  // ...
  maven { url "https://jitpack.io" }
}


dependencies {
  // ...
  implementation "com.github.stoyicker:store-kotlin-bindings:$storeVersion"
}
```

This library brings into your classpath (unexposed): 
* [Store](https://github.com/NYTimes/Store/), so you don't need to include it manually. The version of Store used matches that of this artifact.
* The [Kotlin standard library](https://github.com/JetBrains/kotlin/tree/master/libraries/stdlib). The version used can be found in [the table below](README.md#Kotlin-dependency-table).

#### Kotlin dependency table

| Store bindings for Kotlin | Kotlin stdlib version |
|:-------------------------:|:---------------------:|
|        3.0.0-alpha        |           1.1.2-4     |

## Usage

```java
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
        val myMemoryPolicy = MemoryPolicy.MemoryPolicyBuilder().build()
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
        val myMemoryPolicy = MemoryPolicy.MemoryPolicyBuilder().build()
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
        val myMemoryPolicy = MemoryPolicy.MemoryPolicyBuilder().build()
        return FluentStoreBuilder.parsedWithKey(myFetcher) {
            persister = myPersister
            parser = myKeyParser
            parsers = listOf(myParser, myParser)
            memoryPolicy = myMemoryPolicy
            stalePolicy = StalePolicy.REFRESH_ON_STALE
        }
    }
```
