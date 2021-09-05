package detectors;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

/*
 * Name: Morad E.
 */

public class Driver {

	public static void main(String[] args) {
		FileInputStream inputStream = null;
		String FILEPATH = "./Calculator.java";	
		
		try {
			inputStream = new FileInputStream(FILEPATH);
			CompilationUnit compUnit = JavaParser.parse(inputStream);
			
			/*
			 * Create visitors for the useless control flow and recursion detectors.
			 */
			UselessControlFlowDetector visitorUCF = new UselessControlFlowDetector();
			RecursionDetector visitorRD = new RecursionDetector();
			
			/*
			 * Set up collectors which will store the break-points.
			 */
			List<Breakpoints> collectorUCF = new ArrayList<>();
			List<Breakpoints> recursionCollector = new ArrayList<>();
			
			
			/*
			 * Visit the file
			 */
			visitorUCF.visit(compUnit, collectorUCF);
			visitorRD.visit(compUnit, recursionCollector);
			
			
			/*
			 * Print out the breakpoints.
			 */
			System.out.println("Useless Control Flows:");
			for (Breakpoints breakPoints : collectorUCF)
				System.out.println(breakPoints);
						
			System.out.println("\n\nRecursions:");
			for (Breakpoints breakPoints : recursionCollector)
				System.out.println(breakPoints);
			
			
		/* Handle exceptions. */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
