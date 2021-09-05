package detectors;

/*
 * Name: Morad E.
 */

public class Breakpoints {

	private String methodName;
	private String className;
	private int startLine;
	private int endLine;
	

	public Breakpoints(String className, String methodName, int startLine, int endLine) {
		this.className = className;
		this.methodName = methodName;
		this.startLine = startLine;
		this.endLine = endLine;
	}
	
	
	@Override
	public String toString() {
		return "className=" + className + ",methodName=" + methodName
				+ ",startline=" + startLine + ",endLine=" + endLine;
	}
	
	
	
	
	
}
