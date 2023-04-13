import java.util.*;

public class Algorithm_Q143 {
    /*
     * 이 문제는 stack을 활용한 문제다.
     * 정상적인 stack의 연산 과정과 순서가 같은지 물어보는 문제로 이에 대한 처리 방법에 대해 고민하며 풀이를 진행해야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/validate-stack-sequences/
     * 풀이를 확인해보자.
     */

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // stack에 데이터를 쌓기 위해 stack 선언
        Stack<Integer> stack = new Stack<>();
        // 푸쉬한 위치와 팝을 한 위치를 얻기 위해 index를 선언
        int pushedIndex = 0;
        int poppedIndex = 0;
        
        // 두 index 중 하나라도 배열의 크기보다 커지기 전 까지 아래 로직을 진행
        while(pushedIndex < pushed.length && poppedIndex < popped.length) {
            // 만약 stack이 비어 있다면
            if(stack.isEmpty()) {
                // stack에 값을 하나 push 처리한다.
                stack.push(pushed[pushedIndex]);
                pushedIndex += 1;
                // 이후 pop 해야할 값과 stack의 peek값이 같다면 곧바로 pop 처리한다.
                if(stack.peek() == popped[poppedIndex]) {
                    stack.pop();
                    poppedIndex += 1;
                }
            } else {
                // 스택이 비어 있지 않다면
                if(stack.peek() == popped[poppedIndex]) {
                    // 현재 스택의 peek가 지금 pop 하는 인덱스의 값과 같으면 pop 처리 후 poppedIndex를 1 더한다.
                    stack.pop();
                    poppedIndex += 1;
                } else {
                    // 그게 아니라면 그냥 push를 곧바로 한번 더 진행한다.
                    stack.push(pushed[pushedIndex]);
                    pushedIndex += 1;
                }
            }
        }

        // 만약 pop 인덱스가 아직 배열 크기보다 작으면
        if(poppedIndex < popped.length) {
            // 배열을 현재 팝 인덱스 부터 진행하면서 peek와 비교하여 같지 않으면 바로 false 처리
            for(int k = poppedIndex; k < popped.length; k++) {
                if(stack.peek() == popped[k]) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        // 모든 로직이 처리되면 true로 정상 stack 처리 되었음을 알린다.
        return true;
    }
}
