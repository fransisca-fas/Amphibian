package fas.sharedcode

import java.text.NumberFormat
import java.util.*

actual fun platformName() = "Android"

actual fun formatCurrency(installment: Double): String {
    return NumberFormat.getIntegerInstance(Locale("in", "ID")).format(installment)
}