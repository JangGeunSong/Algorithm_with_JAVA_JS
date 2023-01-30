public class Algorithm_Q93 {
    /*
     * 이 문제는 간단한 DP 구현 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/n-th-tribonacci-number/
     * 풀이를 확인해보자.
     */

    public int tribonacci(int n) {
        if(n == 0) {
            return 0;
        }
        
        if(n == 1) {
            return 1;
        }

        if(n == 2) {
            return 1;
        }

        // n 번째를 구하라는 것은 0, 1, 2 ... n 으로 사실상 배열의 크기를 n + 1만큼 잡고 n 번째 원소를 구해야 한다.
        // DP를 통해 이전 값을 memozation 하면서 처리하면 연산량을 줄일 수 있다.
        int[] triArr = new int[n + 1];

        // 초기 알려진 값 세팅 진행
        triArr[0] = 0;
        triArr[1] = 1;
        triArr[2] = 1;

        // 계산 식에 의거하여 이전 값을 저장하면서 데이터 처리
        for(int i = 3; i <= n; i++) {
            triArr[i] = triArr[i - 1] + triArr[i - 2] + triArr[i - 3];
        }

        // n 번째 원소를 리턴 처리
        return triArr[n];
    }
    
}
