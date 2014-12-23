package es.fjtorres.cpFacturas.common.pagination;

import java.io.Serializable;

public class PageFilter implements Serializable {

   private static final long serialVersionUID = -8039861156535572760L;

   private int page;

   private int pageSize;

   public int getPage() {
      return page;
   }

   public void setPage(final int page) {
      this.page = page;
   }

   public int getPageSize() {
      return pageSize;
   }

   public void setPageSize(final int pageSize) {
      this.pageSize = pageSize;
   }

}
