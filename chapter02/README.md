# Chapter 2. 객체 생성과 파괴

객체를 만들어야 할 때와 만들지 말아야 할 때를 구분하는 법, 올바른 객체 생성 방법과 불필요한 생성을 피하는 방법, 제때 파괴됨을 보장하고 파괴 전에 수행해야 할 정리 작업을 관리하는 요령을 다룬다.

---

## 📖 Items

| Item | 제목 | 상태 |
|------|------|------|
| **[Item 1](./docs/item01.md)** | 생성자 대신 정적 팩터리 메서드를 고려하라 | 📝 작성 예정 |
| **[Item 2](./docs/item02.md)** | 생성자에 매개변수가 많다면 빌더를 고려하라 | ✅ 완료 |
| **[Item 3](./docs/item03.md)** | private 생성자나 열거 타입으로 싱글턴임을 보증하라 | 📝 작성 예정 |
| **[Item 4](./docs/item04.md)** | 인스턴스화를 막으려거든 private 생성자를 사용하라 | 📝 작성 예정 |
| **[Item 5](./docs/item05.md)** | 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라 | 📝 작성 예정 |
| **[Item 6](./docs/item06.md)** | 불필요한 객체 생성을 피하라 | 📝 작성 예정 |
| **[Item 7](./docs/item07.md)** | 다 쓴 객체 참조를 해제하라 | 📝 작성 예정 |
| **[Item 8](./docs/item08.md)** | finalizer와 cleaner 사용을 피하라 | 📝 작성 예정 |
| **[Item 9](./docs/item09.md)** | try-finally보다는 try-with-resources를 사용하라 | 📝 작성 예정 |

**진행 상황**: 1/9 완료 (11%)

---

## 🎓 학습 가이드

### 추천 학습 순서

#### 🔰 기초 (필수)
1. **Item 1**: 정적 팩터리 메서드 - 객체 생성의 다양한 방법
2. **Item 2**: 빌더 패턴 - 매개변수가 많을 때의 해결책 ✅
3. **Item 3**: 싱글턴 - 인스턴스 하나만 보장하기

#### 🏃 실무 필수
4. **Item 5**: 의존 객체 주입 - 유연한 설계의 핵심
5. **Item 6**: 불필요한 객체 생성 피하기 - 성능 최적화
6. **Item 9**: try-with-resources - 자원 관리의 올바른 방법

#### 🎯 심화
7. **Item 4**: private 생성자 - 인스턴스화 방지
8. **Item 7**: 메모리 누수 방지 - 참조 해제
9. **Item 8**: finalizer/cleaner - 사용하지 말아야 할 것들

---

## 📚 완료된 Items

### ✅ Item 2: 생성자에 매개변수가 많다면 빌더를 고려하라

**📖 문서**: [item02.md](./docs/item02.md)

**핵심 내용**:
- 점층적 생성자 패턴의 문제점
- 자바빈즈 패턴의 치명적 단점
- 빌더 패턴의 장점과 구현
- 계층적 빌더 패턴

**💻 예제 코드**:
- [점층적 생성자](../src/main/java/effectivejava/chapter2/item02/telescopingconstructor/NutritionFacts.java)
- [자바빈즈](../src/main/java/effectivejava/chapter2/item02/javabean/NutritionFacts.java)
- [빌더](../src/main/java/effectivejava/chapter2/item02/builder/NutritionFacts.java)
- [계층적 빌더](../src/main/java/effectivejava/chapter2/item02/hierarchicalbuilder/)

**🎯 핵심 교훈**:
```
매개변수가 4개 이상이면 빌더 패턴 고려
= 점층적 생성자의 안전성 + 자바빈즈의 가독성
```

---

## 💡 Chapter 핵심 메시지

```
객체 생성은 단순해 보이지만, 
올바른 방법을 선택하는 것이 중요하다

- 객체 생성 시점과 방법의 선택
- 불변성과 안전성 보장
- 메모리와 자원 관리
```

---

## 🔗 연관된 개념들

### 객체 생성
- Item 1: 정적 팩터리 메서드
- Item 2: 빌더 패턴
- Item 3: 싱글턴
- Item 4: private 생성자

### 객체 관리
- Item 5: 의존 객체 주입
- Item 6: 객체 재사용

### 객체 파괴
- Item 7: 참조 해제
- Item 8: finalizer/cleaner
- Item 9: try-with-resources

---

**이전**: [Chapter 1](../../chapter01/README.md) | **다음**: [Chapter 3](../../chapter03/README.md) | **목차**: [메인 README](../README.md)
