#include <string>
#include <stdio.h>
#include <algorithm>
#define INF 1 << 30;
using namespace std;


int solution(string s) {
    int answer = INF;
    int sSize = s.size();
    
    for(int patternSize = 1; patternSize <= sSize/2 + 1; patternSize++) {
        int pos = 0, patterMatchingCount = 0;
        string pattern, result, stringToCompare;
        
        pattern = s.substr(0, patternSize);
        while(pos < sSize - patternSize + 2) {
            stringToCompare = s.substr(pos, patternSize);
            if (pattern == stringToCompare) { // 같으면 패턴 길이만큼 더 이동
                patterMatchingCount++;
                pos += patternSize;
            }
            else { // 틀리면 
                result += (patterMatchingCount > 1 ? to_string(patterMatchingCount) : "") + pattern;
                pattern = stringToCompare;
                patterMatchingCount = 0;
            }
            
        }
        result += (patterMatchingCount > 1 ? to_string(patterMatchingCount) : "") + pattern;  // 마지막 비교당한 부분분자열
        if (pos < sSize) result += string(s.begin() + pos, s.end());  // 패턴의 길이가 더 길어서 비교당하지 못한 문자열.
        
        answer = min(answer, (int)result.size());
    }
    
    return answer;
}
