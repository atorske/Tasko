package task

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class ListController {

    def index() {
        render( List.findAll() as JSON)
    }

/*
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [listInstanceList: List.list(params), listInstanceTotal: List.count()]
    }

    def create() {
        [listInstance: new List(params)]
    }

    def save() {
        def listInstance = new List(params)
        if (!listInstance.save(flush: true)) {
            render(view: "create", model: [listInstance: listInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'list.label', default: 'List'), listInstance.id])
        redirect(action: "show", id: listInstance.id)
    }

    def show(Long id) {
        def listInstance = List.get(id)
        if (!listInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'list.label', default: 'List'), id])
            redirect(action: "list")
            return
        }

        [listInstance: listInstance]
    }

    def edit(Long id) {
        def listInstance = List.get(id)
        if (!listInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'list.label', default: 'List'), id])
            redirect(action: "list")
            return
        }

        [listInstance: listInstance]
    }

    def update(Long id, Long version) {
        def listInstance = List.get(id)
        if (!listInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'list.label', default: 'List'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (listInstance.version > version) {
                listInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'list.label', default: 'List')] as Object[],
                          "Another user has updated this List while you were editing")
                render(view: "edit", model: [listInstance: listInstance])
                return
            }
        }

        listInstance.properties = params

        if (!listInstance.save(flush: true)) {
            render(view: "edit", model: [listInstance: listInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'list.label', default: 'List'), listInstance.id])
        redirect(action: "show", id: listInstance.id)
    }

    def delete(Long id) {
        def listInstance = List.get(id)
        if (!listInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'list.label', default: 'List'), id])
            redirect(action: "list")
            return
        }

        try {
            listInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'list.label', default: 'List'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'list.label', default: 'List'), id])
            redirect(action: "show", id: id)
        }
    }
*/
}
