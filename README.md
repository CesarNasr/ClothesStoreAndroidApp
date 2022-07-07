# ClothesStoreAndroidApp

This is a sample app that includes screens to view : -Catalogue; -Item Details ; -Wishlist; -Basket.

Note: I have included a sample package for showcasing my Compose UI skills ("composeuisample" package), I am still in the learning curve regarding this technology, so I was not completely comfortbale
to solely use in the app, as I would have not been able to showcase my real understanding of the architecture I like to use as well as the skills in Android development.
For future projects, I plan to get for comfortable with Compose UI and start using it instead of the traditional XML layouts and Databinding. Thank you for understanding.


SDKs and Languages used :
- Kotlin
- Android SDK

Jetpack Library
- Coroutines, StateFlow and Flow (especilly for room database)
- Navigation Component Architecture
- Safe Args
- ViewModels
- Data binding

Architecures and patterns :
- MVVM
- Repository pattern
- Dependency Injection using Dagger-Hilt, integrated with viewmodels and views(fragments and activities)
- A  single layer of abstraction between Data layer and Presention Layer using interfaces (Reposiotry & RepositoryImpl)
- SOLID principles

Layouts and Designs:
- ConstraintLayout and other Layouts
- Material Design
- Data binding
- Sample compose UI "composeuisample" package

- I have implemented a sample work for :
  - UnitTests (MockRepository and viewmodels)
  - Integrated Tests ( ex : to test DAOs in Room Database)


What could have been added :
- Fully implementing Clean Architecture (added UseCase classes)
- Better UX practices
- Better SOLID Principles
- Implementing Compose UI instead of XML layouts
- Better UniTests practices and wider implementations
- CI/CD pipelines (github actions / bitrise / etc...)

Thank you :) 






