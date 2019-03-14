package de.tammo.kommand.executor

import de.tammo.kommand.CommandModel
import de.tammo.kommand.composer.CommandModelComposer

class CommandExecutor private constructor() {

    companion object {
        val INSTANCE = CommandExecutor()
    }

    private val commands = ArrayList<CommandModel>()

    fun register(commandModels: List<Class<*>>) = commandModels.forEach {
        this.commands.add(CommandModelComposer.INSTANCE.compose(it))
    }

    fun execute(input: String): Boolean {
        if (input.isEmpty() || input.isBlank()) return false

        val split = input.split("\\s+".toRegex())
        val label = split[0]
        val commandModel = this.commandModel(label) ?: return false

        val parameters = split.drop(1)

        return commandModel.execute(parameters)
    }

    private fun commandModel(label: String) = this.commands.find { it.label.toLowerCase() == label.toLowerCase() ||
            it.aliases.map { alias -> alias.toLowerCase() }.contains(label.toLowerCase()) }

}