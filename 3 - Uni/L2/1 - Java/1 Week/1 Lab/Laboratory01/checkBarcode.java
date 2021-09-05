boolean checkBarcode(String barcode)
{
	System.out.println(barcode);
    if (barcode.length() == 8 || barcode.length() == 13) 
	{
		System.out.println("Okay");
		char[] barcodeArray = barcode.toCharArray();
		
		int sum1 = 0;
		int sum2 = 0;
		
		for (int i = 0; i < barcodeArray.length - 1; i++)
		{
			if Character.isDigit(barcodeArray[i] == true)
				return true;
				break;
		}
		
		//return false;
	}
	else
		{
			return false;
		}
}