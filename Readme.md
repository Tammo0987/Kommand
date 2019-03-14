# Kommand

Kommand is a lightweight, declarative command framework written in Kotlin. You can declare command models via annotations.
The commands will be executed through reflections.

## Example

Here is an example of a command model.
```kotlin
package de.tammo.kommand.test

import de.tammo.kommand.annotation.Model
import de.tammo.kommand.annotation.Parameter
import de.tammo.kommand.annotation.Parameters
import de.tammo.kommand.annotation.Route

@Model("test", ["alias"], [SubTestCommand::class])
class TestCommand {

    @Route(description = "Default route of the test command")
    fun default() = true

    @Route("parameter", "Route with parameters")
    @Parameters([
        Parameter("test", String::class),
        Parameter("optional", String::class, optional = true)
    ])
    fun parameter(test: String, optional: String?) = true

}
```

And this is how you register and execute them:
```kotlin
package de.tammo.kommand.test.executor

import de.tammo.kommand.executor.CommandExecutor

class CommandExecutorTest {

    fun composeCommand() = CommandExecutor.INSTANCE.register(listOf(TestCommand::class.java))

    fun execute() = CommandExecutor.INSTANCE.execute("test")

}
```

Look in the unit tests to see more examples.

## Build with Gradle

You have to add the `jitpack` repository:

```groovy
repository {
    maven {
        url 'https://jitpack.io' 
    }
}
```

Add the dependency:

```groovy
dependencies {
    implementation("com.github.Tammo0987:Kommand:Version")
}
```