package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GetPassengers : AppCompatActivity() {
    var passList: MutableList<Passenger> = ArrayList<Passenger>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)
    }

    fun enterPassenger(v: View) {
        // Step 1: Get the input fields and extract the text
        val firstName = findViewById<EditText>(R.id.first_name).text.toString()
        val lastName = findViewById<EditText>(R.id.last_name).text.toString()
        val phone = findViewById<EditText>(R.id.phone_number).text.toString()

        // Step 2: Create a Passenger object
        val newPassenger = Passenger(firstName, lastName, phone)

        // Step 3: Add it to the list
        passList.add(newPassenger)

        // Step 4: Display the updated list in the TextView
        findViewById<TextView>(R.id.accum_list).append("\n${newPassenger.fName} ${newPassenger.lName}, ${newPassenger.phone}")

        // Step 5: Clear input fields
        findViewById<EditText>(R.id.first_name).text.clear()
        findViewById<EditText>(R.id.last_name).text.clear()
        findViewById<EditText>(R.id.phone_number).text.clear()
    }

    fun backToMain(v: View) {
        // Step 1: Create an Intent to send data back
        val intent = Intent()

        // Step 2: Add the passenger count (so MainActivity knows how many passengers to expect)
        intent.putExtra("COUNT", passList.size.toString())  // Convert size to string

        // Step 3: Loop through passList and add each passenger to the Intent
        for (i in passList.indices) {
            val passenger = passList[i]
            val key = "PASS${i + 1}"  // Create keys like PASS1, PASS2, etc.

            // Step 4: Put each passenger's string version into the Intent
            intent.putExtra(key, passenger.toString())  // Add passenger data to Intent
        }

        // Step 5: Send data back to MainActivity and close the second screen
        setResult(RESULT_OK, intent)
        finish()  // Ends this activity, returning to MainActivity
    }
}