public class Algorithm_Q119 {
    /*
     * 이 문제는 이진 탐색을 활용하여 푸는 문제다.
     * 어제 풀이했던 탐색 범위의 정의와 그 정의에 맞춰 탐색 범위를 줄이는 스킬들을 활용해 문제 해결에 접근해야 하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-time-to-complete-trips/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public long minimumTime(int[] time, int totalTrips) {
        // right 경계를 잡기 위한 maxTime 변수 선언
        int maxTime = 0;
        long left = 1L; 
        long right = 0L;
        long mid = 0L;

        // 주어진 버스의 시간 중 가장 길게 걸리는 시간을 얻어낸다.
        for(int t : time) {
            maxTime = Math.max(t, maxTime);
        }

        // 오른쪽 경계값 = 최대 걸리는 시간 * 총 여행 시간
        right = (long) maxTime * totalTrips;

        // left < right 조건에 맞춘 이진 탐색을 진행
        while(left < right) {
            // 중앙값 갱신 => 중앙값을 갱신하며 절반씩 탐색 범위가 줄어들기 때문에 이 문제는 이진 탐색이다.
            mid = (left + right) / 2;

            // 만약 현재 중앙값 시간을 time들로 나눌 때 합이 totalTrips 보다 크거나 같다면
            if(isEnoughTime(time, mid, totalTrips)) {
                // 오른쪽 경계값을 mid로 갱신
                right = mid;
            } else {
                // 그렇지 않다면 왼쪽 경계값을 mid + 1로 갱신
                // => why? mid는 이미 정답 검증에서 정답이 아님을 증명했거나 혹은 더 이상 고려 대상인 값이 아니기 때문
                left = mid + 1;
            }
        }
        
        // 이렇게 얻은 left 값을 리턴
        return left;
    }

    // 실제 여행 시간을 구하고, 주어진 여행 시간보다 더 걸렸는지 적게 걸렸는지 체크 하는 함수
    public boolean isEnoughTime(int[] time, long mid, int totalTrips) {
        long actualTrips = 0L;

        // 실제 여행 시간 = sum(걸리는 시간의 중앙값 / 현재 버스의 걸리는 시간)
        for(int t : time) {
            actualTrips += mid / t;
        }

        // 만약 실제 걸리는 여행 시간이 주어진 총 여행 시간보다 길거나 같다면 true 아니면 false를 처리
        // 왜냐하면 함수 이름이 충분한 시간인가? 의 결과를 얻는 함수이기 때문이다.
        return totalTrips <= actualTrips;
    }
}
