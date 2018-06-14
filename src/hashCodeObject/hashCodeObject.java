package hashCodeObject;

import Controller.Controller;
import Model.Model;
import View.View;

public class hashCodeObject {
	private static final String PATH = "C://Users/gabriell.thurnher/Desktop/Stage/Jour 4/hashCode/";
	
	public static void main(String[] args) {
		
		Model tab = new Model(PATH +"logo.in");
		Model candidate = new Model(14,80);
		View view = new View();
		Controller controller = new Controller(tab, view);
		view.setController(controller);
		view.setModel(tab);	
		view.setModel(candidate);
		view.printTab(tab.getTab());
		
		
//		Controller.findSolutionLine(tab.getTab(),Model.getCommande());
//		Controller.findSolutionColumn(tab.getTab(),Model.getCommande());
//		Controller.findSolutionSquare(tab.getTab(), Model.getCommande());
		Controller.findFinalSolution(tab.getTab(), Model.getCommande());
		Controller.paintTab(candidate.getTab(), Model.getCommande());
		
		view.printTab(candidate.getTab());		
//		view.displayTab(candidate.getTab());
		
		view.nbCoups();
		view.compareTabs(tab, candidate);
		
	}
	
}
