# luxury_jm_clinic

## BaseResponseDTO

```json
{
    "code" : "200",
    "message" : "성공",
    "data" : {
        ...,
    }
}
```

## 목표

### 도메인

| 도메인  | Mapper             | 인터페이스 예시                               | XML 매퍼 파일 (Namespace)                                                                         | 주요 SQL 및 설명 |
| ------- | ------------------ | --------------------------------------------- | ------------------------------------------------------------------------------------------------- | ---------------- |
| Owner   | OwnerMapper.java   | dev.spring.petclinic.mapper.OwnerMapper.xml   | - 소유자 조회 (findOwnerById, findAllOwners, findOwnersByLastName) - 소유자 등록, 수정, 삭제 쿼리 |
| Pet     | PetMapper.java     | dev.spring.petclinic.mapper.PetMapper.xml     | - 특정 소유자의 펫 목록 조회 (findPetsByOwnerId) - 펫 등록, 수정, 삭제 쿼리                       |
| Visit   | VisitMapper.java   | dev.spring.petclinic.mapper.VisitMapper.xml   | - 펫 방문 기록 조회 (selectVisitsByPetId) - 방문 등록, 수정, 삭제 쿼리                            |
| PetType | PetTypeMapper.java | dev.spring.petclinic.mapper.PetTypeMapper.xml | - 펫 타입 목록 및 상세 조회 쿼리                                                                  |

### api

| 도메인        | HTTP 메서드 | URL 요청 예시 (JSON)                                                                                                                           | 응답 예시 (JSON)                                                                                                                        | 설명                                            |
| ------------- | ----------- | ---------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------- |
| **Owner**     | GET         | `/api/owners?lastName=Doe&page=1&size=10`                                                                                                      | `[ { "id": 1, "firstName": "John", "lastName": "Doe", "address": "123 Main St", "city": "Seoul", "telephone": "...", "pets": [...] } ]` | 전체 소유자 목록 조회 (검색 및 페이징 지원)     |
|               | GET         | `/api/owners/1`                                                                                                                                | `{ "id": 1, "firstName": "John", "lastName": "Doe", "address": "123 Main St", "city": "Seoul", "telephone": "...", "pets": [...] }`     | 특정 소유자 상세 조회 (소유자에 등록된 펫 포함) |
|               | POST        | `{ "firstName": "John", "lastName": "Doe", "address": "123 Main St", "city": "Seoul", "telephone": "010-1234-5678" }`                          | `{ "id": 1, "firstName": "John", "lastName": "Doe", "address": "123 Main St", "city": "Seoul", "telephone": "010-1234-5678" }`          | 새 소유자 등록                                  |
|               | PUT         | `/api/owners/1` <br> `{ "firstName": "John", "lastName": "Doe", "address": "456 New Address", "city": "Seoul", "telephone": "010-1234-5678" }` | `{ "id": 1, "firstName": "John", "lastName": "Doe", "address": "456 New Address", "city": "Seoul", "telephone": "010-1234-5678" }`      | 기존 소유자 정보 업데이트                       |
|               | DELETE      | `/api/owners/1`                                                                                                                                | -                                                                                                                                       | 소유자 삭제 (필요 시)                           |
| **Pet**       | GET         | `/api/owners/1/pets`                                                                                                                           | `[ { "id": 101, "name": "Buddy", "birthDate": "2020-01-01", "petType": { "id": 1, "name": "Dog" }, "visits": [...] } ]`                 | 특정 소유자에 등록된 펫 목록 조회               |
|               | GET         | `/api/pets/101`                                                                                                                                | `{ "id": 101, "name": "Buddy", "birthDate": "2020-01-01", "petType": { "id": 1, "name": "Dog" }, "visits": [...] }`                     | 단일 펫 상세 조회 (방문 기록 포함)              |
|               | POST        | `/api/owners/1/pets` <br> `{ "name": "Buddy", "birthDate": "2020-01-01", "typeId": 1 }`                                                        | `{ "id": 101, "name": "Buddy", "birthDate": "2020-01-01", "petType": { "id": 1, "name": "Dog" }, "visits": [] }`                        | 새 펫 등록                                      |
|               | PUT         | `/api/pets/101` <br> `{ "name": "Buddy Updated", "birthDate": "2020-01-01", "typeId": 1 }`                                                     | `{ "id": 101, "name": "Buddy Updated", "birthDate": "2020-01-01", "petType": { "id": 1, "name": "Dog" }, "visits": [...] }`             | 펫 정보 업데이트                                |
|               | DELETE      | `/api/pets/101`                                                                                                                                | -                                                                                                                                       | 펫 삭제 (필요 시)                               |
| **Visit**     | GET         | `/api/pets/101/visits`                                                                                                                         | `[ { "id": 201, "visitDate": "2021-05-15", "description": "Regular check-up" } ]`                                                       | 특정 펫의 방문 기록 조회                        |
|               | POST        | `/api/pets/101/visits` <br> `{ "visitDate": "2021-05-15", "description": "Regular check-up" }`                                                 | `{ "id": 201, "visitDate": "2021-05-15", "description": "Regular check-up" }`                                                           | 새 방문 기록 추가                               |
|               | PUT         | `/api/visits/201` <br> `{ "visitDate": "2021-05-20", "description": "Updated check-up" }`                                                      | `{ "id": 201, "visitDate": "2021-05-20", "description": "Updated check-up" }`                                                           | 방문 기록 업데이트 (선택 사항)                  |
|               | DELETE      | `/api/visits/201`                                                                                                                              | -                                                                                                                                       | 방문 기록 삭제 (선택 사항)                      |
| **Vet**       | GET         | `/api/vets`                                                                                                                                    | `[ { "id": 1, "firstName": "Alice", "lastName": "Smith", "specialties": [ { "id": 1, "name": "Surgery" } ] } ]`                         | 전체 수의사 목록 조회                           |
|               | GET         | `/api/vets/1`                                                                                                                                  | `{ "id": 1, "firstName": "Alice", "lastName": "Smith", "specialties": [ { "id": 1, "name": "Surgery" } ] }`                             | 단일 수의사 상세 조회 (전문 분야 포함)          |
| **Specialty** | GET         | `/api/specialties`                                                                                                                             | `[ { "id": 1, "name": "Surgery" }, { "id": 2, "name": "Dentistry" } ]`                                                                  | 전문 분야 목록 조회 (선택 사항)                 |
|               | GET         | `/api/specialties/1`                                                                                                                           | `{ "id": 1, "name": "Surgery" }`                                                                                                        | 단일 전문 분야 상세 조회 (선택 사항)            |
| **PetType**   | GET         | `/api/types`                                                                                                                                   | `[ { "id": 1, "name": "Dog" }, { "id": 2, "name": "Cat" } ]`                                                                            | 펫 타입 목록 조회                               |
|               | GET         | `/api/types/1`                                                                                                                                 | `{ "id": 1, "name": "Dog" }`                                                                                                            | 단일 펫 타입 상세 조회 (필요 시)                |
