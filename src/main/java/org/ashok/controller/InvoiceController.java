package org.ashok.controller;

import java.util.List;

import javax.validation.Valid;

import org.ashok.dto.SearchInvoiceDto;
import org.ashok.entity.Invoice;
import org.ashok.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//https://www.baeldung.com/thymeleaf-generate-pdf
//https://stackoverflow.com/questions/44220739/invoice-generation-thymeleaf-spring-itextpdf

@Controller
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    
    @GetMapping(path = "/invoices")
    public String invoices(Model model) {
    	System.out.println("Entering invoices controller method");
    	SearchInvoiceDto searchInvoiceDto = new SearchInvoiceDto();
    	model.addAttribute("searchInvoice", searchInvoiceDto);
    	return "searchInvoice.html";
    	
    }
    
    @PostMapping("/search/invoice")
    public String searchInvoice(@Valid @ModelAttribute("searchInvoice") SearchInvoiceDto invoiceDto,
                               BindingResult result,
                               Model model){
        System.out.println("Entering searchInvoice controller method");
    	
    	List<Invoice> invoices = invoiceService.searchInvoice(invoiceDto);
    	System.out.println("invoices.size() =" + invoices.size());
        
        if (result.hasErrors()) {
            model.addAttribute("searchInvoice", invoiceDto);
            System.out.println("searchInvoice found errors, so returning to searchInvoice.html");
            return "searchInvoice.html";
        }
        
        model.addAttribute("invoices", invoices);
        return "invoices.html";
    }
   
}
