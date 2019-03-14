package de.tammo.kommand

data class CommandModel (
    val label: String,
    val aliases: Collection<String> = emptyList(),
    val routes: Collection<CommandRoute> = emptyList(),
    val subModels: Collection<CommandModel> = emptyList()
) {

    fun execute(parameters: List<String>): Boolean {
        if (parameters.isEmpty()) {
            val defaultRoute = this.route("")?: return false
            return defaultRoute.execute(emptyList())
        }

        if (this.subModels.isNotEmpty()) {
            val label = parameters[0].toLowerCase()
            val subCommandModel = this.subModels.find { it.label.toLowerCase() == label }

            if (subCommandModel != null) {
                return subCommandModel.execute(parameters.drop(1))
            }
        }

        val route = this.route(parameters[0].toLowerCase())?: return false
        return route.execute(parameters.drop(1))
    }

    private fun route(name: String): CommandRoute? = this.routes.find { it.name == name }

}