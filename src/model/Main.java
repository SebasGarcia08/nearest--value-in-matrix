package model;

public class Main {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 1, 1, 1, 2 }, { 5, 3, 2, 12, 4 }, { 1, 1, 1, 1, 9 } };
		System.out.println(nearestValueOf(7, matrix));
	}

	public static int nearestValueOf(int value, int[][] matrix) {
		// Base cases
		if (matrix.length == 0 || matrix[0].length == 0) { // empty matrix
			return Integer.MAX_VALUE;
		} else if (matrix.length == 1 && matrix[0].length == 1) { // matrix with one element
			return matrix[0][0];
		}

		int nrows = matrix.length;
		int mcols = matrix[0].length;

		int midRow = nrows / 2;
		int midCol = mcols / 2;

		// Divide
		int[][] leftTopSubmatrix = submatrixOf(matrix, 0, midRow, 0, midCol);
		int[][] leftBottomSubmatrix = submatrixOf(matrix, midRow, nrows, 0, midCol);

		int[][] rightTopSubmatrix = submatrixOf(matrix, 0, midRow, midCol, mcols);
		int[][] rightBottomSubmatrix = submatrixOf(matrix, midRow, nrows, midCol, mcols);

		// Conquer
		int ltr = nearestValueOf(value, leftTopSubmatrix);
		int lbr = nearestValueOf(value, leftBottomSubmatrix);
		int rtr = nearestValueOf(value, rightTopSubmatrix);
		int rbr = nearestValueOf(value, rightBottomSubmatrix);

		// Combine
		return greatestNearestTo(value, ltr, lbr, rtr, rbr);
	}

	public static int greatestNearestTo(int value, int... numbers) {
		int nearest = Integer.MAX_VALUE;
		int minimumDiff = Integer.MAX_VALUE;

		for (int num : numbers) {
			int diff = Math.abs(num - value);
			if (diff < minimumDiff || (diff == minimumDiff && num > nearest)) {
				nearest = num;
				minimumDiff = diff;
			}
		}

		return nearest;
	}

	public static int[][] submatrixOf(int[][] matrix, int fromRow, int toRow, int fromCol, int toCol) {
		int[][] submatrix = new int[toRow - fromRow][toCol - fromCol];
		for (int ithRow = fromRow, i = 0; ithRow < toRow; ithRow++, i++)
			for (int jthCol = fromCol, j = 0; jthCol < toCol; jthCol++, j++)
				submatrix[i][j] = matrix[ithRow][jthCol];
		return submatrix;
	}
}