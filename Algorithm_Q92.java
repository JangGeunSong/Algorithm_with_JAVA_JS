import java.util.*;

public class Algorithm_Q92 {
    /*
     * 이 문제는 hash set을 사용해서 data stream을 주었을 때 원하는 조건에 맞는 배열을 전달하는 메서드를 설계하는 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/data-stream-as-disjoint-intervals/
     * 풀이를 확인해보자.
     */

    /**
     * Your SummaryRanges object will be instantiated and called as such:
     * SummaryRanges obj = new SummaryRanges();
     * obj.addNum(value);
     * int[][] param_2 = obj.getIntervals();
     */
    
    // 나의 풀이
    class SummaryRanges {

        // 해시셋을 통해 같은 데이터가 또 들어와도 하나의 elem으로만 처리할 수 있도록 한다.
        private HashSet<Integer> streamSet;
    
        public SummaryRanges() {
            streamSet = new HashSet<>();    
        }
        
        public void addNum(int value) {
            // hashSet을 통해 데이터를 삽입한다.
            streamSet.add(value);
        }
        
        public int[][] getIntervals() {
            List<Integer> list = new ArrayList<>(streamSet);
    
            Collections.sort(list);
    
            List<int[]> intervals = new ArrayList<>();
            int cnt = 0;
            for(int i = 0; i < list.size(); i++) {
                if(intervals.size() == 0) {
                    int[] element = new int[2];
                    element[0] = list.get(i);
                    element[1] = list.get(i);
                    intervals.add(element);
                } else {
                    int[] intervalElem = intervals.get(cnt);
                    if(intervalElem[1] + 1 == list.get(i)) {
                        // stream에 해당되는 케이스
                        intervalElem[1] = list.get(i);
                        intervals.set(cnt, intervalElem);
                    } else {
                        intervalElem = new int[2];
                        intervalElem[0] = list.get(i);
                        intervalElem[1] = list.get(i);
                        intervals.add(intervalElem);
                        cnt += 1;
                    }
                }
            }
            // 반복문을 돌면서 생성된 list를 int[][] 로 변환해서 리턴한다.
            int[][] result = new int[intervals.size()][2];
    
            for(int i = 0; i < intervals.size(); i++) {
                result[i] = intervals.get(i);
            }
    
            return result;
        }
    }
}
