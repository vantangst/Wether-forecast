# Android Development Challenge

## Problem Statement
Build an android app to retrieve weather information based on their searching criteria and render the searched results on dashboard screen.

## Problem solved
We will build an MVVM version with Retrofit to call and retrieve data from OpenWeatherMapsAPI.
#### For more detail:
- MVVM architecture: https://developer.android.com/jetpack/guide
- Retrofit: https://square.github.io/retrofit/

## Features
- Theapplication is a simple Androidapplication which is written by Java/Kotlin.
- Theapplication is able to retrieve the weather information from OpenWeatherMapsAPI.
- Theapplication is able to allow user to input the searching term.

## Next steps
- The application is able to support caching mechanism so as to prevent the app from generating a bunch of API requests.

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
WEATHER_APP_ID="Your api string with 32 characters long"
```
2. `sync` your project
