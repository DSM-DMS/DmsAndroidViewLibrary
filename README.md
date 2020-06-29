# DmsAndroidViewLibrary
Dms에서 사용할 Android View Library 입니다.

## Setting
build.gradle에 다음과 같이 추가해주세요.
```gradle
implementation 'com.dsm.dms.library:dmsviewlibrary:0.0.1'
```

## Functions
### DmsBigCalendarView
![image](images/DmsBigCalendarView.png)

```kotlin
class MainActivity: AppCompatActivity(), DmsCalendarUserListener
```
DmsBigCalendar를 사용하는 Activity 및 Fragment에 ```DmsCalendarUserListener``` 인터페이스를 implement 해줍니다.

```kotlin
override fun selectedEvent(dateString: String) {
    // todo
}
```
인터페이스의 selectedEvent 함수를 구현해주는데 이 함수는 day가 선택될 때마다 ```yyyy년 M월 d일 E일``` 형태로 dateString이 반환됩니다.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    // essential
    calendar.setCalendar(Date(), this)

    // option
    calendar.setCalendarEventDays(
        arrayListOf(
            // event Date
        )
    )
}
```
onCreate에 Date를 설정해주고 필요한 경우 eventDays도 설정해줍니다.

## DmsSmallCalendarView
![image](images/DmsSmallCalendarView.png)

사용 방법은 DmsBigCalendarView와 동일하게 ```DmsCalendarUserListener``` 인터페이스를 implement를 하여 사용합니다.