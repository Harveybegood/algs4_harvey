package Chapter1_4_AnalysisOfAlgorithms;
/*
* Memory requirement on a 32-bit machine... references are 4 bytes, object overhead is 8 bytes, and padding is to
* a multiple of 4 bytes
*
* */
public class Ex33 {
    /*
    *   Integer: 8(object overhead) + 4N(int values) + 4(padding) = 12+4N bytes
    *
    * */
    /*
    *   Date: 8(object overhead) + (4+4+4)N(int values) + 4(padding) = 12 + 12N bytes
    *
    * */
    /*
    *   Counter: 8(object overhead) + 8N(string values) + 4N(int values) + 4(padding) = 12 + 12N bytes
    *
    * */
    /*
    *   int[]: 8(object overhead) + 4N(int values) + 4(padding) = 12 + 4N bytes
    *
    * */
    /*
    *   double[]: 8(object overhead) + 8N(string values) + 4(padding) = 12 + 8N bytes
    *
    * */
    /*
    *   double[][]: 8(object overhead) + 8NM(string values) +
    *
    * */
    /*
    *   String: 8(object overhead) + 8N(string values) + 4(padding) = 12 + 8N bytes
    *
    * */
    /*
    *   Node: 8(object overhead) + 4N(node next values) + 4N(Item item values) + 4(reference to
    *   object values) + 4(padding) =
    *
    * */
    /*
    *   Stack: 8(object overhead) + 4(Node reference first) +
    *
    * */
}
