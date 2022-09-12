#include <vector>
#include <queue>

#define vi vector<int>
#define pair pair<double, int>
using namespace std;

struct compare {
    bool operator()(const pair& a, const pair& b){
        if (a.first == b.first)
            return a.second > b.second;
        else
            return a.first < b.first;
    }
};

vi solution(int N, vi stages) {
    
    vi triedPlayerNumberOf(N + 2, 0);
    vi failedPlayerNumberOf(N + 2, 0);
    
    for(int& stage: stages) {
        for(int i = 1; i <= stage; i++) {
            triedPlayerNumberOf[i] += 1;
        }
        failedPlayerNumberOf[stage] += 1;
    }

    priority_queue<pair, vector<pair>, compare> failureRateAndStageNumber;
    double failureRate;
    for(int stageNumber = 1; stageNumber <= N; stageNumber++){
        
        if (triedPlayerNumberOf[stageNumber] == 0)
            failureRate = 0;
        else
            failureRate = (double) failedPlayerNumberOf[stageNumber] / triedPlayerNumberOf[stageNumber];
        
        failureRateAndStageNumber.push({failureRate, stageNumber});
    }
    
    vi answer;
    int stageNumber;
    while(!failureRateAndStageNumber.empty()) {
        
        stageNumber = failureRateAndStageNumber.top().second;
        answer.push_back(stageNumber);

        failureRateAndStageNumber.pop();   
    }
    
    return answer;
}
