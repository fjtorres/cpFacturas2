package es.fjtorres.cpFacturas.server.reports;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import es.fjtorres.cpFacturas.common.dto.InvoiceLineDto;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class InvoiceReportScriptlet extends JRDefaultScriptlet {

   private static final BigDecimal PERCENTAGE = new BigDecimal(100);
   
   @SuppressWarnings("unchecked")
   @Override
   public void afterDetailEval() throws JRScriptletException {
      final List<InvoiceLineDto> lines =  (List<InvoiceLineDto>)getFieldValue("lines");
      final BigDecimal taxRate = (BigDecimal)getFieldValue("taxRate");
      
      BigDecimal totalInvoice = (BigDecimal)getVariableValue("totalInvoice");
      if (totalInvoice == null) {
         totalInvoice = BigDecimal.ZERO;
      }
      
      if (CollectionUtils.isNotEmpty(lines)) {
         for (final InvoiceLineDto line:lines) {
            if (line != null) {
               
               BigDecimal subtotal = line.getAmount().multiply(line.getPrice());
               if (line.getDiscount() != null && !BigDecimal.ZERO.equals(line.getDiscount())) {
                  subtotal = subtotal.subtract(subtotal.multiply(line.getDiscount()).divide(PERCENTAGE));
               }
               
               totalInvoice = totalInvoice.add(subtotal);
            }
         }
      }
      
      final BigDecimal totalInvoiceTax = totalInvoice.multiply(taxRate).divide(PERCENTAGE);
      final BigDecimal totalInvoiceWithTax = totalInvoice.add(totalInvoiceTax);
      
      setVariableValue("totalInvoice", totalInvoice);
      setVariableValue("totalInvoiceTax", totalInvoiceTax);
      setVariableValue("totalInvoiceWithTax", totalInvoiceWithTax);
      
   }

   
   
}
