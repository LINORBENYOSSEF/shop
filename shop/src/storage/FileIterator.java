//David Halevi 305268153 Moshe samahov 205787229
package storage;

import model.Product;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public class FileIterator implements Iterator<Product> {

    private RandomAccessFile file;
    private ProductSerializer serializer;

    private long previousLocation;
    private boolean didNext;

    public FileIterator(RandomAccessFile file, ProductSerializer serializer) throws IOException {
        this.file = file;
        this.serializer = serializer;

        previousLocation = file.getFilePointer();
        didNext = false;
    }

    @Override
    public boolean hasNext() {
        try {
            return file.length() - file.getFilePointer() != 0;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Product next() {
        if (!hasNext())
            return null;
        try {
            previousLocation = file.getFilePointer();
            didNext = true;
            return serializer.deserialize(file);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void remove() {
        if (!didNext)
            return;
        try {
            byte[] restOfFile = new byte[(int) (file.length() - file.getFilePointer())];
            file.read(restOfFile);
            file.seek(previousLocation);
            file.write(restOfFile);
            file.setLength(file.getFilePointer());
            didNext = false;
        } catch (IOException e) {
            return;
        }
    }
}
