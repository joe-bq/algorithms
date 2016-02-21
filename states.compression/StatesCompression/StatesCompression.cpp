#include "stdafx.h"
#include <cstdio>
#include <cstring>

/* where the key to the solution is so-called states compression, and you can find the related answers here:

*/
using namespace std;


const int N = 13;
const int M = (1 << N);
int st[M], map[M];
const int mod = 100000000;
int dp[N][M];


bool judge1(int x)
{
    return (x & (x << 1));
}

bool judge2(int i, int x)
{
    return (map[i] & st[x]);
}


int main3()
{
    int n, m, x;

    while (~scanf("%d%d", &n, &m))
    {

        memset(st, 0, sizeof(st));
        memset(map, 0, sizeof(map));
        memset(dp, 0, sizeof(dp));
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= m; j++)
            {
                scanf("%d", &x);
                if (x == 0)
                {
                    map[i] += (1 << (j - 1));
                }

            }
        }



        int k = 0;
        // initialize st
        for (int i = 0; i < (1 << m); i++)
        {
            if (!judge1(i)) {
                st[k++] = i;
            }
        }

        for (int i = 0; i < k; i++)
        {
            if (!judge2(1, i))
            {
                dp[1][i] = 1;
            }
        }

        for (int i = 2; i <= n; i++)
        {
            for (int j = 0; j < k; j++)
            {
                if (judge2(i, j))
                {
                    continue;
                }

                for (int f = 0; f < k; f++)
                {
                    if (judge2(i - 1, f))
                    {
                        continue;
                    }

                    if (!(st[j] & st[f]))
                    {
                        dp[i][j] += dp[i - 1][f];
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < k; i++)
        {
            ans += dp[n][i];
            ans %= mod;
        }

        printf("%d\n", ans);

    }
    return 0;
}