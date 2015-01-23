package es.fjtorres.cpFacturas.common.pagination;

import es.fjtorres.cpFacturas.common.exception.ExceptionUtils;

public enum OrderBy {
   ASC, DESC;

   /**
    * Convert string to {@link OrderBy}.
    * 
    * @param str
    *           String to convert.
    * @return Return OrderBy.
    * @throws IllegalArgumentException
    *            If str isn't {@link OrderBy} option.
    */
   public static OrderBy fromString(final String str) {
      OrderBy order = null;
      try {
         order = OrderBy.valueOf(str);
      } catch (final IllegalArgumentException iae) {
         ExceptionUtils
               .throwIllegalArgument("sort direction isn't valid. Only ASC or DESC.");
      }

      return order;
   }
}