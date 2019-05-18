package de.tammo.kommand.composer

import de.tammo.kommand.CommandRoute
import de.tammo.kommand.annotation.Route
import de.tammo.kommand.result.CommandResult
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation

/**
 * Composing a [Collection] of [CommandRoutes][CommandRoute] from a [KClass].
 *
 * @author Tammo0987
 * @since 1.0
 */
object CommandRouteComposer {

    /**
     * Compose and scanning a [KClass] to create a [Collection] of [CommandRoute] instances.
     *
     * @param [clazz] [KClass] to compose.
     *
     * @return [Collection] of [CommandRoutes][CommandRoute].
     */
    fun compose(clazz: KClass<*>): Collection<CommandRoute> = collectRoutes(clazz).map { composeRoute(it) }

    /**
     * Compose a specific [function][KFunction] to a [CommandRoute].
     *
     * @param [function] [KFunction] to compose.
     *
     * @return [CommandRoute] instance.
     */
    private fun composeRoute(function: KFunction<*>): CommandRoute {
        val route = function.findAnnotation<Route>()!!
        val parameters = CommandParameterComposer.compose(function)

        return CommandRoute(route.name, route.description, parameters, function)
    }

    /**
     * Collect [Functions][KFunction] of [clazz] for composing it into a [CommandRoute].
     *
     * @param [clazz] [KClass] where the routes should be collected.
     *
     * @return [Collection] of [functions][KFunction].
     */
    private fun collectRoutes(clazz: KClass<*>): Collection<KFunction<*>> {
        return clazz.declaredMemberFunctions
                .filter { it.findAnnotation<Route>() != null }
                .filter { it.returnType != CommandResult::class }
    }

}