package com.bih.nic.attendenceapp.utilities;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyHandlerThreadExample {

    private static final String TAG = "HandlerThreadExample";
    private HandlerThread handlerThread;
    private Handler backgroundHandler;
    private Handler mainHandler;

    public void start() {
        // Step 1: Initialize the HandlerThread
        handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start(); // Start the background thread

        // Step 2: Create a Handler for the HandlerThread
        backgroundHandler = new Handler(handlerThread.getLooper());

        // Step 3: Create a Handler for the main thread (optional)
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public void doBackgroundWork() {
        // Step 4: Post a task to the background handler
        backgroundHandler.post(() -> {
            Log.d(TAG, "Running a background task on: " + Thread.currentThread().getName());

            // Simulate a long-running operation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, "Background task interrupted", e);
            }

            // Update the UI after the task (via the main thread handler)
            mainHandler.post(() -> {
                Log.d(TAG, "Task completed! Updating the UI on: " + Thread.currentThread().getName());
            });
        });
    }

    public void stop() {
        // Step 5: Stop the HandlerThread and cleanup
        if (handlerThread != null) {
            handlerThread.quitSafely(); // Terminates the HandlerThread
            handlerThread = null;
        }
    }
}

