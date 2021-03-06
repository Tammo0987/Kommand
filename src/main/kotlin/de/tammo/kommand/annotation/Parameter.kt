package de.tammo.kommand.annotation

import kotlin.reflect.KClass

/**
 * Declares a [de.tammo.kommand.CommandParameter].
 *
 * @property [name] Name of the parameter.
 * @property [type] Typ of the parameter, to convert the input string into the type of the parameter for a better usage.
 * @property [description] Description of the parameter.
 * @property [optional] Declares, if the parameter is optional to provide to execute the command.
 *
 * @author Tammo0987
 * @since 1.0
 */
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Parameter(
        val name: String,
        val type: KClass<*>,
        val description: String,
        val optional: Boolean = false
)