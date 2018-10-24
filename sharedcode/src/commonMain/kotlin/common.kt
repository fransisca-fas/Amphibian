package fas.sharedcode

import fas.sharedcode.data.AllowedLoan
import fas.sharedcode.data.DefaultSimulation
import fas.sharedcode.data.LoanOption

expect fun platformName(): String

expect fun formatCurrency(installment: Double): String

fun createScreenMessage() = "Amphibian on ${platformName()}"

fun calculateInstallment(amount: Int, tenure: Int): Double {
    val interestRate: Double = 0.12
    val interest = amount * interestRate * 30 / 360 * tenure

    return (amount / tenure) + interest
}

fun provideLoanOption(): LoanOption {
    val defaultSimulation = DefaultSimulation(1000000, 1, 1296000.0)
    val allowedLoans = arrayListOf<AllowedLoan>(
        AllowedLoan(500000, arrayListOf(1)),
        AllowedLoan(1000000, arrayListOf(1)),
        AllowedLoan(2000000, arrayListOf(1)),
        AllowedLoan(3000000, arrayListOf(1, 2, 3)),
        AllowedLoan(5000000, arrayListOf(1, 2, 3)),
        AllowedLoan(7000000, arrayListOf(1, 2, 3, 4, 5, 6)),
        AllowedLoan(8000000, arrayListOf(1, 2, 3, 4, 5, 6))
    )

    return LoanOption(allowedLoans, defaultSimulation)
}