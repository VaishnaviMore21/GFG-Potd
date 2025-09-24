// Helper class Geeks to implement
// insert() and findFrequency()
class Geeks {

   
    static void insert(PriorityQueue<Integer> q, int k) {
       q.offer(k);
    }

 
    static boolean find(PriorityQueue<Integer> q, int k) {

        return q.contains(k);
        
    }

    
    static int delete(PriorityQueue<Integer> q) {
        
   if (q.isEmpty()) return -1;  // or handle according to problem spec
        return q.poll(); // rem
    }
}
