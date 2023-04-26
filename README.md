# [PrisonCore](https://github.com/PrisonPL)
This plugin is central to all submodules and is required for them to work.

To make your own submodules, you can add it as a dependency with Maven or Gradle.
**Maven**
```xml
<repositories>
  <repository>
    <id>CollidaCube-PrisonPL</id>
    <url>https://packagecloud.io/CollidaCube/PrisonPL/maven2</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.collidacube.prison</groupId>
  <artifactId>PrisonCore</artifactId>
  <version>1.0</version>
</dependency>
```

**Gradle**
```gradle
repositories {
    maven {
        url "https://packagecloud.io/CollidaCube/PrisonPL/maven2"
    }
}

compile 'com.collidacube.prison:PrisonCore:1.0'
```
