# java-problems

## About

A repository containing solutions to common programming problems. Some of these came from job interviews, others from
programming challenges or algorithm learning materials. I've included only those problems i was allowed to publish.

## Table of Contents

1. [Gradle](#gradle)
    1. [Dependencies](#dependencies)
    2. [Plugins](#plugins)
    3. [Cache](#cache)
    4. [Gradle Build Language](#gradle-build-language)
        - [The Project object](#the-project-object)
    5. [JVM Builds](#jvm-builds)
        - [Simple Java Build](#simple-java-build)
        - [Changing compiler options](#changing-compiler-options)
        - [Targeting a specific Java version](#targeting-a-specific-java-version)
        - [Enable Java preview features](#enable-java-preview-features)
        - [Increase memory for compiling Groovy source files](#increase-memory-for-compiling-groovy-source-files)

2. [Problems](#problems)
    1. [Problem 1 - Two sum](#problem-1---two-sum)
    2. [Problem 2 - IP Regex](#problem-2---ip-regex)
    3. [Problem 3 - Smallest positive integer](#problem-3---smallest-positive-integer)
    4. Problem 4 -
    5. Problem 5 -

3. [Usage](#usage)

4. [Contact](#contact)

## Gradle

### Dependencies

To add dependency to the project, specify it in the `build.gradle` file under `dependencies { }` section. For example:

```groovy
plugins {
    id 'java-library' // 1. Apply the Java Library plugin, which supports building Java libraries
}

dependencies {
    // Add your dependencies here
    implementation 'org.apache.commons:commons-lang3:3.12.0' // 2. Add a dependency on Apache Commons Lang used in production code
    api 'org.apache.commons:commons-collections4:4.4' // 3. Add a depencency on Apache Commons Collections used in library code
}
```

Dependencies in Gradle are grouped by configurations, which define when and how the dependency is used (e.g., at compile
time, runtime, or test time). Some common configurations include:

- `implementation`: Used for dependencies that are required to compile and run the code
- `api`: Used for dependencies that are part of the public API of the library, meaning they are exposed to consumers of the library
- `testImplementation`: Used for dependencies that are only required for testing purposes
- `runtimeOnly`: Used for dependencies that are only required at runtime, not at compile time
- `compileOnly`: Used for dependencies that are required at compile time but not at runtime (e.g., annotations)

To view the dependency tree: `./gradlew dependencies`

The version catalog `gradle/libs.versions.toml` typically contains four sections:

1. **[versions]** to declare the version numbers
2. **[libraries]** define libraries used in the build files
3. **[bundles]** define set of dependencies
4. **[plugins]** define plugins

```toml
# gradle/libs.versions.toml

[versions]
guava = "32.1.2-jre"
junit-jupiter = "5.11.3"

[libraries]
guava = { group = "com.google.guava", name = "guava", version.ref = "guava" }
junit-jupiter = { module = "org.junit.jupiter:junit-jupiter", version.ref = "junit-jupiter" }
```

Sample `build.gradle`:

```groovy
// build.gradle

dependencies {
  // Use JUnit Jupiter for testing
  testImplementation libs.junit.jupiter

  testRuntimeOnly "org.junit.platform:junit-platform-launcher"

  // This dependency is used by the application
  implementation libs.guava
}
```

> The `1ibs.junit.jupiter` syntax is used because the key contains hyphen which is a special character.

List available tasks: `$ ./gradlew tasks`

### Plugins

A plugin is a reusable piece of software that provides additional functionality to the Gradle build system. It can:

- add new tasks to the build (like `compileJava` or `test`)
- add new configurations (like `implementation` or `runtimeOnly`)
- contribute DSL elements (like `application {}` or `publishing {}`)

Some popular plugins are:

- **Java Library Plugin (java-library)** compiles Java source code, generates Javadoc, and packages classes into a JAR;
  adds tasks like `compileJava`, and `jar`
- **Google Services Plugin (com.google.gms.google-services)** configures Firebase and Google APIs in Android builds
- **Gradle Bintray Plugin (com.jfrog.bintray)** publishes artifacts to Binary (or other Maven-style repositories) using
  a `bintray {}` configuration block

Gradle supports three types of plugins:

1. script plugins - reusable. `gradle` or `gradle.kts` files that are applied using `apply from: `
2. pre-compiled plugins - packaged Kotlin or Groovy code applied with the `plugins {}` block
3. binary plugins - packaged and published plugins (Plugin Portal or Maven) that are applied with the `plugins {}` block

Gradle plugins came from different sources:

1. **Core Plugins** that are a set of plugins that are included in the Gradle distribution itself. There plugins provide
   essential functionality for building and managing projects.
2. **Community Plugins (from the Plugin Portal)** are plugins developed by the Gradle community, rather being part of the
   core Gradle distribution.
3. **Local or Custom Plugins** (defined in the build)

`application` plugin adds tasks like `run`, `build`, `compileJava`, `test`, and `jar`. Gradle provides several build-in
task types that make it easy to perform common operations.Popular built-in tasks include:

- **Copy** - is useful to copy files around,
- **Delete** - is useful to delete files and directories,
- **Exec** - is useful to execute arbitrary O/S commands,
- **Zip** - is useful to bundle files.

`Maven Publish Plugin` provides the ability to publish build artifacts to an Apache Maven repository (it can also
publish to Maven local which is a repository located on the local machine).

```groovy
// build.gradle

plugins {
  // Apply the application plugin to add support for building a CLI application in Java
  id 'application'
  // Apply the maven publish plugin
  id('maven-publish')
}

// configuring the maven publish plugin
publishing {
  publications {
    maven(MavenPublication) {
      from components.java // 1. Publish the Java component

      groupId = 'pl.myapp.java' // 2. Set the group ID
      artifactId = 'problems' // 3. Set the artifact ID
      version = '1.0.0' // 4. Set the version
    }
  }
}
```

The `publishToMavenLocal` task builds the POM file and the artifacts to be published. It then installs them into the
local Maven repository.

### Cache

Modify settings to enable the Local Build Cache

```groovy
// gradle.properties

org.gradle.console=verbose
org.gradle.caching=true
```

- **FROM-CACHE** - tasks have been fetched from the local build cache
- **UP-TO-DATE** - tasks that used incremental build and were not re-run

To remember:

- use `build` task to populate local cache with task inputs and outputs
- use `clean` to mimic switching branches - overriding previous outputs
- use `build` task again to use previous outputs from the local cache

### Gradle Build Language

There are some basic concepts to understand when writing Gradle scripts.

**First**, Gradle scripts are configuration scripts. When the script executes, it configures an object of a particular
type. This object is called the delegate object of the script.

| Type of script  | Object type |
| :-------------- | :---------- |
| Build script    | Project     |
| Init script     | Gradle      |
| Settings script | Settings    |

**Second**, each Gradle script implements the `Script` interface. This interface defines a number of properties and
methods that could be used.

#### The Project object

Build script is made of zero or more statements and script blocks. Statements can include method calls, property
assignments, and local variable definitions. The top level script blocks are listed below:

| Block               | Desctription                                                       |
| :------------------ | :----------------------------------------------------------------- |
| `allprojects {}`    | Configures this project and each of it's sub-projects in the build |
| `artifacts {}`      | Configures the artifacts produced by this project                  |
| `buildscript {}`    | Configures the build script classpath for this project             |
| `configurations {}` | Configures the dependency configurations for this project          |
| `dependencies {}`   | Configures the dependencies for this project                       |
| `repositories {}`   | Configures the repositories for this project                       |
| `sourceSets {}`     | Configures the source sets for this project                        |
| `subprojects {}`    | Configures the sub-projects of this build                          |
| `publishing {}`     | Configures the PublishingExtension added by the publishing scripts |

Standard `Project` properties that are commonly used:

| Name         | Type   | Description                                   |
| :----------- | :----- | :-------------------------------------------- |
| name         | String | the fully qualified name of the project       |
| path         | String | the name od the project directory             |
| description  | String | description for the project                   |
| dependencies |        | returns the dependency handler of the project |
| repositories |        |                                               |
| layout       |        |                                               |
| group        |        |                                               |
| version      |        |                                               |

### JVM Builds

#### Simple Java Build

The simplest build script for Java project is the one that applies the Java Library Plugin. By this plugin, the below
tasks become available:

- **compileJava** that compiles all the Java source files under `src/main/java`
- **compileTestJava** task for source files under `src/test/java`
- **test** task that runs the tests from `src/test/java`
- **jar** task that package the `main` compiled classes and resources from `src/main/resources` into a single JAR named
  `<project>-<version>.jar`
- **javadoc** task that generates Javadoc from the `main`` classes

```groovy
//build.gradle

plugins {
  id 'java-library'
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

version = '1.0.0'
```

The main `dependencies {}` glossaries are:

- **compileOnly** for dependencies that are necessary to compile production code but shouldn't be part of the runtime
  classpath,
- **implementation** used for compilation and runtime; supersedes **compile**
- **runtimeOnly** only used at runtime, not for compilation; supersedes **runtime**
- **testCompileOnly** same as **compileOnly** except it is for the test
- **testImplementation** test equivalent of **implementation**
- **testRuntimeOnly** test equivalent of **runtimeOnly**

#### Changing compiler options

Most of the compiler options are accessible through the corresponding task, such as `compileJava` and `compileTestJava`.

```groovy
compileJava {
  options.incremental = true // 1. Enable incremental compilation
  options.fork = true // 2. Fork the compiler process
  options.failOnError = false // 3. Do not fail the build on compilation errors
}
```

#### Targeting a specific Java version

Using Java toolchains is a preferred way to target a language version.

```groovy
// build.gradle

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}
```

Using Java release version.

```groovy
// build.gradle

compileJava {
  options.release = 17
}
```

Using Java compatibility options.

```groovy
// build.gradle

java {
  sourceCompatibility = '21'
  targetCompatibility = '21'
}
```

#### Enable Java preview features

To enable Java preview features for compilation, test execution and runtime, use the following snippet:

```groovy
// build.gradle

tasks.withType(JavaCompile).configureEach {
  options.compilerArgs += "--enable-preview"
}

tasks.withType(Test).configureEach {
  jvmArgs += "--enable-preview"
}

tasks.withType(JavaExec).configureEach {
  jvmArgs += "--enable-preview"
}
```

#### Increase memory for compiling Groovy source files

```groovy
// build.gradle

tasks.withType(GroovyCompile).configureEach {
  options.incremental = true // 1. Enable incremental compilation for Groovy source files
  options.incrementalAfterFailure = true // 2. Continue incremental compilation after a failure
  options.fork = true // 3. Fork the Groovy compiler process
  options.forkOptions.jvmArgs += ["-Xms1024m", "-Xmx2048m", "-XX:MaxMetaspaceSize=1024m", "-XX:+UseParallelGC"]
}
```

## Problems

### Problem 1 - two sum

There is an array of integers `numbers` and an integer `target`. Your task is to create a function that returns an array with two indices of the numbers that they add up to the value of `target`.

**Assumptions:**

- each input would be an array with at least two elements,
- each input have exact one solution,
- you may not use the same element twice,
- you can return the answer in any order.

**Example 1:**

~~~
    Input: numbers = [3, 2, 4], target = 6
    Output: [1, 2]
    Explanation: numbers[1] + numbers[2] = 2 + 4 = 6
~~~

**Example 2:**

~~~
    Input: numbers = [2, 7, 11, 15], target = 9
    Output: [0, 1]
~~~

**Example 3:**

~~~
    Input: numbers = [3, 3], target = 6
    Output: [0, 1]
~~~

---

### Problem 2 - IP Regex

---

### Problem 3 - Smallest positive integer

The problem is also known as _First Missing Positive_.

Create a function `public int smallestPositiveInteger(int[] A)` that for given array **A** of **N** integers, will
return the smallest positive integer that **does not occur in `A`**.

```java
class Solution {
    public int smallestPositiveInteger(int[] A) {
        // Put your code here

    }
}
```

**Example 1:**

~~~
    For given array arr = [1, 3, 6, 4, 1, 2], the function should return 5.
~~~

**Example 2:**

~~~
    For arr = [1, 2, 3], the function should return 4.
~~~

**Example 3:**

~~~
    For arr = [−1, −3], the function should return 1.
~~~

**Constraints:**

- $N = A.length$
- $1 \leq A.length \leq 100000$
- $-2^{31} \leq A[i] \leq 2^{31} - 1$

**Solutions:**

This is a naive solution with poor performance for the "Smallest Positive Integer" problem. At this stage, i hadn't yet
recognized the relationship between the array size and the integer value being searched for.

```java
public int smallestPositiveInteger(int[] A);
```

One of the most strategic insights in this problem is realizing that when the positive integer is not in the given
array, the missing one must be in a range [1, n], where n is the length of the array. Why is that? Let's consider two
cases:

- There is no missing integer in the array. For n=5 we have A = [1, 2, 3, 4, 5]. The solution in this case is 6
- There is a missing integer in the array. For n=5 we have B = [1, 2, 4, 6, 8]. The solution is 3.
  
The algorithm works by placing each value into its corresponding index position - for example number 1 should go to
index 0, 2 to index 1, and so on. The first index that miss its value is the result.

```java
public int smallestPositiveIntegerCycleSort(int[] A);
```

---

## Usage

Tests can be run in a scope of a single sub-project. The command is:

```bash
$ ./gradlew :sub-project:test
```

For example, to run tests of the `two-sum` sub-project, the command is:

```bash
$ ./gradlew :two-sum:test
```

## Contact
