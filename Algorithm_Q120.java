public class Algorithm_Q120 {
    /*
     * 이 문제는 이진 탐색을 활용하여 푸는 문제다.
     * 2일간 풀이했던 이진 탐색을 활용한 문제 유형 3번째다 풀이를 잘 확인해 보자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/koko-eating-bananas/
     * 풀이를 확인해보자.
     */


    // 풀이 코드
    public int minEatingSpeed(int[] piles, int h) {
        // 이진 탐색을 통한 문제 풀이
        int left = 1;
        int right = Integer.MIN_VALUE;
        int mid = 0;
        // 위 left to right 사이에 값에서 선택된 시간당 바나나 소비량을 활용해 얻을 수 있는
        // 모든 바나나를 소비하는데 걸리는 시간을 담는 변수
        int consumeBananaTime = 0;

        // 최대로 먹을 수 있는 바나나의 수 경계값을 잡기 위한 loop
        for(int pile : piles) {
            right = Math.max(right, pile);
        }
        
        // 이진 탐색 시작
        while(left < right) {
            // 두 경계값의 중앙값을 잡는다.
            mid = (left + right) / 2;

            // 1 시간에 mid 개 의 바나나를 먹을 수 있다면, 이때 걸리는 시간을 계산
            consumeBananaTime = calculateSumOfConsume(piles, mid);    
            
            // 만약 계산한 바나나 소비 시간이 h보다 작거나 같다면 시간당 더 적은 바나나 소비량으로 맞춰야 한다.
            // 왜냐하면 minimum integer k 를 얻으라고 했기 때문이다. 즉, 최대 경계값인 right 갱신
            if(consumeBananaTime <= h) {
                right = mid;
            } else {
                // h 보다 크면 바나나 소비량을 더 늘려야 한다. 왜냐하면 주어진 시간안에 모든 바나나를 못 먹기 때문이다.
                // 따라서, 최소 소비량의 경계값인 left를 갱신
                left = mid + 1;
            }
        }
        
        // 모든 탐색을 마친 후 최소 경계값을 리턴
        return left;
    }

    // 1 시간에 mid 개 의 바나나를 먹을 수 있다면, 이때 걸리는 시간을 계산하는 함수
    public int calculateSumOfConsume(int[] piles, int mid) {
        int sum = 0;
        double temp = 0;

        // mid 개만큼 1 시간에 바나나를 먹을 수 있다면, 이때 모든 바나나를 먹는데 걸리는 시간 계산
        for(int pile : piles) {
            temp = (double) pile / (double) mid;

            // double 로 계산 했을 때 결과물을 int로 형 변환 해서 더하기 (정수형으로 남음)
            sum += (int) temp;

            if(pile % mid != 0) {
                // 바나나 더미가 mid로 나누어 떨어지지 않을 때 위의 temp 시간에 1시간 더 먹어야 한다.
                // 따라서 해당 조건에 진입하면 sum에 1을 더한다.
                sum += 1;
            }
        }

        return sum;
    }
}
