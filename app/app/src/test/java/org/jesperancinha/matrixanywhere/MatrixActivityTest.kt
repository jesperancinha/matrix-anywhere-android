package org.jesperancinha.matrixanywhere

import org.jesperancinha.matrixanywhere.ui.theme.getDeterminant
import org.junit.Test

internal class MatrixActivityTest {
    @Test
    fun `should calculate the determinant correctly`() {
        val determinant: Double = getDeterminant(
            arrayOf(
                doubleArrayOf(1.0, 1.0),
                doubleArrayOf(2.0, 1.0)
            )
        )
        assert(determinant == -1.0)
    }
}