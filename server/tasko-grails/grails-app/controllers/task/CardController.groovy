package task

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class CardController {

    def index() {
        render( Card.findAll() as JSON)
    }

   def save() {
      def card = new Card(request.JSON)
      render( card.save() as JSON )
   }

   def delete() {
      def card = Card.findById(params.id)
      card?.delete()
      render( card as JSON )
   }

   def edit() {
      def card = Card.findById(params.id)
      bindData(card, request.JSON)
      render( card.save() as JSON )
   }
}
