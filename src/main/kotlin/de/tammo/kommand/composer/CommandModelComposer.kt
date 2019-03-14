package de.tammo.kommand.composer

import de.tammo.kommand.CommandModel
import de.tammo.kommand.annotation.Model

class CommandModelComposer private constructor() {

    companion object {
        val INSTANCE = CommandModelComposer()
    }

    fun compose(clazz: Class<*>): CommandModel {
        if (!clazz.isAnnotationPresent(Model::class.java)) {
            throw IllegalStateException("${clazz.name} does not declare an annotation of type Model")
        }

        val routes = CommandRouteComposer.INSTANCE.compose(clazz)
        val model = clazz.getDeclaredAnnotation(Model::class.java)

        val aliases = model.aliases.toList()
        val subModels = model.subModels.map { this.compose(it.java) }

        return CommandModel(model.label, aliases, routes, subModels)
    }

}