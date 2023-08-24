import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm_Q203 {
    /*
     * 하라는 대로만 구현하면 되는 문제이지만, 수 많은 edge case가 괴롭히는 고통스러운 문제다.
     * hard 난이도 중에서는 처음으로 혼자서 최대한 고민해보고 풀이에 성공 했다.
     * https://leetcode.com/problems/text-justification/
     * 풀이를 확인해보자.
     */
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> answer = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int currSize = 0;
        int line = 0;
        List<Integer> li = null;

        // 먼저 각 라인에 몇개의 단어가 들어갈 수 있는지 map을 통해서 index list를 담아
        // 해당 라인에 들어가야 하는 단어들을 그룹화 하여 묶는다.
        for(int i = 0; i < words.length; i++) {
            if(currSize + words[i].length() <= maxWidth) {
                if(map.get(line) == null) {
                    li = new ArrayList<>();
                    li.add(i);
                    map.put(line, li);
                    currSize += words[i].length() + 1;
                } else {
                    li = map.get(line);
                    li.add(i);
                    currSize += words[i].length() + 1;
                    map.put(line, li);
                }
            } else {
                line += 1;
                li = new ArrayList<>();
                li.add(i);
                map.put(line, li);
                currSize = words[i].length() + 1;
            }
        }

        line = 0;

        // 이제 모든 line의 그룹을 순회하면서 정답을 만들어 나간다/
        while(map.get(line) != null) {
            li = map.get(line);

            int charSize = 0;
            int needSpace = 0;

            // 일단 현재 단어들이 점유하게 된 공간을 계산하여 필요한 공백의 수를 구한다.
            for(int i = 0; i < li.size(); i++) {
                String str = words[li.get(i)];

                charSize += str.length();
            }

            needSpace = maxWidth - charSize;

            // 만약 마지막 줄 이라면 단어들 사이에는 한칸씩의 공백만 넣고 나머지 공백을 전부 이후 남은 공간에
            // 전부 투자하도록 만든다.
            // 이때도 공백이 더 만들어 지지 않도록 조심한다.
            if(map.get(line + 1) == null) {
                String ans = "";
                for(int i = 0; i < li.size(); i++) {
                    if(needSpace != 0) {
                        ans += words[li.get(i)] + " ";
                        needSpace -= 1;
                    } else {
                        ans += words[li.get(i)];
                    }
                }

                if(needSpace > 0) {
                    ans += new String(new char[needSpace]).replace('\0', ' ');
                } 

                answer.add(ans);

                line += 1;
                continue;
            }

            // 그게 아니라면 이제 각 단어들 사이의 공백을 균일하게 만들기 위한 최소 필요 공백의 길이와
            // 추가로 + 1칸을 더 하기 위한 나머지 공백의 갯수를 구한다.
            String ans = "";

            int needMinimumSpace = 0;
            int restOfMinimumSpace = 0;

            // 이제 현재 단어들이 2개로 구성되어 있다면
            if(li.size() - 1 == 1) {
                // 그냥 needSpace 즉 필요 공백의 길이를 단어 사이에 넣어서 문장을 완성
                ans = words[li.get(0)] + new String(new char[needSpace]).replace('\0', ' ') + words[li.get(1)];
            } else if(li.size() - 1 == 0) {
                // 단어가 하나 뿐 이라면, 지금 단어를 먼저 입력한 후 뒤에 나머지 공백을 추가
                ans = words[li.get(0)] + new String(new char[needSpace]).replace('\0', ' ');
            } else {
                // 이 외에는 균일하게 단어 + 공백 + 단어 + 공백을 하는데
                // 이때 나머지는 공백이 들어갈 공간의 수 보다는 작은 것이므로(나눗셈 하여 나온 나머지 값이니까)
                // 각 첫번쨰 공백 사이에 + 1칸씩 공백을 추가하여 넣고 rest 값을 1 뺀다.
                // 이렇게 해서 rest가 값이 더 이상 없으면 추가 공백을 안넣고 String을 생성
                needMinimumSpace = needSpace / (li.size() - 1);
                restOfMinimumSpace = needSpace % (li.size() - 1);
                for(int i = 0; i < li.size() - 1; i++) {
                    ans += words[li.get(i)];
                    if(restOfMinimumSpace > 0) {
                        ans += new String(new char[needMinimumSpace + 1]).replace('\0', ' ');
                        restOfMinimumSpace -= 1;
                    } else {
                        ans += new String(new char[needMinimumSpace]).replace('\0', ' ');
                    }
                }
                ans += words[li.get(li.size() - 1)];
            }
            
            // 이제 이렇게 만든 문장이 각 line의 문장이 된다.
            answer.add(ans);

            // 다음 문장으로
            line += 1;
        }

        // 결과를 리턴한다.
        return answer;
    }
}
