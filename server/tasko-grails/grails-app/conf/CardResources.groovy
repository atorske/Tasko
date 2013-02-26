modules = {
   card {
      dependsOn 'jquery, underscore, backbone'

      resource url: '/card.css'
      resource url: '/card.js'
   }
   backbone {
      resource url: '/backbone.js'
   }
   underscore {
      resource url: '/underscore.js'
   }
}
