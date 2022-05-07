# EBU6304_G058_flight


EBU6304 – Software Engineering Group Project

**Last Modified Date: 2022-05-08.**

## Table of Contents

[TOC]

## Project Introduction

Our team will develop a **smart flight check-in kiosk software** for British Airways in a few weeks using **agile methods**. We will conduct a systematic requirements survey, analysis and design, followed by writing implementation and testing of the software. We will deliver **four major iterations** in a planned manner.

<img src="src/main/resources/com/app/flight/ico/BritishAirwayLogo.png" alt="BritishAirwayLogo"  style="zoom:50%;" />



## Features

- Passengers look for their own booking information.
- Passengers choose seats.
- Passengers choose meals.
- Passengers can check in.
- Passengers can print tags.
- Administrator can login.
- Administrator can view all the passengers' information.(coming soon)



------



## Quick Start : How to run our project

**§§	jdk 17 and Maven 3.8.1 are required	**

1. #### **Use IDE**

   

   Our project was developed using **JetBrains IntelliJ IDEA** compiler. You can simply open this project folder in IDEA, wait for **Maven **to automatically complete the project build, and click the green triangle next to the application startup class **Main.java** to run it.

   <img src="src/main/resources/com/app/flight/image/startUp.png" alt="startUp" style="zoom:100%;" />

   

2. #### **Use the command line**

   

   Go to the project folder.

   Execute the command 	**mvn clean javafx:run**

   **For example:**

   ```powershell
   PS C:\Users\LianJunhong\Desktop> cd E:\Java\EBU6304_G058_flight
   PS E:\Java\EBU6304_G058_flight> mvn clean javafx:run
   ```

   Running result:

   ```powershell
   [INFO] Scanning for projects...
   [INFO]
   [INFO] ---------------------------< com.app:flight >---------------------------
   [INFO] Building fight 2.0
   [INFO] --------------------------------[ jar ]---------------------------------
   [INFO]
   [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ flight ---
   [INFO] Deleting E:\Java\EBU6304_G058_flight\target
   [INFO]
   [INFO] >>> javafx-maven-plugin:0.0.8:run (default-cli) > process-classes @ flight >>>
   [INFO]
   [INFO] --- lombok-maven-plugin:1.18.20.0:delombok (default) @ flight ---
   [INFO]
   [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ flight ---
   [INFO] Using 'UTF-8' encoding to copy filtered resources.
   [INFO] Copying 43 resources
   [INFO]
   [INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ flight ---
   [INFO] Changes detected - recompiling the module!
   [INFO] Compiling 40 source files to E:\Java\EBU6304_G058_flight\target\classes
   [INFO]
   [INFO] <<< javafx-maven-plugin:0.0.8:run (default-cli) < process-classes @ flight <<<
   [INFO]
   [INFO]
   [INFO] --- javafx-maven-plugin:0.0.8:run (default-cli) @ flight ---
   ```

   <img src="src/main/resources/com/app/flight/image/firstInterface.png" alt="firstInterface" style="zoom:50%;" />

   

3. #### **Use Jar or executable file**

   Coming soon…

   

   ------

   
## Requirements

- maven -- 3.8.1 
- jdk -- 17





## Documentation

Coming soon…




------

## Iteration logs

### 4.0 Beta (May 8, 2022) The Latest version

- Several bugs fixed and new features added in this release.
- Separated printing function and created scanning function.
- Updated the help function and fastjson snapshot.
- Fixed bugs of Printer and Scanner.
- Update fastjson 2.0.2 fulling test of new dependency.
- Added the navigation map and showed the boarding gate in page.
- Improved the ScanInstructionController and show the video how to scan ID card to users.
- Improved Scanner to ues Runnable and Thread pool.
- Refactor SeatMapImpl with GetSeatMapImpl and SetSeatMapImpl.

### 3.5 RELEASE (May 1, 2022)

- Several bugs fixed and new features added in this release.
- Fixed the bug of showing the retrieve information and beautify the page.
- Improved the interface of selecting food type.
- The interface logic of searching user information according to function selection is realized.
- The function of finding user interface is reconstructed.
- Fixed RetrieveController select passenger flight bugs.
- Improved the implementation of the interfaces.
- Adjust the effect of printing boarding pass progress bar and added printer sound.
- Optimized thread synchronization.
- Fix percentage showing during processing print the tags.
- Updated the Json and CSV files.
- Refactor to update fastjson1 to fastjson2 and fixed bugs.
- Added help page.
- Added several test units.

### 3.0 Beta (April 24, 2022)

- Several bugs fixed and new features added in this release.
- Fixed scene switching. Now the scene can be switched in the same window.
- Fixed the bug that the application will crash when the user select nothing but submit.
- The showSeatMap function has been refactored to reduce coupling.
- The interface logic of searching user information according to function selection is realized.
- The function of finding user interface is reconstructed.
- Updated CSV file to generate corresponding flight seating map based on flight ID.
- Added the ability to automatically generate passenger boarding cards.
- Improved front and back seating map compatibility.
- Many unit tests have been added to test our main functions.

### 2.1 RELEASE (April 20, 2022)

- Several bugs fixed in this release.
- New features added in this release.
- New view of seat selection.

### 2.0 Beta (April 17, 2022)

- Further improve the basic functions of our page and realize the closed loop of basic operation logic.
- Integrate the administrator login function page.
- Implements the display of flight information (in the form of a list) and passenger selection of seats and meals.
- Several bugs fixed in this release.

### 1.5 (April 12, 2022)

- Fix bugs and complete the iteration of the 1.0 version.
- Finish the pages and complete the functions of booking and retrieve information.
- Change the design of flight and booking CSV data regardless of whether a passenger has more than one booking and flight.
- Implement the GetFlight and GetReservation interface and GetFlightImpl and GetReservationImpl class.

### 1.0 (March 28, 2022)

- Complete the first page about selecting a language.
- Make entity classes about version 1.0.
- Complete the programming of common tools related to CSV and JSON.
- Complete the design of the interface between boundary class and control class.
- Complete the login interface design and the implementation of the login function.

 ### 0.5 (March 21, 2022)

- Finally determine 28 user stories and acceptance criteria.
- Complete product prototype design using Axure.
- Complete the priority and  Fibonacci story points （first version）.

 ### 0.1 (March 15, 2022)

- Set up the QMPlus Hub group and discuss the project handout.
- Discuss and write user stories focus on more specific and quantifiable details.
- Build project framework.

   

   

------



## Credits

   We would first like to thank **Queen Mary University** and **Beijing University of Posts and Telecommunications** for the joint training program that provided much needed guidance and assistance for this course project.
   Secondly, please allow us to express our sincere gratitude to **JetBrains** for providing free individual licenses for students and teachers to learn programming with its best-in-class development tools.
