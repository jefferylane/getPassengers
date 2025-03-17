/*  Jeffery Lane
    CS3013-001 Spring 2025
    Get Passengers App

    GetPassengers.kt

    This file controls entering new passengers, generating the list, and sending the data
    back to the main activity.*/
package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GetPassengers : AppCompatActivity() {
    var passList: MutableList<Passenger> = ArrayList<Passenger>()

    //Override onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)
    }
    /*Process the information the first, last, and phone number fields, create a passenger
    object, add it to the list, display it, and clear the input fields.*/
    fun enterPassenger(v: View) {
        val firstName = findViewById<EditText>(R.id.first_name).text.toString()
        val lastName = findViewById<EditText>(R.id.last_name).text.toString()
        val phone = findViewById<EditText>(R.id.phone_number).text.toString()

        val newPassenger = Passenger(firstName, lastName, phone)

        passList.add(newPassenger)

        findViewById<TextView>(R.id.accum_list).append("\n${newPassenger.fName} ${newPassenger.lName}, ${newPassenger.phone}")

        findViewById<EditText>(R.id.first_name).text.clear()
        findViewById<EditText>(R.id.last_name).text.clear()
        findViewById<EditText>(R.id.phone_number).text.clear()
    }

    /*Create an intent, add the passenger count, loop through the list and add the passengers,
    add the passengers to the intent as strings, & send it back to main and close. */
    fun backToMain(v: View) {
        val intent = Intent()

        intent.putExtra("COUNT", passList.size.toString())

        for (i in passList.indices) {
            val passenger = passList[i]
            val key = "PASS${i + 1}"

            intent.putExtra(key, passenger.toString())
        }

        setResult(RESULT_OK, intent)
        finish()
    }
}