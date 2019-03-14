package de.tammo.kommand.converter.impl

import de.tammo.kommand.converter.InputConverter

class StringInputConverter : InputConverter<String> {

    override fun convert(input: String): String = input

    override fun compatibleTypes(): Collection<Class<String>> = listOf(String::class.java)

}