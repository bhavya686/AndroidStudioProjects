# Experiment 05 — Android Notifications

## 1. Experiment Title

**Develop an Android application that displays Notifications.**

## 2. Objective

The objective of this experiment is to understand and implement the Android Notification mechanism.

The application demonstrates:

- Creating a notification channel.
- Requesting notification permission on supported Android versions.
- Creating and displaying a notification.
- Displaying the notification in the Android notification shade.
- Making the notification clickable using a `PendingIntent`.
- Updating the application UI after a notification is sent.
- Displaying the student's Name and USN as part of the application evidence.

## 3. Concept / Technology Used

### Android Notifications

An Android Notification is a message displayed by the Android system to provide information or reminders to the user.

In this experiment, a notification is generated when the user presses the **SEND NOTIFICATION** button.

### Notification Channel

A notification channel is used to organize notifications on Android versions that support notification channels.

The application creates a channel named:

```text
MAD Lab Notifications
```

### Notification Permission

On newer Android versions, applications need the `POST_NOTIFICATIONS` permission before they can display notifications.

The application requests this permission at runtime when required.

### PendingIntent

A `PendingIntent` is attached to the notification so that the notification can perform an action when the user taps it.

In this experiment, tapping the notification opens `MainActivity`.

## 4. Application Scenario

A simple **MAD Lab Reminder** application is used to demonstrate Android notifications.

The application provides a screen titled **My Reminders** with a reminder card and a **SEND NOTIFICATION** button.

When the button is pressed:

```text
User presses SEND NOTIFICATION
            ↓
Notification is created
            ↓
Notification appears in notification shade
            ↓
User can tap the notification
            ↓
Application opens
```

The notification contains:

```text
MAD Lab Reminder

Don't forget to complete your MAD Lab experiment.
```

The application also displays:

```text
Name: Bhavya Khatri
USN: 25MCAR0217
```

## 5. User Interface

### Main Screen

The application contains:

- My Reminders heading
- Android Notification Demo subtitle
- MAD Lab Reminder card
- SEND NOTIFICATION button
- Student Name and USN
- Notification status

The status initially displays:

```text
Notification Status: Ready
```

After successfully sending a notification, it changes to:

```text
Notification Status: Sent
```

## 6. Application Workflow

```text
Start Application
       ↓
Notification permission requested if required
       ↓
Main Screen displayed
       ↓
User presses SEND NOTIFICATION
       ↓
Notification is created
       ↓
Status changes to "Sent"
       ↓
Notification appears in Android notification shade
       ↓
User taps notification
       ↓
MainActivity opens
```

## 7. Project Folder Structure

```text
Experiment05/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── bhavya/
│           │           └── exp05/
│           │               └── MainActivity.kt
│           │
│           ├── res/
│           │   ├── drawable/
│           │   ├── layout/
│           │   │   └── activity_main.xml
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
└── README.md
```

## 8. Important Files

| File | Purpose |
|---|---|
| `MainActivity.kt` | Creates the notification channel, requests notification permission when required, generates notifications, and handles notification interaction |
| `activity_main.xml` | Defines the main application UI |
| `AndroidManifest.xml` | Declares the notification permission |
| `res/drawable/` | Contains drawable resources used by the application UI |

## 9. Implementation Highlights

### Notification Permission

The application declares:

```xml
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

For supported Android versions, the application requests this permission at runtime.

### Notification Channel

A notification channel is created using:

```kotlin
val channel = NotificationChannel(
    CHANNEL_ID,
    "MAD Lab Notifications",
    NotificationManager.IMPORTANCE_DEFAULT
)
```

### Creating the Notification

The notification is constructed using `NotificationCompat.Builder`:

```kotlin
val notification = NotificationCompat.Builder(
    this,
    CHANNEL_ID
)
    .setSmallIcon(android.R.drawable.ic_dialog_info)
    .setContentTitle("MAD Lab Reminder")
    .setContentText("Don't forget to complete your MAD Lab experiment.")
    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    .setContentIntent(pendingIntent)
    .setAutoCancel(true)
    .build()
```

### Displaying the Notification

```kotlin
NotificationManagerCompat.from(this)
    .notify(NOTIFICATION_ID, notification)
```

### Notification Click Action

A `PendingIntent` is used so that tapping the notification opens the application.

# 10. Test Cases

## Test Case 1 — Notification Generation

**Test Case ID:** TC01

**Description:** Verify that pressing the notification button generates a notification.

**Input:**

```text
Press SEND NOTIFICATION
```

**Expected Output:**

The notification should be generated and the application status should change from `Notification Status: Ready` to `Notification Status: Sent`.

**Actual Output:**

Notification was successfully generated and the status changed to `Sent`.

**Result:** PASS

**Screenshot:**

```text
screenshots/test_case_1_notification_sent.png
```

---

## Test Case 2 — Notification Display and Interaction

**Test Case ID:** TC02

**Description:** Verify that the generated notification appears in the Android notification shade and can be selected.

**Input:**

```text
Generate notification and open the notification shade.
```

**Expected Output:**

The notification should appear with:

```text
MAD Lab Reminder
Don't forget to complete your MAD Lab experiment.
```

Tapping the notification should open the application.

**Actual Output:**

The notification appeared successfully in the notification shade and the notification interaction worked as expected.

**Result:** PASS

**Screenshot:**

```text
screenshots/test_case_2_notification_shade.png
```

---

## Test Case 3 — Student Name and USN

**Test Case ID:** TC03

**Description:** Verify that the student's Name and USN are displayed correctly in the application.

**Input:**

```text
Name: Bhavya Khatri
USN: 25MCAR0217
```

**Expected Output:**

The application should clearly display:

```text
Name: Bhavya Khatri
USN: 25MCAR0217
```

**Actual Output:**

The Name and USN were displayed correctly.

**Result:** PASS

**Screenshot:**

```text
screenshots/test_case_3_name_usn.png
```

## 11. Output Screenshots

Add the final application screenshots to the `screenshots` folder.

Recommended files:

```text
screenshots/
├── main_screen.png
├── test_case_1_notification_sent.png
├── test_case_2_notification_shade.png
└── test_case_3_name_usn.png
```

### Main Application

<!-- Add final application screenshot here -->

### Notification

<!-- Add notification shade screenshot here -->

## 12. Test Case Evidence

The submitted screenshots demonstrate:

1. Successful notification generation.
2. Successful notification display in the Android notification shade.
3. Successful notification interaction.
4. Correct display of the student's Name and USN.

## 13. Result

The Android application was successfully developed to demonstrate the Android Notification mechanism.

The application successfully:

- Creates a notification channel.
- Requests notification permission when required.
- Generates a notification using `NotificationCompat`.
- Displays the notification in the Android notification shade.
- Allows the user to interact with the notification.
- Updates the application status after sending the notification.
- Displays the student's Name and USN for submission evidence.

## 14. Conclusion

This experiment demonstrates how Android applications can communicate information to users through system notifications. The implementation covers notification channels, notification permissions, notification construction, notification display, and notification interaction using `PendingIntent`.

The application provides a simple MAD Lab reminder scenario while demonstrating the core Android Notification concepts required for the experiment.
