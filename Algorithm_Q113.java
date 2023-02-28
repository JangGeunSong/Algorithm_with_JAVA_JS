import java.util.*;

public class Algorithm_Q113 {
    /*
     * 이 문제는 해시맵과 트리를 사용하는 문제다.
     * 처음에는 이해가 되지 않아 조금 어렵다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/find-duplicate-subtrees/
     * 풀이를 확인해보자.
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 풀이 코드 -> 나의 풀이도 아래와 비슷 했지만, 틀린 답이어서 다시금 풀이 했다.
    // 틀린 이유를 찾아보자.
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // 해시 테이블을 통해 같은 패턴이 나타나는 트리에 대해서는 중복으로 보고 list에 한번만 담는다.
        // "한번만!!"
        List<TreeNode> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // 해싱을 통한 리스트 담기 과정 진행
        subTreeHasing(root, map, list);

        // 얻은 정답을 리턴
        return list;
    }

    public String subTreeHasing(TreeNode node, Map<String, Integer> map, List<TreeNode> list) {
        // 종료 조건 node가 null 이면 종료
        if(node == null) {
            return "#";
        }

        // 좌 우 자식의 패턴 스트링을 반환받음
        String left = subTreeHasing(node.left, map, list);
        String right = subTreeHasing(node.right, map, list);

        // ,를 통한 정답을 받는다.
        String identifier = node.val + "," + left + "," + right;

        // map에 없다면 0 + 1 있다면, 해당 값 + 1을 처리
        map.put(identifier, map.getOrDefault(identifier, 0) + 1);
        
        // 만약 해당 map에 값이 2가 되었다면
        if (map.get(identifier) == 2) {
            // 중복 패턴이므로, list에 삽입한다.
            list.add(node);
        }

        // 해당 스트링을 반환한다.
        return identifier;
    }

    // 나의 틀린 풀이
    public List<TreeNode> findDuplicateSubtrees_wrong(TreeNode root) {
        // 해시 테이블을 통해 같은 패턴이 나타나는 트리에 대해서는 중복으로 보고 list에 한번만 담는다.
        // "한번만!!"
        List<TreeNode> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // 해싱을 통한 리스트 담기 과정 진행
        subTreeHasing_wrong(root, map, list);

        // 얻은 정답을 리턴
        return list;
    }

    public void subTreeHasing_wrong(TreeNode node, Map<String, Integer> map, List<TreeNode> list) {
        // 종료 조건 node가 null 이면 종료
        if(node == null) {
            return;
        }

        // key로 받을 서브 트리의 문자열 패턴을 구한다.
        String key = subTreeString_wrong(node);

        // 만약 해시맵에 이 key를 가진 값이 존재한다면
        if(map.get(key) != null) {
            if(map.get(key) == 1) {
                // 그 값이 1일때 첫 중복을 확인 했으므로, list에 삽입한 후 2로 map에 값을 바꾼다
                // "한번만" node를 넣기 위해서
                list.add(node);
                map.put(key, 2);
            }
        } else {
            // 이 외에는 처음 이 패턴이 나왔으므로, 1을 put 처리
            map.put(key, 1);
        }

        // 왼쪽, 오른쪽 자식 노드를 탐색
        subTreeHasing_wrong(node.left, map, list);
        subTreeHasing_wrong(node.right, map, list);

        return;
    }
    
    // 서브 트리의 문자열 패턴을 얻기 위한 함수
    public String subTreeString_wrong(TreeNode node) {
        // 종료 조건 : 현재 노드가 null 이면 특수 문자열을 리턴
        if(node == null) {
            return "#";
        }

        // 현재 노드 패턴 = 현재 노드의 값 + 왼쪽 자식 노드의 패턴 + 오른쪽 자식 노드의 패턴
        String str = Integer.toString(node.val);
        String left = subTreeString_wrong(node.left);
        String right = subTreeString_wrong(node.right);

        str = str + left + right;

        return str;
    }
}
