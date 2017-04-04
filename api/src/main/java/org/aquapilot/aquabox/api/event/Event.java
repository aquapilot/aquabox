package org.aquapilot.aquabox.api.event;

/**
 * Created by vermeille on 04.04.2017.
 */
public enum Event {

   STATE_CHANGED_EVENT(StateChangedEvent.class), SENSOR_VALUE_CHANGED_EVENT(SensorValueChangeEvent.class);

   private Class<? extends AquaboxEvent> clazz;

   Event(Class<? extends AquaboxEvent> clazz) {

      this.clazz = clazz;
   }

   public static Event valueOf(Class<? extends AquaboxEvent> clazz) {

      for (Event event : Event.values()) {

         if (event.getAssociatedClass().equals(clazz)) {
            return event;
         }
      }
      return null; // Todo throw exception
   }

   public Class<? extends AquaboxEvent> getAssociatedClass() {

      return clazz;
   }
}
