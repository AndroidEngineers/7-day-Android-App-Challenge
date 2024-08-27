<img src="https://github.com/user-attachments/assets/23afc770-8f95-4c9a-8971-2f550ba1c4d4" width= "150" height="175"/> 

# Anime X

Anime X is a sample app built with Jetpack Compose
This sample showcases:

- jetpack compose + kotlin
- MVVM + Clean Arch (data, domain (usecases) and UI) - as of now implemented on the network layer
- kotlin flows + coroutines
- compose navigation
- hilt 
- Retrofit
- UI testing

## Screenshots (Current Scenario)

https://github.com/user-attachments/assets/9e3a6dad-4a85-4c83-a706-a53533d55ca5

### Overall Status: 🚧 In progress

Anime X is still in under development, and some features are not yet implemented.
Features in pipeline include but not limited to: 
- Favorites
- Display more info on the landing page (top anime/manga, latest news)
- offline first approach
- Events and States segregation on UI layer (feature based)
- pagination on Anime List Screen
- Share anime listing/details

## Bugs
There might be some bugs that would have crept in and went unnoticed while learning and developing, but main priority is to implement the functionality and designs agreed upon initially and these bugs would be fixed in coming updated versions/releases.

## Features

### Anime List
The [AnimeList](Abhi-AnimeX/app/src/main/java/com/abhijith/animex/ui/screens/animelist/AnimeList.kt) composable is the entry point to this screen.
This screen basically calls the [SeasonNow API](https://api.jikan.moe/v4/seasons/now) from [jikan](https://jikan.moe/) that displays current seasonal anime.

### Anime Details
The [AnimeDetails](Abhi-AnimeX/app/src/main/java/com/abhijith/animex/ui/screens/animedetails/AnimeDetails.kt) composable is the entry point to this screen.
This screen displays the details of the selected anime from Anime List screen. Before displaying the details, there is a user induced lottie animated [loading screen](Abhi-AnimeX/app/src/main/java/com/abhijith/animex/ui/screens/loading/LoadingScreen.kt) displayed to mimic an API call but the details are already present on List screen itself.

### UI tests
In [androidTest](Abhi-AnimeX/app/src/androidTest/java/com/abhijith/animex) you'll find a suite of UI tests that showcase interesting patterns in Compose:

#### [NavigationTest](Abhi-AnimeX/app/src/androidTest/java/com/abhijith/animex/NavigationTest.kt)
Shows how to write tests that assert directly on the [AppNavHost](Abhi-AnimeX/app/src/main/java/com/abhijith/animex/ui/navigation/AppNavHost.kt).

### Progression
<img src="https://github.com/user-attachments/assets/d2cccce8-1183-4815-acef-4b1d4a51374e" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/d80d10e0-86b9-4ff7-80e3-e6d6ac700581" width="250" height="500"/>

### Setup
To try out this sample app, use the latest stable version
of [Android Studio](https://developer.android.com/studio).
You can clone this repository or import the
project from Android Studio following the steps
[here](https://developer.android.com/jetpack/compose/setup#sample).

## Additional Info
- This project is started as a part of 7 Day Challenge organised by [Android Engineers](https://www.linkedin.com/company/android-engineers/posts/?feedView=all)
- App icon is created and edited using [Microsoft Designer](https://designer.microsoft.com/image-creator) tool.

## Contribution

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Added some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request
