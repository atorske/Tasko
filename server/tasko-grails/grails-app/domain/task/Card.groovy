package task

class Card {
   static belongsTo = List
   String description
   Boolean done
   Integer order

   static mapping = {
      order column:'c_order'
   }

    static constraints = {
    }
}
