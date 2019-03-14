package de.tammo.kommand.annotation

@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Parameters(val value: Array<Parameter>)