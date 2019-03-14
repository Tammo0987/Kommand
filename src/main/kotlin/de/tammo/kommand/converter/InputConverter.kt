package de.tammo.kommand.converter

interface InputConverter<T> {

    fun convert(input: String): T

    fun compatibleTypes(): Collection<Class<T>>

}