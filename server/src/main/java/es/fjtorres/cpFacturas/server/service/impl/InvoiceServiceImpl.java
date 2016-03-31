package es.fjtorres.cpFacturas.server.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.InvoiceState;
import es.fjtorres.cpFacturas.common.dto.InvoiceDto;
import es.fjtorres.cpFacturas.common.dto.pagination.InvoicePageDto;
import es.fjtorres.cpFacturas.common.exception.ExceptionUtils;
import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.config.ReportsConfiguration;
import es.fjtorres.cpFacturas.server.config.ReportsConfiguration.Reports;
import es.fjtorres.cpFacturas.server.model.Invoice;
import es.fjtorres.cpFacturas.server.model.InvoiceLine;
import es.fjtorres.cpFacturas.server.service.ExportResult;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IInvoiceService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;
import es.fjtorres.jasperReport.service.Format;
import es.fjtorres.jasperReport.service.IReportService;
import es.fjtorres.jasperReport.service.Report;
import es.fjtorres.jasperReport.service.ReportServiceException;

@Named
@Transactional(readOnly = true)
public class InvoiceServiceImpl extends AbstractEntityService<Invoice, InvoiceDto, Long>
      implements IInvoiceService {

   private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);

   private final IReportService reportService;

   @Inject
   public InvoiceServiceImpl(final IBasicService pBasicService,
         final IPersistenceService<Long, Invoice> pPersistenceService,
         final IReportService pReportService) {
      super(pBasicService, pPersistenceService);

      this.reportService = pReportService;
   }

   @Override
   public Class<Invoice> getEntityClass() {
      return Invoice.class;
   }

   @Override
   public Class<InvoiceDto> getDtoClass() {
      return InvoiceDto.class;
   }

   @Override
   public InvoicePageDto find(final int page, final int pageSize, final String sortField,
         final String sortDirection) throws IllegalArgumentException {
      if (pageSize == 0) {
         ExceptionUtils.throwIllegalArgument("page size cannon't be zero");
      }

      final OrderBy order = OrderBy.fromString(sortDirection);

      List<InvoiceDto> dtos = Collections.emptyList();

      final Long total = getPersistenceService().count(getEntityClass());

      if (total > 0) {

         int maxPages = (int) (total / pageSize);

         if (page > maxPages) {
            ExceptionUtils.throwIllegalArgument("the page cannon't be greater than: {0}", maxPages);
         }

         final int startPosition = page == 0 ? page : (page * pageSize);

         final List<Invoice> entities = getPersistenceService().find(startPosition, pageSize,
               sortField, order, getEntityClass());

         dtos = getBasicService().convert(entities, getDtoClass());
      }

      final InvoicePageDto pageWrapper = new InvoicePageDto();
      pageWrapper.setList(dtos);
      pageWrapper.setTotal(total);
      return pageWrapper;
   }

   @Override
   public void prePersist(Invoice pEntity) {
      // Set invoice reference on lines
      if (pEntity != null) {
         if (CollectionUtils.isNotEmpty(pEntity.getLines())) {
            for (final InvoiceLine line : pEntity.getLines()) {
               if (line != null) {
                  line.setInvoice(pEntity);
               }
            }
         }
         
         if (pEntity.isNew()) {
            pEntity.setTaxRate(new BigDecimal(21));// FIXME Load from configuration
         }
      }
   }

   @Override
   public ExportResult export(final Long pId) {
      final InvoiceDto dto = findById(pId);
      final Report<InvoiceDto> pdfReport = new Report<InvoiceDto>();
      pdfReport.setData(Arrays.asList(dto));
      pdfReport.setFormat(Format.PDF);
      pdfReport.setOutputName(
            dto.getVehicle().getRegistration() + "." + pdfReport.getFormat().getExtension());
      pdfReport.setTemplate(ReportsConfiguration.getResourcePath(Reports.INVOICE.getTemplatePath()));
      pdfReport.getParameters().put(ReportsConfiguration.Parameters.REPORTS_PATH,
            ReportsConfiguration.getReportsLocation());
      pdfReport.getParameters().put(IReportService.REPORT_RESOURCE_BUNDLE,
            ResourceBundle.getBundle(Reports.INVOICE.getI18nResourcePath(), LocaleContextHolder.getLocale()));
      pdfReport.getParameters().put(IReportService.REPORT_LOCALE, LocaleContextHolder.getLocale());
      pdfReport.getParameters().put("draft", InvoiceState.CREATED.equals(dto.getState()));
      pdfReport.getParameters().put("backgroundImage", ReportsConfiguration.getResourcePath(Reports.INVOICE.getReportPath() + "logo_background.jpg"));
      pdfReport.getParameters().put("companyImage", ReportsConfiguration.getResourcePath(Reports.INVOICE.getReportPath() + "logo.jpg"));
      pdfReport.getParameters().put("companyInfo", "Taller de Chapa y Pintura Raúl, C.B.\nN.I.F: E-72259641\nTlfn. y FAX: 956364705\nCallejón de la Paja, 4\n11540 - Sanlúcar de barrameda (Cádiz)");
      pdfReport.getParameters().put("companyIBAN", "ES82 2100 8517 8102 0002 2207");

      try {
         final byte[] file = reportService.generate(pdfReport, false);
         ExportResult result = new ExportResult(pdfReport.getOutputName(), file,
               pdfReport.getFormat().getContentType());
         return result;
      } catch (final ReportServiceException e) {
         LOGGER.error("Error generating invoice report, invoice identifier: " + pId, e);
      }
      return null;
   }

}
