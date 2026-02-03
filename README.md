# 🎯 Effective Java 3/E 학습 프로젝트

> AI와 함께하는 깊이 있는 학습

**Dev Book Lab** 스타일로 정리한 『이펙티브 자바 3판』 학습 저장소

---

## 🚀 빠른 시작

### 1️⃣ 프로젝트 구조

```
effective-java/
├── chapter02/                      # Chapter 2: 객체 생성과 파괴
│   ├── docs/
│   │   └── item02.md              # Item 2 상세 문서
│   └── README.md
│
└── src/main/java/effectivejava/
    └── chapter2/
        └── item02/                # Item 2 예제 코드
            ├── builder/
            ├── hierarchicalbuilder/
            ├── javabean/
            └── telescopingconstructor/
```

### 2️⃣ 코드 실행하기

```bash
# 컴파일
javac -d out -sourcepath src/main/java src/main/java/effectivejava/chapter2/item02/**/*.java

# 실행 - 점층적 생성자 패턴
java -cp out effectivejava.chapter2.item02.telescopingconstructor.NutritionFacts

# 실행 - 자바빈즈 패턴
java -cp out effectivejava.chapter2.item02.javabean.NutritionFacts

# 실행 - 빌더 패턴
java -cp out effectivejava.chapter2.item02.builder.NutritionFacts

# 실행 - 계층적 빌더 패턴
java -cp out effectivejava.chapter2.item02.hierarchicalbuilder.PizzaTest
```

---

## 📚 Chapter 2: 객체 생성과 파괴

### Item 2: 생성자에 매개변수가 많다면 빌더를 고려하라

**📖 문서**: [`chapter02/docs/item02.md`](./chapter02/docs/item02.md)

**💻 예제 코드**:
- [점층적 생성자 패턴](./src/main/java/effectivejava/chapter2/item02/telescopingconstructor/NutritionFacts.java)
- [자바빈즈 패턴](./src/main/java/effectivejava/chapter2/item02/javabean/NutritionFacts.java)
- [빌더 패턴](./src/main/java/effectivejava/chapter2/item02/builder/NutritionFacts.java)
- [계층적 빌더 패턴](./src/main/java/effectivejava/chapter2/item02/hierarchicalbuilder/)

---

## 🎓 학습 방법

1. **문서 읽기**: `chapter02/docs/item02.md`부터 시작
2. **코드 실행**: 각 패턴의 예제 코드 실행해보기
3. **비교 분석**: Before/After 코드 비교
4. **실습**: 자신의 클래스에 빌더 패턴 적용해보기

---

## ⭐️ 핵심 요약

```
생성자 매개변수가 많다면 → 빌더 패턴 고려

점층적 생성자의 안전성 + 자바빈즈의 가독성 = 빌더 패턴
```

---

Made with ❤️ by Dev Book Lab
