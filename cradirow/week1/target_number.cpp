#include <string>
#include <vector>
using namespace std;

int dfs(vector<int> numbers, int target, int idx, int cur){
    if(idx == numbers.size()){
        if(cur == target) return 1;
        else return 0;
    }else{
        return dfs(numbers, target, idx+1, cur+numbers[idx])
            + dfs(numbers, target, idx+1, cur-numbers[idx]);
    }
}

int solution(vector<int> numbers, int target) {
    int answer = 0;
    answer = dfs(numbers, target, 0, 0);
    return answer;
}