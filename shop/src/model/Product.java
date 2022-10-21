//David Halevi 305268153 Moshe samahov 205787229
package model;

public class Product {

    public static long nextCreationId = -1;

    private long creationId;
    private String id;
    private String name;
    private int shopPrice;
    private int clientPrice;
    private Customer client;

    public Product(long creationId, String id, String name, int shopPrice, int clientPrice, Customer client) {
        if (creationId < 0) {
            throw new IllegalArgumentException("Expected non-negative creationId");
        }

        this.creationId = creationId;
        this.id = id;
        this.name = name;
        this.shopPrice = shopPrice;
        this.clientPrice = clientPrice;
        this.client = client;
    }

    public Product(String id, String name, int shopPrice, int clientPrice, Customer client) {
        this(nextCreationId++, id, name, shopPrice, clientPrice, client);
    }

    public long getCreationId() {
        return creationId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShopPrice() {
        return shopPrice;
    }

    public void setPriceForShop(int priceForShop) {
        this.shopPrice = priceForShop;
    }

    public int getClientPrice() {
        return clientPrice;
    }

    public void setPriceForClient(int priceForClient) {
        this.clientPrice = priceForClient;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public boolean equals(Product p) {
        return this.name.equals(p.getName()) && this.clientPrice == p.getClientPrice() &&
                this.shopPrice == p.getShopPrice() && this.client.getSaleUpdates() == p.getClient().getSaleUpdates()
                && this.client.getName().equals(p.getClient().getName()) && this.client.getPhone().equals(p.getClient().getPhone());
    }

    public int profitOfShopForProduct() {
        int profitPerProduct;
        profitPerProduct = clientPrice - shopPrice;
        if (profitPerProduct < 0)
            profitPerProduct = 0;
        return profitPerProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "creationId=" + creationId +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shopPrice=" + shopPrice +
                ", clientPrice=" + clientPrice +
                ", client=" + client +
                '}';
    }
}
