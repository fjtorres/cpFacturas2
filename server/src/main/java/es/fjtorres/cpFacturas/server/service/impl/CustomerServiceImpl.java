package es.fjtorres.cpFacturas.server.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.CustomerPageDto;
import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.api.impl.AbstractResource;
import es.fjtorres.cpFacturas.server.dozer.service.IDozerService;
import es.fjtorres.cpFacturas.server.model.Customer;
import es.fjtorres.cpFacturas.server.service.ICustomerService;
import es.fjtorres.cpFacturas.server.service.IPersistenceService;

@Named
@Transactional(readOnly = true)
public class CustomerServiceImpl extends AbstractResource implements ICustomerService {

    private final IDozerService dozerService;

    private final IPersistenceService<Long, Customer> persistenceService;

    @Inject
    public CustomerServiceImpl(final IDozerService pDozerService,
            final IPersistenceService<Long, Customer> pPersistenceService) {
        this.dozerService = pDozerService;
        this.persistenceService = pPersistenceService;
    }

    public IDozerService getDozerService() {
        return dozerService;
    }

    public IPersistenceService<Long, Customer> getPersistenceService() {
        return persistenceService;
    }

    @Override
    public CustomerPageDto find(int page, int pageSize, String sortField, String sortDirection) {

        if (pageSize == 0) {
            badRequest("page size can not be zero");
        }

        OrderBy order = null;
        try {
            order = OrderBy.valueOf(sortDirection);
        } catch (final IllegalArgumentException iae) {
            badRequest("sort direction isn't valid. Only ASC or DESC.");
        }

        List<CustomerDto> dtos = Collections.emptyList();

        final Long total = getPersistenceService().count(Customer.class);

        if (total > 0) {

            int maxPages = (int) (total / pageSize);

            if (page > maxPages) {
                badRequest("the page can not be greater than: {0}", maxPages);
            }

            final int startPosition = page == 0 ? page : (page * pageSize) - 1;

            final List<Customer> entities = getPersistenceService().find(startPosition, pageSize,
                    sortField, order, Customer.class);

            dtos = getDozerService().convert(entities, CustomerDto.class);
        }

        final CustomerPageDto pageWrapper = new CustomerPageDto();
        pageWrapper.setList(dtos);
        pageWrapper.setTotal(dtos.size());
        return pageWrapper;
    }

    @Override
    public CustomerDto find(final Long pId) {
        final Customer entity = getPersistenceService().findById(pId, Customer.class);
        return getDozerService().convert(entity, CustomerDto.class);
    }

    @Override
    @Transactional
    public void add(final CustomerDto pDto) {
        getPersistenceService().persist(getDozerService().convert(pDto, Customer.class));
    }

    @Override
    @Transactional
    public void update(final CustomerDto pDto) {
        getPersistenceService().update(getDozerService().convert(pDto, Customer.class));
    }

    @Override
    @Transactional
    public void delete(final Long pId) {
        getPersistenceService().delete(pId, Customer.class);
    }

}
