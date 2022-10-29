import java.util.*;

public class Algorithm_Q24 {
    /*
     * 해당 문제는 DP및 Two pointer에 대한 문제이다.
     * 2개의 포인터를 써야하는 이유는 물이 채워지는 공간의 크기를 구하기 위해서는 한쪽 벽은 가장 큰 길이의 벽인 것으로 간주하고 풀어야 하기 때문이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/trapping-rain-water/
     * 이 문제는 나의 풀이와 더 빠른 풀이를 모두 체크해 볼 필요가 있다.
     * 풀이를 확인해보자.
     */


    /*
     * stack을 통해 공간의 사이즈를 저장하고, 만약 새로운 높은 벽을 만나 갱신할 떄가 되면, 
     * stack에 있는 공간을 빼서 정답으로 이동 시킨다. 이때 지나간 길이를 재기 위해 length를 사용했다.
     * 또한 가장 높은 벽을 기준으로 왼쪽에서 시작, 오른쪽에서 시작 해야 정확하게 값을 구할 수 있기 때문에,
     * 이를 위해 maxHeightIndex를 두고 height를 왼쪽 오른쪽으로 구분해서 풀이 하였다.
     */
    public int trap_mine(int[] height) {
        int answer = 0;
        int left_height = 0;
        int right_height = 0;
        int maxHeightIndex = 0;
        int length = 0;

        Stack<Integer> stack = new Stack<>();

        // 가장 높은 벽의 index를 찾기 위한 loop
        for(int i = 0; i < height.length; i++) {
            if(left_height <= height[i]) {
                left_height = height[i];
                maxHeightIndex = i;
            }
        }

        left_height = 0;

        // 왼쪽에서 부터 시작해서 가장 높은 벽 까지 채워지는 물의 양 계산
        for(int i = 0; i <= maxHeightIndex; i++) {
            if(left_height <= height[i]) {
                // 만약 현재 만난 벽이 저장된 벽 보다 더 크다면, stack에 값을 빼서 answer에 저장
                left_height = height[i];
                for(int cnt = 0; cnt < length; cnt++) {
                    answer += stack.pop();
                }
                length = 0;
            } else {
                // 현재 벽의 길이가 이전에 저장한 벽 길이보다 작다면, 채워질 공간이 생기는 격이므로, stack에 값을 추가
                length += 1;
                stack.push(left_height - height[i]);
            }
        }

        // 지나온 길이 얼마인지 모르지만, 0으로 초기화
        length = 0;

        // 오른쪽 부터 시작해서 가장 높은 벽 까지 채워지는 물의 양 계산
        for(int i = height.length - 1; i >= maxHeightIndex; i--) {
            if(right_height <= height[i]) {
                // 만약 현재 만난 벽이 저장된 벽 보다 더 크다면, stack에 값을 빼서 answer에 저장
                right_height = height[i];
                for(int cnt = 0; cnt < length; cnt++) {
                    answer += stack.pop();
                }
                length = 0;
            } else {
                // 현재 벽의 길이가 이전에 저장한 벽 길이보다 작다면, 채워질 공간이 생기는 격이므로, stack에 값을 추가
                length += 1;
                stack.push(right_height - height[i]);
            }
        }
        
        // 최종 계산된 값을 리턴
        return answer;
    }

    /*
     * 더 빠른 풀이
     * two pointer를 사용하였으며, 한번의 반복문으로 계산하였다.
     */
    public int trap_faster_version(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int answer = 0;
        
        // 배열을 한번만 순회하기 위해 left < right 조건이 만족할때만 진행
        while(left < right) {
            if(height[left] < height[right]) {
                // 만약 left이 길이가 right위치의 길이보다 작다면, 가장 큰 벽이 height[right]라고 가정하고
                if(maxLeft > height[left]) {
                    // maxLeft 값이 left 위치의 길이보다 크다면, left는 계속 진행되는 값으로
                    // maxLeft - height[left] 길이만큼의 공간에 물이 채워진다. 따라서 아래와 같이 처리
                    answer += maxLeft - height[left];
                } else {
                    // left 위치의 값이 maxLeft 보다 크거나 같다면, 채워질 공간은 없으므로 maxLeft를 left 위치로 갱신한다.
                    maxLeft = height[left];
                }
                // left를 +1 움직인다. => 오른쪽으로 이동(왼쪽 끝에서 시작하니까)
                left += 1;
            } else {
                // 만약 left이 길이가 right위치의 길이보다 크거나 같다면, 가장 큰 벽이 height[left]라고 가정하고
                if(maxRight > height[right]) {
                    // maxRight 값이 right 위치의 길이보다 크다면, right는 계속 진행되는 값으로
                    // maxRight - height[right] 길이만큼의 공간에 물이 채워진다. 따라서 아래와 같이 처리
                    answer += maxRight - height[right];
                } else {
                    // right 위치의 값이 maxRight 보다 크거나 같다면, 채워질 공간은 없으므로 maxRight를 right 위치로 갱신한다.
                    maxRight = height[right];
                }
                // right를 -1 움직인다. => 왼쪽으로 이동(오른쪽 끝에서 시작하니까)
                right -= 1;
            }
        }
        
        return answer;
    }
}