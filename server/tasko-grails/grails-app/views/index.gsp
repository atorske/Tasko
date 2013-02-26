<!DOCTYPE html>
<html>

  <head>
    <title>Backbone Demo: Cards</title>
    <r:require module="card" />
    <meta name="layout" content="main">
  </head>

  <body>

    <!-- Card App Interface -->

    <div id="cardapp">

      <div class="title">
        <h1>Card</h1>
      </div>

      <div class="content">

        <div id="create-card">
          <input id="new-card" placeholder="What needs to be done?" type="text" />
          <span class="ui-tooltip-top" style="display:none;">Press Enter to save this task</span>
        </div>

        <div id="cards">
          <ul id="card-list"></ul>
        </div>

        <div id="card-stats"></div>

      </div>

    </div>

    <!-- Templates -->

    <script type="text/template" id="item-template">
      <div class="card {{ done ? 'done' : '' }}">
        <div class="display">
          <input class="check" type="checkbox" {{ done ? 'checked="checked"' : '' }} />
          <div class="card-text"></div>
          <span class="card-destroy"></span>
        </div>
        <div class="edit">
          <input class="card-input" type="text" value="" />
        </div>
      </div>
    </script>

    <script type="text/template" id="stats-template">
      {! if (total) { !}
        <span class="card-count">
          <span class="number">{{ remaining }}</span>
          <span class="word">{{ remaining == 1 ? 'item' : 'items' }}</span> left.
        </span>
      {! } !}
      {! if (done) { !}
        <span class="card-clear">
          <a href="#">
            Clear <span class="number-done">{{ done }}</span>
            completed <span class="word-done">{{ done == 1 ? 'item' : 'items' }}</span>
          </a>
        </span>
      {! } !}
    </script>

  </body>

</html>
