int findMinimumValue(int[] ints) {
	int min;
	// This is buggy -- doesn't work unless there is at least one negative number
	// min = 0;
	
	// One possible solution -- you need to check for empty list though
	if (ints.length == 0) {
		return Integer.MIN_VALUE;
	}
	min = ints[0];
	
	// Another possible solution that works even with an empty list
	min = Integer.MAX_VALUE;
	
	// Using a for-each loop to go through values in an array
	for (int i : ints) {
		if (i < min) {
			min = i;
		}
	}
	return min;
}
