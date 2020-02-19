//
//  main.cpp
//  sw-4013
//
//  Created by YunBum Han on 2020/02/19.
//  Copyright © 2020 YunBum Han. All rights reserved.
//

#include <iostream>
#include <math.h>
using namespace std;

int k;
int arr[4][8];
bool isRotate[4]; // 회전은 한 번만 일어나야 한다.

void initRotateInfo(){
    for(int i=0; i<4; i++){
        isRotate[i] = false;
    }
}

// 자석 회전시키기
void rotation(int num, int direction){
    if(isRotate[num] == true) return;
    isRotate[num] = true;
    
    // left
    if(num-1 >= 0){
        if(arr[num][6] != arr[num-1][2]){
            rotation(num-1, -1*direction);
        }
    }
    // right
    if(num+1 <= 3){
        if(arr[num][2] != arr[num+1][6]){
            rotation(num+1, -1*direction);
        }
    }
    
    // 시계방향 회전
    if(direction == 1){
        int last = arr[num][7];
        for(int i=6; i>=0; i--){
            arr[num][i+1] = arr[num][i];
        }
        arr[num][0] = last;
    }
    // 반시계방향 회전
    else if(direction == -1){
        int first = arr[num][0];
        for(int i=0; i<7; i++){
            arr[num][i] = arr[num][i+1];
        }
        arr[num][7] = first;
    }
    
    return;
}

int main(int argc, const char * argv[]) {
    int T; cin >> T;
    for(int t=1; t<=T; t++){
        cin >> k;
        // 자석 정보 입력받기
        for(int i=0; i<4; i++){
            for(int j=0; j<8; j++){
                cin >> arr[i][j];
            }
        }
        
        // 회전 정보 입력받기
        for(int i=0; i<k; i++){
            int num, direction;
            cin >> num >> direction;
            initRotateInfo();
            rotation(num-1, direction);
        
        }
        
        // 점수 계산하기
        int answer = 0;
        for(int i=0; i<4; i++){
            if(arr[i][0] == 1) answer += pow(2, i);
        }
        
        printf("#%d %d\n", t, answer);
    }
    return 0;
}

