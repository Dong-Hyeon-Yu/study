#include <sstream>
#include <string>
#include <cmath>

using namespace std;

int sum[4] = {-1, -1, -1, -1};


int solution(string dartResult) {
    stringstream ss(dartResult);
    
    for (int currentRound = 1; currentRound <=3; currentRound++) {
        int score;
        char bonus, option;
        
        ss >> score;

        bonus = ss.get();
        option = ss.get();

        if (option != '*' && option != '#') {
            ss.unget();
        }
        
        switch (bonus){
            case 'S':
                sum[currentRound] = score;
                break;
            case 'D':
                sum[currentRound] = pow(score, 2);
                break;
            case 'T':
                sum[currentRound] = pow(score, 3);
                break;
        } 
        
        switch (option){
            case '*':
                sum[currentRound] *= 2;
                if (currentRound > 0) 
                    sum[currentRound - 1] *= 2;
                break;
            case '#':
                sum[currentRound] *= -1;
                break;
            default:
                break;
        } 
    }

    int answer = sum[1] + sum[2] + sum[3];
    return answer;
}
