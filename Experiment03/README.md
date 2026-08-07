# Experiment 03 - Android Fragments and Debugging

## Student Details

**Name:** Bhavya Khatri

**USN:** 25MCAR0217

---

# Experiment Title

Build an Android application using Fragments for a flexible User Interface and demonstrate Android Studio Debugging using Normal and Conditional Breakpoints.

---

# Aim

To develop an Android application using Fragments where one Fragment displays a list of courses and another Fragment displays the details of the selected course. The application should adapt to different screen sizes and demonstrate the use of Android Studio Debugger using Normal and Conditional Breakpoints.

---

# Concept / Technology Used

## Fragments

A Fragment is a reusable portion of an Activity's user interface. Multiple fragments can exist inside a single activity, allowing flexible layouts for phones and tablets.

In this experiment:

- ListFragment displays the list of courses.
- DetailFragment displays the details of the selected course.
- FragmentManager is used to switch between fragments.
- Bundle is used to transfer data between fragments.

---

## Responsive Layout

Two layouts are used.

### Phone Layout

- One Fragment is displayed at a time.

### Tablet Layout

- Both fragments are displayed side by side using the `layout-sw600dp` resource directory.

---

## Android Debugger

Android Studio Debugger helps inspect program execution.

This experiment demonstrates:

- Normal Breakpoint
- Conditional Breakpoint
- Local Variables
- Call Stack
- Fragment Lifecycle

---

# Demonstration Scenario

The application displays a list of programming courses.

Available courses:

- Android
- Kotlin
- Java
- Flutter
- Python

When a course is selected:

- On a phone, the Detail Fragment replaces the List Fragment.
- On a tablet, both fragments remain visible and the details are updated.

The debugger is then used to inspect the execution and variables.

---

# Project Structure

```
Experiment03
│
├── app
│   ├── src
│   │   ├── main
│   │   │
│   │   ├── java
│   │   │   └── com.bhavya.exp03
│   │   │       ├── MainActivity.kt
│   │   │       ├── ListFragment.kt
│   │   │       └── DetailFragment.kt
│   │   │
│   │   └── res
│   │       ├── layout
│   │       │   ├── activity_main.xml
│   │       │   ├── fragment_list.xml
│   │       │   └── fragment_detail.xml
│   │       │
│   │       └── layout-sw600dp
│   │           └── activity_main.xml
│
└── README.md
```

---

# Output

Insert screenshot here.

Example:

```
images/output.png
```

---

# Test Cases

## Test Case 1

### Objective

Verify that selecting a course displays its details.

### Input

Select **Java**.

### Expected Output

Java details are displayed.

### Actual Output

Working Successfully.

Screenshot:

```
images/testcase1.png
```

---

## Test Case 2

### Objective

Verify Conditional Breakpoint.

### Condition

```
selectedCourse == "Android"
```

### Expected Output

Debugger pauses only when Android is selected.

### Actual Output

Working Successfully.

Screenshot:

```
images/testcase2.png
```

---

## Test Case 3

### Objective

Verify application using Name and USN.

### Input

Display

Bhavya Khatri

25MCAR0217

### Expected Output

Name and USN appear correctly.

### Actual Output

Working Successfully.

Screenshot:

```
images/testcase3.png
```

---

# Result

The Android application was successfully developed using Fragments. The application adapts to different screen sizes and the Android Studio Debugger was successfully used to demonstrate both Normal and Conditional Breakpoints.