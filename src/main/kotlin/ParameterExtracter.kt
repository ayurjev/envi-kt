
package envi.core

import java.util.regex.Matcher
import java.util.HashMap

import envi.exceptions.UrlPatternMismatch


public class ParameterExtracter {
    public fun extract(route: String, routePattern: String): Map<String, Any> {

        val names: Array<String> = routePattern.trimTrailing("/").split("/")
        val values: Array<String> = route.trimTrailing("/").split("/")
        if (names.size != values.size) {
            throw UrlPatternMismatch()
        }
        var result: MutableMap<String, String> = HashMap();
        for (name in names) {
            if (name.matches("<.+?>")) {
                val trimmed_name = name.trim("<", ">")
                result.put(trimmed_name, values.get(names.indexOf(name)))
            }
        }

        return result
    }

}