# Chapter 2. 객체 생성과 파괴

객체를 만들어야 할 때와 만들지 말아야 할 때를 구분하는 법, 올바른 객체 생성 방법과 불필요한 생성을 피하는 방법, 제때 파괴됨을 보장하고 파괴 전에 수행해야 할 정리 작업을 관리하는 요령을 다룬다.

---

## 📖 Items

| Item | 제목 | 핵심 내용 |
|------|------|-----------|
| **Item 1** | 생성자 대신 정적 팩터리 메서드를 고려하라 | (예정) |
| **[Item 2](./docs/item02.md)** | 생성자에 매개변수가 많다면 빌더를 고려하라 | 빌더 패턴의 이해와 활용 ✅ |
| **Item 3** | private 생성자나 열거 타입으로 싱글턴임을 보증하라 | (예정) |
| **Item 4** | 인스턴스화를 막으려거든 private 생성자를 사용하라 | (예정) |
| **Item 5** | 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라 | (예정) |
| **Item 6** | 불필요한 객체 생성을 피하라 | (예정) |
| **Item 7** | 다 쓴 객체 참조를 해제하라 | (예정) |
| **Item 8** | finalizer와 cleaner 사용을 피하라 | (예정) |
| **Item 9** | try-finally보다는 try-with-resources를 사용하라 | (예정) |

---

## 🎯 Item 2 상세 가이드

### 📖 [생성자에 매개변수가 많다면 빌더를 고려하라](./docs/item02.md)

**학습 목표**:
- 점층적 생성자 패턴의 문제점 이해
- 자바빈즈 패턴의 치명적 단점 파악
- 빌더 패턴의 장점과 활용법
- 계층적 빌더 패턴 구현

**예제 코드**:
- [점층적 생성자](../src/main/java/effectivejava/chapter2/item02/telescopingconstructor/NutritionFacts.java)
- [자바빈즈](../src/main/java/effectivejava/chapter2/item02/javabean/NutritionFacts.java)
- [빌더](../src/main/java/effectivejava/chapter2/item02/builder/NutritionFacts.java)
- [계층적 빌더](../src/main/java/effectivejava/chapter2/item02/hierarchicalbuilder/)

---

## 💡 핵심 메시지

```
객체 생성은 단순해 보이지만, 
올바른 방법을 선택하는 것이 중요하다

매개변수가 많다면? → 빌더 패턴
```

---

**이전**: Chapter 1 | **다음**: Chapter 3 | **목차**: [메인 README](../README.md)
