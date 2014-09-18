package envi.tests.ParameterExtracterTest;

import java.util.HashMap

import kotlin.test.*
import org.junit.Test as test
import org.junit.Before as setup

import envi.core.ParameterExtracter
import envi.exceptions.UrlPatternMismatch

/**
 * Tests for {@link ParameterExtracter}.
 *
 * @author andrey.yurjev@gmail.com (ayurjev)
 */
public class ParameterExtracterTest {

    private var pe: ParameterExtracter = ParameterExtracter();

    setup public fun setUp() {
        this.pe = ParameterExtracter();
    }

    test public fun extractParameters() {
        assertEquals(mapOf("action" to "go"), this.pe.extract("/test/go", "/test/<action>/"))
        assertEquals(mapOf("action" to "go"), this.pe.extract("/test/go/", "/test/<action>/"))
        assertEquals(mapOf("action" to "go", "param" to "222"), this.pe.extract("/test/go/222", "/test/<action>/<param>"))
        assertEquals(mapOf("action" to "go", "param" to "222"), this.pe.extract("/test/go/222", "/test/<action>/<param>/"))
        assertEquals(mapOf("action" to "go", "param" to "222"), this.pe.extract("/test/go/222/", "/test/<action>/<param>"))
        assertEquals(mapOf("action" to "go", "param" to "222"), this.pe.extract("/test/go/222/", "/test/<action>/<param>/"))

        failsWith(javaClass<UrlPatternMismatch>()) { this.pe.extract("/test/go/", "/test/test/<action>/") }
        failsWith(javaClass<UrlPatternMismatch>()) { this.pe.extract("/test/go/foo/bar", "/test/test/<action>/") }
    }

}
