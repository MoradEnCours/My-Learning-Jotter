package enums;

public enum Day {
	MON, TUE, WED, THU, FRI, SAT, SUN;
	
	public boolean isWeekend() {
		return (this == SAT || this == SUN);
	}
}
