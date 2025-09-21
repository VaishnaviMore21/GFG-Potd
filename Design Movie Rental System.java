import java.util.*;

public class MovieRentingSystem {
    // Map (shop, movie) → price
    private Map<Pair<Integer, Integer>, Integer> priceMap;

    // For each movie, set of available entries (price, shop)
    private Map<Integer, TreeSet<AvailEntry>> available;

    // Set of rented entries (price, shop, movie)
    private TreeSet<RentedEntry> rented;

    // Custom entry classes with comparators
    private static class AvailEntry implements Comparable<AvailEntry> {
        int price;
        int shop;
        public AvailEntry(int price, int shop) {
            this.price = price;
            this.shop = shop;
        }
        public int compareTo(AvailEntry o) {
            if (this.price != o.price) return this.price - o.price;
            return this.shop - o.shop;
        }
        // equals & hashCode should be consistent
    }

    private static class RentedEntry implements Comparable<RentedEntry> {
        int price;
        int shop;
        int movie;
        public RentedEntry(int price, int shop, int movie) {
            this.price = price;
            this.shop = shop;
            this.movie = movie;
        }
        public int compareTo(RentedEntry o) {
            if (this.price != o.price) return this.price - o.price;
            if (this.shop != o.shop) return this.shop - o.shop;
            return this.movie - o.movie;
        }
        // equals & hashCode too
    }

    public MovieRentingSystem(int n, int[][] entries) {
        priceMap = new HashMap<>();
        available = new HashMap<>();
        rented = new TreeSet<>();

        for (int[] e : entries) {
            int shop = e[0];
            int movie = e[1];
            int price = e[2];
            priceMap.put(new Pair<>(shop, movie), price);

            available.putIfAbsent(movie, new TreeSet<>());
            available.get(movie).add(new AvailEntry(price, shop));
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!available.containsKey(movie)) return res;
        Iterator<AvailEntry> it = available.get(movie).iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            AvailEntry ae = it.next();
            res.add(ae.shop);
            count++;
        }
        return res;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(new Pair<>(shop, movie));
        // Remove from available
        TreeSet<AvailEntry> set = available.get(movie);
        set.remove(new AvailEntry(price, shop));
        // Add to rented
        rented.add(new RentedEntry(price, shop, movie));
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(new Pair<>(shop, movie));
        // Remove from rented
        rented.remove(new RentedEntry(price, shop, movie));
        // Add back to available
        available.get(movie).add(new AvailEntry(price, shop));
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<RentedEntry> it = rented.iterator();
        int count = 0;
        while(it.hasNext() && count < 5) {
            RentedEntry re = it.next();
            res.add(Arrays.asList(re.shop, re.movie));
            count++;
        }
        return res;
    }
}
