#include <string>
#include <vector>

using namespace std;

static vector<int> mask;

string getRowString(vector<int>& mask, int row, int n) {
    string ans = "";
    for (int i = n - 1; i >= 0; i--) 
        ans += (mask[i] & row ? "#" : " ");
    
    return ans;
}

vector<string> solution(int n, vector<int> arr1, vector<int> arr2) {
  
    for (int i = 0; i < n; i++) {
        mask.push_back(1 << i); // create mask
        arr1[i] |= arr2[i];     // compare two maps
    }
    
    vector<string> answer;
    for (int i = 0; i < n; i++) 
        answer.push_back(getRowString(mask, arr1[i], n));
    
    return answer;
}
