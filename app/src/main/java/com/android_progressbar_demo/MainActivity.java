package com.android_progressbar_demo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private static ProgressBar horizontal_progressbar, vertical_progressbar,
			circular_progressbar;
	private static Button horizontal_bar_button, vertical_bar_button,
			download_file, circular_progressbar_buttom;
	private static TextView horizontal_status, vertical_status,
			circular_progressbar_status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
		setListeners();
	}

	// Initialize all widgets
	void init() {
		horizontal_progressbar = (ProgressBar) findViewById(R.id.horizontal_progressbar);
		horizontal_bar_button = (Button) findViewById(R.id.start_horizontal_progressbar);
		horizontal_status = (TextView) findViewById(R.id.horizontalprogress_percentage);

		vertical_progressbar = (ProgressBar) findViewById(R.id.vertical_progressbar);
		vertical_bar_button = (Button) findViewById(R.id.start_vertical_progressbar);
		vertical_status = (TextView) findViewById(R.id.verticalprogress_percentage);

		download_file = (Button) findViewById(R.id.download_file);

		circular_progressbar = (ProgressBar) findViewById(R.id.circular_progressbar);
		circular_progressbar_buttom = (Button) findViewById(R.id.start_circular_progressbar);
		circular_progressbar_status = (TextView) findViewById(R.id.circularprogress_percentage);

	}

	// Set Click Listeners to all buttons
	void setListeners() {
		horizontal_bar_button.setOnClickListener(this);
		vertical_bar_button.setOnClickListener(this);
		download_file.setOnClickListener(this);
		circular_progressbar_buttom.setOnClickListener(this);
	}

	// Execute Async Task method on button click
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_horizontal_progressbar:
			new UpdateHorizontalProgressBar().execute();
			break;

		case R.id.start_vertical_progressbar:
			new UpdateVerticalProgressBar().execute();
			break;

		case R.id.download_file:
			new DownloadFile().execute();
			break;

		case R.id.start_circular_progressbar:
			new UpdateCircularProgressBar().execute();
			break;
		}

	}

	// Update Horizontal progress bar asynctask
	public class UpdateHorizontalProgressBar extends
			AsyncTask<Void, Integer, Void> {

		// Progress variable for progressbar progress
		int progress;

		@Override
		protected void onPreExecute() {

			// Set Progress 0 at starting
			progress = 0;

			// Setting text to status textView
			horizontal_status.setText(progress + " %");
		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			// Update the progressbar and textView Text on Progress Update
			horizontal_progressbar.setProgress(values[0]);
			horizontal_status.setText(values[0] + " %");
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			// In Background increment the progress one by one till 100
			while (progress < 100) {
				progress++;

				// Publish the progress
				publishProgress(progress);

				// Time wait of 100ms
				SystemClock.sleep(100);
			}
			return null;
		}

	}

	// Update Vertical progress bar asynctask
	public class UpdateVerticalProgressBar extends
			AsyncTask<Void, Integer, Void> {

		int progress;

		@Override
		protected void onPreExecute() {

			progress = 0;
			vertical_status.setText(progress + " %");
		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			vertical_progressbar.setProgress(values[0]);
			vertical_status.setText(values[0] + " %");
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			while (progress < 100) {
				progress++;
				publishProgress(progress);

				SystemClock.sleep(100);
			}
			return null;
		}

	}

	// Download File asynctask
	public class DownloadFile extends AsyncTask<Void, Integer, Void> {

		int progress;

		// Progress Dialog to show progress on dialog
		ProgressDialog dialog = new ProgressDialog(MainActivity.this);

		@Override
		protected void onPreExecute() {

			// Setting title to dialog
			dialog.setTitle("File is downloading..Please Wait..");

			// This will make the dialog uncancelabe on outside or back clicking
			dialog.setCancelable(false);

			// Maximum value
			dialog.setMax(100);

			// Starting Progress
			dialog.setProgress(0);

			// Progress Style (Horizontal or Circular)
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

			// Spinner Style i.e. Circular
			// dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

			// Setting intermediate i.e. nothing happen when any intermediate is
			// happens
			dialog.setIndeterminate(false);

			// Showing dialog
			dialog.show();

			progress = 0;

		}

		@Override
		protected void onPostExecute(Void result) {

			// Dismiss the dialog when task is done
			// dialog.hide();
			dialog.dismiss();
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			// Update Progress the dialog
			dialog.setProgress(values[0]);

		}

		@Override
		protected Void doInBackground(Void... arg0) {

			while (progress < 100) {
				progress++;
				publishProgress(progress);

				SystemClock.sleep(100);
			}
			return null;
		}

	}

	// Update Circular progress bar asynctask
	public class UpdateCircularProgressBar extends
			AsyncTask<Void, Integer, Void> {

		int progress;

		@Override
		protected void onPreExecute() {

			progress = 0;
			circular_progressbar_status.setText(progress + " %");
		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			circular_progressbar.setProgress(values[0]);
			circular_progressbar_status.setText(values[0] + " %");
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			while (progress < 100) {
				progress++;
				publishProgress(progress);

				SystemClock.sleep(100);
			}
			return null;
		}

	}
}
