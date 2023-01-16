import java.util.*;

public class Algorithm_Q84 {
    /*
     * 배열의 요소값을 활용하여 겹치는 간선의 속성을 파악해 풀어야 하는 문제이다.
     * 생각할 부분이 있어 그림을 그려서 케이스를 만들어 풀어야 해결되는 문제다
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/insert-interval/
     * 풀이를 확인해보자.
     */

    // 풀이 코드 -> 해당 문제의 로직이 어떻게 전개되는가를 잘 이해하면 쉽게 문제에 해답에 도달할 수 있다.
    //              이러한 패턴은 자주 나올 수 있으므로 잘 확인하자.
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<List<Integer>> answer = new ArrayList<>();
        // a1, b1 => 매 간선의 시작과 끝점의 좌표
        // a2, b2 => 주어진 삽입 필요 간선 정보로 시작해서 겹쳐지는 순간 해당 겹치는 간선으로 변경되는 좌표 값
        int a1 = 0;
        int a2 = newInterval[0];
        int b1 = 0;
        int b2 = newInterval[1];
        // 삽입 되었는지 여부를 체크하는 변수
        boolean inserted = false;
        // 몇개의 이차원 배열이 나올지 알 수 없으므로, list를 통해서 변동되는 값으로 구현
        List<Integer> list = new ArrayList<>();
        // 모든 간선 정보를 순회
        for(int i = 0; i < intervals.length; i++) {
            // a1, b1을 가져온다.
            a1 = intervals[i][0];
            b1 = intervals[i][1];
            // 만약 a1이 b2 보다 크다면 겹쳐서 새롭게 만들어진 간선이 삽입되거나 혹은 현재 간선이 삽입될 차례다.
            // 즉, 아래와 같은 상황이다.
            // a2 ==== b2
            //            a1 ===== b1
            if(a1 > b2) {
                if(!inserted) {
                    // 아직 신규 간선이 삽입 되기 전 이라면 해당 신규 간선을 삽입
                    list.add(a2);
                    list.add(b2);
                    answer.add(list);
                    list = new ArrayList<>();
                    inserted = true;
                }
                // 신규 간선이 삽입된 후라면 이제 원레 간선이 삽입
                list.add(a1);
                list.add(b1);
                answer.add(list);
                list = new ArrayList<>();
            } else {
                // 해당 else로 온다면 다음과 같은 상황이다.
                // case 1
                //      a1 ===== b1
                //   a2 ===== b2
                // case 2
                //    a1 ===== b1
                //       a2 ====== b2
                // case 3
                //  a1 =========== b1
                //        a2 === b2
                // case 4
                //  a1 === b1
                // a2 ======= b2
                // case 5
                // a1 ====== b1
                //               a2 ======= b2
                if(a2 <= b1 && a1 <= b2) {
                    // 만약 두 간선이 겹치는 케이스라면 (case 1 ~ 4) 신규 간선의 좌표 갱신을 한다.
                    // a2 는 2개의 점 중 가장 작은 값
                    // b2 는 2개의 점 중 가장 큰 값
                    a2 = Math.min(a1, a2);
                    b2 = Math.max(b1, b2);
                } else {
                    // 이 외의 안 겹치는 상황이라면 (case 5) 그냥 이 간선을 정답에 삽입한다.
                    list.add(a1);
                    list.add(b1);
                    answer.add(list);
                    list = new ArrayList<>();
                }
            }
        }

        // 모든 순회가 종료된 후에도 아직 삽입 처리가 안되었다면 삽입할 간선을 정답에 넣는다.
        if(!inserted) {
            list = new ArrayList<>();
            list.add(a2);
            list.add(b2);
            answer.add(list);
        }

        // 결과 이차원 배열을 만들어 준다
        int[][] result = new int[answer.size()][2];

        // 리스트를 순회하며 리턴할 이차원 배열에 값을 채워준다.
        for(int i = 0; i < answer.size(); i++) {
            list = answer.get(i);
            result[i][0] = list.get(0);
            result[i][1] = list.get(1);
        }

        // 결과 리턴
        return result;
    }
}
