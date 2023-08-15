package org.jesperancinha.matrixanywhere

import android.widget.EditText

class MatrixCalculator {

    fun calculateDeterminant(tableCalc: Array<Array<EditText>>): Double {
        val matrix = Array(tableCalc.size) {
            DoubleArray(
                tableCalc[0].size
            )
        }
        for (i in tableCalc.indices) {
            for (j in tableCalc[0].indices) {
                if (tableCalc[i][j].text != null && !tableCalc[i][j].text.toString().isEmpty()) {
                    matrix[i][j] = tableCalc[i][j].text.toString().toDouble()
                } else {
                    matrix[i][j] = 0.0
                }
            }
        }
        return calculateDeterminant(matrix)
    }


    fun calculateDeterminant(matrix: Array<DoubleArray>): Double {
        var determinant = 0.0
        if (matrix.size == 1) {
            determinant += matrix[0][0]
        } else if (matrix.size == 2) {
            determinant += matrix[0][0] * matrix[1][1] - matrix[0][1]* matrix[1][0]
        } else {
            var multiplier = 1
            for (matrix1 in matrix) {
                for (j in matrix[0].indices) {
                    val subMatrix = getSubMatrix(matrix, j)
                    determinant += (multiplier * matrix1[j]
                            * calculateDeterminant(subMatrix))
                    multiplier *= -1
                }
            }
        }
        return determinant
    }

    private fun getSubMatrix(matrix: Array<DoubleArray>, c: Int): Array<DoubleArray> {
        val subTable = Array(matrix[0].size - 1) {
            DoubleArray(
                matrix.size - 1
            )
        }
        var currentC = 0
        for (i in 1 until matrix.size) {
            for (j in matrix[0].indices) {
                if (j != c) {
                    subTable[i - 1][currentC++] = matrix[i][j]
                }
            }
            currentC = 0
        }
        return subTable
    }
}