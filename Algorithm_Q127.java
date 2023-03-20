public class Algorithm_Q127 {
    /*
     * 이 문제는 배열을 1회만 순회하면서 조건에 맞는 상황일 때의 처리를 묻는 문제이다.
     * edge case들에 대해 주의 하며 풀이하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/can-place-flowers/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int left = 0;
        int curr = 0;
        int right = 0;
        int size = flowerbed.length;

        if(n == 0) {
            return true;
        }

        // edge case 1 size가 1 일때
        if(size == 1) {
            // 해당 위치가 0 이면 n 이 0, 1 어떤 값이든 놓을 수 있으므로 true
            if(flowerbed[0] == 0) {
                return true;
            } else {
                return false;
            }
        }

        // edge case 2 size가 2 일때
        if(size == 2) {
            // 만약 양쪽이 모두 0 이면
            if(flowerbed[0] == 0 && flowerbed[1] == 0) {
                // n이 0 혹은 1 일때 어디든 놓을 수 있으므로 true
                if(n == 0 || n == 1) {
                    return true;
                } else {
                    // 이 외에는 false
                    return false;
                }
            } else {
                // 양쪽 중 하나라도 심어져 있을 때
                if(n > 0) {
                    // n 이 1보다 크면 무조건 false
                    return false;
                } else {
                    // n 이 0이면 true로 처리
                    return true;
                }
            }
        }
        
        // 위 edge case 이외에는 아래 로직을 진행
        for(int i = 0; i < flowerbed.length; i++) {
            // 왼쪽 현재 오른쪽 을 각각 받고 모두 0 이면 n을 빼면서 i 위치에 1로 교체
            left = i == 0 ? 0 : flowerbed[i - 1];
            curr = flowerbed[i];
            right = i == size - 1 ? 0 : flowerbed[i + 1];

            if(left == 0 && curr == 0 && right == 0) {
                flowerbed[i] = 1;
                n -= 1;
            }

            // 만약 n 이 0이 되면 true
            if(n == 0) {
                return true;
            }
        }

        // 모든 반복문을 돈 후 n 이 0 이 되었다면, 모든 꽃을 심은 것이므로, true 아니면 false
        return n == 0 ? true : false;
    }
}
