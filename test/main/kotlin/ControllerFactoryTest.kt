package envi.tests.ControllerFactory;

import kotlin.test.*
import org.junit.Test as test
import org.junit.Before as setup

import envi.core.ControllerFactory
import envi.Controller
import envi.exceptions.NotFound


/**
 * Tests for {@link ControllerFactory}.
 *
 * @author andrey.yurjev@gmail.com (ayurjev)
 */
public class ControllerFactoryTest {

    private var cf: ControllerFactory = ControllerFactory();

    setup public fun setUp() {
        this.cf = ControllerFactory();
    }

    test public fun emptyFactory() {
        failsWith(javaClass<NotFound>()) { this.cf.get("/"); }
        failsWith(javaClass<NotFound>()) { this.cf.get("/test/"); }
    }

    test public fun setNewController() {
        failsWith(javaClass<NotFound>()) { this.cf.get("/"); }
        this.cf.set("/", Controller())
        assertTrue() { this.cf.get("/") is Controller }

        failsWith(javaClass<NotFound>()) { this.cf.get("/test/"); }
        this.cf.set("/test/", Controller())
        assertTrue() { this.cf.get("/test/") is Controller }
    }

    test public fun parametrizedRoutes() {
        failsWith(javaClass<NotFound>()) { this.cf.get("/test/go/"); }
        this.cf.set("/test/<action>/", Controller())
        assertTrue() { this.cf.get("/test/go/") is Controller }

        failsWith(javaClass<NotFound>()) { this.cf.get("/test/go/view/"); }
        this.cf.set("/test/<action>/view/", Controller())
        assertTrue() { this.cf.get("/test/go/view/") is Controller }

        failsWith(javaClass<NotFound>()) { this.cf.get("/test2/go/view/"); }
        this.cf.set("/test2/<action>/<action2>/", Controller())
        assertTrue() { this.cf.get("/test2/go/view/") is Controller }
        assertTrue() { this.cf.get("/test2/go/view") is Controller }
    }

    test public fun isMatch() {
        assertTrue() { this.cf.isMatch("/test/go", "/test/<action>/") }
        assertTrue() { this.cf.isMatch("/test/go/", "/test/<action>/") }
        assertTrue() { this.cf.isMatch("/test/go/view/", "/test/<action>/view/") }
        assertTrue() { this.cf.isMatch("/test/go/view/", "/test/<action>/<view>/") }
        assertTrue() { this.cf.isMatch("/test/go/test/", "/test/<action>/<view>/") }
        assertTrue() { this.cf.isMatch("/test", "/<action>/") }
        assertTrue() { this.cf.isMatch("/test/", "/<action>/") }
    }

}