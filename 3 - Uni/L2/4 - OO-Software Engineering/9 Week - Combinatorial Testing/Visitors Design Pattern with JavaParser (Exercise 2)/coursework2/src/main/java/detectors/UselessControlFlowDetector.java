package detectors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;

import java.util.List;

/*
 * Name: Morad E.
 */

public class UselessControlFlowDetector extends VoidVisitorAdapter<List<Breakpoints>> {
	
	private List<Breakpoints> breakpointCollection;
	private String currentMethod;
	private String currentClass;
	
	/*
	 * Method for generating new break points.
	 */
	public void newBreakPoint(Node node) {
		this.breakpointCollection
				.add(new Breakpoints(
								currentClass, currentMethod, node.getRange().get().begin.line, 
															  node.getRange().get().end.line));
	}
	
	
	/*
	 * Raises a breakpoint if a for loop does not possess a body.
	 */
	@Override
	public void visit(ForStmt forStatement, List<Breakpoints> breakPointCollection) {
		if (forStatement.getBody().isEmptyStmt())
				newBreakPoint(forStatement);
		super.visit(forStatement, breakPointCollection);
	}
	
	
	/*
	 * Raises a breakpoint if a do statement doesn't possess a body.
	 */
	@Override
	public void visit(DoStmt doStatement, List<Breakpoints> breakPointCollection) {
		if (doStatement.getBody().isEmptyStmt())
			newBreakPoint(doStatement);
		super.visit(doStatement, breakpointCollection);
	}
	

	/*
	 * Raises a breakpoint if a while loop doesn't possess a body.
	 */
	@Override
	public void visit(WhileStmt whileStatement, List<Breakpoints> breakPointCollection) {
		super.visit(whileStatement, breakPointCollection);
		if (whileStatement.getBody().isEmptyStmt())
			newBreakPoint(whileStatement);
	}
	

	/*
	 * Raises a breakpoint if the last case of a switch case statement doesn't possess a value.
	 */
	@Override
	public void visit(SwitchStmt switchStatement, List<Breakpoints> breakPointCollection) {
		NodeList<SwitchEntry> entries = switchStatement.getEntries();
		if (entries.isEmpty())
			newBreakPoint(switchStatement);
		else if(entries.get(entries.size()-1).isEmpty())
			newBreakPoint(switchStatement);
		super.visit(switchStatement, breakPointCollection);
	}
	

	/*
	 * Gets and sets the name of the method.
	 */
	@Override
	public void visit(MethodDeclaration currentMethod, List<Breakpoints> breakPointCollection) {
		this.currentMethod = currentMethod.getName().asString();
		if (currentMethod.getBody().isEmpty()) {
			System.out.println("Method body is empty");
			return;
		}
		super.visit(currentMethod, breakPointCollection);
	}
	

	/*
	 * Raises a break point if the body of a block statement is empty.
	 */
	@Override
	public void visit(BlockStmt blockStatement, List<Breakpoints> breakPointCollection) {
		if (blockStatement.isEmpty()) {
			newBreakPoint(blockStatement);
			return;
		}
		super.visit(blockStatement, breakPointCollection);			
	}
	
	
	/*
	 * Typically used to create object instances of a class, an empty constructor isn't
	 *   necessarily useless.
	 */
	@Override
	public void visit(ConstructorDeclaration consDec, List<Breakpoints> breakpointCollection) {
		if (consDec.getBody().isEmpty())
			return;
		super.visit(consDec, breakpointCollection);

	}

	
	/*
	 * Retrieves the class name and stores it.
	 */
	@Override
	public void visit(ClassOrInterfaceDeclaration cid, List<Breakpoints> breakpointCollection) {
		this.currentClass = cid.getName().asString();
		this.breakpointCollection = breakpointCollection;
		super.visit(cid, breakpointCollection);
	}
	

	

	
	

}
