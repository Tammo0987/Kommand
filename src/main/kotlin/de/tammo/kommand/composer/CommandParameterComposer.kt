package de.tammo.kommand.composer

import de.tammo.kommand.CommandParameter
import de.tammo.kommand.annotation.Parameter
import de.tammo.kommand.annotation.Parameters
import java.lang.reflect.Method
import kotlin.reflect.full.valueParameters
import kotlin.reflect.jvm.kotlinFunction

class CommandParameterComposer private constructor() {

    companion object {
        val INSTANCE = CommandParameterComposer()
    }

    fun compose(method: Method): Collection<CommandParameter> = when {
        method.isAnnotationPresent(Parameters::class.java) -> {
            method.getDeclaredAnnotation(Parameters::class.java).value.mapIndexed { index, parameter ->
                this.composeParameter(index, parameter, method)
            }
        }

        method.isAnnotationPresent(Parameter::class.java) -> {
            listOf(this.composeParameter(0, method.getDeclaredAnnotation(Parameter::class.java), method))
        }

        else -> {
            emptyList()
        }
    }

    private fun composeParameter(index: Int, parameter: Parameter, method: Method): CommandParameter {
        if (parameter.optional && !method.kotlinFunction!!.valueParameters[index].type.isMarkedNullable) {
            throw IllegalStateException("Function parameter ${parameter.name} should be nullable!")
        }

        return CommandParameter(index, parameter.name, parameter.description, parameter.type.java, parameter.optional)
    }

}