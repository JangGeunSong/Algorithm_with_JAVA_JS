import java.util.*;

public class Algorithm_Q146 {
    /*
     * 이 문제는 간단한 배열 처리 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
     * 풀이를 확인해보자.
     */

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        // 사탕을 가진 아이들 중 가장 많은 사탕의 갯수를 구하고 정답 리스트를 선언해 초기화
        int greatestCandies = -1;
        List<Boolean> answer = new ArrayList<>();

        // 모든 사탕을 돌면서 최대값을 구한다.
        for(int candy : candies) {
            greatestCandies = Math.max(greatestCandies, candy);
        }

        // 모든 사탕을 다시 돌면서 추가 사탕을 주었을때 최대값 이상이면 true 아니면 false를 answer에 추가
        for(int candy : candies) {
            if(candy + extraCandies >= greatestCandies) {
                answer.add(true);
            } else {
                answer.add(false);
            }
        }

        // 답을 리턴한다.
        return answer;
    }
}
