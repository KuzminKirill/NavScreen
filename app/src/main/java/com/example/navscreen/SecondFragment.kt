package com.example.navscreen


import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment


class SecondFragment : Fragment() {

    private var progressBar: ProgressBar? = null
    private var txtState: TextView? = null
    private var btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_second, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        txtState = view.findViewById(R.id.txtState)
        btn = view.findViewById(R.id.btnFetch)

        MyProgressBarAsyncTask().execute()

        return view

    }


    internal inner class MyProgressBarAsyncTask : AsyncTask<Void, Int, Void>() {

        private var progressBarValue = 60

        override fun doInBackground(vararg p0: Void): Void? {
            while (progressBarValue > 0) {
                progressBarValue--
                publishProgress(progressBarValue)
                SystemClock.sleep(1000)
            }
            return null;
        }


        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            values[0]?.let { progressBar?.setProgress(it) }
            txtState?.text = values[0].toString() + "c"
        }


        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)

            progressBar?.visibility = View.INVISIBLE
            txtState?.visibility = View.INVISIBLE
            btn?.visibility = View.VISIBLE
        }


        override fun onPreExecute() {
            super.onPreExecute()
        }


    }


}
