# Android Development Challenge

## Problem Statement
Build an android app to retrieve weather information based on their searching criteria and render the searched results on dashboard screen.

## Problem solved
- Used MVVM for design partent
- Used Retrofit to call and retrieve data from OpenWeatherMapsAPI.
- Used Hilt for dependency injection with Hilt
#### For more detail:
- MVVM architecture: https://developer.android.com/jetpack/guide
- Retrofit: https://square.github.io/retrofit/
- Hilt: https://developer.android.com/training/dependency-injection/hilt-android

## Project Structure
For the project, we built a version of MVVM. Our package in the project will look like below:

![image](https://user-images.githubusercontent.com/13620155/145409226-e4b9d70e-deaa-4812-87c6-9a55cbc50097.png)

## App Components
All the architecture components are as follows:
### Foundation Components
- Test: mockito, junit4 for local testing
### Architecture Components
- LiveData: Notify views of any database changes.
- ViewModel: Manage UI-related data in a lifecycle-conscious way.
- Lifecycles: Manages activity lifecycles of our app.

## Features
- The application is a simple Androidapplication which is written by Java/Kotlin.
- The application is able to retrieve the weather information from OpenWeatherMapsAPI.
- The application is able to allow user to input the searching term.
- The application is able to proceed searching with a condition of the search term length
must be from 3 characters or above.
- The application is able to render the searched results as a list of weather items.
- The application is able to handle failures.

## Next steps
- The application is able to support caching mechanism so as to prevent the app from generating a bunch of API requests.
- The application is able to manage caching mechanism & lifecycle.
- The application is able to support the disability to scale large text for who can't see the text
clearly.
- The application is able to support the disability to read out the text using VoiceOver
controls.

## Note
- I have done for 6/10 items at Problem Statement. I don't have time to finish all items.
- I have done Unit test for logic code (`repository`, `viewmodel`) and some basic test case for ui test (`view`)
- I am a beginer writing UI test so just some basic test was written.

## Screenshots

![wheather app screenshot](https://user-images.githubusercontent.com/13620155/145417831-69c8b727-e436-4247-bb0b-cca525d64600.gif)


## Development
Bellow are the obligatory requirements that you must meet to run project:
1. add configuration in your `local.properties` file.
```
BASE_URL="<your OpenWeatherMapsAPI domain>"
WEATHER_APP_ID="<OpenWeatherMapsAPI App Id>"
```
Example:
```
BASE_URL="https://somedomain.org/"
WEATHER_APP_ID="Your app id string with 32 characters long"
```
2. `sync` your project
