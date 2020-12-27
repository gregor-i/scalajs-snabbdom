# scalajs-snabbdom

### Introduction and Motivation

`scalajs-snabbdom` is a simple and light weight wrapper around [snabbdom](https://github.com/snabbdom/snabbdom) for scala.js.
The API of `scalajs-snabbdom` is as close as possible to the interface of `snabbdom`.
So for information about the usage, please refer to the documentation of `snabbdom` itself.
Also the source of `scalajs-snabbdom` is quite small, so don't be afraid to look into the source.  

There is already another library [raquo/Snabbdom.scala](https://github.com/raquo/Snabbdom.scala) as a snabbdom wrapper, 
but the project is archived and seems not to be maintained anymore. Also `raquo/Snabbdom.scala` focuses more on type safty and is more complex to use.

### Setup

To use the library extend your sbt build with:
```sbt
resolvers += Resolver.bintrayRepo("gregor-i", "maven")
libraryDependencies += "com.github.gregor-i" %% "scalajs-snabbdom" % {current-version}
```


### Changelog

### 1.2.2:
- moved components into this project

### 1.2.1:
- moved toasts into this project 

#### 1.2:
- dependency upgrades
- reworked api
    - Hooks are now typed
    - Node.event allows setting of a type parameter
    - Removed Snabbdom.hook and Snabbdom.event
    - Added Syntax object for easy conversion from Selectors to Nodes

#### 1.1:
- upgraded api for snabbdom `2.8`

#### 1.0.1:
- release for scala.js `1.0`

#### 1.0.0:
- initial release
