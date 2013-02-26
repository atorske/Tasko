import task.*
class BootStrap {

    def init = { servletContext ->
      List list = new List()
      list.name = "Test List"
      Card c = new Card()
      c.setDescription("Hello")
      list.cards = []
      list.cards << c
      c = new Card()
      c.setDescription("World")
      list.cards << c
      list.save()
    }
    def destroy = {
    }
}
