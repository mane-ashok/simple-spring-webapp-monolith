package org.ashok.util;

import org.ashok.entity.Invoice;

public interface InvoiceGenerator {
	
	Invoice generate(String userId, String month);
}
