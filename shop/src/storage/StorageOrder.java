package storage;

import model.Product;

import java.util.Comparator;

public enum StorageOrder implements Comparator<Product> {
    ID_ASCENDING {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getId().compareTo(o2.getId());
        }
    },
    ID_DESCENDING {
        @Override
        public int compare(Product o1, Product o2) {
            return -o1.getId().compareTo(o2.getId());
        }
    },
    INSERTION_ASCENDING {
        @Override
        public int compare(Product o1, Product o2) {
            return Long.compare(o1.getCreationId(), o2.getCreationId());
        }
    };
}
