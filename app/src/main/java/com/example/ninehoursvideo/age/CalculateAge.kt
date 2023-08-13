package com.example.ninehoursvideo.age

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ninehoursvideo.databinding.ActivityCalculateAgeBinding
import java.util.Calendar
import java.util.Locale

class CalculateAge : AppCompatActivity() {
    private lateinit var binding : ActivityCalculateAgeBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            selectDate.setOnClickListener {
                dateClicked()
            }
        }
    }

    private fun dateClicked() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this, { view , selectedYear , selectedMonth , selectedDay ->
                val correctMonth = selectedMonth+1
                binding.apply {
                    val dateSelected = "$selectedDay/$correctMonth/$selectedYear"
                    selectedDate.text = dateSelected
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = sdf.parse(dateSelected)

                    val dateInMinutes = theDate.time / 60000
                    val dateInSeconds = dateInMinutes * 60
                    val dateInHours = dateInMinutes / 60

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes = currentDate.time / 60000
                    val currentDateInSeconds = currentDateInMinutes * 60
                    val currentDateInHours = currentDateInMinutes / 60

                    val theDifferenceInMinutes = currentDateInMinutes - dateInMinutes
                    val theDifferenceInSeconds = currentDateInSeconds - dateInSeconds
                    val theDifferenceInHors = currentDateInHours - dateInHours


                    timeInMinutes.text = "Time in minutes = $theDifferenceInMinutes"
                    timeInSeconds.text = "Time in seconds = $theDifferenceInSeconds"
                    timeInHours.text = "Time in hours = $theDifferenceInHors"


                }
            } ,
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}