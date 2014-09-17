
package envi.core

import java.util.HashMap

import envi.Controller
import envi.exceptions.NotFound


public class ControllerFactory {

    private var routes: MutableMap<String, Controller> = HashMap();

    public fun get(route: String): Controller {
        val fast_acs_controller = this.routes.get(route)
        if (fast_acs_controller != null) return fast_acs_controller;

        for ((route_pattern, controller) in this.routes) {
            if (this.isMatch(route, route_pattern)) {
                return controller;
            }
        }
        throw NotFound()
    }

    public fun set(route: String, controller: Controller) {
        this.routes.put(route, controller);
    }

    public fun isMatch(route: String, routePattern: String): Boolean {
        return route.trimTrailing("/").matches(routePattern.trimTrailing("/").replaceAll("(/<.+?>)", "/([^/]+?)[/]*"))
    }

}