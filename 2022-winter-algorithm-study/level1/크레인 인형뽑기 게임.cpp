#include <string>
#include <vector>

#define vi vector<int>
#define vvi vector<vi>

using namespace std;

int putIntoBucket(vi& bucket, int stuff) {
    if (!bucket.empty() && bucket.back() == stuff) { 
        bucket.pop_back();
        return 2;
    }
    else {
        bucket.push_back(stuff);
        return 0;
    }
}

int getTopElementOf(vvi& board, int move) {
    int boardSize = board.size();
    
    for(int i = 0; i < boardSize; i++) {
        if(board[i][move-1] != 0){
            int tmp = board[i][move-1];
            board[i][move-1] = 0;

            return tmp;
        }
    }
    return -1;
}

int solution(vvi board, vector<int> moves) {
    int answer = 0;
    vi bucket;
    
    int stuff;
    for(auto& idx: moves) {
        stuff = getTopElementOf(board, idx);
        
        if (stuff != -1) // 인형이 없다.
            answer += putIntoBucket(bucket, stuff);
    }
    return answer;
}
