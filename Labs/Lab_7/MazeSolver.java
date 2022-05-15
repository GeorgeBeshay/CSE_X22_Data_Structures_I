package Labs.Lab_7;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.*;

public class MazeSolver implements IMazeSolver{
	
	@Override
	public int[][] solveDFS(java.io.File maze){
		
		Scanner sc = null;
		String tempS;
		String tempS1;
		String tempS2;
		char[][] array_2d = null;
		int n = 0, m = 0;
		try {
			sc = new Scanner(maze);
			if(sc.hasNextLine()) {
				tempS = sc.nextLine();
				tempS1 = tempS.substring(0,tempS.indexOf(' '));
				tempS2 = tempS.substring(tempS.indexOf(' ') + 1 , tempS.length());
				n = Integer.parseInt(tempS1);
				m = Integer.parseInt(tempS2);
				array_2d = new char[n][m];
			}
			while(sc.hasNext() && n > 0 && m > 0) {
				for(int i = 0 ; i < n ; i++) {
					tempS = sc.nextLine();
					for(int j = 0 ; j < m ; j++) {
						array_2d[i][j] = tempS.charAt(j);
					}
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
		int[][] result=new int[50][2];
		MyStack s= new MyStack(null,0);
		int counter=0;
		int i,j;
		cell t =new cell();
		
		boolean[][] visited=new boolean[n][m];
		
		for(int k=0;k<n;k++) {
			for(int l=0;l<m;l++) {
				visited[k][l]=false;
			}
		}
		
		for(int k=0;k<n;k++) {
			for(int l=0;l<m;l++) {
				if(array_2d[k][l]=='S') {
					s.push(k,l,'S',null);
					visited[k][l]=true;
					
					while(!s.isEmpty()) {
						t=s.pop();
						visited[t.getI()][t.getJ()]=true;
						
						if(t.getA()=='.' || t.getA()=='S') {
							result[counter][0]=t.getI();
							result[counter][1]=t.getJ();
							counter++;
						}
						
						if(t.getA()=='E') {
							result[counter][0]=t.getI();
							result[counter][1]=t.getJ();
							counter++;
							
							int[][] result_final=new int[counter][2];
							for(int q=0;q<counter;q++) {result_final[q][0]=result[q][0];result_final[q][1]=result[q][1];}
							
							
							System.out.print("DFS: ");
							for(int h=0;h<result_final.length-1;h++) {
								System.out.print("{" + result_final[h][0] + ", " + result_final[h][1] + "}, ");
							}
							System.out.print("{" + result_final[result_final.length-1][0] + ", " + result_final[result_final.length-1][1] + "}");
							
							return result_final;
						}
						
						if(t.getA()=='.' || t.getA()=='S') {
							i=t.getI();
							j=t.getJ();
							boolean child_found=false;
							
							if(j+1<m) {
								if(visited[i][j+1]==false){
									if(array_2d[i][j+1]=='.' || array_2d[i][j+1]=='E') {s.push(i, j+1, array_2d[i][j+1],t); child_found=true;}}
								}
								
							if(i-1>=0) {
								if(visited[i-1][j]==false){ 
									if(array_2d[i-1][j]=='.' || array_2d[i-1][j]=='E') {s.push(i-1, j, array_2d[i-1][j],t); child_found=true;}}
							}
							if(j-1>=0) {
								if(visited[i][j-1]==false) {
									if(array_2d[i][j-1]=='.'|| array_2d[i][j-1]=='E') {s.push(i,j-1,array_2d[i][j-1],t); child_found=true;}}
							}
							if(i+1<n) {
								if(visited[i+1][j]==false) {
									if(array_2d[i+1][j]=='.'|| array_2d[i+1][j]=='E') {s.push(i+1, j, array_2d[i+1][j],t); child_found=true;}}
							}
							if(child_found==false) {
								cell temp = s.peek().getParent();
								int i_temp= temp.getI();
								int j_temp= temp.getJ();
								for(int d=0;d<counter;d++) {
									if(result[d][0]==i_temp && result[d][1]==j_temp) {counter=d+1;}
								}
							}
						}
						
						
					}
				}
			}
		}
		System.out.println("Error");
		return null;
		}
		catch(Exception e) {
			System.out.print("Error");
			return null;

		}
	}
	
	
	@Override
	public int[][] solveBFS(java.io.File maze){
		
		Scanner sc = null;
		String tempS;
		String tempS1;
		String tempS2;
		char[][] Map = null;
		int Rows = 0, Columns = 0;
		try {
			sc = new Scanner(maze);
			if(sc.hasNextLine()) {
				tempS = sc.nextLine();
				tempS1 = tempS.substring(0,tempS.indexOf(' '));
				tempS2 = tempS.substring(tempS.indexOf(' ') + 1 , tempS.length());
				Rows = Integer.parseInt(tempS1);
				Columns = Integer.parseInt(tempS2);
				
				Map = new char[Rows][Columns];
			}
			while(sc.hasNext() && Rows > 0 && Columns > 0) {
				for(int i = 0 ; i < Rows ; i++) {
					tempS = sc.nextLine();
					for(int j = 0 ; j < Columns ; j++) {
						Map[i][j] = tempS.charAt(j);
					}
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		LLQueue myQueue = new LLQueue();
		Stack myStack = new Stack();
		Graph myGraph = new Graph(Map);
		GraphNode temp = myGraph.getStart();
		
		myQueue.enqueue(temp);
		temp.setVisitedFlag(true);
		
		while(myQueue.size() > 0) {
			
			temp = myQueue.dequeue();
			temp.setVisitedFlag(true);
			if(temp.getObj() == 'E') {
				break;
			}
			for(int i = 0 ; i < 4 ; i++) {
				if(temp.getAdjGNs()[i] != null){	
					if( !temp.getAdjGNs()[i].isVisitedFlag() && temp.getAdjGNs()[i].getObj() != '#') {
						myQueue.enqueue(temp.getAdjGNs()[i]);
						temp.getAdjGNs()[i].setP(temp);
					}
				}
			}
			
		}
		
		GraphNode tempPRO = temp;
		
		while(tempPRO != null) {
			myStack.push(new Point(tempPRO.getX(), tempPRO.getY()));
			tempPRO = tempPRO.getP();
		}
		
		// Printing
		Point tempNewPRO;
		Point[] ArrayOfPoints = new Point[myStack.size()]; 
		int ArrayOfPointsIndex = 0;
		System.out.print("BFS: ");
		while(myStack.size() > 1) {
			tempNewPRO = myStack.pop();
			ArrayOfPoints[ArrayOfPointsIndex] = tempNewPRO;
			ArrayOfPointsIndex++;
			System.out.print("{" +tempNewPRO.x + ", " + tempNewPRO.y +  "}, ");
		}
		tempNewPRO = myStack.pop();
		System.out.print("{" +tempNewPRO.x + ", " + tempNewPRO.y +  "}\n");
		ArrayOfPoints[ArrayOfPointsIndex] = tempNewPRO;
		return convertFromArrOfPointsToArrOfIntegers(ArrayOfPoints);
	}
	
	public int[][] convertFromArrOfPointsToArrOfIntegers(Point[] Input){
		int[][] Output = new int[Input.length][2];
		for(int i = 0 ; i < Input.length ; i++) {
			Output[i][0] = Input[i].x;
			Output[i][1] = Input[i].y;
		}
		return Output;
	}
	
}
