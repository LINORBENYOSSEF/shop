//David Halevi 305268153 Moshe samahov 205787229
package storage;

import model.Customer;
import model.Product;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ProductSerializer {

    public void serialize(DataOutput output, Product product) throws IOException {
        output.writeLong(product.getCreationId());
        output.writeUTF(product.getId());
        output.writeUTF(product.getName());
        output.writeInt(product.getShopPrice());
        output.writeInt(product.getClientPrice());
        output.writeUTF(product.getClient().getName());
        output.writeUTF(product.getClient().getPhone());
        output.writeBoolean(product.getClient().getSaleUpdates());
    }

    public Product deserialize(DataInput input) throws IOException {
        long creationId = input.readLong();
        String id = input.readUTF();
        String name = input.readUTF();
        int priceForShop = input.readInt();
        int priceForClient = input.readInt();
        String nameOfClient = input.readUTF();
        String phoneNumber = input.readUTF();
        boolean saleUpdates = input.readBoolean();

        Customer customer = new Customer(nameOfClient, phoneNumber, saleUpdates);
        return new Product(creationId, id, name, priceForShop, priceForClient, customer);
    }
}
