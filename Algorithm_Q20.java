import java.util.*;

public class Algorithm_Q20 {
    /*
     * 해당 문제는 백트래킹 문제이다. 백 트래킹을 하기 위해서는 DFS, BFS 등의 방법을 활용하여 풀이를 진행한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
     * 이 문제는 DFS를 통해 풀이하면 되는 문제인데, 내가 푼 버전은 반복문을 통해 해결하는 방법이며, 재귀를 통해 풀이하면 코드가 더 쉽게 보일것이다.
     * 풀이를 확인해보자.
     */ 

    /*
     * 반복문을 통해 DFS를 처리하였다.
     * 해당 풀이를 활용하면, 하나의 함수로 처리가능하여 사용자 입장에서는 편리하다.
     * 하지만 구현 자체가 복잡하기 때문에, 재귀를 통한 풀이가 더 쉬울수도 있으므로, 주의해서 푸는것이 좋다.
     */
    public List<String> letterCombinations_my_solution(String digits) {
        List<String> answer = new ArrayList<>();
        List<List<String>> digitList = new ArrayList<>();

        // edge case => 만약 문자열의 길이가 0이라면 아무것도 입력 안한 것이므로, 바로 answer를 return
        if(digits.length() == 0) {
            return answer;
        }

        char digit = 'a';

        // 각 번호를 누를때 표시할 수 있는 알파벳
        for(int i = 0; i < 10; i++) {
            if(i == 0 || i == 1) {
                // 2번 부터 문자가 드러나기 때문에, 직관적으로 아래 4중 for문을 돌아 DFS를 진행하기 위해
                // 0, 1 일때는 null로 가상 공간 할당해서 넣어주기
                digitList.add(null);
                continue;
            }
            List<String> list = new ArrayList<>();
            if(i == 7 || i == 9) {
                for(int j = 0; j < 4; j++) {
                    list.add(Character.toString(digit));
                    digit = (char) (digit + 1);
                }
            } else {
                for(int j = 0; j < 3; j++) {
                    list.add(Character.toString(digit));
                    digit = (char) (digit + 1);
                }
            }
            digitList.add(list);
        }

        // 각 누른 번호에 따른 알파벳 리스트를 받아서 진행 해야 함.
        List<String> list1 = digitList.get(digits.charAt(0) - '0');
        String element = "";

        // for Each 문을 통해 반복
        for(String elementList1 : list1) {
            element += elementList1;
            if(digits.length() == 1) {
                answer.add(element);
                // 만들어진 결과물이 answer에 들어갔으므로, 새로 입력했던 한글자는 삭제 처리
                // 이후 진행되는 아래 로직들 전부 이런식으로 진행
                element = element.substring(0, 0);
            } else {
                List<String> list2 = digitList.get(digits.charAt(1) - '0');

                for(String elementList2 : list2) {
                    element += elementList2;
                    if(digits.length() == 2) {
                        answer.add(element);
                        element = element.substring(0, 1);
                    } else {
                        List<String> list3 = digitList.get(digits.charAt(2) - '0');

                        for(String elementList3 : list3) {
                            element += elementList3;

                            if(digits.length() == 3) {
                                answer.add(element);
                                element = element.substring(0, 2);
                            } else {
                                List<String> list4 = digitList.get(digits.charAt(3) - '0');

                                for(String elementList4 : list4) {
                                    element += elementList4;

                                    answer.add(element);
                                    element = element.substring(0, 3);
                                    // 아래 1, 2, 3번째 까지는 바로앞의 문자를 지우기 위해 for 문 안의 else 범위 내부에서
                                    // 문자 처리를 진행한다.
                                    // 다만 맨 마지막인 4번째에는 그냥 for문 안에서 바로 처리되므로, 
                                    // 중괄호 갯수가 맨 마지막만 제외하고는 밑에 2개씩 들어 있어야 한다.
                                }
                                element = element.substring(0, 2);
                            }
                        }
                        element = element.substring(0, 1);
                    }
                }
                element = element.substring(0, 0);
            }
        }
        
        return answer;
    }

    /*
     * 재귀를 사용한 풀이
     */
    public List<String> letterCombinations_recursion(String digits) {
        List<String> answer = new ArrayList<String>();
        List<List<String>> letters = new ArrayList<List<String>>();
        
        String msg = "";
        
        if(digits.equals("")) {
            return answer;
        }
        
        int cnt = 0;
        
        for(int i = 0; i < 8; i++) {
            letters.add(new ArrayList<String>());
            if(i == 5 || i == 7) {
                letters.get(i).add(Character.toString('a' + cnt));
                cnt++;
                letters.get(i).add(Character.toString('a' + cnt));
                cnt++;
                letters.get(i).add(Character.toString('a' + cnt));
                cnt++;
                letters.get(i).add(Character.toString('a' + cnt));
                cnt++;
            } else {
                letters.get(i).add(Character.toString('a' + cnt));
                cnt++;
                letters.get(i).add(Character.toString('a' + cnt));
                cnt++;
                letters.get(i).add(Character.toString('a' + cnt));
                cnt++;
            }
        }
        
        findMessage(msg, 0, digits, letters, answer);
        
        return answer;
    }
    
    /*
     * DFS의 재귀 처리 버전 => 위의 반복문을 통한 구현보다는 더 쉽게 풀이된다.
     * 하지만, 종료 조건에 대한 명확한 이해가 없다면, 무한 반복을 돌거나 예상하지 못한 결과물로 떨어질 수 있으므로, 풀이에 주의가 필요하다.
     */
    public void findMessage(String msg, int length, String digits, List<List<String>> letters, List<String> answer) {
        if(digits.length() == length) {
            answer.add(msg);
            return;
        }
        
        int digit = digits.charAt(length) - '2';
        List<String> letter = letters.get(digit);
        for(int i = 0; i < letter.size(); i++) {
            msg += letter.get(i);
            findMessage(msg, length + 1, digits, letters, answer);
            msg = msg.substring(0, msg.length() - 1);
        }
    }
}
