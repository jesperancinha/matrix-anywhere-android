package nl.joaofilipesabinoesperancinha.matrixanywhere.ui.theme

import android.widget.EditText

fun calculateDeterminant(tableCalc: Array<Array<EditText?>>?): Double {
    val matrix = Array(tableCalc!!.size) {
        DoubleArray(
            tableCalc[0].size
        )
    }
    for (i in tableCalc.indices) {
        for (j in tableCalc[0].indices) {
            if (tableCalc[i][j]!!.text != null && tableCalc[i][j]!!.text.toString().isNotEmpty()
            ) {
                matrix[i][j] = tableCalc[i][j]!!.text.toString().toDouble()
            } else {
                matrix[i][j] = 0.0
            }
        }
    }
    return getDeterminant(matrix)
}

fun getDeterminant(matrix: Array<DoubleArray>): Double =
    when (matrix.size) {
        1 -> matrix[0][0]
        2 -> matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
        else -> {
            var determinant = 0.0
            var multiplier = 1
            for (row in matrix) {
                for (j in matrix[0].indices) {
                    val subMatrix = getSubMatrix(matrix, j)
                    determinant += (multiplier * row[j] * getDeterminant(subMatrix))
                    multiplier *= -1
                }
            }
            determinant
        }
    }

private fun getSubMatrix(matrix: Array<DoubleArray>, c: Int): Array<DoubleArray> {
    val subTable = Array(matrix[0].size - 1) { DoubleArray(matrix.size - 1) }
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
