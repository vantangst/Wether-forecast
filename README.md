# Android Development Challenge

## Problem Statement
Build an android app to retrieve weather information based on their searching criteria and render the searched results on dashboard screen.

## Problem solved
We will build an MVVM version with Retrofit to call and retrieve data from OpenWeatherMapsAPI.
#### For more detail:
- MVVM architecture: https://developer.android.com/jetpack/guide
- Retrofit: https://square.github.io/retrofit/

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
