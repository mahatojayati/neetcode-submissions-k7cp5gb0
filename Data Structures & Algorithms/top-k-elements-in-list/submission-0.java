
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1) Count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 2) Min-heap to keep top k elements by frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 3) Extract the keys from the heap
        int[] res = new int[k];
        int idx = k - 1;
        while (!minHeap.isEmpty()) {
            res[idx--] = minHeap.poll().getKey();
        }

        // If you want any order, you can fill from 0 to k-1 instead of reverse.
        return res;
    }
}