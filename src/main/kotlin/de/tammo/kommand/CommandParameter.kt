package de.tammo.kommand

data class CommandParameter (
        val index: Int,
        val name: String,
        val description: String,
        val type: Class<*>,
        val optional: Boolean
)