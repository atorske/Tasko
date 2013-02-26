package task



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(List)
class ListTests {

/*
    void testLog() {
      println "testing the logging in unit tests ***"
    }
*/

    void testSave() {
      List list = new List()
      list.name = "Test List"
      list.cards = []
      Card card = new Card()
      card.description = "Test Card"
      Card card2 = new Card()
      card2.description = "Test Card"
      list.cards << card
      list.cards << card2
      list.save()
      def lists = List.findAll()
      assert lists.size() == 1
      List l = lists[0] 
      assert l.cards != null
      assert l.cards.size() == 2
      def cardsArray = l.cards.asList()
      assert ArrayList.class == cardsArray.class
      Card c = (Card) cardsArray.getAt(0)
      assert c.description == "Test Card"
    }

/*
    void testSomething() {
       fail "Implement me"
    }
*/
}
