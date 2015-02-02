package es.fjtorres.cpFacturas.server.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.InvoiceDto;
import es.fjtorres.cpFacturas.common.dto.pagination.InvoicePageDto;
import es.fjtorres.cpFacturas.common.exception.ExceptionUtils;
import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.model.Invoice;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IInvoiceService;
import es.fjtorres.cpFacturas.server.service.IPersistenceService;

@Named
@Transactional(readOnly = true)
public class InvoiceServiceImpl extends
      AbstractEntityService<Invoice, InvoiceDto, Long> implements
      IInvoiceService {

   @Inject
   public InvoiceServiceImpl(final IBasicService pBasicService,
         final IPersistenceService<Long, Invoice> pPersistenceService) {
      super(pBasicService, pPersistenceService);
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
   public InvoicePageDto find(final int page, final int pageSize,
         final String sortField, final String sortDirection)
         throws IllegalArgumentException {
      if (pageSize == 0) {
         ExceptionUtils.throwIllegalArgument("page size cannon't be zero");
      }

      final OrderBy order = OrderBy.fromString(sortDirection);

      List<InvoiceDto> dtos = Collections.emptyList();

      final Long total = getPersistenceService().count(getEntityClass());

      if (total > 0) {

         int maxPages = (int) (total / pageSize);

         if (page > maxPages) {
            ExceptionUtils.throwIllegalArgument(
                  "the page cannon't be greater than: {0}", maxPages);
         }

         final int startPosition = page == 0 ? page : (page * pageSize);

         final List<Invoice> entities = getPersistenceService().find(
               startPosition, pageSize, sortField, order, getEntityClass());

         dtos = getBasicService().convert(entities, getDtoClass());
      }

      final InvoicePageDto pageWrapper = new InvoicePageDto();
      pageWrapper.setList(dtos);
      pageWrapper.setTotal(total);
      return pageWrapper;
   }

}
