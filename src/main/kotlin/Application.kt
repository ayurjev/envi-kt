package envi

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import java.util.HashMap

import envi.Controller
import envi.core.ControllerFactory
import envi.exceptions.NotFound

public open class Application : HttpServlet() {

    private val cf: ControllerFactory = ControllerFactory()

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val uri: String? = req?.getRequestURI();
        if (uri != null) {
            resp?.getWriter()?.write(uri);
            val controller = this.cf.get(uri)
        }
    }

    public fun route(route: String, controller: Controller) {
        this.cf.set(route, controller)
    }

}