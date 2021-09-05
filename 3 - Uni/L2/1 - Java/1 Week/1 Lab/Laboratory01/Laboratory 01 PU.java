int computeFibonacchi(int n)
{
    int result = 1;
	int lastResult = 1;
	for (int i = 0; i < n - 2; i++)
		{
			int temp = result;
			result += lastResult;
			lastResult = temp;
			
		return result;
			
		}
	
}
===================== ERROR 01 ======================

jshell> int n = 12
n ==> 12

jshell> int computeFibonacchi( int n) {
   ...>     int result = 1;
   ...>     int lastResult = 1;
   ...>     for (int i = 0; i < n-2; i++) {
   ...>         int temp = result;
   ...>         result += lastResult;
   ...>         lastResult = temp;
   ...>
   ...>     return result;
   ...>     }
   ...> }
|  Error:
|  missing return statement
|  int computeFibonacchi( int n) {
|                                ^
====================================================
                     ERROR 02
====================================================
jshell> int computeFibonacchi( int n) {
   ...>     int result = 1;
   ...>     int lastResult = 1;
   ...>     for (int i = 0; i < n-2; i++) {
   ...>         int temp = result;
   ...>         result += lastResult;
   ...>         lastResult = temp;
   ...>
   ...>         return result;
   ...> }
   ...> }
|  Error:
|  missing return statement
|  int computeFibonacchi( int n) {
|                                ^
=====================================================
			WORKING SOLUTION JShell
=====================================================
jshell> int computeFibonacchi( int n) {
   ...>     int result = 1;
   ...>     int lastResult = 1;
   ...>     for (int i = 0; i < n-2; i++) {
   ...>         int temp = result;
   ...>         result += lastResult;
   ...>         lastResult = temp;
   ...> }
   ...>     return result;
   ...> }
|  created method computeFibonacchi(int)

jshell> computeFibonacchi(12)
$12 ==> 144

jshell> computeFibonacchi(10)
$13 ==> 55

jshell> computeFibonacchi(2)
$14 ==> 1
=====================================================
			TESTING TO OPEN FILE IN JShell
=====================================================
jshell> /open C:/Users/Lenovo/Desktop/Laboratory01U.java

jshell> computeFibonacchi(10)
$27 ==> 55

jshell> computeFibonacchi(2)
$28 ==> 1
=====================================================
					FINAL SOLUTION
=====================================================
int computeFibonacchi(int n) {
    int result = 1;
	int lastResult = 1;
	for (int i = 0; i < n-2; i++) {
	    int temp = result;
	    result += lastResult;
	    lastResult = temp;
	}
    return result;
}


