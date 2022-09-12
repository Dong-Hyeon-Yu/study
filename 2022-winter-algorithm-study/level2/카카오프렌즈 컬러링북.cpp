#include <vector>
#include <queue>
#include <map>

#define vvi vector<vector<int>>
#define dict map<int, Count>
using namespace std;

struct Point {
    int r;
    int c;
};
struct Count {
    int value = 0;
};
int dr[4] = {1, 0, -1, 0};
int dc[4] = {0, 1, 0, -1};

vector<int> solution(int m, int n, vector<vector<int>> picture) {
    queue<Point> q; 
    int number_of_area = 0;
    int maxSizeOfOneArea = 0;
    dict colorAndNumberDictionary; // 색깔별 개수 

    
    int pictureRowSize = picture.size();
    int pictureColSize = picture[0].size();
    for (int row = 0; row < pictureRowSize; row++) {
        for (int col = 0; col < pictureColSize; col++) {
            
            if (picture[row][col] != -1) {
                int color = picture[row][col];
                int sizeCount = 0;
                q.push({row, col}); // 시작점
                
                while(!q.empty()) {
                    Point currentPoint = q.front();
                    q.pop();

                    for(int i = 0; i < 4; i++){
                        int nextRow = currentPoint.r + dr[i];
                        int nextCol = currentPoint.c + dc[i];
                        if (0 <= nextRow && nextRow < pictureRowSize && 
                            0 <= nextCol && nextCol < pictureColSize && 
                            picture[nextRow][nextCol] != -1 &&
                            picture[nextRow][nextCol] == color) {
                            
                            // 방문 표시
                            q.push({nextRow, nextCol});                
                            colorAndNumberDictionary[color].value += 1;
                            picture[nextRow][nextCol] = -1; 
                            sizeCount += 1;
                        }
                    }
                }
                if (color != 0) {
                    number_of_area += 1;
                    maxSizeOfOneArea = max(maxSizeOfOneArea, sizeCount);   
                }
                
            }
        }
    }
    
    vector<int> answer(2);
    answer[0] = number_of_area;
    answer[1] = maxSizeOfOneArea;
    return answer;
}
