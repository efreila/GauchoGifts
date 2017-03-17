# GauchoGifts
### About
---------
GuachoGifts is an android application that hosts themed gift exchanges exclusively for the UCSB community.

### How to Download/Run
---------
1. [Clone this repository to your local computer](https://github.com/efreila/GauchoGifts)
2. [Install the latest version of Android Studio](https://developer.android.com/studio/index.html)
3. Open Android Studio on your computer.
4. Click "Open an existing Android Studio project', find the path to the repo, and select 'Open'. 
5. Once the project has opened, select the 'Run' tab, then 'Run app'.
6. Select 'Create New Virtual Device' and choose 'Nexus 6' (then finish navigating menus).
7. Finally, select 'Nexus 6' and click 'OK'.
8. The application will then launch.

In order to run our app you must use any virtual Android device with an API 22 or 
higher. To do this users should have the most recent update of Android Studio downloaded.
You can then click the green arrow to build and run the app. There are a lot of 
dependencies that the app relies on so users should not edit the gradle files at all.

# Include any necessary details about where to store data files too.
All the data is stored online through Google's Firebase.

# Also include any other special information we need to know in order to test your 
system properly (a list of known bugs, for example).
As of now, users can not access the add an exchange page since this part was not planned
to be implemented in our iteration. However, we have manually added data through 
Firebase online to confirm that our data retrieval works as intended. We will 
demonstrate this in our demo.

# Finally include brief guidance about how to use the system 
# (e.g., how to play the game).
THe first thing a user of our app should do is sign up and make an account using their
umail. Upon completing the sign up process, the user will get an email to verify their
account. The user can not log in until their email has been verified. Once verified,
the user can click the log in button and log in using their umail and personal password.
Once in the app, the user can explore the app using the navigation bar. For our first
iteration we only have the ability to sign up, login, and explore their home/exchanges
page, the FAQ, along with their personal profile. The user can also log out using the 
log out button in the profile activity.
