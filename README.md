# Android Development Challenge

## Problem Statement

Build an android app to retrieve weather information based on their searching criteria and show the searched results on the dashboard screen.

## Problem Solution

- Written by Kotlin.
- Used MVVM design pattern.
- Used Retrofit as a REST client to call and retrieve data from OpenWeatherMapsAPI.
- Used Hilt for dependency injection.
- Accessibility for Disability Supports.

#### If you want to know more:

- MVVM architecture: https://developer.android.com/jetpack/guide
- Retrofit: https://square.github.io/retrofit/
- Hilt: https://developer.android.com/training/dependency-injection/hilt-android
- Caching: https://bapspatil.medium.com/caching-with-retrofit-store-responses-offline-71439ed32fda
- Talkback: https://medium.com/microsoft-mobile-engineering/android-accessibility-resolving-common-talkback-issues-3c45076bcdf6

## Project Structure

The project is built using the MVVM model. The package structure will look like below:

![image](https://user-images.githubusercontent.com/13620155/145409226-e4b9d70e-deaa-4812-87c6-9a55cbc50097.png "Project Structure")

## App Components

All the architecture components are as follows:

### Foundation Components

- Unit test: mockito, junit4, robolectric

### Architecture Components

- LiveData: Notify views of any data changes.
- ViewModel: Manage UI-related data in a lifecycle-conscious way.
- Lifecycles: Manages activity lifecycles of our app.

## Features

The Android application is written by Kotlin is able to

- Retrieve the weather information from OpenWeatherMapsAPI.
- Allow users to enter the searching term.
- Proceed to search if the search term length is at least 3 characters.
- Show the searched results as a list.
- Able to handle failures like network down, etc.
- Caching mechanism to prevent the app from generating a bunch of API requests.
- Manage caching mechanism & lifecycle.
- Support the disability to read out the text with Talkback.
- Support the disability to scale large text for those who can't see the text clearly.

## Next steps

The application can be enhanced more for

- Add more local test for ui.
- Unit test for Exception case (`Checked exception` issue), ApiService interface.

## Note

- I have done a Unit test for logic code (`Repository`, `ViewModel`) and some basic test cases for UI test (`View`).
- UI from the requirement is not good enough, need to discuss and improve later.

## Runnable app

![wheather app screenshot](https://user-images.githubusercontent.com/13620155/145417831-69c8b727-e436-4247-bb0b-cca525d64600.gif "Light mode | fetched data from weather API")

![c9272c39fe9935c76c88](https://user-images.githubusercontent.com/13620155/145570512-707258f1-8343-4532-a3a8-a1710d3675fe.jpg "Dark mode | Talkback feature")


## Development

Below are the obligatory requirements that you must meet to run the project:

1. Adding configuration in your `local.properties` file.

```properties
BASE_URL="<your OpenWeatherMapsAPI domain>"
WEATHER_APP_ID="<OpenWeatherMapsAPI App Id>"
```

Example:

```properties
BASE_URL="https://somedomain.org/"
WEATHER_APP_ID="Your app id string with 32 characters long"
```

2. `sync` your project
