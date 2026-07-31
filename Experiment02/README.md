# Experiment 02 – Activity Lifecycle in Android

## Aim

To develop an Android application that demonstrates the Android Activity Lifecycle by implementing all lifecycle callback methods and observing their execution using Custom Toast messages and Logcat.

---

## Objective

- Understand the Android Activity Lifecycle.
- Implement all major lifecycle methods.
- Display lifecycle events using a custom Toast.
- Record lifecycle events in Logcat.
- Understand the order in which lifecycle methods are invoked.

---

## Technologies Used

- Android Studio Quail 2 Patch 1
- Kotlin
- Java (Custom Toast Class)
- XML (ConstraintLayout)
- Android SDK 37

---

## Lifecycle Methods Implemented

- onCreate()
- onStart()
- onResume()
- onPause()
- onStop()
- onRestart()
- onDestroy()

---

## Project Structure

```
Experiment02
│
├── AndroidProject
│
├── Observation
│
├── Screenshots
│
└── README.md
```

---

## Features

- Demonstrates complete Activity Lifecycle.
- Custom Toast implemented using a separate Java class.
- Custom Toast UI with rounded background.
- Lifecycle events recorded using Logcat.
- Clean and modular project structure.

---

## Custom Toast

A separate Java class (`CustomToast.java`) was created to display lifecycle messages using a custom XML layout instead of Android's default Toast.

The custom Toast includes:

- Rounded background
- Information icon
- White bold text
- Center screen positioning

---

## Output

### App Launch

```
onCreate()
onStart()
onResume()
```

### Home Button

```
onPause()
onStop()
```

### Reopen Application

```
onRestart()
onStart()
onResume()
```

### Exit Application

```
onPause()
onStop()
onDestroy()
```

> Note:
> On newer Android versions, custom Toasts are not always displayed when the application moves to the background. Therefore, Logcat was used to verify the execution of `onPause()`, `onStop()`, and `onDestroy()`.

---

## Proof of Execution

A screen recording demonstrating the execution of all lifecycle methods has been included as proof.

**Recording:**

```


https://github.com/user-attachments/assets/2228f5d3-fdc3-485d-b14b-5cabd7b87b0b


```

---

## Logcat Verification

Lifecycle callbacks were verified using Logcat with the following tag:

```
LifecycleDemo
```

Example:

```
D/LifecycleDemo: onCreate() called
D/LifecycleDemo: onStart() called
D/LifecycleDemo: onResume() called
D/LifecycleDemo: onPause() called
D/LifecycleDemo: onStop() called
D/LifecycleDemo: onRestart() called
D/LifecycleDemo: onDestroy() called
```

---

## Learning Outcomes

After completing this experiment, I learned:

- Android Activity Lifecycle.
- Purpose of each lifecycle callback.
- Difference between foreground and background states.
- Creating and using a custom Toast.
- Using Java classes in a Kotlin project.
- Using Logcat for debugging lifecycle events.
- Organizing an Android Studio project for version control.

---

## Author

**Name:** Bhavya Khatri

**USN:** 25MCAR0217
