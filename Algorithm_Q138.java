public class Algorithm_Q138 {
    /*
     * 이 문제는 이진 탐색을 통해 원하는 결과에 도달 하거나, 혹은 prefix sum을 통해 원하는 결과에 도달할 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimize-maximum-of-array/
     * 풀이를 확인해보자.
     */

    // 이진 탐색을 통한 풀이
    // https://leetcode.com/problems/minimize-maximum-of-array/solutions/3381375/easy-solutions-in-java-python-and-c-look-at-once-with-exaplanation/
    // 위 경로 참고
    public int minimizeArrayValue(int[] nums) {
        // 이진 탐색을 위한 변수 선언
        int left = 0;
        int right = -1;
        int mid = 0;

        // right는 배열 중 최대 값으로 잡아야 하므로, 이를 찾기 위한 처리
        for(int n : nums) {
            right = Math.max(right, n);
        }

        // 배열 요소의 0과 최대 값 사이에 이진 탐색 시작
        while(left < right) {
            // 중앙값을 하나 얻어주고
            mid = (left + right) / 2;
            // 만약 현재 중앙값이 이 배열들에서 조정이 가능한 값이면 해당 값보다 작은 값도 조정 가능할 수 있다는 것이므로
            // 최대값 중 가장 큰 값으로 보고 right에 조정
            if(isMovable(nums, mid)) {
                right = mid;
            } else {
                // 만약 조정이 안된다면, mid 보다는 큰 값으로 조정이 되어야 한다는 것이므로 현재 값을 left로 조정하되
                // mid는 안되는것을 확인 했으니 +1 을 한다.
                left = mid + 1;
            }
        }

        // 위 과정을 통해 얻은 left 값을 정답으로 리턴
        return left;
    }

    // 중앙값 midValue가 있을 때, 
    public boolean isMovable(int[] nums, int midValue) {
        // midValue로 nums 의 요소가 1씩 빼기 위해서 총 차감해야 하는 step의 수가 현재 가능한 step의 수와
        // 비교했을 때, 계산 가능한지 여부를 통해 true / false를 체크해야 한다.
        // 이때 중앙값보다 큰 값은 1을 차감하기 위해 step을 소모하지만, 중앙값 보다 작거나 같은 값은
        // 1을 더해도 중앙값 까지 도달 하기 위해서 step을 더 밟아 줄 수 있다.
        // 이를 활용해 아래 로직을 만들어 준다.
        // 이를 위한 whole step 변수 선언
        long wholeStep = 0;

        // 모든 요소에 대해
        for(int number : nums) {
            if(number <= midValue) {
                // 만약 현재 요소의 값이 중앙값으로 들어온 값 보다 작거나 같다면
                // 이 숫자는 뺄셈을 할 필요가 없으므로, 중앙값 - 현재 숫자 만큼의 1 더하기가 가능하다.
                // 따라서 whole step에 현재 중앙값 - 현재 요소의 값 으로 step수를 추가
                wholeStep += (midValue - number);
            } else {
                // 만약 현재 요소가 중앙값 보다 크다면 일단 중앙값 만큼 빼기 위해 step을 밟아야 한다.
                // 여기서 아래 분기를 진행
                if(wholeStep < number - midValue) {
                    // 만약 총 step의 수가 현재 요소의 값을 중앙값으로 만들기 위한 step 수 보다 모자라다면,
                    // movable 하지 않으므로 false
                    return false;
                } else {
                    // 위와는 달리 그게 아니라면, 현재 총 step 값에서 이동해야 하는 step 값을 뺸다
                    wholeStep -= (number - midValue);
                }
            }
        }

        // 모든 반복문이 중앙값으로 조정이 가능하므로, true 리턴
        return true;
    }
}
