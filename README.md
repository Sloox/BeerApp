# BeerApp v1.0
This beer application is a showcase of a solution with the following requirements.

```Build an app that lists a number of beers from the Brewdog public API. The app must also provide a detail view for a beer. The UI, UX and implementation details are at the discretion of the candidate. You can use any architecture or third party dependencies as you choose.```

## Architecture & 3rd Party dependancies
The application makes use of the following architecture & 3rd Party dependancies
* MVVM
* Data-binding
* Co-routines
* Google design libraries (AndroidX)
* Dagger2
* Glide
* Retrofit + Gson
* Android Jetpack Navigation
* Kotlin
* Repository layer
* Service layer

## Further Requirements
Further requirements included:
```
Beer List Screen
Fetch a small number of beers and display them in a list. Each item
should show a few critical details:
•	Image
•	Name
•	ABV

Beer Details Screen
By clicking on any item in the list, the App should navigate to the details view including following elements:
•	Image
•	Name
•	ABV
•	Description
•	Display all Hops in a list
•	Display all Malts in a list
•	Display all Methods in a list

```
The application therefore makes use of a mono-activity applciation whereby two fragments are used to represent the requirements above.

BeerListFragment contains a staggered list of beers retrieved from the [PunkAPI](https://punkapi.com/documentation/v2) which includes only the beers ABV, picture and name.

BeerDetailFragment contains more details which include, name, ABV, DOB, tagline, ingredients (hops, malts and methods).


## Other requirements and thoughts
It was stated to not spend too much time developing a solution. Therefore more time was taken for a complete architecture than a beautiful UI. 

Some lacking UI elements would be proper error screens instead of toasts and suitable error messages for failures. Other improvements would include better toolbar handling and a better abstraction of the Adapters for the recyclerviews.

Testing is also lacking due to honestly just not being enough to test in a unit test sense. UI testing would be a great addition. However time constraints push against the focus on it here.

