package com.hany.retrofittest
//출처 https://blog.yena.io/studynote/2020/01/08/Android-Kotlin-Retrofit.html
//https://joycehong0524.medium.com/android-studio-retrofit2-%EA%B8%B0%EB%B3%B8-%EC%82%AC%EC%9A%A9%EB%B2%95-retrofit-%EC%9D%98%EB%AC%B8%EC%A0%90-%ED%92%80%EC%96%B4%ED%97%A4%EC%B9%98%EA%B8%B0-%EC%8A%A4%EC%95%95-f150db436add

//응답받을 data class
data class ResultGetSearchNews(
    var lastBuildDate: String = "", //검색 결과생성 시간
    var total: Int = 0, //검색 결과 문서 총 개수
    var start: Int = 0, //검색 결과 문서중 문서의 시작점
    var display: Int = 0, //검색된 검색 결과의 개수
    var items: List<Items> //개별 검색 결과
)

data class Items(
    var title: String = "", //
    var originallink: String = "",//문서 제공 언론사 하이퍼링크
    var link: String = "", //문서 제공 네이버 하이퍼링크
    var description: String = "", //검색 결과 문서의 내용을 요약한 패시지 정보이다. 문서 전체의 내용은 link를 따라가면 읽을 수 있다. 패시지에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
    var pubDate: String = "" //검색 결과 문서가 네이버에 제공된 시간이다.

)