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