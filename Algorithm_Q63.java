import java.util.*;

public class Algorithm_Q63 {
    /*
     * 해당 문제는 그래프를 활용한 문제이다. 그래프의 간선이 열쇠의 형태로 나타나 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/keys-and-rooms/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        // 열쇠 번들을 만들어 준다.
        Queue<Integer> keyBundle = new LinkedList<>();
        // 해당 방을 방문할 수 있어 방문 했는지를 체크하는 리스트를 만든다.
        boolean[] visited = new boolean[n];
 
        // 0번 방은 무조건 열려 있다고 했으므로, 해당 방은 open 한다. 그리고 방문 가능하니까 방문 표시 처리      
        visited[0] = true;

        // 추가로 0번 방의 열쇠 꾸러미를 가져온다.
        List<Integer> zeroRoomKeys = rooms.get(0);

        for(Integer roomsKey : zeroRoomKeys) {
            // 해당 열쇠 꾸러미에 맞는 방은 열쇠를 갖게 되었으므로, 열쇠 꾸러미 안에 넣는다.
            keyBundle.offer(roomsKey);
        }

        // 갖고 있는 열쇠들을 사용해 방문을 시작
        while(!keyBundle.isEmpty()) {
            // 열쇠 하나를 꺼내서 그 방으로 이동한다.
            int key = keyBundle.poll();

            if(visited[key]) {
                // 만약 해당 방을 이미 방문 했다면 아래 로직을 실행하지 않는다. 이미 방문한 방의 열쇠는 넣을 필요가 없다.
                continue;
            }
            // key에 맞는 방에 이동했으므로 방문 처리
            visited[key] = true;

            // 이 방의 열쇠 꾸러미를 가져온다.
            List<Integer> keys = rooms.get(key);

            for(Integer roomsKey : keys) {
                // 해당 열쇠 꾸러미에 맞는 방은 열쇠를 갖게 되었으므로, 열쇠 꾸러미 안에 넣는다.
                keyBundle.offer(roomsKey);
            }
        }

        // 여태까지 방문한 방들을 체크한다.
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                // 만약 방문하지 못한 방이 있다면 순회 불가능하므로 false 처리
                return false;
            }
        }

        // 반복문을 모두 돌았는데 전부 true라면 모든 방을 방문할 수 있으므로 true
        return true;
    }


    // DFS를 활용한 풀이
    public boolean canVisitAllRooms_dfs(List<List<Integer>> rooms) {
        boolean visited[] = new boolean[rooms.size()];
        dfs(rooms.get(0), 0, rooms, visited);

        for(int i=0;i<visited.length;i++) {
            if(!visited[i]) {
                return false;
            }
        }
        return true; 
    }
    
    private void dfs(List<Integer> keysInRoom, int room, List<List<Integer>> rooms, boolean[] visited) {
        visited[room] = true;

        for(Integer i: keysInRoom) {
            // 핵심은 그 방을 들어가서 반복문을 돌게 만드는 것이다.
            if(!visited[i]) {
                dfs(rooms.get(i), i, rooms, visited);
            }
        }
    }
}
