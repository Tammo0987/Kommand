package de.tammo.kommand.annotation

@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Route (val name: String = "", val description: String)