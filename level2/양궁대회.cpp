#include <string>
#include <vector>
#define vi vector<int>
using namespace std;

/* 어피치와 라이언 점수 차이 구하기 */
static int getScoreDiff(vector<int> apeach, vector<int> lion) {
    int lionScore = 0;
    int apeachScore = 0;
    for (int i = 0; i < 11; i++) {
        if (apeach[i] < lion[i]) lionScore += (10 - i);
        else if (apeach[i] > 0) apeachScore += (10 - i);
    }
    
    return lionScore - apeachScore;
}

/** 백트래킹으로 중복 조합 구하기
* @param idx 현재 깊이에서 탐색 시작할 인덱스
* @param repeatedCombination 중복조합의 경우의 수, 여기서는 라이언의 가능한 모든 점수 조합임.
* @param apeachInfo 문제에서 주어진 어피치의 점수 현황
* @param n 총 화살 수
* @param candidateAnswer 현재 유력한 라이언의 점수 조합
* @param curMaxValue 현재 유력한 라이언의 최고 점수
*/
static void backTracking(int idx, vi& repeatedCombination, vi& apeachInfo, int n, vi& candidateAnswer, int& curMaxValue) {
    if (n == 0) {
        int diff = getScoreDiff( apeachInfo, repeatedCombination );
        
        if (diff > 0 && diff > curMaxValue) { /* 라이언이 더 큰 점수 && 최고 기록 갱신 */
            curMaxValue = diff;
            copy( repeatedCombination.begin(), repeatedCombination.end(), candidateAnswer.begin() );
        }
        else if (diff > 0 && diff == curMaxValue) { /* 라이언이 더 큰 점수 && 기존 최고기록과 동일 */
            for (int i = 10; i >= 0; i--) {
                if (repeatedCombination[i] < candidateAnswer[i]) {
                    break;
                }
                else if  (repeatedCombination[i] > candidateAnswer[i]) {
                    copy(repeatedCombination.begin(), repeatedCombination.end(), candidateAnswer.begin());
                    break;
                }
            }
        }
        return;
    }
    
    for (int i = idx; i < 11; i++) {
        repeatedCombination[i]++;
        backTracking(i, repeatedCombination, apeachInfo, n - 1, candidateAnswer, curMaxValue);
        repeatedCombination[i]--;
    }
}

vector<int> solution(int n, vector<int> info) {
    vector<int> repeatedCombination(11, 0);
    vector<int> candidateAnswer(11, 0);
    int maxValue = -1;
    
    backTracking(0, repeatedCombination, info, n, candidateAnswer, maxValue);
    
    vector<int> answer;
    if (maxValue == -1) /* 변경된 적이 없으면 => 라이언은 답이 없다... */
        answer.push_back(-1);
    else
        answer = candidateAnswer;
    
    return answer;
}
