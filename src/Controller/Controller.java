package Controller;

import java.util.List;

import Model.Line;
import Model.Model;
import Model.Paint;
import Model.Square;
import View.View;

public class Controller {
	private Model model;
	private View view;
	
	
	
	public Controller(Model model,View view) {
		this.model = model;
		this.view = view;
	}

	public static boolean compare(char[][] tab, char[][]tab2) {
    	for (int i=0; i<tab.length;i++) {
    		for (int j=0; j<tab[0].length;j++) {
    			if (tab[i][j]!=tab2[i][j]) {
    				System.out.println("Erreur à:" + i + "|"+ j);
    				return false;
    			}
    		}
    	}
		return true;
    }
    
	public static void findSolutionLine(char[][] tab, List<Paint> cmds) {
		for (int row = 0; row<tab.length;row++) {
			findSolutionOneLine(tab[row], row, cmds);
		}
	}
	
	public static void findSolutionOneLine(char[] line, int numLine, List<Paint> cmds) {
		int i=0;
		boolean block = false;
		int startBlock = 0;
		int lenBlock =0;
		
		while(i<line.length) {
			if(line[i]==Paint.PAINT) {
				if(block == false) {
					block=true;
					startBlock = i;
					lenBlock=0;
				}
				else {
					lenBlock++;
				}
			}else {
				if(block == true) {
					cmds.add(new Line(numLine, startBlock, numLine, startBlock + lenBlock, lenBlock));
					block = false;
				}
			}
			
			i++;			
		}
		
		// Si un bloc touche la fin de la line
		if(block == true) {
			cmds.add(new Line(numLine, startBlock, numLine, startBlock + lenBlock, lenBlock));
		}
	}
	
	public static void findSolutionColumn(char[][] tab, List<Paint> cmds) {
		for (int column = 0; column<tab[0].length;column++) {
			findSolutionOneColumn(tab, column, cmds);
		}
	}

	private static void findSolutionOneColumn(char[][] tab, int numColumn, List<Paint> cmds) {
		int i = 0;
		boolean block = false;
		int startBlock = 0;
		int lenBlock=0;
		
		while (i<tab.length) {
			if (tab[i][numColumn] == Paint.PAINT) {
				if (block == false) {
					block = true;
					startBlock = i;
					lenBlock = 0;
				}
				else {
					lenBlock++;
				}
			} 
			else {
				if(block == true) {
					cmds.add(new Line(startBlock, numColumn, startBlock + lenBlock,numColumn, lenBlock));
					block = false;
				}
			}
			
			i++;
		}
		
		// Si un bloc touche la fin de la colonne
		if(block == true) { 
			cmds.add(new Line(startBlock, numColumn, startBlock + lenBlock,numColumn, lenBlock));
		}
	}
	
	public static void findSolutionSquare(char[][] tab, List<Paint> cmds) {
		for (int row = 0; row<tab.length - 2;row++) {
			for (int column = 0; column < tab[0].length - 2;column++) {
				findSolutionOneSquare(tab, row, column, cmds);
			}
		}
	}

	private static void findSolutionOneSquare(char[][] tab, int row, int column, List<Paint> cmds) {
		int nb = 0;
		for (int sizeSquareL = row; sizeSquareL < row + 3; sizeSquareL++ ) {
			for (int sizeSquareC = column; sizeSquareC < column + 3; sizeSquareC++) {
				if (tab[sizeSquareL][sizeSquareC] == Paint.PAINT) {
					nb++;
					System.out.println("Coordonnées: " + row + " " + column);
				}
				else {
					continue;
				}
			}
		}
		if (nb == 9) {
			cmds.add(new Square(row + 1, column + 1, 1));
			
			for (int sizeSquareL = row; sizeSquareL < row + 3; sizeSquareL++ ) {
				for (int sizeSquareC = column; sizeSquareC < column + 3; sizeSquareC++) {
					tab[sizeSquareL][sizeSquareC] = Paint.BLANK;
				}
			}
		}
	}
	
	public static void findFinalSolution(char[][] tab, List<Paint> cmds) {
		findSolutionSquare(tab,cmds);
		findSolutionLine(tab, cmds);
	}

	public static void paintTab(char[][] tab, List<Paint> cmds) {
		for(Paint p: cmds) {
				p.paint(tab);
				Model.nbCoups++;
		}
	}
	
}
