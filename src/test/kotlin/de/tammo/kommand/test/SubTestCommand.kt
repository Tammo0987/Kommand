package de.tammo.kommand.test

import de.tammo.kommand.annotation.Model
import de.tammo.kommand.annotation.Route

@Model("sub")
class SubTestCommand {

    @Route(description = "Default route")
    fun default() = true

}