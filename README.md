# java-problems

## About

A repository containing solutions to common programming problems. Some of these came from job interviews, others from
programming challenges or algorithm learning materials. I've included only those problems i was allowed to publish.

## Table of Contents

1. [Gradle](#gradle)
2. [Problems](#problems)
   - [Two sum](#two-sum)
   - [IP Regex](#ip-regex)
3. [Usage](#usage)
4. [Contact](#contact)

## Gradle

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
[versions]
guava = "32.1.2-jre"
juneau = "8.2.0"

[libraries]
guava = { group = "com.google.guava", name = "guava", version.ref = "guava" }
juneau-marshall = { group = "org.apache.juneau", name = "juneau-marshall", version.ref = "juneau" }
```

Sample `build.gradle`:

```groovy
dependencies {
  implementation libs.guava
  api libs.juneau.marshall
}
```

List available tasks: `$ ./gradlew tasks`

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

1. Core Plugins that are a set of plugins that are included in the Gradle distribution itself. There plugins provide
   essential functionality for building and managing projects.
2. Community Plugins (from the Plugin Portal) are plugins developed by the Gradle community, rather being part of the
   core Gradle distribution.
3. Local or Custom Plugins (defined in the build)

## Problems

### Two sum

There is an array of integers `numbers` and an integer `target`. Your task is to create a function that returns an array with two indices of the numbers that they add up to the value of `target`.

**Assumptions:**

- each input would be an array with at least two elements,
- each input have exact one solution,
- you may not use the same element twice,
- you can return the answer in any order.

**Example 1:**

    Input: numbers = [3, 2, 4], target = 6
    Output: [1, 2]
    Explanation: numbers[1] + numbers[2] = 2 + 4 = 6

**Example 2:**

    Input: numbers = [2, 7, 11, 15], target = 9
    Output: [0, 1]

**Example 3:**

    Input: numbers = [3, 3], target = 6
    Output: [0, 1]

### IP Regex

## Usage

## Contact
