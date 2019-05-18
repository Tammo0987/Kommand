package de.tammo.kommand.annotation

/**
 * Declares a [de.tammo.kommand.CommandRoute].
 *
 * @property [name] Name of the route to identify with. As default, the name is empty for the default route.
 * @property [description] Description of the usage of the [Route].
 *
 * @author Tammo0987
 * @since 1.0
 */
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Route(
    val name: String = "",
    val description: String
)