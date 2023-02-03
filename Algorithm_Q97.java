import java.util.*;

public class Algorithm_Q97 {
    /*
     * 이 문제는 문자열을 분리하여 원하는 형태로 만드는 구현 문제다
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/zigzag-conversion/
     * 풀이를 확인해보자.
     */
    
    public String convert(String s, int numRows) {
        // 문자열의 Map을 만들어서 체크
        Map<Integer, String> stringMap = new HashMap<>();
        String answer = "";
        // 현재 row가 어디인지 체크하기 위해 row 위치 값인 currRow와 row의 증감 방향을 체크할 변수를 추가
        int currRow = 0;
        boolean isIncreaseRow = true;

        // edge case numRows == 1 이면 그냥 원레 문자열 리턴
        if(numRows == 1) {
            return s;
        }

        // 모든 Map의 key에 대해 초기화
        for(int i = 0; i < numRows; i++) {
            stringMap.put(i, "");
        }

        // 모든 문자열을 돌면서 증/감 여부를 체크하고 이후 map에 현재 위치에 문자를 문자열에 하나 더한다.
        for(int i = 0; i < s.length(); i++) {
            if(currRow == 0) {
                isIncreaseRow = true;
            } else if(currRow == numRows - 1) {
                isIncreaseRow = false;
            }
            stringMap.put(currRow, stringMap.get(currRow) + String.valueOf(s.charAt(i)));
            // 이후 현재 증감 방향에 따라 현재 row위치를 지정한다.
            if(isIncreaseRow) {
                currRow += 1;
            } else {
                currRow -= 1;
            }
        }

        // map을 순회하면서 모든 문자열을 integer key 순서대로 더한다.
        for(Map.Entry<Integer, String> entry : stringMap.entrySet()) {
            answer = answer + entry.getValue();
        }

        // 만든 결과를 리턴
        return answer;
    }
}