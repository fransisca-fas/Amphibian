package fas.amphibian

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import fas.sharedcode.calculateInstallment
import fas.sharedcode.createScreenMessage
import fas.sharedcode.data.AllowedLoan
import fas.sharedcode.data.DefaultSimulation
import fas.sharedcode.provideLoanOption
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var defaultSimulation: DefaultSimulation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = createScreenMessage()

        val loanOption = provideLoanOption()
        defaultSimulation = loanOption.defaultSimulation
        populateLoanAmount(loanOption.allowedLoans)
    }

    fun populateLoanAmount(allowedLoans: ArrayList<AllowedLoan>) {
        val loans: ArrayList<Int> = ArrayList()
        var defaultAmountIndex = 0

        allowedLoans.forEachIndexed { index, allowedLoan ->
            loans.add(allowedLoan.amount)

            if (allowedLoan.amount == defaultSimulation.loanAmount) {
                defaultAmountIndex = index
            }
        }

        var amountAdapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item).apply {
            addAll(loans)
        }

        loanAmount.run {
            adapter = amountAdapter
            setSelection(defaultAmountIndex)
            onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    populateTenure(allowedLoans[position].tenures, amountAdapter.getItem(position))
                }

            }
        }
    }

    fun populateTenure(tenures: ArrayList<Int>, selectedAmount: Int) {
        var defaultTenureIndex = 0

        tenures.forEachIndexed { index, tenure ->
            if (selectedAmount == defaultSimulation.loanAmount && tenure == defaultSimulation.loanTenure) {
                 defaultTenureIndex = index
            }
        }

        var tenureAdapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item).apply {
            addAll(tenures)
        }

        loanTenure.run {
            adapter = tenureAdapter
            setSelection(defaultTenureIndex)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    installment.text = calculateInstallment(selectedAmount, tenureAdapter.getItem(position)).toString()
                }
            }
        }
    }
}
