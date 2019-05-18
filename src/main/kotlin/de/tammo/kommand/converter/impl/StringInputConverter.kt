package de.tammo.kommand.converter.impl

import de.tammo.kommand.converter.InputConverter

/**
 * [InputConverter] implementation for type [String].
 *
 * @author Tammo0987
 * @since 1.0
 */
object StringInputConverter : InputConverter<String> {

    override fun convert(input: String) = input

    override fun compatibleTypes() = listOf(String::class)

}