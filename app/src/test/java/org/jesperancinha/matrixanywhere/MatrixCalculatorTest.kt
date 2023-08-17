package org.jesperancinha.matrixanywhere

import io.kotest.matchers.shouldBe
import org.jesperancinha.matrixanywhere.ui.theme.getDeterminant
import org.junit.Test

internal class MatrixCalculatorTest {
    @Test
    fun `should calculate the determinant correctly for a 2 by 2 matrix`() {
        val determinant: Double = getDeterminant(
            arrayOf(
                doubleArrayOf(1.0, 1.0),
                doubleArrayOf(2.0, 1.0)
            )
        )
        determinant shouldBe -1.0
    }
    @Test
    fun `should calculate the determinant correctly for a 3 by 3 matrix`() {
        val determinant: Double = getDeterminant(
            arrayOf(
                doubleArrayOf(4.0, 5.0, 8.0),
                doubleArrayOf(7.0, 3.0, 3.0),
                doubleArrayOf(2.0, 1.0, 2.0)
            )
        )
       determinant shouldBe -20.0
    }
}