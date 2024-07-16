# Java Backend Engineer 과제

## 구현범위


#### 1번


#### 카테고리별로 최저가 코디


<img width="335" alt="image" src="https://github.com/user-attachments/assets/6def2837-5a3b-425e-8546-6a7715a0db5c">


- 한 카테고리에 최저가인 상품이 여러개일 경우 브랜드에 (A,E) 와 같이 표시되도록 구현했습니다


#### 2번


#### 단일브랜드 전체 구매시 총액이 최저인 브랜드와 상품가격, 총액


<img width="591" alt="image" src="https://github.com/user-attachments/assets/3df112f3-b789-4694-8b1d-cacfccbf0d92">


- 총액이 최저가인 브랜드가 여러개일 수 있음으로 최저가를 list 형으로 구현했습니다
  

#### 3번


#### 한 카테고리의 최저, 최대가격과 브랜드


<img width="420" alt="image" src="https://github.com/user-attachments/assets/541bd4eb-c556-4821-94c7-6d0c295d189d">

#### 4번


#### 브랜드 및 상품 CUD API


##### 생성 POST /merchandise


request:
 {
    "category": "TOP",
    "price": 9000,
    "brand": "A"
}
response:
{
    "id": 74
}
- 금액, 카테고리, 브랜드를 모두 입력해야합니다
- 상품을 생성할 떄 기존에 없던 브랜드를 추가 가능합니다
- 상품 생성이 되면 상품의 ID를 반환합니다


##### 수정 PUT /merchandise/{id}
request:
 {
    "category": "TOP",
    "price": 9000,
    "brand": "A"
}
response:
{
    "category": "TOP",
    "price": 9000,
    "brand": "A"
}
- 상품 수정시 상품 ID가 필요하며 금액, 카테고리, 브랜드를 모두 입력해야합니다
- 수정시에 상품ID 가 필요합니다
- 수정된 정보를 반환합니다


##### 삭제 DELETE /merchandise/{id}
request: x
response: x
- 상품 ID가 필요합니다

  
### Unit test 구현 완료


## 실행방법
- 빌드 : gradle >tasks> build> build
- 테스트 : gradle > tasks> verificaiton> test
- 실행 : com.musinsa.MssApplication 
  - mss-test.main()


