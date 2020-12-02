package com.tobi.mc.parser.optimisations

import com.tobi.mc.assertComputablesEqual
import com.tobi.mc.computable.Computable
import com.tobi.mc.computable.Program
import com.tobi.mc.computable.function.FunctionDeclaration
import com.tobi.mc.parser.MinusCParser
import com.tobi.mc.parser.ParserConfiguration
import com.tobi.mc.parser.optimisation.ProgramOptimiser
import com.tobi.mc.parser.optimisation.optimisations.NestedSequenceOptimisation
import com.tobi.mc.parser.optimisation.optimisations.SingleFunctionCallOptimisation
import com.tobi.mc.parser.optimisation.optimisations.UnreachableCodeOptimisation
import com.tobi.mc.parser.util.traverseAllNodes
import org.junit.Test

class SingleFunctionCallTest {

    private val parser = MinusCParser(ParserConfiguration(optimisations = emptyList()))

    @Test
    fun `Ensure single function calls are optimised correctly`() {
        val result = ProgramOptimiser(NestedSequenceOptimisation, SingleFunctionCallOptimisation, UnreachableCodeOptimisation).optimise(createInput())
        assertComputablesEqual(createOutput(), result)
    }

    private fun createInput(): Program = parser.parse("""
        int outer() {
            int inner() {
                return readInt() * 2;
            }
            return inner();
        }
    """.trimIndent()).removeSourceInformation()

    private fun createOutput(): Program = parser.parse("""
        int outer() {
            return readInt() * 2;
        }
    """.trimIndent()).removeSourceInformation()

    private fun <T : Computable> T.removeSourceInformation(): T {
        for (computable in this.traverseAllNodes()) {
            computable.sourceRange = null
            if(computable is FunctionDeclaration) {
                computable.parameters.forEach { it.sourceRange = null }
            }
        }
        return this
    }
}