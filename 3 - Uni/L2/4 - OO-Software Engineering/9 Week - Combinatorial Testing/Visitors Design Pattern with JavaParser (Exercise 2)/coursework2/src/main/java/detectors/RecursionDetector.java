package detectors;

import java.util.List;
import java.util.Objects;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/*
 * Name: Morad E.
 */

public class RecursionDetector extends VoidVisitorAdapter<List<Breakpoints>> {
	
	private String currentMethod;
	private String currentClass;
	private List<Breakpoints> breakpointCollection;
	
	
	/*
	 * Method for generating new breakpoints..
	 */
	public void newBreakpoint(Node node) {
		this.breakpointCollection
				.add(new Breakpoints(
								currentClass, currentMethod, node.getRange().get().begin.line, 
															  node.getRange().get().end.line));
	}
	
	
	@Override
	public void visit(MethodCallExpr methodCall, List<Breakpoints> breakpointCollection) {
		super.visit(methodCall, breakpointCollection);
		this.currentMethod = methodCall.getName().asString();
		Node parentNode = methodCall.getParentNode().get();
		
		/* Nodes which aren't instances of the parent node are removed */
		while (!(parentNode instanceof MethodDeclaration)) {
			parentNode = parentNode.getParentNode().get();
		}
		/* Conditional check to see if the current method being observed calls itself
		 * Raises a breakpoint if it does.
		 */  
        if (Objects.equals(((MethodDeclaration) parentNode).getNameAsString(), currentMethod)) { 
        	newBreakpoint(methodCall);
        }
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
