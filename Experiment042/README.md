# Experiment 04 — Android Intent: Login and Dashboard Navigation

## 1. Experiment Title

**Develop an Android application using Intent to navigate from a Login screen to a Dashboard screen.**

## 2. Objective

The objective of this experiment is to understand and implement **Intent-based navigation between Android Activities**.

The application contains:
- A Login screen with username and password fields.
- A Login button that starts another Activity using an explicit Intent.
- A Dashboard screen that receives the entered username through Intent extras.
- A Logout button that finishes the Dashboard Activity and returns to the Login screen.
- A simple, clean UI using XML layouts and drawable resources.

## 3. Concept / Technology Used

### Android Intent

An **Intent** is a messaging object used by Android to request an action from another application component.

In this experiment, an **explicit Intent** is used to start `HomeActivity` from `MainActivity`.

```text
MainActivity
(Login Screen)
      |
      | User clicks LOGIN
      |
      | Explicit Intent + username
      v
HomeActivity
(Dashboard)
      |
      | User clicks LOG OUT
      v
MainActivity
(Login Screen)
```

### Passing Data with Intent

The entered username is passed from `MainActivity` using:

```kotlin
intent.putExtra("username", enteredUsername)
```

The value is retrieved in `HomeActivity` using:

```kotlin
val username = intent.getStringExtra("username")
```

This demonstrates both **Activity navigation** and **data transfer between Activities**.

> **Note:** This application demonstrates Intent navigation only. It does not perform real user authentication or validate credentials against a database/server.

## 4. Application Scenario

A simple student application is used as the scenario.

The user enters a username and password on the Login screen. When the **LOGIN** button is pressed, the application opens the Dashboard screen.

The entered username is transferred to the Dashboard and displayed as:

```text
Welcome, <username>!
```

The Dashboard contains basic student information and a **LOG OUT** button. Pressing Logout closes the current Activity and returns the user to the Login screen.

## 5. User Interface

### Login Screen

The Login screen contains:
- Welcome heading
- Username input field
- Password input field
- Login button
- Rounded input fields
- Green accent colour
- Light background

### Dashboard Screen

The Dashboard contains:
- Dashboard heading
- Personalized welcome message
- Student Dashboard card
- Semester information
- Courses summary
- Projects summary
- Logout button

The UI uses a simple card-based structure, readable typography, spacing, and clear navigation.

## 6. Project Folder Structure

```text
Experiment04/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── bhavya/
│           │           └── exp04/
│           │               ├── MainActivity.kt
│           │               └── HomeActivity.kt
│           │
│           ├── res/
│           │   ├── drawable/
│           │   │   ├── login_background.xml
│           │   │   ├── input_background.xml
│           │   │   ├── login_button_background.xml
│           │   │   ├── dashboard_background.xml
│           │   │   └── dashboard_card_background.xml
│           │   │
│           │   ├── layout/
│           │   │   ├── activity_main.xml
│           │   │   └── activity_home.xml
│           │   │
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
└── README.md
```

## 7. Important Files

| File | Purpose |
|---|---|
| `MainActivity.kt` | Handles the Login screen and starts `HomeActivity` using an explicit Intent |
| `HomeActivity.kt` | Displays the Dashboard and receives the username from the Intent |
| `activity_main.xml` | Defines the Login screen UI |
| `activity_home.xml` | Defines the Dashboard UI |
| `login_background.xml` | Background styling for the Login screen |
| `input_background.xml` | Rounded styling for input fields |
| `login_button_background.xml` | Styling for the Login button |
| `dashboard_background.xml` | Background styling for the Dashboard |
| `dashboard_card_background.xml` | Styling for the Dashboard card |
| `AndroidManifest.xml` | Declares the Activities used by the application |

## 8. Implementation Highlights

### Starting the Dashboard Activity

The Login button creates an explicit Intent:

```kotlin
val intent = Intent(this, HomeActivity::class.java)
```

The username is attached to the Intent:

```kotlin
intent.putExtra("username", enteredUsername)
```

The Activity is then started:

```kotlin
startActivity(intent)
```

### Receiving the Username

`HomeActivity` retrieves the value:

```kotlin
val username = intent.getStringExtra("username")
```

The Dashboard displays it:

```kotlin
if (!username.isNullOrEmpty()) {
    tvWelcome.text = "Welcome, $username!"
}
```

### Returning to Login

The Logout button uses:

```kotlin
finish()
```

This closes `HomeActivity` and returns to the previous Login Activity.

## 9. Test Cases

### Test Case 1 — Login Navigation

**Input:**
- Username: `Bhavya`
- Password: `1234`

**Action:**
1. Enter username.
2. Enter password.
3. Click **LOGIN**.

**Expected Result:**

The application should navigate from the Login screen to the Dashboard screen.

**Proof:**



https://github.com/user-attachments/assets/3850d8d9-adcc-4860-9855-3da664a9678e



### Test Case 2 — Intent Data Transfer

**Input:**
- Username: `Bhavya`
- Password: `1234`

**Action:**
1. Enter the username.
2. Click **LOGIN**.
3. Observe the Dashboard.

**Expected Result:**

The Dashboard should display:

```text
Welcome, Bhavya!
```

This confirms that the username was successfully passed from `MainActivity` to `HomeActivity` through the Intent.

**Proof:**



https://github.com/user-attachments/assets/403e3ec0-601e-4dd3-b476-00faa4becfcc



## 12. Result

The Android application was successfully developed using **explicit Intent** for navigation between `MainActivity` and `HomeActivity`.

The experiment demonstrates:
- Activity navigation using Intent.
- Passing data between Activities using Intent extras.
- Receiving and displaying Intent data.
- Returning between Activities using `finish()`.
- Designing a simple and user-friendly Login and Dashboard interface.
