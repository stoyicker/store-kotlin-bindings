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

### Kotlin dependency table

| Store bindings for Kotlin | Kotlin stdlib version |
|:-------------------------:|:---------------------:|
|        3.0.0-alpha        |           1.1.2-4     |
