//David Halevi 305268153 Moshe samahov 205787229
package storage;

import messaging.MessageQueue;
import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private File file;
    private Map<String, Product> productsMap;
    private ProductSerializer serializer;
    private StorageHistory history;

    private StorageOrder storageOrder;

    public Storage(File file) {
        this.file = file;
        this.productsMap = new LinkedHashMap<>();
        this.serializer = new ProductSerializer();
        this.history = new StorageHistory();
    }

    public void load() throws IOException {
        try {
            loadFromFile();
        } catch (FileNotFoundException e) {
        }

        Product.nextCreationId = findMaxCreationId() + 1;
        setOrder(StorageOrder.ID_ASCENDING);
    }

    public void setOrder(StorageOrder order) throws IOException {
        storageOrder = order;
        resortMap();
    }

    public void addProduct(Product product) throws IOException {
        productsMap.put(product.getId(), product);
        history.insert(product);
        MessageQueue.getInstance().registerObserver(product.getClient());

        resortMap();
    }

    public Product findProductById(String id) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(this.file, "r")) {
            FileIterator iterator = new FileIterator(file, serializer);
            while (iterator.hasNext()) {
                Product foundProduct = iterator.next();
                if (foundProduct.getId().equals(id)) {
                    return foundProduct;
                }
            }
        }

        throw new IllegalArgumentException("Key not found " + id);
    }

    public List<Product> getAllProducts() throws IOException {
        List<Product> products = new ArrayList<>();

        try (RandomAccessFile file = new RandomAccessFile(this.file, "r")) {
            FileIterator iterator = new FileIterator(file, serializer);
            while (iterator.hasNext()) {
                Product foundProduct = iterator.next();
                products.add(foundProduct);
            }
        }
        return products;
    }

    public void removeProductById(String id) throws IOException {
        productsMap.remove(id);

        try (RandomAccessFile file = new RandomAccessFile(this.file, "rw")) {
            FileIterator iterator = new FileIterator(file, serializer);
            while (iterator.hasNext()) {
                Product foundProduct = iterator.next();
                if (foundProduct.getId().equals(id)) {
                    MessageQueue.getInstance().unregisterObserver(foundProduct.getClient());
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public void removeLastProduct() throws IOException {
        if (history.isEmpty()) {
            return;
        }

        Product product = history.removeLast();
        MessageQueue.getInstance().unregisterObserver(product.getClient());
        removeProductById(product.getId());
    }

    public void removeAllProducts() {
        productsMap.clear();
        history.clear();
        MessageQueue.getInstance().clearObservers();
        file.delete();
    }

    private void resortMap() throws IOException {
        List<Product> products = new ArrayList<>(productsMap.values());
        products.sort(storageOrder);
        productsMap.clear();
        products.forEach((p) -> productsMap.put(p.getId(), p));

        saveToFile();
    }

    private void loadFromFile() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(this.file, "r")) {
            FileIterator iterator = new FileIterator(file, serializer);
            while (iterator.hasNext()) {
                Product product = iterator.next();
                productsMap.put(product.getId(), product);
                history.insert(product);

                MessageQueue.getInstance().registerObserver(product.getClient());
            }
        }
    }

    private void saveToFile() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(this.file, "rw")) {
            for (Map.Entry<String, Product> entry : productsMap.entrySet()) {
                serializer.serialize(file, entry.getValue());
            }
        }
    }

    private long findMaxCreationId() {
        long max = 0;
        for (Product product : productsMap.values()) {
            max = Math.max(max, product.getCreationId());
        }
        return max;
    }
}
