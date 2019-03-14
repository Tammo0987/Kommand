package de.tammo.kommand.converter.impl

import de.tammo.kommand.converter.ConvertException
import de.tammo.kommand.converter.InputConverter

class IntInputConverter : InputConverter<Int> {

    @Throws(ConvertException::class)
    override fun convert(input: String): Int {
        try {
            return input.toInt()
        } catch (e: Exception) {
            throw ConvertException(e.message!!)
        }
    }

    override fun compatibleTypes(): Collection<Class<Int>> = listOf(Int::class.java)

}