#include <string>
#include <vector>

#define RIGHT_HAND "R"
#define LEFT_HAND "L"
using namespace std;

int distance(int num1, int num2) {
    num1--;
    num2--;
    
    int row1 = num1 / 3;
    int col1 = num1 % 3;
    int row2 = num2 / 3;
    int col2 = num2 % 3;
    
    return abs(row1 - row2) + abs(col1 - col2);
}

string chooseThumb(int targetNumber, int& curLeftThumbNumber, int& curRightThumbNumber, string hand) {

    if (targetNumber == 0) targetNumber = 11;
    
    if (targetNumber % 3 == 1) { // 3,6,9
        curLeftThumbNumber = targetNumber;
        return "L";
    }
    if (targetNumber % 3 == 0) { // 1,4,7
        curRightThumbNumber = targetNumber;
        return "R";
    }   
    
    int leftDistance = distance(targetNumber, curLeftThumbNumber);
    int rightdistance = distance(targetNumber, curRightThumbNumber);
    
    if (leftDistance == rightdistance) { // 같은 거리일 경우
        if (hand == "L") curLeftThumbNumber = targetNumber;
        else if (hand == "R") curRightThumbNumber = targetNumber;
        
        return hand;
    }
    else { // 다른 거리일 경우
        if (leftDistance > rightdistance) {
            curRightThumbNumber = targetNumber;
            return "R";
        } else {
            curLeftThumbNumber = targetNumber;
            return "L";
        }
    }
}

string solution(vector<int> numbers, string hand) {
    int curLeftThumbNumber = 10; // *
    int curRightThumbNumber = 12; // #
    
    if (hand == "right") hand = RIGHT_HAND;
    else if (hand == "left") hand = LEFT_HAND;
    
    string answer = "";
    for(auto& targetNum: numbers) {
        answer += chooseThumb(targetNum, curLeftThumbNumber, curRightThumbNumber, hand);
    }
    
    return answer;
}
