package de.tammo.kommand.converter

import de.tammo.kommand.converter.impl.IntInputConverter
import de.tammo.kommand.converter.impl.StringInputConverter

class InputConverterRegistry {

    companion object {
        val INSTANCE = InputConverterRegistry().apply {
            listOf(
                    StringInputConverter(),
                    IntInputConverter()
            ).forEach { this.register(it) }
        }
    }

    private val inputConverters = ArrayList<InputConverter<*>>()

    fun register(inputConverter: InputConverter<*>) {
        this.inputConverters.add(inputConverter)
    }

    fun inputConverter(type: Class<*>): InputConverter<*>? {
        return this.inputConverters.find { it.compatibleTypes().contains(type) }
    }

}