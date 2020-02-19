#include <iostream>
#include <cmath>
#define endl "\n"

using namespace std;

int T, K;
int magnet[4][8];
bool visited[4];


void initVisited()
{
    for(int i=0; i<4; i++)
    {
        visited[i] = false;
    }
}

void rotate(int num, int dir)
{
    if(dir == 1) // right rotate
    {
        int temp = magnet[num][7];
        for(int i=7; i>0; i--)
        {
            magnet[num][i] = magnet[num][i-1];
        }
        magnet[num][0] = temp;
    } else { // left rotate
        int temp = magnet[num][0];
        for(int i=0; i<7; i++)
        {
            magnet[num][i] = magnet[num][i+1];
        }
        magnet[num][7] = temp;
    }
}


void solution(int num, int dir)
{
    visited[num] = true;

    if(magnet[num][2] != magnet[num+1][6] && num < 3 && !visited[num+1])
    {
        solution(num+1, -1*dir);
    }

    if(magnet[num][6] != magnet[num-1][2] && num > 0 && !visited[num-1])
    {
        solution(num-1, -1*dir);
    }

    rotate(num, dir);

    return;
}

int result()
{
    int score = 0;

    for(int i=0; i<4; i++)
    {
        if(magnet[i][0] == 1)
        {
            score += pow(2, i);
        }
    }

    return score;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(false); //cpp의 iostream을 c의 stdio와 동기화 끊어줌 -- 속도 개선 목적
    cin.tie(NULL); // cin을 cout으로 부터 untie -- stream을 tie하면 다른 stream에서 입출력 요청이 오기전에 stream을 flush함
    cout.tie(NULL);

    cin >> T;
    for(int i=0; i<T; i++)
    {
        cin >> K;
        for(int j=0; j<4; j++)
        {
            for(int k=0; k<8; k++)
            {
               cin >> magnet[j][k];
            }
        }
        for(int j=K; j>0; j--)
        {
            int num, dir;
            cin >> num >> dir;
            initVisited();
            solution(num-1, dir);
        }
        cout << "#" << i+1 << " " << result() << endl;
    }
    return 0;
}
