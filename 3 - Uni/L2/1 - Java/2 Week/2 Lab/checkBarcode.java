boolean checkBarcode(String barcode)		// The String Signature as specified
{
	// In this (big) if block, the first test checks to see if the appropriate string length
	// is given - an EAN of 8 or 13 digits; if the condition is not met, then this if block is
	// completely skipped and the result returned should be false.
	if (barcode.length() == 8 || barcode.length() == 13)	// Test if EAN number is 8 or 13...
	{
		char[] barcodeArray = barcode.toCharArray();		// Create an array to enable to for-loop
		
		for (int i = 0; i < barcodeArray.length; i++) {		// Here we are going to check if the 8 characters...		
			{												// are all digits
			
			// If this test fails and not all characters are digits, then the original condition
			// also fails, hence this whole block is skipped, any following procedures are cancelled,
			// and the result returned is again false.
			
			if (Character.isDigit(barcodeArray[i]) == true) 
				
				if (barcode.length() == 8)			
				// Based on the previous length test, reaching this stage means that the length of the
				// the barcode is either 8 or 13; now we use an if condition to make a distinction between
				// the two barcodes. If it's 8 digits, then the next block should execute.
				// Otherwise, the only other alternative is that it's a 13 digit EAN barcode.
				{
					int sum = 0;	
					// A variable called sum is used for summing the integer equivalent of the characters in the array
					
					for (int item = 0; item < barcodeArray.length - 1; item++)
					{
						// The temporary integer variable item created in the for loop is basically an indexer
						// so what can be done now is to compare whether or not the integer equivalent of each
						// character should be added to the sum variable without first multiplying by 3 or not.
						
						if (item % 2 == 0)												// If the index is even... 
							sum += 3 * Character.getNumericValue(barcodeArray[item]);	// multiply value by 3
																						// and add it to sum
						else
							sum += Character.getNumericValue(barcodeArray[item]);	// otherwise, add the unmultiplied value
					}
					
					// The next part is straightforward: modulate, then subtract,
					// and then finally compare the checkdigit with the last digit on the barcode
					// If they are not equal, return false; if they are, return true
					int modulated = sum % 10;
					int checkDigit = 10 - modulated;
					if (checkDigit != Integer.parseInt(barcode.substring(7)))
						return false;
					return true;
				}
				
				else
					// This else block is similar to the previous if block, except...
					// this time odd indexes multiply by 3 while even ones do not.
					// For simplicity, most variable names have a b at the end...
					// to represent the alternate EAN in this case
				{
					int sumb = 0;
					for (int itemb = 0; itemb < barcodeArray.length - 1; itemb++)
					{
						if (itemb % 2 == 0)
							sumb += Character.getNumericValue(barcodeArray[itemb]);
						else
							sumb += 3 * Character.getNumericValue(barcodeArray[itemb]);
					}
					
					int modulatedb = sumb % 10;
					int checkDigitb = 10 - modulatedb;
					if (checkDigitb != Integer.parseInt(barcode.substring(12)))
						return false;
					return true;
				}
								
			}

		}
			
	}
	
		
	return false;				// The EAN barcode is not valid
	
}


