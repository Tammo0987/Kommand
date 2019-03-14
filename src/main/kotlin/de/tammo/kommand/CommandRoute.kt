package de.tammo.kommand

import de.tammo.kommand.converter.ConvertException
import de.tammo.kommand.converter.InputConverterRegistry
import java.lang.reflect.Method

data class CommandRoute(
    val name: String,
    val description: String,
    val parameters: Collection<CommandParameter>,
    private val method: Method
) {

    fun execute(parameters: List<String>): Boolean {
        val invokeInstance = this.method.declaringClass.declaredConstructors[0].newInstance()

        if (this.parameters.isEmpty()) {
            if (parameters.isNotEmpty()) return false

            return this.method.invoke(invokeInstance) as Boolean
        } else {
            if (parameters.isEmpty()) return false
        }

        if (this.hasOptionalParameters()) {
            when {
                parameters.size > this.parameters.size -> return false

                parameters.size == this.parameters.size -> {
                    val invokeParameters = this.parameters.mapIndexed { index, commandParameter ->
                        try {
                            InputConverterRegistry.INSTANCE.inputConverter(commandParameter.type)!!.convert(parameters[index])
                        } catch (e: ConvertException) {
                            return false
                        }
                    }

                    if (!this.method.isAccessible) {
                        this.method.isAccessible = true
                    }

                    return this.method.invoke(invokeInstance, *invokeParameters.toTypedArray()) as Boolean
                }

                else -> {
                    val invokeParameters = parameters.mapIndexed { index, commandParameter ->
                        try {
                            InputConverterRegistry.INSTANCE.inputConverter(this.parameters.elementAt(index).type)!!.convert(commandParameter)
                        } catch (e: ConvertException) {
                            return false
                        }
                    } as MutableList<Any?>

                    repeat(this.parameters.size - parameters.size) {
                        invokeParameters.add(null)
                    }

                    if (!this.method.isAccessible) {
                        this.method.isAccessible = true
                    }

                   return this.method.invoke(invokeInstance, *invokeParameters.toTypedArray()) as Boolean
               }
            }
        }

        if (parameters.size != this.parameters.size) return false

        val invokeParameters = this.parameters.mapIndexed { index, commandParameter ->
            try {
                InputConverterRegistry.INSTANCE.inputConverter(commandParameter.type)!!.convert(parameters[index])
            } catch (e: ConvertException) {
                return false
            }
        }

        if (!this.method.isAccessible) {
            this.method.isAccessible = true
        }

        return this.method.invoke(invokeInstance, *invokeParameters.toTypedArray()) as Boolean
    }

    private fun hasOptionalParameters(): Boolean {
        return this.parameters.any { it.optional }
    }

}