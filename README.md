## Product Listing App

This is an android app to display a list of products loaded from a remote mock API server and cached in local Room database.

### Architecture
The app uses:

Clean architecture with layers for data, domain, and presentation
MVVM pattern (View - ViewModel - Model)
Single activity architecture with fragments
Room persistance library for offline cache
Retrofit for API interface
Kotlin coroutines & flow for async
Features
The app has the following features:

- Fetch latest products from mock API
- Cache products locally in Room database
- Display products listing in RecyclerView
- Offline support using cached data
### Modules
The app is split into the following modules:

* data - Handles data sources like API and Database
* domain - Business logic
* presentation - UI and ViewModels

As this app uses the single-activity architecture, the navigation graph handles flows between fragments.

The views and UI layouts are defined in XML.

Libraries
ViewModel
Room
Retrofit
