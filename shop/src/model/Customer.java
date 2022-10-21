//David Halevi 305268153 Moshe samahov 205787229
package model;

import commands.AcknowledgeMessageCommand;
import commands.CommandQueue;
import messaging.Message;
import messaging.Observer;

public class Customer implements Observer {

    private String name;
    private String phone;
    private boolean saleUpdates;

    public Customer(String name, String phone, boolean saleUpdates) {
        this.name = name;
        this.phone = phone;
        this.saleUpdates = saleUpdates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getSaleUpdates() {
        return saleUpdates;
    }

    public void setSaleUpdates(boolean saleUpdates) {
        this.saleUpdates = saleUpdates;
    }

    @Override
    public String toString() {
        return "Client [name=" + name + ", phone=" + phone + ", saleUpdates=" + saleUpdates + "]";
    }

    public boolean equals(Customer c) {
        return this.name.equals(c.getName()) &&
                this.phone.equals(c.getPhone()) && this.saleUpdates == c.getSaleUpdates();
    }

    @Override
    public void handle(Message message, CommandQueue commandQueue) {
        if (saleUpdates) {
            commandQueue.addCommand(new AcknowledgeMessageCommand(message, this));
        }
    }
}
