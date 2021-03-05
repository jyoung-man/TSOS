# TSOS(The season of the Song)
## 그때 그 노래: 노래와 계절, 날씨를 찾는 앱

### 개요

음악은 추억을 담고 있습니다. 노래를 들으며 그 노래를 듣던 시절을 추억해본 분들이 많을 것입니다. 그런데 그 때를 상기시키는 요소가 몇 가지만 더해져도 훨씬 생생하게 추억할 수 있다는 것을 발견했습니다.
노래를 찾아주는 것과 더불어 그 때 사용자의 나이, 계절과 날씨까지 보여준다면 사용자가 그때를 좀 더 생생하게 회상할 수 있을 것입니다.

### 기능

#### 노래로 계절 찾기
노래의 제목을 통해 그 노래가 유행한 계절과 그 때의 날씨 정보를 찾는 기능입니다.
노래의 제목을 검색하면 해당 문자열이 포함된 노래 목록을 보여 주고, 그 중에서 찾고자 하는 노래를 클릭하면 그 노래가 유행한 계절과 평균 대비 그 해 그 계절의 날씨 정보를 알려줍니다. 히트하지 않은 노래는 검색이 어려울 수 있습니다.

#### 계절로 노래 찾기

이 앱에 준비된 데이터들을 통해 기억하고자 하는 날에 대한 정보를 되새기는 기능입니다. 
연도와 계절을 입력하면 당시 유행한 노래와 그 때의 날씨 정보가 출력됩니다.

### 범위
검색 기능을 가지며 검색은 데이터베이스에 질의를 거쳐 탐색하는 것으로 이루어집니다.

앱에 내장된 데이터베이스에 1990년 3월부터 2020년 8월까지의 날씨 정보와 유행하던 노래 정보를 저장하고 있습니다. 
노래 정보와 날씨 조회도 1990년 봄부터 가능합니다.
이 앱에서 말하는 ‘날씨’란 날씨를 보고자 하는 계절이 그 계절의 평균 대비 추웠는지 더웠는지, 비(눈)가 많이 왔는지, 적게 왔는지를 의미합니다.

노래 정보란 그 노래를 부른 가수가 누구고 그 노래가 유행한 해에 사용자가 몇 살이었는지, 그리고 유행한 계절이 언제였는지를 의미합니다.

유행하던 노래 목록은 멜론 월간차트 Top 50을 바탕으로 탐색 범위 내의 무작위 세 곡을 추출합니다. 

탐색 범위는 사용자가 선택한 계절에 따라 다르며, 각각 봄: 3월-5월, 여름: 6월-9월, 가을: 10월-11월, 겨울: 12월-이듬해 2월입니다.

1월~2월에 유행한 노래는 이전 해 겨울을 선택하여 조회 가능합니다.

예) 2000년 겨울을 선택하였을 때 2001년 1월~2월 노래 조회 가능

### 관련 영상
발표 영상  
https://konkukackr-my.sharepoint.com/:v:/g/personal/jyp13_konkuk_ac_kr/EZabDaYHvclBtZU4LuGzn0UBCUs4cxwPro3Z9wwf_oelLw?e=4%3aI3repV&at=9
앱 구동 영상  
https://konkukackr-my.sharepoint.com/:v:/g/personal/jyp13_konkuk_ac_kr/EXcyYFZ_QDNFpStsiE1efqgBX0mrNn3jNNAApg-ZP0xBKA?e=c2EtT1  
