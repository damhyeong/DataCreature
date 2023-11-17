# Getting Started

### Reference Documentation

<hr>

## signIn - 로그인

```js
request : {
    email : "string"
    password : "string"
}

// ---------------------- 성공 시 ----------------------
/ HTTP Status - 200 (OK) - 인증 성공 
response : {
    code : "SU"
    message : "Success."
    token : "jwt..."
    expiredDate : 123456789
}

// ---------------------- 실패 시 ----------------------
/ Http Status - 401 (UnAuthorized) - 로그인 실패 
response : {
    code : "SF"
    message : "Sign In Failed."
}

/ Http Status - 500 (Internal Server Error) - 데이터베이스 에러
response : {
    code : "DE"
    message : "Database Error."
}
```

<br>

## signUp - 회원가입 

```js
request : {
    email : "string" // 필수
    password : "string" // 필수
    nickname : "string" // 필수
    telNumber : "string" // 필수
    address : "string" // 필수
    addressDetail : "string"
    
}

// ---------------------- 성공 시 ----------------------
/ HTTP Status - 200 (OK) - 인증 성공 
response : {
    code : "SU"
    message : "Success."
}

// ---------------------- 실패 시 ----------------------
/ HTTP Status -  - 필수 정보 미입력, 이메일 포맷 불일치 , 비밀번호 8자 미만, 전화번호 포맷 불일치

/ HTTP Status - 400 (Bad Request) - 이메일 중복 
response : {
    code : "EE"
    message : "Existed Email."
}

/ HTTP Status - 500 (Internal Server Error) - 데이터베이스 에러
response : {
    code : "DE"
    message : "Database Error."
}
```

<br>

## currentList - 최신 게시물 리스트

```
request : {
    
}
```

<br>

## searchList - 검색 게시물 리스트

```

```

<br>

## examDetail - 문제 상세 정보

```

```

<br>

## favoriteList - 좋아요 리스트

```

```

<br>

## examDelete - 문제 삭제

```

```

<br>

## examWrite - 문제 업로드

```

```

<br>

## examUpdate - 게시물 수정

```

```

<br>

## getUser - 유저 정보 

```

```

<br>

## userExamList - 특정 유저 문제 리스트 

```

```
