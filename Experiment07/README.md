
# Experiment 07: Adaptive UI Using ListView and ImageView

## Aim
To develop an Android application using `ListView` and `ImageView` to display a collection of travel destinations and adapt the user interface to different screen orientations.

## Description
Travel Explorer is an Android application that presents a list of popular travel destinations. Users can select a destination from the list to view its image, name, location, and description.

The application uses a portrait layout for a vertical arrangement and a separate landscape layout to display the selected destination and destination list side by side.

## Concepts and Technologies Used
- **Android Studio:** Integrated development environment used to build the application.
- **Kotlin:** Programming language used for application logic.
- **XML Layouts:** Used to define the user interface.
- **ListView:** Displays a scrollable list of travel destinations.
- **ImageView:** Displays the selected destination's image and the thumbnails in the list.
- **ArrayAdapter:** Connects destination data to the ListView.
- **Custom Adapter:** Binds each destination's image, name, and location to a custom row.
- **OnItemClickListener:** Handles destination selection.
- **LinearLayout and ScrollView:** Arrange the interface and support scrolling.
- **Alternative Resources:** `layout` and `layout-land` provide different layouts for portrait and landscape orientations.

## Application Scenario
A travel information application needs to present multiple destinations in a simple, interactive interface. Users should be able to browse destinations and select one to view more information. The interface should remain usable when the device orientation changes.

## Features
1. Displays five travel destinations:
   - Taj Mahal — Agra, India
   - Eiffel Tower — Paris, France
   - Bali — Indonesia
   - Swiss Alps — Switzerland
   - Great Wall — China
2. Shows destination thumbnails in a ListView.
3. Displays the selected destination's image, name, and description.
4. Updates the displayed information when a destination is selected.
5. Uses a portrait layout for phones held vertically.
6. Uses a two-column layout in landscape orientation.

## Project Structure

```text
Experiment07/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/bhavya/experiment07/
│           │       └── MainActivity.kt
│           ├── res/
│           │   ├── drawable/
│           │   │   ├── bali.jpg
│           │   │   ├── eiffel_tower.jpg
│           │   │   ├── great_wall.jpg
│           │   │   ├── swiss_alps.jpg
│           │   │   └── taj_mahal.jpg
│           │   ├── layout/
│           │   │   ├── activity_main.xml
│           │   │   └── item_destination.xml
│           │   └── layout-land/
│           │       └── activity_main.xml
│           └── AndroidManifest.xml
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── screenshots/
│   ├── test1_initial_display.png
│   ├── test2_destination_selection.png
│   └── test3_landscape_layout.png
└── README.md
```

## Testing and Results

### Test Case 1: Initial Display

**Action:** Launch the application.

**Expected Result:** The Travel Explorer screen appears with the initial destination, its image and description, and the destination list.

**Observed Result:** The initial destination and list are displayed correctly.

**Screenshot:**

![Test Case 1 - Initial Display](screenshots/test1_initial_display.png)

![alt text](test1_initial_display.jpeg)

### Test Case 2: Destination Selection

**Action:** Select a different destination, such as Eiffel Tower, from the ListView.

**Expected Result:** The main image, destination name, and description update to match the selected destination.

**Observed Result:** The selected destination's information is displayed correctly.

**Screenshot:**

![Test Case 2 - Destination Selection](screenshots/test2_destination_selection.png)

![alt text](test2_destination_selection.jpeg)

### Test Case 3: Adaptive Landscape Layout

**Action:** Rotate the device or emulator to landscape orientation.

**Expected Result:** The application displays the selected destination and destination list side by side.

**Observed Result:** The landscape layout is displayed and destination selection remains functional.

**Screenshot:**

![Test Case 3 - Landscape Layout](screenshots/test3_landscape_layout.png)

![alt text](test3_landscape_layout.jpeg)

## Conclusion
The Travel Explorer application was developed successfully using `ListView` and `ImageView`. It displays travel destinations, responds to user selection, and uses alternative XML layouts to support portrait and landscape orientations.