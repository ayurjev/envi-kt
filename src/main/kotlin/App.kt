package custom

import javax.servlet.annotation.WebServlet

import envi.Application
import envi.Controller

WebServlet(urlPatterns=array("/*"))
class App: Application() {

    override fun init() {
        this.route("/envi/ping/", Controller());
    }

}


