# Context

## 1. Context?
- 시스템을 사용하기 위한 Propery와 Method가 담겨 있는 클래스
- Component가 Runtime에 들어가면 Context가 실행되고, 생성된 Component가 가지고 있는 Method를 호출해 각각의 Method를 이용할 수 있다.


## 2. Android에서의 Context
- 앱에서 사용하는 기능이 담겨있는 Base Class
- Context를 상속받은 Component들은 ``baseContext``를 호출하는 것만으로 Android의 기본 기능을 사용 가능하다.
- [예시] \
  Activity에서 ``StartActivity()``를 사용하여 다른 Activity를 호출할 수 있는 이유는 이미 해당 메소드가 설계되어있는\
  Context를 상속받아 구현되어있기 때문이다.
  
  
  
  
