#include <string>
#include <cctype>

using namespace std;

bool isAcceptable(char a) {
    return a == '-' || a == '_' || a == '.' || isdigit(a) || islower(a);
}

string solution(string new_id) {
    
    // 1st
    for(auto &ch: new_id) 
        if(isupper(ch)) ch |= 32;
    
    // 2nd
    string ans;
    for (auto &ch: new_id)
        if (isAcceptable(ch)) ans += ch;
    
    
    new_id = ans;
    ans = "";
    
    //3rd
    bool continuousDotFlag = false;
    if(!new_id.empty())
        for(auto &ch: new_id){
            if (ch == '.' && !continuousDotFlag) {
                continuousDotFlag = true;
                ans += ch;
            }
            else if (ch != '.') {
                ans += ch;
                continuousDotFlag = false;
            }
        }
    
    //4th
    if (ans.front() == '.') ans.erase(ans.begin());
    if (ans.back() == '.') ans.pop_back();
        
    
    //5th
    if (ans.empty())
        ans = "a";
    
    //6th
    if (ans.length() >= 16) {
        ans = ans.substr(0, 15);
    }
    if (ans.back() == '.')
        ans.pop_back();
    
    //7th
    char last = ans.back();
    while(ans.length() < 3){
        ans += last;
    }
    
    return ans;
}
