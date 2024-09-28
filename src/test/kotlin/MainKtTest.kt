import org.junit.Test

import org.junit.Assert.*
import org.junit.Assert.assertEquals
class MainKtTest {

    @Test
    fun calculationCommission() {
        val count = 222
        val result = calculationCommission(count)

        assertEquals(355.0, result,0.001)
    }
}