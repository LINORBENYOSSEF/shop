//David Halevi 305268153 Moshe samahov 205787229
package storage;

import model.Product;

import java.util.ArrayDeque;
import java.util.Deque;

public class StorageHistory {

    private Deque<Product> insertionHistory;

    public StorageHistory() {
        this.insertionHistory = new ArrayDeque<>();
    }

    public void insert(Product product) {
        insertionHistory.push(product);
    }

    public Product removeLast() {
        return insertionHistory.pop();
    }

    public boolean isEmpty() {
        return insertionHistory.isEmpty();
    }

    public void clear() {
        insertionHistory.clear();
    }
}
