package es.fjtorres.cpFacturas.server.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import es.fjtorres.cpFacturas.common.FuelType;
import es.fjtorres.cpFacturas.common.InvoiceState;
import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.InvoiceDto;
import es.fjtorres.cpFacturas.common.dto.InvoiceLineDto;
import es.fjtorres.cpFacturas.common.dto.VehicleBrandDto;
import es.fjtorres.cpFacturas.common.dto.VehicleDto;
import es.fjtorres.cpFacturas.common.dto.VehicleModelDto;
import es.fjtorres.cpFacturas.server.config.ReportsConfiguration;
import es.fjtorres.cpFacturas.server.model.Invoice;
import es.fjtorres.cpFacturas.server.service.ExportResult;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;
import es.fjtorres.jasperReport.service.impl.DefaultReportLoader;
import es.fjtorres.jasperReport.service.impl.DefaultReportServiceImpl;

public class InvoiceServiceImplTest {

   @Mock
   private IBasicService mockBasicService;
   
   @Mock
   private IPersistenceService<Long, Invoice> mockPersistenceService;
   
   @BeforeMethod
   public void beforeMethod() {
      MockitoAnnotations.initMocks(this);
   }
   
   @Test
   public void reportTest () throws IOException {
      final Long testId = 1L;
      Invoice mockInvoice = new Invoice();
      
//      final CustomerDto mockCustomerDto = new CustomerDto();
//      mockCustomerDto.setCode("TEST_CODE");
//      mockCustomerDto.setFirstName("NOMBRE");
//      mockCustomerDto.setLastName("APELLIDOS");
      
      final VehicleBrandDto mockBrand = new VehicleBrandDto();
      mockBrand.setName("PEUGEOT");
      final VehicleModelDto mockModel = new VehicleModelDto();
      mockModel.setBrand(mockBrand);
      mockModel.setName("307");
      
      final VehicleDto mockVehicleDto = new VehicleDto();
//      mockVehicleDto.setCustomer(mockCustomerDto);
      mockVehicleDto.setDoors(new Short("1"));
      mockVehicleDto.setFuelType(FuelType.Diesel);
      mockVehicleDto.setModel(mockModel);
      mockVehicleDto.setRegistration("9198BNJ");
      
      final List<InvoiceLineDto> mockLines = new ArrayList<>();
      
      for (int i = 1;i<=30;i++) {
         final InvoiceLineDto mockLine = new InvoiceLineDto();
         mockLine.setAmount(new BigDecimal(i));
         mockLine.setDescription("PRODUCTO " + i);
         mockLine.setDiscount(BigDecimal.ZERO);
         mockLine.setPrice(new BigDecimal(2 * i));
         mockLines.add(mockLine);
      }
      
      final InvoiceDto mockInvoiceDto = new InvoiceDto();
      mockInvoiceDto.setState(InvoiceState.CREATED);
      mockInvoiceDto.setTaxRate(new BigDecimal(21));
      mockInvoiceDto.setVehicle(mockVehicleDto);
      mockInvoiceDto.setLines(mockLines);
      
      Mockito.when(mockPersistenceService.findById(testId, Invoice.class)).thenReturn(mockInvoice);
      Mockito.when(mockBasicService.convert(mockInvoice, InvoiceDto.class)).thenReturn(mockInvoiceDto);
      
      InvoiceServiceImpl service = new InvoiceServiceImpl(mockBasicService, mockPersistenceService, new DefaultReportServiceImpl(ReportsConfiguration.LOCATION, new DefaultReportLoader()));
      
      final ExportResult result = service.export(testId);
      
      final Path tmp = Files.createTempFile("TEST", ".pdf");
      
      Files.write(tmp, result.getContent());
      
   }
}
