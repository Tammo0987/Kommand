package de.tammo.kommand.annotation

import kotlin.reflect.KClass

@Retention
@Repeatable
@Target(AnnotationTarget.FUNCTION)
annotation class Parameter (
        val name: String,
        val type: KClass<*>,
        val description: String = "",
        val optional: Boolean = false
)