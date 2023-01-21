import java.util.*;

public class Algorithm_Q89 {
    /*
     * 이 문제는 문자열과 백트래킹을 사용하는 문제다.
     * 나의 경우 hashset을 통해 중복요소를 제거하는 방식을 활용해 문제를 풀었다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/restore-ip-addresses/
     * 풀이를 확인해보자.
     */

    // 나의 풀이 코드
    public List<String> restoreIpAddresses(String s) {
        List<String> answer = null;
        HashSet<String> set = new HashSet<>();

        // 정답을 찾기위한 backtracking 진행
        ipCheck(s, 0, set);

        // 얻어낸 set 결과를 list로 담는다.
        answer = new ArrayList<>(set);

        return answer;
    }

    public void ipCheck(String curAddress, int dotNum, HashSet<String> set) {
        // 종료조건 1 dot 이 3개가 되면 종료
        if(dotNum == 3) {
            // 특수문자로 자르려면 \\ 가 필수
            String[] addressElements = curAddress.split("\\.");
            if(addressElements.length == 4) {
                for(String elem : addressElements) {
                    // 만약 . 을 기준으로 잘랐는데 그게 "" 이면 처리불가능 하므로, pass
                    if(elem.equals("")) {
                        return;
                    }
                    // 01, 001 이런식의 값은 무조건 invalid 이므로, 이를 처리하기 위한 로직
                    if(elem.length() >= 2 && elem.charAt(0) == '0') {
                        return;
                    }
                    // 각 IP 요소의 허용되는 값은 길이가 0보다 크고 4보다 작아야 하며
                    if(elem.length() > 4) {
                        return;
                    }
                    // 0보다는 크고, 256보다 작아야 함
                    if(255 < Integer.parseInt(elem)) {
                        return;
                    }
                }
                // 모든 조건을 충족하면 정답이므로, set에 추가
                set.add(new String(curAddress));
            }
            return;
        }

        String str = "";
        for(int i = 1; i < curAddress.length(); i++) {
            str = curAddress.substring(0, i) + "." + curAddress.substring(i);
            ipCheck(str, dotNum + 1, set);
        }
    }

    // 더 짧은 풀이 => hash set 없이 풀이 진행
    public List<String> restoreIpAddresses_short(String s) {
        List<String> ans = new ArrayList<String>();
        recurse(s, ans, 0, "", 0);
        return ans;
    }
    private void recurse(String curr, List<String> ans, int index, String temp, int count) {
        if (count > 4)
            return;
        if (count == 4 && index == curr.length())
            ans.add(temp);
        for (int i=1; i<4; i++) {
            if (index+i > curr.length()){
                break;
            }
            String s = curr.substring(index,index+i);
            if ((s.startsWith("0") && s.length()>1) || (i==3 && Integer.parseInt(s) >= 256)){
                continue;
            }
            recurse(curr, ans, index+i, temp+s+(count==3?"" : "."), count+1);
        }
    }
}
