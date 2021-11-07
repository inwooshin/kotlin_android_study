# Context

## 1. Context?
- 시스템을 사용하기 위한 Propery와 Method가 담겨 있는 클래스
- Component가 Runtime에 들어가면 Context가 실행되고, 생성된 Component가 가지고 있는 Method를 호출해 각각의 Method를 이용할 수 있다.
- Android Developer Docs의 정의 
  >Interface to global information about an application environment. This is an abstract class whose implementation is provided by the Android system. It allows access to application-specific resources and classes, as well as up-calls for application-level operations such as launching activities, broadcasting and receiving intents, etc.

## 2. Android에서의 Context
- 앱에서 사용하는 기능이 담겨있는 Base Class
- Context를 상속받은 Component들은 ``baseContext``를 호출하는 것만으로 Android의 기본 기능을 사용 가능하다.
- [예시] \
  Activity에서 ``StartActivity()``를 사용하여 다른 Activity를 호출할 수 있는 이유는 이미 해당 메소드가 설계되어있는\
  Context를 상속받아 구현되어있기 때문이다.
  
  ### [1] Context의 종류  
  (1) Application Context 
  - Application과 관련된 핵심 기능을 담고 있는 클래스.
  - 앱을 통틀어서 하나의 인스턴스만 생성됩니다. (싱클턴?)
  - Component에서 `getApplicationContext()`를 호출해서 사용할 수 있는데 이는 모두 동일한 Context

  (2) Base Context
  - Base Context는 4대 Component의 기반 클래스.
  - 각각의 Component에서 `baseContext`나 `this`를 통해 사용할 수 있다.
  - Component 개수만큼 Context도 생성된다.






## 참고할만한 글

[ 이해하고 가면 좋은 글 ]\
https://blog.mindorks.com/understanding-context-in-android-application-330913e32514

[ 따라서 깊게 공부해볼만한 글 ]\
https://black-jin0427.tistory.com/220
