package Chapter2_3_QuickSort;
/*
*   Show, in the style of the quicksort trace given in this section, how quicksort sorts the array
*       E   A   S   Y   Q   U   E   S   T   I   O   N
*   for the purpose of this exercise, ignore the initial shuffle
*
* */
public class Ex2_3_2_quicksort_trace {
    /*
    *   low j high  0   1   2   3   4   5   6   7   8   9   10  11
    *               E   A   S   Y   Q   U   E   S   T   I   O   N
    *
    *   lo	j	hi	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15
			        K	R	A	T	E	L	E	P	U	I	M	Q	C	X	O	S
        0	5	15	E	C	A	I	E	K	L	P	U	T	M	Q	R	X	O	S	i = 5, j = 6; i = 6, j = 5
        0	3	4	E	C	A	E	I	K	L	P	U	T	M	Q	R	X	O	S	sort(array, 0, 4)
        0	2	2	A	C	E	E	I	K	L	P	U	T	M	Q	R	X	O	S	sort(array, 0, 2)
        0	0	1	A	C	E	E	I	K	L	P	U	T	M	Q	R	X	O	S	sort(array, 0, 1)
        1		1	A	C	E	E	I	K	L	P	U	T	M	Q	R	X	O	S


    *
    * */
}
