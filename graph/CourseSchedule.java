package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};

        CourseSchedule solver = new CourseSchedule();
        if (solver.canFinish(numCourses, prerequisites)) {
            System.out.println("Can finish all courses ✅");
        } else {
            System.out.println("Cannot finish courses (Cycle detected) ❌");
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adjacencyList.add(new ArrayList<>());

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preReq = prerequisite[1];

            adjacencyList.get(preReq).add(course);
        }
        int[] indegree = new int[numCourses];
        for (List<Integer> adj: adjacencyList) {
            for (Integer ele : adj) {
                indegree[ele]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int totalCompletedCourses = 0;

        while (!queue.isEmpty()) {
            int preReq = queue.poll();
            totalCompletedCourses++;
            List<Integer> neighbors = adjacencyList.get(preReq);
            for (Integer neighbor: neighbors) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return totalCompletedCourses == numCourses;
    }
}