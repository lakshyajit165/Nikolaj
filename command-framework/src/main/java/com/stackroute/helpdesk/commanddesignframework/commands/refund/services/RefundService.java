package com.stackroute.helpdesk.commanddesignframework.commands.refund.services;

import com.stackroute.helpdesk.commanddesignframework.commands.refund.model.Invoice;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
	public void getInvoice(){
		Invoice invoice = new Invoice();
		invoice.setBookedAt("awd");
	}
}
