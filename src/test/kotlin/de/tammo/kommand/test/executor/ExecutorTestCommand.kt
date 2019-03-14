package de.tammo.kommand.test.executor

import de.tammo.kommand.annotation.Model
import de.tammo.kommand.annotation.Parameter
import de.tammo.kommand.annotation.Parameters
import de.tammo.kommand.annotation.Route
import de.tammo.kommand.test.SubTestCommand

@Model("executor", ["alias"], [SubTestCommand::class])
class ExecutorTestCommand {

    @Route(description = "Default route description")
    fun default() = true

    @Route("route", "Route route description")
    fun route() = true

    @Route("parameters", "Parameter route description")
    @Parameters([
        Parameter("string", String::class),
        Parameter("number", Int::class)
    ])
    fun routeWirhParameters(string: String, number: Int) = true

    @Route("optional", "Optional parameter route description")
    @Parameters([
        Parameter("string", String::class),
        Parameter("optional", Int::class, optional = true)
    ])
    fun routeWithOptionalParameters(string: String, optional: Int?) = true

}