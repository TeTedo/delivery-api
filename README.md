# 하면서 고민하거나 공부했던 것들
[DDD설계 vs SQL 중심 설계](https://diary-blockchain.tistory.com/283)

[HTTP method](https://diary-blockchain.tistory.com/293)

[REST API](https://diary-blockchain.tistory.com/294)

[URI vs URL](https://diary-blockchain.tistory.com/296)

[REST API 규칙](https://diary-blockchain.tistory.com/297)

[DI 구현 방법](https://diary-blockchain.tistory.com/300)

[this는 꼭 써야 하나?](https://diary-blockchain.tistory.com/303)

[assert를 필요한곳에만 사용](https://diary-blockchain.tistory.com/311)

[Mockito란?](https://diary-blockchain.tistory.com/301)

---
## 정리, 공부할 것들
- 직렬화, 역직렬화
- 구글 코딩 컨벤션
- spring security 인증, 인가
- EntityManager를 직접 다루는 JPA 구현


# API URI
| 기능            | Method | URI                |
|---------------|--------|--------------------|
| 음식 전체 조회      | GET    | /foods             |
| 음식 단일 조회      | GET    | /foods/{food-id}   |
| 음식 등록         | POST   | /foods             |
| 음식 전체 수정      | PUT    | /foods             |
| 음식 부분 수정      | PATCH  | /foods             |
| 음식 삭제         | DELETE | /foods/{food-id}   |

| 기능       | Method | URI                |
|----------|--------|--------------------|
| 주문 생성    | POST   | /orders            |
| 주문 전체 조회 | GET    | /orders            |
| 주문 단일 조회 | GET    | /orders/{order-id} |

| 기능    | Method | URI                    |
|-------|--------|------------------------|
| 결제 시도 | POST   | /payments              |
| 결제 확인 | GET    | /payments/{payment-id} |
---
