package org.ashok.service;


import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.Valid;

import org.ashok.dto.SearchInvoiceDto;
import org.ashok.entity.Invoice;
import org.ashok.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class InvoiceService {

    
	private InvoiceRepository invoiceRepository;
	
	@Value("${cdn.url}")
    private String cdnUrl;

    
	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}
	
	
    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF Template from S3...");
        // TODO download from s3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Deleting downloaded templates...");
        // TODO actual deletion of PDFs
    }

    @Transactional
    public List<Invoice> searchInvoice(@Valid SearchInvoiceDto invoiceDto) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        if(invoiceDto.getMonth().equalsIgnoreCase("all")) {
        	return invoiceRepository.findByUserId(invoiceDto.getEmail());
        }
        return invoiceRepository.findByUserIdAndMonth(invoiceDto.getEmail(), invoiceDto.getMonth());
    }
    
    @Transactional
    public void create(String userId, Integer amount, String month) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
    
        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";
        
        Invoice invoice = new Invoice();
        invoice.setId(new Date().getTime());
        invoice.setAmount(amount);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setUserId(userId);
        invoice.setMonth(month);
        
        Invoice savedEntity = invoiceRepository.save(invoice);
        System.out.println("savedEntity=" + savedEntity);
    }

    
}
