package task

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class ListController {

    def index() {
        render( List.findAll() as JSON)
    }

   def save() {
      def list = new List(request.JSON)
      render( list.save() as JSON )
   }

   def delete() {
      def list = List.findById(params.id)
      list?.delete()
      render( list as JSON )
   }

   def edit() {
      def list = List.findById(params.id)
      bindData(list, request.JSON)
      render( list.save() as JSON )
   }
}
