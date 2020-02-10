#include <iostream>
#include <string>
#include <vector>
#include <stack>
#include <math.h>
#include <map>
using namespace std;

string number;
vector<char> v;
map<int, int> m;
int isVisited[7];

bool isPrime(int n) {
    if (n < 2) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false;

    for (int i = 3; i <= sqrt(n); i+=2) {
        if (n % i == 0) return false;
    }
    return true;
}

int dfs(int cnt){
    int ret = 0;
    if(cnt == v.size() + 1){
        return 0;
    }
    if(cnt >= 1){
        if(isPrime(stoi(number)) && m[stoi(number)] == 0){
            m[stoi(number)] = 1;
            ret++;
        }
    }

    for(int i=0; i<v.size(); i++){
        if(cnt == 0 && number[i] == '0') continue;

        if(isVisited[i] == 0){
            isVisited[i] = 1;
            number += v[i];
            ret += dfs(cnt + 1);
            number.erase(number.size()-1, 1);
            isVisited[i] = 0;
        }
    }

    return ret;
}

int solution(string numbers) {
    int answer = 0;
    for(int i=0; i<numbers.size(); i++){
        v.push_back(numbers[i]);
    }
    answer = dfs(0);
    return answer;
}