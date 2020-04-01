package Online_Assessment.Microsoft;

// Max Network Rank
// https://leetcode.com/discuss/interview-question/364760/

/*
Explanation:

edgeCount is of size N+1 hence we can fetch a city node's edges by it's number directly.
Example, if the edge is 1-> 4, then edgeCount's state will be [0, 1, 0, 0, 1]
first loop - iterates over A and B arrays and adds edges for both nodes
second loop - iterates over both A and B at the same time,
we collect the total count of edges for both A[i] node and B[i] node.
Since the edges are bidirectional, the sum will count the same edge twice A[i] -> B[i] and B[i] -> A[i].
So we deduct 1 to remove duplicate.
With the example from 1) if the edge is 1-> 4, then edgeCount's state will be [0, 1, 0, 0, 1],
sum will be equal to 2 and then we deduct 1 duplicate edge -> total rank is 1.
We compare the current rank for the pair of nodes with the max known value so far,
and if the current rank is bigger we save that.
 */

public class  Max_Network_Rank {
    public static int solution(int[] A, int[] B, int N) {
        int maxRank = 0;
        int edgesLen = A.length;

        int[] edgesCount = new int[N + 1];

        for(int i=0; i<edgesLen; i++){
            edgesCount[A[i]] += 1;
            edgesCount[B[i]] += 1;
        }

        for(int i=0; i<edgesLen; i++){
            int localMax = edgesCount[A[i]] + edgesCount[B[i]] - 1;
            maxRank = Math.max(maxRank, localMax);
        }

        return maxRank;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 3};
        int[] B = {2, 3, 1, 4};
        int N = 4;

        System.out.println("expected " + 4 + " actual " + solution(A, B, N));
    }
}
