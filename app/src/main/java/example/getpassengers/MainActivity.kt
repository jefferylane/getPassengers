/*  Jeffery Lane
    CS3013-001 Spring 2025
    Get Passengers App

    MainActivity.kt

    This file controls access to the second activity, GetPassengers.kt
    and accepts the returned data to display.*/

package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var listText: TextView? = null

    //Starts second activity, waits for result.
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data

            if (data == null) {
                println("DEBUG: No data received from GetPassengers.kt")
                return@registerForActivityResult
            }

            val count = data.getStringExtra("COUNT")?.toIntOrNull() ?: 0

            listText?.text = "Returned Passenger List:"

            for (i in 1..count) {
                val passengerData = data.getStringExtra("PASS$i") ?: "Unknown Passenger"
                listText?.append("\n$passengerData")
            }
        }

    //Updated oncreate yo make listtext
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listText = findViewById(R.id.show_list)
    }

    //Launch startForResult
    fun getList(v: View) {
        startForResult.launch(
            Intent(this, GetPassengers::class.java)
        )
    }
}
//Defines the Passenger class
class Passenger(var fName: String, var lName: String, var phone: String) {

    //Overrides toString to be more readable
    override fun toString(): String {
        return "$fName $lName, Phone: $phone"
    }
}