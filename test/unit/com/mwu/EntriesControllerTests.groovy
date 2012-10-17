package com.mwu



import org.junit.*
import grails.test.mixin.*

@TestFor(EntriesController)
@Mock(Entries)
class EntriesControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/entries/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.entriesInstanceList.size() == 0
        assert model.entriesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.entriesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.entriesInstance != null
        assert view == '/entries/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/entries/show/1'
        assert controller.flash.message != null
        assert Entries.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/entries/list'

        populateValidParams(params)
        def entries = new Entries(params)

        assert entries.save() != null

        params.id = entries.id

        def model = controller.show()

        assert model.entriesInstance == entries
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/entries/list'

        populateValidParams(params)
        def entries = new Entries(params)

        assert entries.save() != null

        params.id = entries.id

        def model = controller.edit()

        assert model.entriesInstance == entries
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/entries/list'

        response.reset()

        populateValidParams(params)
        def entries = new Entries(params)

        assert entries.save() != null

        // test invalid parameters in update
        params.id = entries.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/entries/edit"
        assert model.entriesInstance != null

        entries.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/entries/show/$entries.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        entries.clearErrors()

        populateValidParams(params)
        params.id = entries.id
        params.version = -1
        controller.update()

        assert view == "/entries/edit"
        assert model.entriesInstance != null
        assert model.entriesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/entries/list'

        response.reset()

        populateValidParams(params)
        def entries = new Entries(params)

        assert entries.save() != null
        assert Entries.count() == 1

        params.id = entries.id

        controller.delete()

        assert Entries.count() == 0
        assert Entries.get(entries.id) == null
        assert response.redirectedUrl == '/entries/list'
    }
}
