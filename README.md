# Authme Android App Test

### Features
Create an App which shows the GitHub users in a list.

### Summary
[Module: app]
- User Interface (UI) Display
- Use MVVM architecture.
- There are two pages, `ListFragment` and `UserDetailFragment`, which can be switched through NAVIGATION.
- The drop-down list will reload the data, and swipe up will load the data until the number of data reaches 100.
- Use the Glide library to load images.

[Module: github-user-sdk]
- GitHub user API wrapper, used via `UserInfoRepository`.
- WebAPI is implemented by Volley and Gson libraries.
```
suspend fun getUserDetail(userName: String, listener: ResultListener<UserDetail>) 
suspend fun getUserList(since: Int, perPage: Int, listener: ResultListener<List<User>>)
```

### GitHub API Documentation
[User List](https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#list-users)   
[Pagination](https://docs.github.com/en/rest/using-the-rest-api/using-pagination-in-the-rest-api?apiVersion=2022-11-28#using-link-headers)   

### Library
[Volley](https://developer.android.com/training/volley)   
[Gson](https://www.baeldung.com/kotlin/json-convert-data-class)   
[Coroutines Codelab](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/?hl=da#12)

### Dependency Injection

[Koin](https://insert-koin.io/)

The dependency injection relationship must be defined in MyApplication first.

```
class MyApplication : Application(){
    private val viewModelModule = module{
        viewModel{ YourViewModel(get())} //This 'get()' is the repository that will be injected into the view model
    }

    private val repoModule = module{
        single{ YourRepository(get())} //This 'get()' is the androidContext that will be passed into the view model
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MultiLanguagesApplication)
            modules(
                listOf(
                    viewModelModule,
                    repoModule
                 )
             )
        }
    }
}
```
### UnitTest
- File `UserInfoRepositoryTest`