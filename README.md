![Imgur](https://imgur.com/KSZEQFE.gif)
# News App
The NEWS application fetched the news and displays on the screen in either list or grid view. The user can click and see the detailed news in nexr screen.

## App use case
- Fetch news and show in List or grid. There is an option to change view from list to grid.
- Clicking on the a news takes the user to the details page
- Reaching to the end of the news list, more results should be downloaded.

## App Architecture
![Imgur](https://imgur.com/1HCtlJo.jpg)
The architecture used for this application is MVVM. The activity uses viewModel functions to call API and fetch data via a repository.
The data is pushed to a live data variable which is being observed in the activity class.
Once the changes are detected, the UI is updated.

Components used:
Hilt for Dependency injection
Coroutines for web service calls
Data Binding for setting data in details class

## Third Party Libraries
The following libraries are used:
- Retrofit: for calling APIs.
- SDP intuit: to support multiple screen avoiding the dimens files.
