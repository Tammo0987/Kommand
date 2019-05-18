package de.tammo.kommand.test.executor

import de.tammo.kommand.annotation.Model
import de.tammo.kommand.annotation.Parameter
import de.tammo.kommand.annotation.Parameters
import de.tammo.kommand.annotation.Route
import de.tammo.kommand.result.CommandResult
import de.tammo.kommand.test.SubTestCommand

@Model("executor", "Executor test command for unit testing", ["alias"], [SubTestCommand::class])
class ExecutorTestCommand {

    @Route(description = "Default route description")
    fun default() = CommandResult.SUCCESS

    @Route("route", "Route route description")
    fun route() = CommandResult.SUCCESS

    @Route("parameters", "Parameter route description")
    @Parameters([
        Parameter("string", String::class, "String test parameter"),
        Parameter("number", Int::class, "Int test parameter")
    ])
    fun routeWithParameters(string: String, number: Int) = CommandResult.SUCCESS

    @Route("optional", "Optional parameter route description")
    @Parameters([
        Parameter("string", String::class, "String test parameter"),
        Parameter("optional", Int::class, "Optional int test parameter", true)
    ])
    fun routeWithOptionalParameters(string: String, optional: Int?) = CommandResult.SUCCESS

}