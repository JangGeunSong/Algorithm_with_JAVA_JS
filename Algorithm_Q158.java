import java.util.*;

public class Algorithm_Q158 {
    /*
     * 이 문제는 지문에 대한 이해가 완벽하게 이루어 지면 그 다음에 곧바로 구현만 하면 되는 문제다.
     * 큐를 활용하면 되며, 작은 index가 승리한다는 점에 비추어 볼때 이 문제는 greedy 문제이다.
     * https://leetcode.com/problems/dota2-senate/
     * 풀이를 확인해보자.
     */

    public String predictPartyVictory(String senate) {
        // 이 문제는 문제의 지문을 정확히 읽어도 이해가 되지 않는다.
        // 명확한 룰을 이해 했을 떄 쉽게 해결될 수 있으므로, 규칙에 대해 다시 정의하자면
        // 1. R은 R팀으로 D는 D 팀으로 큐에 쌓는다.
        // 2. 각 큐에 들어간 순서대로 대결을 펼칠때 다음과 같은 사람이 승자가 된다.
        // 3. 만약 R과 D가 승부를 볼 때 더 먼저 앞에 있던 사람 즉, 인덱스가 더 작은 사람이 승리한다.
        // 4. 이후 이 승리한 사람은 다시 각자의 팀의 맨 뒤로 가게 되므로, 인덱스가 현재 팀에 속해있는 모든 사람들 보다
        //    더 큰 숫자로 indexing 해서 팀에 다시 소속 된다.
        // 즉, 0번 인덱스 로 이긴 사람이 다시 자기 팀으로 간다면, 만약 10까지 index가 올라가 있었을 때 11 인덱스를 달고
        // 다시 자기 팀의 맨 뒤로 간다는 뜻이다.
        Queue<Integer> radQueue = new LinkedList<>();
        Queue<Integer> dirQueue = new LinkedList<>();
        int idx = 0;
        char[] arr = senate.toCharArray();

        // 각 단어들을 인덱스로 표현한 후 해당 인덱스 값을 큐에 삽입
        for(idx = 0; idx < arr.length; idx++) {
            if(arr[idx] == 'R') {
                radQueue.offer(idx);
            } else {
                dirQueue.offer(idx);
            }
        }

        // 확실하게 뒤의 인덱스임을 명시 하기 위해 1을 더해주고
        idx += 1;

        // 두 큐 중 하나라도 비어지기 전 까지 반복문을 돈다.
        while(!radQueue.isEmpty() && !dirQueue.isEmpty()) {
            // 만약 dir 팀의 가장 앞에 있는 사람이 승리할 때
            if(radQueue.peek() > dirQueue.peek()) {
                // 두 팀의 큐를 하나씩 poll 하고 dir 팀에 맨 뒤에 사람이 선다
                radQueue.poll();
                dirQueue.poll();
                dirQueue.offer(idx);
            } else {
                // 반대의 경우 rad 팀의 뒤에 선다
                radQueue.poll();
                dirQueue.poll();
                radQueue.offer(idx);
            }
            // 그리고 인덱스를 1 증가 시켜 준다.
            idx += 1;
        } 

        // 모든 과정이 끝나면 반드시 큐가 비어있지 않은 팀이 존재 하게 되는데, 이때 rad팀이 텅 비면 Dire팀 승리
        // 반대의 경우 Radiant 팀의 승리로 처리한다.
        if(radQueue.isEmpty()) {
            return "Dire";
        } else {
            return "Radiant";
        }
    }
}
