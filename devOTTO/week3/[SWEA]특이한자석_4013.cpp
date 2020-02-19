//
// Created by 융미 on 2020-02-19.
//

//[SWEA] 4013-특이한 자석
#include <iostream>
#include <vector>
#include <utility>
#include <queue>
#include <cmath>
using namespace std;
#define X 4
#define Y 8

int t, k;
int num, dir;
int answer;
int gear[X][Y];
bool check[4];
vector<pair<int,int>> vec;
queue<pair<int,int>> q;

void solution();
void rotate_clock(int index, int _dir);
void check_rotate(int index, int _dir);
void init();
void init_check();
void print()
{
    cout << endl;
    for(int i =0 ; i<X; ++i)
    {
        for(int j = 0 ; j<Y;++j)
        {
            cout << gear[i][j] << ' ';
        }
        cout << endl;
    }
    cout << endl;
}

int main(){
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> t;
    for(int i = 1; i<= t; ++i){
        cin >> k;
        for(int m = 0; m< X; ++m){
            for(int n = 0; n<Y; ++n){
                cin >> gear[m][n];
            }
        }
        for(int j = 0; j<k; ++j)
        {
            cin >> num >> dir;
            vec.push_back(make_pair(num-1,dir));
        }


        solution();
        cout << "#" << i << ' ' << answer << '\n';
        init();
    }

    return 0;
}

void solution(){

    for(int i = 0; i<k; ++i)
    {

        check_rotate(vec[i].first, vec[i].second);

        while(!q.empty()){
            pair<int,int> cur = q.front();
            q.pop();

            rotate_clock(cur.first, cur.second);
        }
        init_check();
    }

    for(int i = 0; i<X; ++i)
    {
        if(gear[i][0] != 0){
            answer += pow(2, i);
        }

    }
}

void init(){
    for(int i = 0; i<X; ++i){
        for(int j = 0; j<Y; ++j){
            gear[i][j] = 0;
        }
    }
    vec.clear();

    while(!q.empty()) q.pop();
    answer = 0;
}

void init_check(){

    for(int i = 0; i<4; ++i)
        check[i] = false;
}


void rotate_clock(int index, int _dir){

    int temp = 0;
    if(_dir == 1)
    {
        temp = gear[index][Y-1];
        for(int i = Y-1; i>0; --i)
        {
            gear[index][i] = gear[index][i-1];
        }
        gear[index][0] = temp;

    }
    else{
        temp = gear[index][0];
        for(int i = 0; i < Y-1; ++i)
        {
            gear[index][i] = gear[index][i+1];
        }
        gear[index][Y-1] = temp;

    }

}

void check_rotate(int index, int _dir)
{

    if(check[index]) return;
    check[index] = true;

    q.push(make_pair(index, _dir));
    switch(index) {
        case 0:
            if(gear[index][2] != gear[index+1][6]){
                check_rotate(index+1, (-1)*_dir);
            }
            break;
        case 3:
            if(gear[index][6] != gear[index-1][2]){
                check_rotate(index-1, (-1)*_dir);
            }
            break;
        default:
            if(gear[index][6] != gear[index-1][2]){

                check_rotate(index-1, (-1)*_dir);
            }
            if(gear[index][2] != gear[index+1][6]){
                check_rotate(index+1, (-1)*_dir);
            }
            break;
    }
    return;
}