# To be revised and filled in with the rest, although this is one viable solution. (I am aware of the TitleCasing used, but this is a snippet of handwritten code.)

def ReverseInteger(number):
    Number = number
    SNumber = str(Number)
    IsNegative = '-' in SNumber
    SLength = len(SNumber)
    if IsNegative:
        ExtractedNumbers = SNumber[-1:0:-1]
        ReversedSNumber = '-' + ExtractedNumbers
        Number = int(ReversedSNumber)
    else:
        ExtractedNumbers = SNumber[-1::-1]
        Number = int(ExtractedNumbers)
    return Number
