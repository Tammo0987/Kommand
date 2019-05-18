package de.tammo.kommand.annotation

import de.tammo.kommand.annotation.Parameter

/**
 * Declares an array of [Parameters][Parameter].
 *
 * @property [value] Array of all [Parameter].
 *
 * @author Tammo0987
 * @since 1.0
 */
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Parameters(val value: Array<Parameter>)