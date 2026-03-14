package graph;

import java.util.*;
import java.util.stream.IntStream;

public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i =0; i < numCourses; i++) adjacencyList.add(new ArrayList<>());

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preReq = prerequisite[1];

            adjacencyList.get(preReq).add(course);
        }

        int[] indegree = new int[numCourses];

        for (List<Integer> preReqList: adjacencyList) {
            for (Integer preReq : preReqList) {
                indegree[preReq]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        IntStream.range(0, indegree.length)
                .filter(i -> indegree[i] == 0)
                .forEach(queue::offer);

        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            res.add(node);

            List<Integer> neighbors = adjacencyList.get(node);

            for (Integer neighbor: neighbors) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return res.size() == numCourses ? res.stream().mapToInt(Integer::intValue).toArray() : new int[0];
    }

    public static void main(String[] args) {
        CourseSchedule2 solver = new CourseSchedule2();

        int numCourses1 = 2;
        int[][] prereqs1 = {{1, 0}};
        System.out.println(Arrays.toString(solver.findOrder(numCourses1, prereqs1)));

        int numCourses2 = 4;
        int[][] prereqs2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(solver.findOrder(numCourses2, prereqs2)));
    }
}
