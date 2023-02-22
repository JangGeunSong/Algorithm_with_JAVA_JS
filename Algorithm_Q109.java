public class Algorithm_Q109 {
    /*
     * 이 문제는 배열만을 활용한 이진 탐색 문제 그 두 번째이다.
     * 해당 문제도 역시 조건을 어떻게 이해하고 탐색을 해야 하는가에 대해 아이디어가 없어 어려웠다.
     * 이진 탐색은 단순히 index에 대한 이동만 생각 했는데, 사실 탐색이라는 것은 어떤 범위 안에서 움직이는 것이다.
     * 즉, 크기에 따라서 이동하고 움직이는 것을 생각하며 접근한다면 이 문제가 더 쉽게 풀리게 된다
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int shipWithinDays(int[] weights, int days) {
        // 해당 풀이는 아이디어를 보고 구현 하였다. => 아래 탐색에 대한 개념을 잡지 못해 풀기 어려웠다.
        // 이진 탐색 => 배열의 좌, 우 만 생각하는게 아니라, 값의 범위를 작은값 to 큰 값으로 생각할 수도 있다.
        // 탐색 범위의 최소값 => 현재 배열의 원소 중 가장 큰 값, 탐색 범위의 최대값 => 모든 원소의 합
        int maxLuggage = Integer.MIN_VALUE;
        int totalLuggage = 0;
        // 현재 값과 현재 day 를 저장하는 값
        int currLuggage = 0;
        int curDay = 0;

        // 탐색 범위를 얻기 위한 계산 과정
        for(int i = 0; i < weights.length; i++) {
            maxLuggage = Math.max(maxLuggage, weights[i]);
            totalLuggage += weights[i];
        }

        // 실제 이진 탐색을 진행
        while(maxLuggage < totalLuggage) {
            // 현재 일자와 현재 무게를 0으로 초기화
            curDay = 0;
            currLuggage = 0;

            // 무게를 탐색해 나간다 => 순서대로 진행
            for(int i = 0; i < weights.length; i++) {
                currLuggage += weights[i];
                // 현재 무게를 갱신하고 있는 도중에 최대 무게보다 더 커지면
                if(currLuggage > maxLuggage) {
                    // 일자를 하루 늘리고, 현재 무게를 지금 짐의 무게로 바꾼다.
                    curDay += 1;
                    currLuggage = weights[i];
                } else if(currLuggage == maxLuggage) {
                    // 만약 최대 무게와 같다면, 일자를 하루 늘리고 현재 무게를 0으로 바꾼다.
                    curDay += 1;
                    currLuggage = 0;
                }
            }

            // 아직 남은 짐들이 더 있을 수 있으므로, 현재 무게가 0보다 크다면 +1일을 더한다 
            // => 예를 들어 4일 지나고 나서 현재 무게가 아직 0이 아니라면 1일 더 움직여야 하므로, 이에 대해서 1을 더한다.
            if(currLuggage > 0) {
                curDay += 1;
            }
            
            // 현재 지난 일자가 주어진 일자보다 작거나 같으면 이 무게가 최적화 된 값이므로, 반복문을 중단
            if(curDay <= days) {
                break;
            }

            // 위 조건에 맞지 않는다면 최소 가능 범위에서 1을 증가 시킨 후 다시 탐색을 진행한다.
            maxLuggage += 1;
        }

        // 결과인 maxLuggage를 반환한다.
        return maxLuggage;
    }
}
