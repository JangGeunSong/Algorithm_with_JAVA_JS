public class Algorithm_Q162 {
    /*
     * 이 문제는 행렬을 소용돌이 모양으로 숫자를 채워나가며 만들어야 하는 문제다.
     * 전날 풀이했던 행렬의 나선형 순회 문제와 완전히 같은 문제이다. 하지만 이 문제는 정 사각 행렬이라 더 쉽다
     * https://leetcode.com/problems/spiral-matrix-ii/
     * 풀이를 확인해보자.
     */
    
    public int[][] generateMatrix(int n) {
        // 정답을 얻기위한 2차원 배열(행렬)을 선언
        int[][] answer = new int[n][n];
        // 위치의 경계를 얻기위해 top, bottom / left, right 2개씩 수평과 수직의 boundary를 두고 진행한다.
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        // 값이 1씩 더해지면서 들어가므로 초기값 1을 먼저 선언
        int curr = 1;

        while(left <= right && top <= bottom) {
            // 우선 좌측 최상단 부터 시계방향으로 돌아야 하므로, 가장 윗줄을 채운다.
            for(int i = left; i <= right; i++) {
                answer[top][i] = curr;
                curr += 1;
            }
            // 맨 윗줄은 이제 갈 필요가 없으므로, top에 1을 더한다.
            top += 1;
            // 이제 한칸 내려온 지점이 top, right 지점이므로, 여기서부터 차례대로 아래로 내려가며 값을 채운다.
            for(int i = top; i <= bottom; i++) {
                answer[i][right] = curr;
                curr += 1;
            }
            // 가장 오른쪽은 이제 갈 일이 없으므로, 1 숫자를 낮춘다.
            right -= 1;
            // 위와 마찬가지 논리로 이제 맨 바닥을 채우기 위해 이동
            for(int i = right; i >= left; i--) {
                answer[bottom][i] = curr;
                curr += 1;
            }
            // 밑바닥은 더 이상 갈 일이 없으므로 1을 빼고
            bottom -= 1;
            // 맨 왼쪽에 모든 값을 위로 올라가며 채운다.
            for(int i = bottom; i >= top; i--) {
                answer[i][left] = curr;
                curr += 1;
            }
            // 맨 왼쪽은 이제 더 이상 갈 필요가 없으므로, 1 더한다.
            left += 1;
        }

        // 이렇게 얻게된 최종 행렬을 리턴한다.
        return answer;
    }
}
