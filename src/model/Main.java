package model;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[][] matrix = { { 32, 0, 7 }, { 4, 21, 8 }, { 29, 3, 4 }, { 5, 22, -1 } };
//		int nrows = matrix.length;
//		int mcols = matrix[0].length;
//
//		int midRow = nrows / 2;
//		int midCol = mcols / 2;
//
//		int[][] leftTopMatrix = submatrixOf(matrix, 0, midRow, 0, midCol);
//		int[][] rightTopMatrix = submatrixOf(matrix, 0, midRow, midCol, mcols);
//		int[][] leftBottomMatrix = submatrixOf(matrix, midRow, nrows, 0, midCol);
//		int[][] rightBottomMatrix = submatrixOf(matrix, midRow, nrows, midCol, mcols);
//		printMatrix(leftTopMatrix);
//		System.out.println();
//		printMatrix(leftBottomMatrix);
//		System.out.println();
//		printMatrix(rightTopMatrix);
//		System.out.println();
//		printMatrix(rightBottomMatrix);
		System.out.println(nearestValueOf(13, matrix));
	}

	public static int nearestValueOf(int value, int[][] matrix) {
		if ( matrix[0].length == 0) { // empty matrix
			return Integer.MAX_VALUE;
		} else if(matrix.length == 1 && matrix[0].length == 1 ) { // matrix with one element
			return matrix[0][0];			
		}

		int nrows = matrix.length;
		int mcols = matrix[0].length;

		int midRow = nrows / 2;
		int midCol = mcols / 2;

		int[][] leftTopSubmatrix = submatrixOf(matrix, 0, midRow, 0, midCol);
		int[][] rightTopSubmatrix = submatrixOf(matrix, 0, midRow, midCol, mcols);
		int[][] leftBottomSubmatrix = submatrixOf(matrix, midRow, nrows, 0, midCol);
		int[][] rightBottomSubmatrix = submatrixOf(matrix, midRow, nrows, midCol, mcols);

		int ltr = nearestValueOf(value, leftTopSubmatrix);
		int rtr = nearestValueOf(value, rightTopSubmatrix);
		int lbr = nearestValueOf(value, leftBottomSubmatrix);
		int rbr = nearestValueOf(value, rightBottomSubmatrix);
		
		System.out.println("ltr: " + ltr);
		System.out.println("rtr: " + rtr);
		System.out.println("lbr: " + lbr);
		System.out.println("rbr: " + rbr);
		
		int leftTop = greatestNearestTo(value, leftTopSubmatrix);
		int rightTop = greatestNearestTo(value, rightTopSubmatrix);
		int leftBottom = greatestNearestTo(value, leftBottomSubmatrix);
		int rightBottom = greatestNearestTo(value, rightBottomSubmatrix);
		
		System.out.println("Left top: " + leftTop);
		System.out.println("Left bottom: " + leftBottom);
		System.out.println("Right top: " + rightTop);
		System.out.println("Right bottom: " + rightBottom);
		
		return 0;
	}

	public static int greatestNearestTo(int value, int[][] matrix) {
		if ( matrix[0].length == 0) // empty matrix
			return Integer.MAX_VALUE;
		
		int nearest = matrix[0][0];
		int minimumDiff = Integer.MAX_VALUE;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int diff = Math.abs(matrix[i][j] - value);
				if ( diff < minimumDiff || (diff == minimumDiff && matrix[i][j] > nearest) ) {
					nearest = matrix[i][j];
					minimumDiff = diff;					
				}
			}
		}

		return nearest;
	}

	public static int[][] submatrixOf(int[][] matrix, int fromRow, int toRow, int fromCol, int toCol) {
		int[][] submatrix = new int[toRow - fromRow][toCol - fromCol];

		for (int ir = fromRow, i = 0; ir < toRow; ir++, i++)
			for (int ic = fromCol, j = 0; ic < toCol; ic++, j++)
				submatrix[i][j] = matrix[ir][ic];
		return submatrix;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] r : matrix)
			System.out.println(Arrays.toString(r));
	}
}