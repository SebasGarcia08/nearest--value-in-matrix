package model;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() throws IOException {
		String inputFile = "data/input.txt";
		String expectedOutputFile = "data/expectedOutput.txt";

		BufferedReader br1 = new BufferedReader(new FileReader(new File(inputFile)));
		BufferedReader br2 = new BufferedReader(new FileReader(new File(expectedOutputFile)));
		
		int numTestCases = Integer.parseInt(br1.readLine());
		boolean passedAllTests = true;
		System.out.println("O | EX");
		System.out.println("------");
		for(int t = 0; t < numTestCases; t++) {
			int[] nm = readArrayFromTextFile(br1);
			int value = Integer.parseInt(br1.readLine());
			int[][] matrix = new int[nm[0]][nm[1]];

			for(int i = 0; i <matrix.length; i++)
				matrix[i] = readArrayFromTextFile(br1);
			
			Main.printMatrix(matrix);
			int expectedOutput = Integer.parseInt(br2.readLine());
			
			int actualOutput = Main.greatestNearestTo(value, matrix);
			
			System.out.println(actualOutput + " | " + expectedOutput);				
			passedAllTests = expectedOutput == actualOutput;				
		}
		
		assertTrue("Does not passed all tests" ,passedAllTests);
		br1.close();
		br2.close();
	}

	public int[] readArrayFromTextFile(BufferedReader br) throws IOException {
		return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
}
