# Context
 Android 개발을 하다보면 Context를 써야할 일이 너무 많다. 간단하게, Toast 나 Snackbar부터 시작해서 layout을 infalte한다거나 리소스 파일들을 화면에 그리는 일까지 거의 모든 부분에서 사용된다. 그렇게 무지성으로 Context를 사용하다 Fragment에서 Context를 사용하려니 어떤 방법은 되고, 어떤 방법은 Nullable이 뜨면서 이에 대해 조금 더 깊이 이해할 필요를 느꼈다.
 Context는 무엇이고 어떻게 사용해야되는지 알아보자

<br/>

## 1. Context?
- 시스템을 사용하기 위한 Propery와 Method가 담겨 있는 클래스
- Component가 Runtime에 들어가면 Context가 실행되고, 생성된 Component가 가지고 있는 Method를 호출해 각각의 Method를 이용할 수 있다.
- Android Developer Docs의 정의 

  >Interface to global information about an application environment. This is an abstract class whose implementation is provided by the Android system. It allows access to application-specific resources and classes, as well as up-calls for application-level operations such as launching activities, broadcasting and receiving intents, etc.

<br/>

## 2. Android에서의 Context
- 앱에서 사용하는 기능이 담겨있는 Base Class
- Context를 상속받은 Component들은 ``baseContext``를 호출하는 것만으로 Android의 기본 기능을 사용 가능하다.
- [예시] \
  Activity에서 ``StartActivity()``를 사용하여 다른 Activity를 호출할 수 있는 이유는 이미 해당 메소드가 설계되어있는\
  Context를 상속받아 구현되어있기 때문이다.

<br/>

  ### [1] Context의 종류  
  (1) Application Context 
  - Application과 관련된 핵심 기능을 담고 있는 클래스.
  - 앱을 통틀어서 하나의 인스턴스만 생성됩니다. (싱클턴?)
  - Component에서 `getApplicationContext()`를 호출해서 사용할 수 있는데 이는 모두 동일한 Context

  (2) Base Context
  - Base Context는 4대 Component의 기반 클래스.
  - 각각의 Component에서 `baseContext`나 `this`를 통해 사용할 수 있다.
  - Component 개수만큼 Context도 생성된다.

<br/>

  ### [2] Context의 기능
  ![image](https://user-images.githubusercontent.com/72553228/140634170-dafbed08-11dd-4baa-b762-5fc555bd005f.png)


<br/>

## 3. Fragment에서의 Context 사용
  처음에 `this`와`getContext()`를 사용했지만 각각 this가 Context가 아니고, Null일 가능성으로 인해 컴파일되지 않았고
  해결책은 두 가지가 나왔다.

  1. `requireContext()`
  2. `onAttach()`에서 Context를 받아오는 형식

<br/>

  #### [1] `this`가 안되는 이유
   1. 이를 이해하기 위해서는 먼저 Fragment가 생겨난 이유에 대해서 이해해 볼 필요성이 있다. <br/>
    - 태블릿의 존재로 인해 화면이 커지면서 보다 많은 UI의 구성요소들이 조합되고 상호교환될 필요성이 존재하게 되었다. <br/>
      그러다 보니, 하나의 Acitivy 안에서 다양한 UI 구성요소들을 효율적으로 관리해 줄 존재에 대한 필요성이 생겨났다. <br/>
      이를 해결하기 위해 Fragment를 탄생시켜 하나의 Activity의 Layout을 여러 개의 Fragment로 나누어 관리해줄 수 있게 만들어준 것이다. <br/>  
  ![image](https://developer.android.com/images/fundamentals/fragments.png?hl=ko)

  2. 그럼 Fragment와 Activity의 관계를 어떻게 이해하면 좋을까? <br/>
    -  아래의 그림과 같이 Activity위에 Fragment가 있고 그 위에 View가 존재하는 것이다.

  3. 그럼 왜 Context Parameter자리에 this가 될 수 없는지 짐작 가지 않는가?  <br/>
    -  Activity는 Context에 상속받아 생기는 클래스이기 때문에 this가 가능했지만, Fragment는 Activity 위에 붙는 존재인 것이다.

  4. **결론:**  **Activity위에 Fragment나 View가 존재한다!!** <br/>
    - 이를 이해한다면, 지금까지 Context에 여러 가지 메소드들이 안되는 이유를 모두 이해할 수 있을 것이다.

![image](https://user-images.githubusercontent.com/72553228/140957963-78d1c63d-bd49-438c-bcd3-b9fa5e64cabc.png)
  
 <br/>
 <br/>
 
  #### [2] `getContext()`가 Null일 가능성을 띄우는 이유
  코드를 타고 들어가보니 @Nullable Annotation이 붙어 있었다!!
  
  ![image](https://user-images.githubusercontent.com/72553228/140634383-b3015c8e-5bda-47d4-802f-31629e03d35f.png)
  
 <br/>
 <br/>
 
  #### [3] `requireContext()`와 `onAttach()`의 차이
  
   <br/>
  
  (1) `requireContext()`
    - @NonNull Annotation이 붙어있기 때문에 `getContext()`와 달리 오류를 띄우지 않은 것이다.
    - 
![image](https://user-images.githubusercontent.com/72553228/140634408-f5ec7771-9f95-43d8-b25d-1af2e000719a.png)
  
   <br/>
  
  (2) `onAttach()`
  - Fragment가 Context에 처음 붙을 때, 실행되는 함수
  - `onCreate()`가 다음에 불려진다.
 
  ![image](https://user-images.githubusercontent.com/72553228/140634547-b6336749-d118-405c-bf4b-f964828c40bf.png)
  
  
  - Fragment에서 `getContext()`를 불러올 때는 Context가 첨부되어 있다는 가정하에 불러와야 한다고 한다.
  ViewPager2를 통해 생성된 경우에는 Context가 제대로 붙지 않는 것 같다. (이건 가정!!! 그럼 언제 붙는 걸까?)
  
  ![image](https://user-images.githubusercontent.com/72553228/140635053-15c4e060-cc3b-4d32-9e78-b9c6b399ef4d.png)
  




  



 <br/> <br/> <br/>

## 참고할만한 글

[ 이해하고 가면 좋은 글 ]\
https://blog.mindorks.com/understanding-context-in-android-application-330913e32514

[ 따라서 깊게 공부해볼만한 글 ]\
https://black-jin0427.tistory.com/220
