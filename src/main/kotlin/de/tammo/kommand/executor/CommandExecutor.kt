package de.tammo.kommand.executor

import de.tammo.kommand.CommandModel
import de.tammo.kommand.composer.CommandModelComposer
import de.tammo.kommand.result.CommandResult
import kotlin.reflect.KClass

/**
 * Executes commands by getting an input [String].
 *
 * @author Tammo098
 * @since 1.0
 */
@Suppress("unused")
object CommandExecutor {

    /**
     * List of all registered and composed [CommandModels][CommandModel].
     */
    val commands = mutableListOf<CommandModel>()

    /**
     * Register a list of [Classes][KClass], which will be composed to [CommandModels][CommandModel] and registered.
     *
     * @param [commandModels] List of [Classes][KClass], which will be registered.
     */
    fun register(vararg commandModels: KClass<*>) = commandModels
        .map { CommandModelComposer.compose(it) }
        .forEach { commands.add(it) }

    /**
     * Executes a command.
     *
     * @param [input] Input to parse to find and execute a command.
     *
     * @return [CommandResult] as result of the execution.
     */
    fun execute(input: String): CommandResult {
        if (input.isEmpty() || input.isBlank()) return CommandResult.UNUSABLE_INPUT

        val split = input.split("\\s+".toRegex()).filter { it.isNotEmpty() && !it.isBlank() }
        val label = split[0]
        val commandModel = commandModel(label) ?: return CommandResult.COMMAND_NOT_FOUND

        val parameters = split.drop(1)

        return commandModel.execute(parameters)
    }

    /**
     * Find a [CommandModel] by [label].
     *
     * @param [label] Label to identify the [CommandModel].
     *
     * @return [CommandModel] if found, else null.
     */
    fun commandModel(label: String) = commands.find {
        it.label.toLowerCase() == label.toLowerCase() ||
                it.aliases.find { alias -> alias.toLowerCase() == label.toLowerCase() }
    }

}
