package com.mwu

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class EntriesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", draw: "GET"]

    def index() {
		def e = Entries.list()
		Collections.shuffle(e)
		[entriesInstanceList: e[0..9], entriesInstanceTotal: Entries.count()]
    }

    def draw() {
		def e = Entries.list()
		Collections.shuffle(e)
		def selectedId = e.get(0).id
		Collections.shuffle(e)
		render(contentType: "text/json") {
	            selected = selectedId
				list = e
		}
    }

    def list(Integer max) {
       	params.max = Math.min(max ?: 10, 100)
       	[entriesInstanceList: Entries.list(params), entriesInstanceTotal: Entries.count()]
    }

    def create() {
        [entriesInstance: new Entries(params)]
    }

    def save() {
        def entriesInstance = new Entries(params)
        if (!entriesInstance.save(flush: true)) {
            render(view: "create", model: [entriesInstance: entriesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'entries.label', default: 'Entries'), entriesInstance.id])
        redirect(action: "show", id: entriesInstance.id)
    }

    def show(Long id) {
        def entriesInstance = Entries.get(id)
        if (!entriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entries.label', default: 'Entries'), id])
            redirect(action: "list")
            return
        }

        [entriesInstance: entriesInstance]
    }

    def edit(Long id) {
        def entriesInstance = Entries.get(id)
        if (!entriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entries.label', default: 'Entries'), id])
            redirect(action: "list")
            return
        }

        [entriesInstance: entriesInstance]
    }

    def update(Long id, Long version) {
        def entriesInstance = Entries.get(id)
        if (!entriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entries.label', default: 'Entries'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (entriesInstance.version > version) {
                entriesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'entries.label', default: 'Entries')] as Object[],
                          "Another user has updated this Entries while you were editing")
                render(view: "edit", model: [entriesInstance: entriesInstance])
                return
            }
        }

        entriesInstance.properties = params

        if (!entriesInstance.save(flush: true)) {
            render(view: "edit", model: [entriesInstance: entriesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'entries.label', default: 'Entries'), entriesInstance.id])
        redirect(action: "show", id: entriesInstance.id)
    }

    def delete(Long id) {
        def entriesInstance = Entries.get(id)
        if (!entriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entries.label', default: 'Entries'), id])
            redirect(action: "list")
            return
        }

        try {
            entriesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'entries.label', default: 'Entries'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entries.label', default: 'Entries'), id])
            redirect(action: "show", id: id)
        }
    }
}
