#include <string>
#include <vector>
#include <iostream>
using namespace std;

#define MOD 1000000007;

int dp[102][102];
int map[102][102];
int solution(int m, int n, vector<vector<int>> puddles) {

    for(int i=0; i<puddles.size(); i++){
        int x = puddles[i][0];
        int y = puddles[i][1];
        map[y][x] = 1;
    }
    
    dp[0][1] = 1;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            if(map[i][j] == 1){
                dp[i][j] = 0;
            }else{
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%MOD;
            }
        }
    }
    
    return dp[n][m];
}