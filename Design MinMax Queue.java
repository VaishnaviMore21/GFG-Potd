class SpecialQueue {
    private Deque<Integer> minDQ;
    private Deque<Integer> maxDQ;
    private Queue<Integer> q;

    public SpecialQueue() {
        minDQ = new ArrayDeque<>();
        maxDQ = new ArrayDeque<>();
        q = new LinkedList<>();
    }

    public void enqueue(int x) {
        // Insert element into the queue
        q.offer(x);
        while(!minDQ.isEmpty() && minDQ.peekLast()>x)
        {
             minDQ.pollLast();
        }
          minDQ.offerLast(x);

        // maintain maxDQ (decreasing)
        while (!maxDQ.isEmpty() && maxDQ.peekLast() < x) {
            maxDQ.pollLast();
        }
        maxDQ.offerLast(x);
    }

    public void dequeue() {
        // Remove element from the queue
         if (q.isEmpty()) return;

        int v = q.poll();
        if (!minDQ.isEmpty() && minDQ.peekFirst() == v) {
            minDQ.pollFirst();
        }
        if (!maxDQ.isEmpty() && maxDQ.peekFirst() == v) {
            maxDQ.pollFirst();
        }
    }

    public int getFront() {
        // Get front element
         if (q.isEmpty()) {
            // you may throw exception or return sentinel
            throw new NoSuchElementException("Queue is empty");
        }
        return q.peek();
    }

    public int getMin() {
        // Get minimum element
         if (minDQ.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return minDQ.peekFirst();
    }

    public int getMax() {
        // Get maximum element
        if (maxDQ.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return maxDQ.peekFirst();
    }
}
