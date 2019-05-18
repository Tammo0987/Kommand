package de.tammo.kommand.test

import de.tammo.kommand.annotation.Model
import de.tammo.kommand.annotation.Route
import de.tammo.kommand.result.CommandResult

@Model("sub", "Sub command for unit testing")
class SubTestCommand {

    @Route(description = "Default route")
    fun default() = CommandResult.SUCCESS

}