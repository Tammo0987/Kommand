package de.tammo.kommand.test

import de.tammo.kommand.annotation.Model
import de.tammo.kommand.annotation.Parameter
import de.tammo.kommand.annotation.Parameters
import de.tammo.kommand.annotation.Route
import de.tammo.kommand.result.CommandResult

@Model("test", "Test command for unit testing", ["alias"], [SubTestCommand::class])
class TestCommand {

    @Route(description = "Default route of the test command")
    fun default() = CommandResult.SUCCESS

    @Route("parameter", "Route with parameters")
    @Parameters([
        Parameter("test", String::class, "Test parameter"),
        Parameter("optional", String::class, "Optional test parameter", true)
    ])
    fun parameter(test: String, optional: String?) = CommandResult.SUCCESS

}