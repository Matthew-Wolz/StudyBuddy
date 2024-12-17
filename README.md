---
title: "Study Buddy System Manual"
author: "James, Braiden, Roshan, Aiden, Matthew"
output: html_document
---

# Overview
The **Study Buddy** app aims to help users find compatible study partners based on survey responses. The app incorporates various design patterns to ensure scalability, maintainability, and smooth performance. The main components of the app are described below, focusing on the UI, database, authentication, compatibility algorithm, notification system, and location system.

---

# Design Patterns Used

## 1. **UI: Adapter Pattern**
The app's home feed utilizes a **RecyclerView** to display user profiles/cards. The RecyclerView component requires an adapter to display unique elements, and this responsibility is managed by the **CardAdapter.java** class. It handles the data retrieval from the database and displays it on each card in the RecyclerView. The CardAdapter is initialized in **HomeFragment.java**.

- The main XML layout files are located in `./src/main/res/layout`.
- The most detailed XML files are:
  - `activity_main.xml`
  - `card_layout.xml`

The UI layout uses **LinearLayouts** for organizing elements. Initially, layout margins were used for positioning, but this approach was found to be inefficient across different screen sizes. To address this, containers like **LinearLayout (horizontal and vertical)** were implemented for better responsiveness.

---

## 2. **Database and Authentication: Singleton Pattern**
This project uses **Firebase** for both the real-time database and Firestore to store user profiles and data for compatibility calculations.

- **Firebase Realtime Database**: This NoSQL database stores user survey data in JSON format.
- **Firestore**: A relational database used to store personal information for each user.
  
The **Singleton pattern** is implemented to ensure that the **FirebaseAuth** class has a global instance that can be accessed throughout the app. Additionally, asynchronous tasks are used extensively to handle remote connections for authentication and database management, ensuring a smooth user experience.

The app also employs the **Observer pattern** using **EventListeners** to observe changes in the database and notify the UI accordingly.

---

## 3. **Compatibility Algorithm: Strategy Pattern**
The **Strategy pattern** is used in the compatibility algorithm. A common interface is defined, which is implemented by various strategy classes. The core method `checkCompatibility()` takes two parameters, `user1` and `user2`, which are instances of the **User** class.

- `user1` represents the primary user, and `user2` is any other user with whom compatibility is being compared.
- The compatibility score starts at **100** and is adjusted based on different factors, such as:
  - **Availability**: If the users' availability doesn't match, 15 points are subtracted.
  - **Grades**: Mismatched grades lead to a deduction of 5 points.

The final score is calculated by evaluating these variables, and the result is sent to the database for display.

---

## 4. **Notification System: Observer Pattern**
The **Notification system** uses the **Observer pattern** to manage real-time updates. The system watches for significant events such as:
  - New study buddy matches.
  - Messages from matches.
  - Potential notifications based on the user's proximity to other study buddies (planned for future implementation).

Methods like `addObserver()`, `removeObserver()`, `notifyObservers()`, and `pushNotification()` are used to manage the observer list and notify users of important events.

The **NotificationSystem** class is responsible for this implementation. The Observer pattern is ideal for this purpose as it efficiently handles notifications, news alerts, and system updates.

---

## 5. **Location System: Composite Pattern**
The **Location system** uses the **Composite pattern** to treat individual locations and groups of locations uniformly. This pattern allows locations to be managed in a tree-like structure, making it easier to add or remove locations dynamically at runtime.

- The `addLocation()` method creates new location objects and adds them to a list.
- The `getLocations()` method returns the list of all location objects.

Although the location system has not been fully implemented in the real-time database yet, future plans include adding features like identifying a user’s location within a dorm or whether they live off-campus. This feature will contribute to refining the compatibility score for users.

---

# Key Files and Classes

## 1. **CardAdapter.java**
- Manages the RecyclerView and its data.
- Fetches data from the database and displays it on individual user profile cards.

## 2. **HomeFragment.java**
- Initializes the CardAdapter and handles UI-related tasks.
- Responsible for displaying the home feed of user profiles.

## 3. **Firebase Authentication**
- Manages user authentication with FirebaseAuth using the Singleton pattern.

## 4. **User.java**
- Represents a user in the app, with methods for checking compatibility against other users.

## 5. **NotificationSystem.java**
- Implements the Observer pattern for real-time updates.

## 6. **LocationManager.java**
- Implements the Composite pattern to manage dynamic location data.

---

# Future Enhancements

- **Location-Based Matching**: Add functionality to match users based on proximity, such as identifying study buddies nearby in a dorm.
- **Improved Notification System**: Expand notifications to include alerts about users who are nearby and available for study sessions.
- **Extended Compatibility Algorithm**: Introduce additional factors for compatibility, such as common study preferences or past performance.

---

# Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Matthew-Wolz/study-buddy.git
