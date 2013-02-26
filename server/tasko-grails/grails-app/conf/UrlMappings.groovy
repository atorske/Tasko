class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
      "/list/$id?"(controller: "list") {
         action = [GET:"list"]
      }
      "/card/$id?"(controller: "card") {
         action = [GET:"list", POST:"save", DELETE:"delete", PUT:"edit"]
      }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
