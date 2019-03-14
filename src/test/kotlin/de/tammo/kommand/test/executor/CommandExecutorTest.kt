package de.tammo.kommand.test.executor

import de.tammo.kommand.executor.CommandExecutor
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommandExecutorTest {

    @BeforeAll
    fun composeCommand() = CommandExecutor.INSTANCE.register(listOf(ExecutorTestCommand::class.java))

    @Test
    fun `test default route`() {
        assertTrue(CommandExecutor.INSTANCE.execute("executor"))
        assertTrue(CommandExecutor.INSTANCE.execute("Executor"))
        assertTrue(CommandExecutor.INSTANCE.execute("alias"))
        assertTrue(CommandExecutor.INSTANCE.execute("Alias"))
        assertFalse(CommandExecutor.INSTANCE.execute("Label"))
    }

    @Test
    fun `test sub commands`() {
        assertTrue(CommandExecutor.INSTANCE.execute("executor sub"))
        assertTrue(CommandExecutor.INSTANCE.execute("executor Sub"))
        assertTrue(CommandExecutor.INSTANCE.execute("Executor Sub"))
        assertFalse(CommandExecutor.INSTANCE.execute("Label Sub"))
    }

    @Test
    fun `test routing`() {
        assertTrue(CommandExecutor.INSTANCE.execute("executor route"))
        assertTrue(CommandExecutor.INSTANCE.execute("executor Route"))
        assertTrue(CommandExecutor.INSTANCE.execute("Executor Route"))

        assertFalse(CommandExecutor.INSTANCE.execute("Executor AnotherRoute"))
    }

    @Test
    fun `test parameter execution`() {
        assertTrue(CommandExecutor.INSTANCE.execute("executor parameters string 0"))
        assertFalse(CommandExecutor.INSTANCE.execute("executor parameters string"))
    }

    @Test
    fun `test optional parameters`() {
        assertTrue(CommandExecutor.INSTANCE.execute("executor optional string 0"))
        assertTrue(CommandExecutor.INSTANCE.execute("executor optional string"))
        assertFalse(CommandExecutor.INSTANCE.execute("executor optional"))
        assertFalse(CommandExecutor.INSTANCE.execute("executor optional string 0 anotherParam"))
    }

}