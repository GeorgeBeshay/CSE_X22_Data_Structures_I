package Labs.Lab_7;

import java.io.*;

public class DriverCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File myFile = new File("E:\\College\\CSED Level 1 2021 - 2022\\Second Term\\Data Structures 1\\Java\\src\\Labs\\Lab_7\\TestCaseJ.txt");
		
		// Note: if the .txt file is in the same directory you are working in, no need to mention the exact file path.
		MazeSolver myMazeSolver = new MazeSolver();
		myMazeSolver.solveBFS(myFile);
		myMazeSolver.solveDFS(myFile);
		
	}

}
