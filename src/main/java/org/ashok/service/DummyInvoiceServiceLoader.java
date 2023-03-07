package org.ashok.service;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
//@Profile("dev")

public class DummyInvoiceServiceLoader {

    private final InvoiceService invoiceService;

    public DummyInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    
    @PostConstruct
    public void setup() {
        System.out.println("Creating dev invoices...");
        invoiceService.create("ashok.m.mane@gmail.com", 50,"jan");
        invoiceService.create("sidbuddy@gmail.com", 100,"feb");
        invoiceService.create("kan@abc.com", 200,"mar");
    }
    
}
