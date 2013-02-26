// An example Backbone application contributed by
// [Jérôme Gravel-Niquet](http://jgn.me/). This demo uses a simple
// [LocalStorage adapter](backbone-localstorage.html)
// to persist Backbone models within your browser.

// Load the application once the DOM is ready, using `jQuery.ready`:
$(function(){

    _.templateSettings = {
        interpolate : /\{\{(.+?)\}\}/g,
        evaluate : /\{!(.+?)!\}/g
    };

  // Card Model
  // ----------

  // Our basic **Card** model has `description`, `order`, and `done` attributes.
  window.Card = Backbone.Model.extend({

    // Default attributes for a card item.
    defaults: function() {
      return {
        done:  false,
        order: Cards.nextOrder()
      };
    },

    // Toggle the `done` state of this card item.
    toggle: function() {
      this.save({done: !this.get("done")});
    }

  });

  // Card Collection
  // ---------------

  // The collection of cards is backed by *localStorage* instead of a remote
  // server.
  window.CardList = Backbone.Collection.extend({

    // Reference to this collection's model.
    model: Card,

    url: '/card',

    // Filter down the list of all card items that are finished.
    done: function() {
      return this.filter(function(card){ return card.get('done'); });
    },

    // Filter down the list to only card items that are still not finished.
    remaining: function() {
      return this.without.apply(this, this.done());
    },

    // We keep the Cards in sequential order, despite being saved by unordered
    // GUID in the database. This generates the next order number for new items.
    nextOrder: function() {
      if (!this.length) return 1;
      return this.last().get('order') + 1;
    },

    // Cards are sorted by their original insertion order.
    comparator: function(card) {
      return card.get('order');
    }

  });

  // Create our global collection of **Cards**.
  window.Cards = new CardList;

  // Card Item View
  // --------------

  // The DOM element for a card item...
  window.CardView = Backbone.View.extend({

    //... is a list tag.
    tagName:  "li",

    // Cache the template function for a single item.
    template: _.template($('#item-template').html()),

    // The DOM events specific to an item.
    events: {
      "click .check"              : "toggleDone",
      "dblclick div.card-text"    : "edit",
      "click span.card-destroy"   : "clear",
      "keypress .card-input"      : "updateOnEnter"
    },

    // The CardView listens for changes to its model, re-rendering.
    initialize: function() {
      this.model.bind('change', this.render, this);
      this.model.bind('destroy', this.remove, this);
    },

    // Re-render the contents of the card item.
    render: function() {
      $(this.el).html(this.template(this.model.toJSON()));
      this.setDescription();
      return this;
    },

    // To avoid XSS (not that it would be harmful in this particular app),
    // we use `jQuery.text` to set the contents of the card item.
    setDescription: function() {
      var description = this.model.get('description');
      this.$('.card-text').text(description);
      this.input = this.$('.card-input');
      this.input.bind('blur', _.bind(this.close, this)).val(description);
    },

    // Toggle the `"done"` state of the model.
    toggleDone: function() {
      this.model.toggle();
    },

    // Switch this view into `"editing"` mode, displaying the input field.
    edit: function() {
      $(this.el).addClass("editing");
      this.input.focus();
    },

    // Close the `"editing"` mode, saving changes to the card.
    close: function() {
      this.model.save({description: this.input.val()});
      $(this.el).removeClass("editing");
    },

    // If you hit `enter`, we're through editing the item.
    updateOnEnter: function(e) {
      if (e.keyCode == 13) this.close();
    },

    // Remove this view from the DOM.
    remove: function() {
      $(this.el).remove();
    },

    // Remove the item, destroy the model.
    clear: function() {
      this.model.destroy();
    }

  });

  // The Application
  // ---------------

  // Our overall **AppView** is the top-level piece of UI.
  window.AppView = Backbone.View.extend({

    // Instead of generating a new element, bind to the existing skeleton of
    // the App already present in the HTML.
    el: $("#cardapp"),

    // Our template for the line of statistics at the bottom of the app.
    statsTemplate: _.template($('#stats-template').html()),

    // Delegated events for creating new items, and clearing completed ones.
    events: {
      "keypress #new-card":  "createOnEnter",
      "keyup #new-card":     "showTooltip",
      "click .card-clear a": "clearCompleted"
    },

    // At initialization we bind to the relevant events on the `Cards`
    // collection, when items are added or changed. Kick things off by
    // loading any preexisting cards that might be saved in *localStorage*.
    initialize: function() {
      this.input    = this.$("#new-card");

      Cards.bind('add',   this.addOne, this);
      Cards.bind('reset', this.addAll, this);
      Cards.bind('all',   this.render, this);

      Cards.fetch();
    },

    // Re-rendering the App just means refreshing the statistics -- the rest
    // of the app doesn't change.
    render: function() {
      this.$('#card-stats').html(this.statsTemplate({
        total:      Cards.length,
        done:       Cards.done().length,
        remaining:  Cards.remaining().length
      }));
    },

    // Add a single card item to the list by creating a view for it, and
    // appending its element to the `<ul>`.
    addOne: function(card) {
      var view = new CardView({model: card});
      $("#card-list").append(view.render().el);
    },

    // Add all items in the **Cards** collection at once.
    addAll: function() {
      Cards.each(this.addOne);
    },

    // If you hit return in the main input field, and there is text to save,
    // create new **Card** model persisting it to *localStorage*.
    createOnEnter: function(e) {
      var description = this.input.val();
      if (!description || e.keyCode != 13) return;
      Cards.create({description: description});
      this.input.val('');
    },

    // Clear all done card items, destroying their models.
    clearCompleted: function() {
      _.each(Cards.done(), function(card){ card.destroy(); });
      return false;
    },

    // Lazily show the tooltip that tells you to press `enter` to save
    // a new card item, after one second.
    showTooltip: function(e) {
      var tooltip = this.$(".ui-tooltip-top");
      var val = this.input.val();
      tooltip.fadeOut();
      if (this.tooltipTimeout) clearTimeout(this.tooltipTimeout);
      if (val == '' || val == this.input.attr('placeholder')) return;
      var show = function(){ tooltip.show().fadeIn(); };
      this.tooltipTimeout = _.delay(show, 1000);
    }

  });

  // Finally, we kick things off by creating the **App**.
  window.App = new AppView;

});
