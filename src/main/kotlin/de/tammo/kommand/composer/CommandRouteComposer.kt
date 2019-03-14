package de.tammo.kommand.composer

import de.tammo.kommand.CommandRoute
import de.tammo.kommand.annotation.Route
import java.lang.reflect.Method

class CommandRouteComposer private constructor() {

    companion object {
        val INSTANCE = CommandRouteComposer()
    }

    fun compose(clazz: Class<*>): Collection<CommandRoute> {
        return this.collectRoutes(clazz).map { this.composeRoute(it) }
    }

    private fun composeRoute(method: Method): CommandRoute {
        val route = method.getDeclaredAnnotation(Route::class.java)
        val parameters = CommandParameterComposer.INSTANCE.compose(method)

        return CommandRoute(route.name, route.description, parameters, method)
    }

    private fun collectRoutes(clazz: Class<*>): Collection<Method> {
        return clazz.declaredMethods
                .filter { it.isAnnotationPresent(Route::class.java) }
                .filter { it.returnType == Boolean::class.java }
                .toList()
    }

}