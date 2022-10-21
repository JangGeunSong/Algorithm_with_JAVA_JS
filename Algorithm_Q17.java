public class Algorithm_Q17 {
    /*
     * 해당 문제는 greedy 문제이다. 2개의 pointer를 가지고 최대 넓이를 구하는 문제로 상대적으로 쉽게 풀이된다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/container-with-most-water/
     * 풀이를 확인해보자.
     */ 

    // 나의 풀이
    public int maxArea_mine(int[] height) {
        // 해당 풀이를 위해 눈에 보이는 가시성을 위해 가로길이 => n, 세로길이 => min_height 변수를 사용했다. 사실 이렇게 않하고 right - left 를 해도 아무 상관 없다.
        // min_height, n 을 연산하는 과정이 추가되면서 runtime이 더 작아지기 떄문에 조금 좋지 않을 수 있으나, 다른 사람들이 알아볼 수 있는 부분에서는 더 가독성이 좋다고 생각된다.
        
        // 2개의 pointer 값
        int left = 0;
        int right = height.length - 1;

        // 넓이 계산을 위한 값
        int n = height.length - 1;
        int min_height = 0;

        // return 할 값
        int answer = 0;
        
        while(left < right) {
            min_height = Math.min(height[left], height[right]);
            answer = Math.max(answer, n * min_height);
            if(height[left] <= height[right]) {
                n -= 1;
                left += 1;
            } else {
                n -= 1;
                right -= 1;
            }
        }

        return answer;
    }

    // 실행 시간이 더 빠른 버전
    public int maxArea_more_speedy_version(int[] height) {
        if(height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        
        // 위 코드와 달리 더 빠른 버전은 불필요한 변수 및 추가 연산 과정을 많이 줄여냈다.
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while(left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            
            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
}
