package es.fjtorres.cpFacturas.common.dto.pagination;

import java.util.Collections;
import java.util.List;

import es.fjtorres.cpFacturas.common.dto.VehicleDto;
import es.fjtorres.cpFacturas.common.pagination.Page;

public class VehiclePageDto implements Page<VehicleDto> {

   /**
    * 
    */
   private static final long serialVersionUID = 3811109933097314920L;

   private long total;

   private List<VehicleDto> list;

   public VehiclePageDto() {
      this.total = 0;
      this.list = Collections.emptyList();
   }

   /**
    * @param pTotal
    * @param pList
    */
   public VehiclePageDto(final long pTotal, final List<VehicleDto> pList) {
      total = pTotal;
      list = pList;
   }

   @Override
   public long getTotal() {
      return total;
   }

   @Override
   public void setTotal(final long total) {
      this.total = total;
   }

   @Override
   public List<VehicleDto> getList() {
      return list;
   }

   @Override
   public void setList(final List<VehicleDto> list) {
      this.list = list;
   }
}
