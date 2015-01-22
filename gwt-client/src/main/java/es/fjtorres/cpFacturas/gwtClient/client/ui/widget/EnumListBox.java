package es.fjtorres.cpFacturas.gwtClient.client.ui.widget;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.HasValue;

public class EnumListBox<T extends Enum<T>> extends
      org.gwtbootstrap3.client.ui.ListBox implements HasValue<T>,
      LeafValueEditor<T> {

   private final Class<T> clazzOfEnum;
   private boolean valueChangeHandlerInitialized;
   private T defaultValue;

   public EnumListBox(final Class<T> clazzOfEnum,
         final ConstantsWithLookup constants, final T pDefaultValue) {
      if (clazzOfEnum == null) {
         throw new IllegalArgumentException("Enum class cannot be null");
      }

      this.clazzOfEnum = clazzOfEnum;
      this.defaultValue = pDefaultValue;
      EnumTranslator enumTranslator = new EnumTranslator(constants);

      T[] values = clazzOfEnum.getEnumConstants();

      for (T value : values) {
         // this.addItem(constant.toString(), constant.name());
         this.addItem(enumTranslator.getText(value), value.name());
      }
   }

   public T getEnumValue() {
      if (getSelectedIndex() >= 0) {
         final String name = getSelectedValue();

         T[] values = clazzOfEnum.getEnumConstants();
         for (T value : values) {
            if (value.name().equals(name)) {
               return value;
            }
         }
      }

      return null;
   }

   public void setSelectedValue(T value) {
      if (value == null) {
         value = defaultValue;
      }
      T[] values = clazzOfEnum.getEnumConstants();
      for (int i = 0; i < values.length; i++) {
         if (values[i] == value) {
            this.setSelectedIndex(i);
            return;
         }
      }
      throw new IllegalArgumentException("No index found for value "
            + value.toString());
   }

   /*
    * Methods to implement HasValue
    */

   @Override
   public T getValue() {
      return this.getEnumValue();
   }

   @Override
   public void setValue(final T value) {
      this.setValue(value, false);
   }

   @Override
   public void setValue(final T value, final boolean fireEvents) {
      T oldValue = getValue();
      this.setSelectedValue(value);
      if (fireEvents) {
         ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
      }
   }

   @Override
   public HandlerRegistration addValueChangeHandler(
         final ValueChangeHandler<T> handler) {
      // Initialization code
      if (!valueChangeHandlerInitialized) {
         valueChangeHandlerInitialized = true;
         super.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(final ChangeEvent event) {
               ValueChangeEvent.fire(EnumListBox.this, getValue());
            }
         });
      }
      return addHandler(handler, ValueChangeEvent.getType());
   }

}
