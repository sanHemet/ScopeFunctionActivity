package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

        //Log.d("function output", getTestDataArray().toString())
        Log.d("second function output", averageLessThanMedian(arrayListOf(5.0, 7.0, 3.0)).toString())

    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
//    private fun getTestDataArray() : List<Int> {
//        val testArray = MutableList(10){ Random.nextInt()}
//        testArray.sort()
//        return testArray
//    }

    private fun getTestDataArray() = MutableList(10) { Random.nextInt() }.sorted()

    // Return true if average value in list is greater than median value, false otherwise
//    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean {
//        val avg = listOfNumbers.average()
//        val sortedList = listOfNumbers.sorted()
//        val median = if (sortedList.size % 2 == 0)
//            (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
//        else
//            sortedList[sortedList.size / 2]
//
//        return avg < median
//    }

    private fun averageLessThanMedian(listOfNumbers: List<Double>) =
        listOfNumbers.sorted().run {
            val median = if (this.size % 2 == 0)
                (this[this.size/2] + this[(this.size - 1) / 2]) /2
            else
                this[this.size/2]
            listOfNumbers.average() < median
        }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
//    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View {
//        val textView: TextView
//
//        if (recycledView != null) {
//            textView = recycledView as TextView
//        } else {
//            textView = TextView(context)
//            textView.setPadding(5, 10, 10, 0)
//            textView.textSize = 22f
//        }
//
//        textView.text = collection[position].toString()
//
//        return textView
//    }

    //x ?: y -> 'x' if 'x' is not null, otherwise 'y'
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context) =
        (recycledView?.run{ (this as TextView) } ?: TextView(context).apply{
            this.setPadding(5,10,10,0)
            this.textSize = 22f
        }).apply {
            this.text = collection[position].toString()
        } //parenthesis needed, because second apply would just be the same as putting it inside, close first, then apply after
}