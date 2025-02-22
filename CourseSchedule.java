// Approach: Construct a directed graph using the given information while maintaining the in-degree of each vertex. Perform a modified BFS
// using a queue, where only nodes with zero in-degree are enqueued. As each node is visited, decrement the in-degree of its neighbors,
// enqueueing any that reach zero. Continue this process until the queue is empty. If any node still has a positive in-degree at the end,
// the graph contains a cycle and is not a DAG.
// Time Complexity: O(V + E) where V - vertex count and E - edge count
// Space Complexity: O(V + E) for adjacency list

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// https://leetcode.com/problems/course-schedule/description/

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

@SuppressWarnings("unchecked")
public class CourseSchedule {
    class DGraph {
        int V;
        int[] indegree;
        List<Integer>[] adjList;

        DGraph(int v) {
            V = v;
            adjList = new ArrayList[v];
            indegree = new int[v];

            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        void addEdge(int u, int v) {
            adjList[u].add(v);
            indegree[v]++;
        }

        boolean topologicalSortBFS() {
            Deque<Integer> Q = new ArrayDeque<>();
            for (int i = 0; i < V; i++) {
                if (indegree[i] == 0) {
                    Q.add(i);
                }
            }
            while (!Q.isEmpty()) {
                int curr = Q.poll();
                for (int i: adjList[curr]) {
                    indegree[i]--;
                    if (indegree[i] == 0) {
                        Q.add(i);
                    }
                }
            }
            for (int i = 0; i < V; i++) {
                if (indegree[i] != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    boolean canFinish(int numCourses, int[][] prerequisites) {
        DGraph g = new DGraph(numCourses);
        for (int[] edge: prerequisites) {
            g.addEdge(edge[0], edge[1]);
        }
        return g.topologicalSortBFS();
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();

        /*
        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: true

        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
        Output: false
        */

        int n = 2;
        int[][] prereqs = {
                {1, 0},
                {0, 1}
        };
        System.out.println("Given directed graph is a DAG (true/false): " + cs.canFinish(n, prereqs));
    }
}