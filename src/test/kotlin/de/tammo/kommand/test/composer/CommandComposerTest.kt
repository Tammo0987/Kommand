package de.tammo.kommand.test.composer

import de.tammo.kommand.test.TestCommand
import de.tammo.kommand.CommandModel
import de.tammo.kommand.composer.CommandModelComposer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommandComposerTest {

    private lateinit var testCommand: CommandModel

    @BeforeAll
    fun composeCommand() {
        this.testCommand = CommandModelComposer.compose(TestCommand::class)
    }

    @Nested
    inner class TestModelComposer {

        private val testCommand = this@CommandComposerTest.testCommand

        @Test
        fun `test label`() = assertEquals("test", this.testCommand.label)

        @Test
        fun `test aliases`() {
            assertEquals(1, this.testCommand.aliases.size)
            assertEquals("alias", this.testCommand.aliases.elementAt(0))
        }

        @Test
        fun `test sub commands`() {
            assertEquals(1, this.testCommand.subModels.size)
            assertEquals("sub", this.testCommand.subModels.elementAt(0).label)
        }

    }

    @Nested
    inner class TestRouteComposer {

        private val routes = this@CommandComposerTest.testCommand.routes

        @Test
        fun `test routes`() {
            assertEquals(2, this.routes.size)
        }

    }

    @Nested
    inner class TestParameterComposer {

        private val parameters = this@CommandComposerTest.testCommand.routes.find { it.name == "parameter" }!!.parameters

        @Test
        fun `test parameters`() {
            assertEquals(2, this.parameters.size)

            val parameter = this.parameters.find { it.name == "test" }!!

            assertEquals(String::class, parameter.type)
            assertFalse(parameter.optional)

            val optionalParameter = this.parameters.find { it.name == "optional" }!!

            assertEquals(String::class, optionalParameter.type)
            assertTrue(optionalParameter.optional)
        }

    }

}