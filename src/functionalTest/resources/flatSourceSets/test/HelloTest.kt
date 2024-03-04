import kotlin.test.Test
import kotlin.test.assertEquals

class HelloTest {
    @Test
    fun `say hello should return hello world`() {
        val hello = Hello()
        assertEquals("Hello world!", hello.sayHello())
    }
}
