package Chapter3_4_HashTables;
/*
*   Consider the idea of implementation modular hashing for integer keys with the code(a * k) % M, where a is an arbitrary fixed prime.
*   Does this change mix up the bits sufficiently well that you can use nonprime M?
*
* */
public class Ex07_ConsiderModularHashing {
    /*
    *   Assume Integer sets k[] = {10, 23, 9, 18, 40}; and M is 8.
    *   (a * k) % M with a as prime 7
    *   first key,  (7 * 10) % 8 = 6
    *   second key, (7 * 23) % 8 = 1                (8 * 23) % 7 = 2
    *   third key,  (7 * 9)  % 8 = 7                (8 * 9)  % 7 = 2
    *   fourth key, (7 * 18) % 8 = 6                (8 * 18) % 7 = 4
    *   fifth key,  (7 * 40) % 8 = 0                (8 * 40) % 7 = 5
    *
    *   with a as prime 9
    *   first key,  (9 * 10) % 8 = 2
    *   second key, (9 * 23) % 8 = 7
    *   third key,  (9 * 9)  % 8 = 1
    *   fourth key, (9 * 18) % 8 = 2
    *   fifth key,  (9 * 40) % 8 = 0
    *
    *   Since such these arithmetic formula abide to a condition that one of numerator and denominator is not even, so which means
    *   avoid to result in many same hash values. if both numerator and denominator are even integers
    * */
}
