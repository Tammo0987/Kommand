package de.tammo.kommand.converter

import de.tammo.kommand.converter.impl.IntInputConverter
import de.tammo.kommand.converter.impl.StringInputConverter
import kotlin.reflect.KClass

/**
 * Registry for [InputConverters][InputConverter].
 *
 * @author Tammo0987
 * @since 1.0
 */
object InputConverterRegistry {

    /**
     * List of registered [InputConverter].
     */
    private val inputConverters = mutableListOf<InputConverter<*>>(StringInputConverter, IntInputConverter)

    /**
     * Register an [InputConverter].
     *
     * @param [inputConverter] List of [InputConverter], that will be registered.
     */
    @Suppress("unused")
    fun register(vararg inputConverter: InputConverter<*>) = inputConverters.addAll(inputConverter)

    /**
     * Get a specific [InputConverter] for a type [type].
     *
     * @param [type] Compatible type for the [InputConverter].
     *
     * @return Compatible [InputConverter] if found, else null.
     */
    fun inputConverter(type: KClass<*>) = inputConverters.find { it.compatibleTypes().contains(type) }

}