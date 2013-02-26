package task



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Card)
class CardTests {

    void testSave() {
      Card c = new Card()
      c.description = "hello"
      c.save()
      def cards = Card.findAll()
      assert cards.size() == 1 
    }

/*
    void testSomething() {
       fail "Implement me"
    }
*/
}
