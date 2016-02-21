#include "stdafx.h"

#include <stdio.h>
#include <cstring>

int countZeros(long long k)
{
    int count = k == 0 ? 1 : 0;

    while (k)
    {
        long long remain = k % 10;
        k /= 10;
        if (!remain)
            count++;
    }

    return count;
}

int getHowManyZeros(long long m, long long n) {
    int total = 0;
    for (long long i = m; i <= n; i++) {
        total += countZeros(i);
    }

    return total;
}



/* the problem with this algorithm is that it takes a lot of time to do the computation 
what we can do is to recursive algorithms

1456220 can be splitted acrosss like this 

100000 + 456220

0 .. 456220: suppose there are totally m zeros in-between

456220 .. 1456220 can be divided to:
    1) 456220 .. 999999
    2) 100000 .. 1456220

we know the number of 2) is m, the question remains is to calcaulate the 1)'s value.

1) can also be calculated by diff between

    1) 0 .. 999999
    2) 0 .. 456119

now if we can calculate all the numbers say: 0 .. 9999, then for any given k:

we can do 
    k = countZeros(k % 10) + countZeros(k - (k % 10) - 1) - countZeros((k % 10) -1)
which the formulat can be transformed into th4e following

    k = countZeros(0, k % 10) + countZeros(0,k - (k % 10)) - countZeros(k - (k % 10), k - (k % 10)) - countZeros(0, (k % 10)) + countZeros(k % 10, k % 10)

    then 

    k = countZeros(0,k - (k % 10)) - countZeros(k - (k % 10), k - (k % 10)) + countZeros(k % 10, k % 10)

now suppose that the number now is 

5456220

which means the value now is 
0 .. 9999999           countZeros(0, 9999999)
100000 .. 1999999      countZeros(0, 9999999)
200000 .. .....        countZeros(0, 9999999)
400000 .. 4999999      countZeros(0, 9999999)
500000 .. 5456220      


*/

int mostSignificantDigit(int k)
{
    while (k / 10)
    {
        k /= 10;
    }

    return k;
}

const int TABLE_SIZE = 1000;
const int LONG_LONG_SIZE = 64;
long long table[TABLE_SIZE];
long long SigNum[LONG_LONG_SIZE];

long numberOfDigitis(long k)
{
    int count = 1;
    while (k / 10)
    {
        k /= 10;
        count++;
    }

    return count;
}

void build1000()
{

    for (long long i = 0; i < 1000; i++)
    {
        table[i] = countZeros(i);
    }

    SigNum[0] = table[0];
    SigNum[1] = table[9];
    SigNum[2] = table[99];
    SigNum[3] = table[999];

}

long long getOrBuild(long long k)
{
    int numberOfDigit = numberOfDigitis(k);

    if (SigNum[numberOfDigit] != 0)
    {
        return SigNum[numberOfDigit];
    } 
    else
    {
        return 10 * getOrBuild(k % 10);
    }
}


long long result(long long k)
{
    int mostSigNum = mostSignificantDigit(k);
    long long base = mostSigNum * getOrBuild(k);
    long long rem = result(k % 10);
    return base + rem;
}


long long solution(long long m, long long n)
{
    return result(n) - result(m - 1);
}


int main(int argc, char* argv[])
{

    long long n, m;
    build1000();

    while (~scanf("%lld%lld", &m, &n))
    {
        //printf("%lld\n", getHowManyZeros(m, n));
        long long s = solution(m, n);
        printf("%lld\n", s);
    }

    return 0;
}