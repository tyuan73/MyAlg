/********
// BEGIN CUT HERE
// PROBLEM STATEMENT
// The Centaur company has N servers, numbered 1 through N.
These servers are currently connected into a network.
The topology of the network is a tree.
In other words, there are exactly N-1 bidirectional cables, each connecting some two servers in such a way that the entire network is connected.

The Centaur company is about to split into two new companies: the Human company and the Horse company.
When this happens, the companies will divide the servers randomly.
More precisely, for each of the N servers the two companies will flip a fair coin, and the winner of the coin flip gets the server.
Once they divide the servers, each company will configure their own servers not to use the cables that lead to the servers of the other company.

Of course, it may then happen that some pairs of servers that belong to the same company will not be able to communicate any more.
Therefore, each of the new companies will then use new, additional cables to connect all of its servers to the same network.
New cables can be added for free.
However, each of the servers currently only has one empty slot into which an end of a cable can be inserted.
Adding one additional empty slot to a server costs 1.
If desired, it is possible to add multiple slots to the same server.
Each of the companies will connect their servers using a way with the smallest possible cost.

You are given two int[]s a and b, each containing N-1 elements.
These two arrays describe the original cables:
for each i, there is a cable that connects the servers a[i] and b[i].

Compute and return the expected value of the total cost paid by the two companies to connect their networks.
(The expectation is taken over all possible ways in which they can divide the N servers.)

DEFINITION
Class:CentaurCompany
Method:getvalue
Parameters:int[], int[]
Returns:double
Method signature:double getvalue(int[] a, int[] b)


NOTES
-N can be determined as (1 + the length of a).
-The expected value of a variable can be seen as the average value of the variable, where the average is taken over many rounds of the experiment. See http://en.wikipedia.org/wiki/Expected_value for a formal definition.
-Your return value must have a relative or absolute error of at most 1e-9.


CONSTRAINTS
-N will be between 2 and 36, inclusive.
-a and b will contain exactly N-1 elements.
-Each element of a and b will be between 1 and N, inclusive.
-The network defined by a and b will be a tree (as explained in the problem statement).


EXAMPLES

0)
{1}
{2}

Returns: 0.0

Regardless of the result of the coin flips, the companies don't need additional cables to connect their own servers. Therefore, the expected cost is zero.

1)
{1,1,1}
{2,3,4}

Returns: 0.125

If the Horse company gets only the server 1 and the Human company gets the other servers, the Human company must add one empty slot. For example, the Human company can add one empty slot to the server 2 and add new cables to connect the server 2 with the servers 3 and 4. Similarly, if the Human company gets only the server 1 and the Horse company gets the other servers, the cost will also be 1. In all other cases the cost will be 0. The expected cost is 1/(2^4) + 1/(2^4) = 1/8.

2)
{1,2,3,2,2}
{2,3,4,5,6}

Returns: 0.375

For example, if the Horse company gets only the server 2 and the Human company gets the other servers, the Human company must add one empty slot. In the picture below, initially each server has one empty slot (blue). The Human company adds one extra slot (yellow) to the server 5. The Human company also adds cables that connect the servers 1 and 5, 3 and 5, and 4 and 6.




3)
{1,2,3,4,5,6,7,8,9}
{2,3,4,5,6,7,8,9,10}

Returns: 0.41796875



4)
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
{2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36}

Returns: 15.500000001076842



5)
{10, 7, 2, 5, 6, 2, 4, 9, 7}
{8, 10, 10, 4, 1, 6, 2, 2, 3}

Returns: 0.646484375



// END CUT HERE
********/
import java.util.*;
public class CentaurCompany {
	public double getvalue(int[] a, int[] b) {
		          return 0.0;
	}
	public static void main(String[] args) {
		CentaurCompany temp = new CentaurCompany();
		//System.out.println(temp.getvalue(int[] a, int[] b));
	}
}
