package Chapter3_4_HashTables;
/*
*   Suppose that keys are t-bit integers. For a modular hash function with prime M, prove that each key bit has the property
*   that there exist two keys differing only in that bit that have different hash values.
*
* */
public class Ex06_ProveDifferenceOfTBitIntegers {
    /*
            t-bit:

            for instance, t is 4, M is 5;
            1000(8)     %5 = 3
            1001(9)     %5 = 4
            1010(10)    %5 = 0
            1100(12)    %5 = 2
            1101(13)    %5 = 3 (its hash value equals to 8)
            1111(15)    %5 = 0 (its hash value equals to 10)

    * */
}
