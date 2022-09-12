#include <string>
#include <ctype.h>
using namespace std;

string numbers[10] = {
    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
};


int solution(string s) {
    
    string ans = "";

    while(s.length() > 0){
    
        if (isdigit(s[0])) {
            ans += s.front();
            s.erase(s.begin());
        }
        else {
            int idx = 0;
            for (auto& num: numbers) {
                if(s.find(num) == 0) {
                    ans += to_string(idx);
                    s = string(s.begin() + num.length(), s.end());
                    break;
                }
                idx++;
            }
        }
        
    } 
    
    return stoi(ans);
}
