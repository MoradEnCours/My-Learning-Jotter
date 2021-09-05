int computeChecksum(String iban)							// The method signature
{
	// Remove all spaces and convert to upper case, then store it in variable noSpaceIban
	String noSpaceIban = iban.replaceAll(" ","").toUpperCase();
	
	// Variable assignments made for later testing whether first two digits match the ones given below
	// Also, lengths are stored for comparisons further additional comparison tests 
	String Country1 = "GB"; int lengthC1 = 22;
	String Country2 = "GR"; int lengthC2 = 27;
	String Country3 = "SA"; int lengthC3 = 24;
	String Country4 = "CH"; int lengthC4 = 21;
	String Country5 = "TR"; int lengthC5 = 26;
	
	// This first comparison checks to see if the given country code is valid
	// If this test fails, this entire if block is skipped and an error message is displayed
	if (noSpaceIban.substring(0,2).equals(Country1) ||
	    noSpaceIban.substring(0,2).equals(Country2) ||
		noSpaceIban.substring(0,2).equals(Country3) ||
		noSpaceIban.substring(0,2).equals(Country4) ||
		noSpaceIban.substring(0,2).equals(Country5))
		{
			// This condition tests to compare the length of the given string while also...
			// making sure that the correct length is given for the correct country.
			// If the && operator was not added, it would be possible for GB to be given an iban of...
			// length 21, for example, and the test would fail to notice this because in using ||...
			// as long as one condition is true, the iban would be regared valid nonetheless. 
			if (((int)noSpaceIban.length() == lengthC1 && noSpaceIban.substring(0,2).equals(Country1)) ||
				((int)noSpaceIban.length() == lengthC2 && noSpaceIban.substring(0,2).equals(Country2)) ||
				((int)noSpaceIban.length() == lengthC3 && noSpaceIban.substring(0,2).equals(Country3)) ||
				((int)noSpaceIban.length() == lengthC4 && noSpaceIban.substring(0,2).equals(Country4)) ||
				((int)noSpaceIban.length() == lengthC5 && noSpaceIban.substring(0,2).equals(Country5)))
				
				{
					// The string variable rear is simply the original iban reformatted to include...
					// the country code with 00 at the end, displaced from the front side.
					
					String rear = noSpaceIban.substring(4) + noSpaceIban.substring(0,2) + "00";
					char[] ibanArray = rear.toCharArray();		// To enable for-loop processing, 
																// an array is created
					String stringDigits = "";					
					// The above empty string is declared so as to reform the original iban, giving only...
					// the integer representation of each of the characters within the array.
					for (int i = 0; i < ibanArray.length; i++)
					{
						// Remember: the character must be either a digit or a letter. 
						// If it is not, the iban is invalid and thus an error message should be displayed
						// If it is, though, then the character should be added to the empty string
						
						if (Character.isDigit(ibanArray[i]) == true ||
							Character.isLetter(ibanArray[i]) == true)
							stringDigits += Character.getNumericValue(ibanArray[i]);
						else
						{
							System.out.println("Invalid character in IBAN: " + ibanArray[i]);
							return -1;
						}
							
					}
					
					// Dealing with really big numbers means needing to use the BigInteger class
					// The variable mod stores the remainder of the BigInteger
					// The value of mod is subtracted from 98 to give the checksum
					// Finally, the checksum value is returned.
					
					int mod = new BigInteger(stringDigits).mod(BigInteger.valueOf(97)).intValue();
					int checkSum = 98 - mod;
					return checkSum;
					
				}
			else				// If the IBAN length is invalid, this else block executes
			{
				System.out.println("Invalid IBAN length: " + noSpaceIban.length());
				return -1;
			}
			
		}
	
	// If the country code is invalid, the above block failed and hence got skipped.
	// Ultimately, an error message for this is displayed and -1 is returned.
	
	System.out.println("Unknown country code: " + noSpaceIban.substring(0,2));
	return -1;
}