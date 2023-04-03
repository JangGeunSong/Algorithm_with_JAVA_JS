import java.util.*;

public class Algorithm_Q136 {
    /*
     * 이 문제는 2 포인터와 정렬을 활용하여 답을 얻는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/boats-to-save-people/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int numRescueBoats(int[] people, int limit) {
        // 배에 탈 사람의 무게를 가늠하기 위해 일단 오름차순으로 정렬 진행
        Arrays.sort(people);
        // 정답과 현재 배에 태울 수 잇는 사람을 체킹하기 위해서 2개의 포인터를 활용
        int answer = 0;
        int left = 0;
        int right = people.length - 1;

        // left와 right가 완전히 교차하기 전 까지 진행
        while(left <= right) {
            // case 1 왼쪽 끝과 오른쪽 끝 사람의 무게의 합이 limit을 넘을 때
            if(people[left] + people[right] > limit) {
                // 맨 오른쪽 끝에 사람만 태우고 출발 후 오른쪽 끝 인덱스를 1 줄인다.
                right -= 1;
                answer += 1;
            } else {
                // case 2 왼쪽 끝과 오른쪽 끝 두 사람의 합이 limit에 도달하거나 혹은 작을 때
                // 2 사람이 무조건 타는 케이스 이므로, left와 right를 1씩 올리거나 줄이고 answer를 늘린다.
                left += 1;
                right -=1;
                answer += 1;
            }
        }

        // 위 반복문인 left와 right 가 같을때에도 작동하기 때문에 두 값이 같던 크던 무조건 answer가 1은 더 올라가므로
        // 답이 만들어 진다.
        return answer;
    }
}
