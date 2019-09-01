package com.github.tito.protobufgradle;

import example.Enumeration.StatusEnum;
import example.Enumeration.StatusEnum.StatusEnumMessage;
import example.customer.Customer.CustomerMessage;
import org.omg.PortableInterceptor.ACTIVE;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello universe");

        CustomerMessage.Builder builder = CustomerMessage.newBuilder();
        builder.setId(1)
                .setFirstName("Aditya")
                .setLastName("Tito")
                .setIsEnable(true)
                .addCustomerList(1)
                .addCustomerList(2)
                .addCustomerList(3)
                .addAllCustomerList(Arrays.asList(4, 5, 6));
        System.out.println(builder.toString());

        CustomerMessage customerMessage = builder.build();
        try {
            FileOutputStream outputStream = new FileOutputStream("customer_message.bin");
            customerMessage.writeTo(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = customerMessage.toByteArray();

        try {
            FileInputStream customerInputStream = new FileInputStream("customer_message.bin");
            CustomerMessage customerMessageFromFile = CustomerMessage.parseFrom(customerInputStream);
            System.out.println("Reading from file");
            System.out.println("customerMessageFromFile = " + customerMessageFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("example for enum");
        StatusEnumMessage.Builder builderEnum = StatusEnumMessage.newBuilder();
        builderEnum.setId(2)
                .setStatusList(StatusEnum.StatusList.DISABLE);
        StatusEnumMessage statusEnumMessage = builderEnum.build();
        System.out.println("statusEnumMessage = " + statusEnumMessage);
    }
}
