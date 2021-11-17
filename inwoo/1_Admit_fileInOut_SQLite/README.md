# kotlin_android_study

# 질문 모음(11/5)

## Context

- Context : 맥락을 의미, 새로 생성된 객체가 지금 어떤 일이 일어나고 있는지 알 수 있게 해줌,
- 또한, 핸들과 같다 → 리소스, 데이터베이스, preferences 등에 대한 접근을 제공, 이러한 역할을 하는 액티비티를 상속받음
    - Application Context : getApplicationContext() 를 통해 접근할 수 있다. Object 로 선언된 싱글턴 인스턴스이다. 가비지 콜렉터에 의해 수집된다. 사용은 현재 컨텍스트와 분리된 라이프사이클을 가진 컨텍스트가 필요할 때! 또는 액티비티의 범위를 넘어서 컨텍스트를 전달할 때 사용, 프로세스가 지속되는 동안 계속 유지되고 있다.
    - Activity Context : getContext() 액티비티의 라이프사이클과 연결되어있다. 액티비티에서 사용가능하며 범위 내에서 컨텍스트를 전달하거나, 컨텍스트에 붙은 컨텍스트가 필요할때 사용
- 프래그먼트에서 this 를 왜 쓰는지 명확하게 이해가 잘 되지 않았다. requireContext, getContext, this, mContext as 어쩌구 이런방식 어떤 상황에서는 어떤게 되고 이게 왜안되고
    1. this 가 안되는 이유 - Activity 가 Context 를 상속받은 클래스여서 this 로 호출이가능하다. Fragment 는 Object 로 상속받은 클래스이고 Context 를 상속받지 않아서 this 사용이 불가하다.
    2. onAttach 함수에서 매개변수로 전달된 것을 사용할 수 있다. - 구남's 방법
    3. context(getContext())
    
    ```java
    val helper = context?.let{ SqliteHelper(it, DB_NAME, DB_VERSION)}
    val adapter = RecyclerAdapter()
    adapter.helper = helper
    
    val memos = helper!!.selectMemo()
    adapter.listData.addAll(memos)
    ```
    
    1. requireContext() 사용 - getContext - Nullable, requireContext - NonNull
    
    → getContext 가 안되는 이유는 매개변수가 context? 가 아닌 context 를 받기 때문이다!
    
    ## 생명 주기
    

![Untitled](https://user-images.githubusercontent.com/59462895/141703113-b413d824-ae4b-4cd2-bf65-db3356ba6f7b.png)   

### 생성 주기 메서드

- onAttach() : 프래그먼트 매니저를 통해서 액티비티에 프래그먼트가 추가되고 commit 될때 호출.
- onCreate() : 프래그먼트가 생성됨과 동시에 호출. 프래그먼트 자원(변수)을 초기화할때 주로 사용
- onCreateView() : 프래그먼트가 자신의 인터페이스를 처음 그리기 위해 호출. 사용자 인터페이스 및 관련된 뷰를 초기화하기 위해서 사용.
- onStart() : 프래그먼트가 추가되거나, 사라졌다가 다시 나타나면 onCreateView 는 호출되지 않고 onStart 만 호출된다. 주로 화면생성 뒤에 화면에 입력될 값을 초기화할 용도로 사용한다.
- onResume() : onStart 와 같은 용도로 사용, onPause 상태에서 재개시 onResume 호출.

### 소멸 주기 메서드

: 새로운 프래그먼트가 add 되거나 현재 프래그먼트를 제거하면 소멸주기 관련 메서드 차례로 호출

- onPause() : 현재 프래그먼트가 화면에서 사라지면 호출. 일시정지 및 현재작업 멈추는 용도로 보통사용
- onStop() : 화면에 일부분이라도 보이면 호출이 안됨. 아예 프래그먼트가 안보일때 호출
- onDestroyView() : 뷰의 초기화를 해제하는 용도, 호출 시 onCreateView 에서 인플레이터로 생성한 View 모두 삭제됨.
- onDestroy() : 액티비티에는 아직 연결이 남아있지만, 프래그먼트 자체는 소멸됨.
- onDetach() : 액티비티에서 연결이 해제됨.

onCreate, onCreateView, onPause - 최소로 만들어줘야하는 메서드
