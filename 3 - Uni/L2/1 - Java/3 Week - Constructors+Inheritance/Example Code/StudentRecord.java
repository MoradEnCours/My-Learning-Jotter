public class StudentRecord {
	private int studentNumber;
	private String name;
	private String degreeProgramme;
	private int yearOfStudy;
	private double payments;
	private double tuitionAmount;
	
	public StudentRecord(int studentNumber, String name, String degreeProgramme, int yearOfStudy, double tuitionAmount) {
		this.studentNumber = studentNumber;
		this.name = name;
		this.degreeProgramme = degreeProgramme;
		this.yearOfStudy = yearOfStudy;
		this.tuitionAmount = tuitionAmount;
		this.payments = 0;
	}
	
	public double getBalance() {
		return tuitionAmount - payments;
	}
	
	public void makePayment (double payment) {
		this.payments += payment;
	}
	
	public String getName() {
		return this.name;
	}
	// etc
	
	public String toString() {
		return studentNumber + ": " + name + " " + degreeProgramme + " (" + yearOfStudy + ")";
	}

}
