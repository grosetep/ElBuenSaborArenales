package com.estrategiamovilmx.eats.elbuensaborarenales.requests;

import com.estrategiamovilmx.eats.elbuensaborarenales.model.Contact;
import com.estrategiamovilmx.eats.elbuensaborarenales.model.PaymentMethod;
import com.estrategiamovilmx.eats.elbuensaborarenales.model.ShippingAddress;
import com.estrategiamovilmx.eats.elbuensaborarenales.tools.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 10/08/2017.
 */
public class CreateOrderRequest implements Serializable {
    @SerializedName("id_order")
    private String id_order;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("id_cart")
    private String id_cart;
    @SerializedName("payment_method")
    private PaymentMethod payment_method;
    @SerializedName("total")
    private String total;
    @SerializedName("shipping")
    private ShippingAddress shipping;
    @SerializedName("contact")
    private Contact contact;
    @SerializedName("businessName")
    private String businessName;
    @SerializedName("nameUser")
    private String nameUser;
    @SerializedName("amountFormatTotal")
    private String amountFormatTotal;
    @SerializedName("token")
    private String token;


    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }

    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ShippingAddress getShipping() {
        return shipping;
    }

    public void setShipping(ShippingAddress shipping) {
        this.shipping = shipping;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAmountFormatTotal() {
        return amountFormatTotal;
    }

    public void setAmountFormatTotal(String amountFormatTotal) {
        this.amountFormatTotal = amountFormatTotal;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "id_order='" + id_order + '\'' +
                ", id_user='" + id_user + '\'' +
                ", id_cart='" + id_cart + '\'' +
                ", payment_method=" + payment_method +
                ", total='" + total + '\'' +
                ", shipping=" + shipping +
                ", contact=" + contact +
                ", businessName='" + businessName + '\'' +
                ", nameUser='" + nameUser + '\'' +
                ", amountFormatTotal='" + amountFormatTotal + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
