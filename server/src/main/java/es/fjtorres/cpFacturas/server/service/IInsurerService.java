package es.fjtorres.cpFacturas.server.service;

import es.fjtorres.cpFacturas.common.dto.InsurerDto;
import es.fjtorres.cpFacturas.common.dto.pagination.InsurerPageDto;
import es.fjtorres.cpFacturas.server.model.Insurer;

public interface IInsurerService extends
      IEntityService<Insurer, InsurerDto, Long> {

   /**
    * 
    * @param page
    * @param pageSize
    * @param sortField
    * @param sortDirection
    * @return
    * @throws IllegalArgumentException
    *            If any parameter are invalid.
    */
   InsurerPageDto find(int page, int pageSize, String sortField,
         String sortDirection) throws IllegalArgumentException;

}
