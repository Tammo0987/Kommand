package de.tammo.kommand.annotation

import kotlin.reflect.KClass

@Retention
@Target(AnnotationTarget.CLASS)
annotation class Model (val label: String, val aliases: Array<String> = [], val subModels: Array<KClass<*>> = [])