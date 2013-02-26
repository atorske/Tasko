package task



import org.junit.*
import grails.test.mixin.*

@TestFor(ListController)
@Mock(List)
class ListControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/list/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.listInstanceList.size() == 0
        assert model.listInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.listInstance != null
    }

    void testSave() {
        controller.save()

        assert model.listInstance != null
        assert view == '/list/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/list/show/1'
        assert controller.flash.message != null
        assert List.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/list/list'

        populateValidParams(params)
        def list = new List(params)

        assert list.save() != null

        params.id = list.id

        def model = controller.show()

        assert model.listInstance == list
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/list/list'

        populateValidParams(params)
        def list = new List(params)

        assert list.save() != null

        params.id = list.id

        def model = controller.edit()

        assert model.listInstance == list
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/list/list'

        response.reset()

        populateValidParams(params)
        def list = new List(params)

        assert list.save() != null

        // test invalid parameters in update
        params.id = list.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/list/edit"
        assert model.listInstance != null

        list.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/list/show/$list.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        list.clearErrors()

        populateValidParams(params)
        params.id = list.id
        params.version = -1
        controller.update()

        assert view == "/list/edit"
        assert model.listInstance != null
        assert model.listInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/list/list'

        response.reset()

        populateValidParams(params)
        def list = new List(params)

        assert list.save() != null
        assert List.count() == 1

        params.id = list.id

        controller.delete()

        assert List.count() == 0
        assert List.get(list.id) == null
        assert response.redirectedUrl == '/list/list'
    }
}
