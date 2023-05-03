import java.util.*;

public class Algorithm_Q157 {
    /*
     * 이 문제는 배열을 hash set or table에 삽입한 후 풀이하는 문제다.
     * https://leetcode.com/problems/find-the-difference-of-two-arrays/
     * 풀이를 확인해보자.
     */

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        // 중복을 없에고 단 한번만 들어갈 수 있도록 만드는 자료구조 set을 선언
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        // 정답 이중 리스트 선언
        List<List<Integer>> answer = new ArrayList<>();

        // 첫번째, 두번째 배열의 리스트를 선언
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // 각 set에 배열들의 숫자를 모두 담는다 이때 hashset의 성질로 여러번 들어간 원소는 한번만 들어가는 것으로 확인가능
        for(int num : nums1) {
            set1.add(num);
        }

        for(int num : nums2) {
            set2.add(num);
        }

        // 이제 각 set을 iterator를 돌면서
        Iterator<Integer> itr = set1.iterator();

        while(itr.hasNext()) {
            int a = itr.next();

            // 상대방 set에 존재하지 않는 경우에만 리스트에 삽입한다.
            if(!set2.contains(a)) {
                list1.add(a);
            }
        }

        itr = set2.iterator();

        while(itr.hasNext()) {
            int a = itr.next();

            // 상대방 set에 존재하지 않는 경우에만 리스트에 삽입한다.
            if(!set1.contains(a)) {
                list2.add(a);
            }
        }

        // 얻은 결과를 answer에 삽입
        answer.add(list1);
        answer.add(list2);

        return answer;
    }
}
