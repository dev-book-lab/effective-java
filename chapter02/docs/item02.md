# Item 2. 생성자에 매개변수가 많다면 빌더를 고려하라

> 💡 **핵심 요약**: 매개변수가 많은 클래스의 생성자나 정적 팩터리는 빌더 패턴을 고려하라
>
> 🎯 **적용 시기**: 매개변수가 4개 이상이거나, 선택적 매개변수가 많을 때

---

## 📌 문제 상황

정적 팩터리와 생성자는 **선택적 매개변수가 많을 때** 대응하기 어렵다.

```java
public class NutritionFacts {
    private final int servingSize;  // 필수: 1회 제공량 (mL)
    private final int servings;     // 필수: 총 n회 제공량
    private final int calories;     // 선택: 1회 제공량당 칼로리
    private final int fat;          // 선택: 지방 (g/1회 제공량)
    private final int sodium;       // 선택: 나트륨 (mg/1회 제공량)
    private final int carbohydrate; // 선택: 탄수화물 (g/1회 제공량)
}
```

**필수 2개 + 선택 4개** → 어떻게 객체를 생성할 것인가?

---

## ❌ 해결책 1: 점층적 생성자 패턴 (Telescoping Constructor Pattern)

### 구현

필요한 모든 케이스에 대해서 생성자를 만든다!

```java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize  = servingSize;
        this.servings     = servings;
        this.calories     = calories;
        this.fat          = fat;
        this.sodium       = sodium;
        this.carbohydrate = carbohydrate;
    }
}
```

> 💻 **코드**: [`telescopingconstructor/NutritionFacts.java`](../../src/main/java/effectivejava/chapter2/item02/telescopingconstructor/NutritionFacts.java)

### 사용

```java
NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
//                                                        ↑
//                                              fat을 0으로 명시해야 함
```

### 🚨 단점

#### 1. **매개변수 개수가 많아질수록 코드 작성이 힘들고 가독성이 떨어진다**

```java
NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
```
- 각 숫자가 무엇을 의미하는지 알기 어렵다
- 몇 번째 파라미터를 입력하고 있는지 주의 깊게 살펴야 한다

#### 2. **매개변수 순서를 바꿔도 컴파일 에러가 발생하지 않는다**

```java
// 실수로 calories와 fat의 순서를 바꿈
NutritionFacts mistake = new NutritionFacts(240, 8, 0, 100, 35, 27);
//                                                    ↑   ↑
//                                        버그! but 컴파일 성공
```

#### 3. **원하지 않는 매개변수에도 값을 지정해야 한다**

지방(fat)을 설정하고 싶지 않아도 `0`을 명시적으로 전달해야 한다.

---

## ❌ 해결책 2: 자바빈즈 패턴 (JavaBeans Pattern)

### 구현

매개변수가 없는 생성자로 객체를 만든 후, setter 메서드를 호출해 원하는 매개변수의 값을 설정한다.

```java
public class NutritionFacts {
    // 매개변수들을 기본값으로 초기화
    private int servingSize  = -1;  // 필수; 기본값 없음
    private int servings     = -1;  // 필수; 기본값 없음
    private int calories     = 0;
    private int fat          = 0;
    private int sodium       = 0;
    private int carbohydrate = 0;

    public NutritionFacts() { }

    // Setter 메서드들
    public void setServingSize(int val)  { servingSize = val; }
    public void setServings(int val)     { servings = val; }
    public void setCalories(int val)     { calories = val; }
    public void setFat(int val)          { fat = val; }
    public void setSodium(int val)       { sodium = val; }
    public void setCarbohydrate(int val) { carbohydrate = val; }
}
```

> 💻 **코드**: [`javabean/NutritionFacts.java`](../../src/main/java/effectivejava/chapter2/item02/javabean/NutritionFacts.java)

### 사용

```java
NutritionFacts cocaCola = new NutritionFacts();
cocaCola.setServingSize(240);
cocaCola.setServings(8);
cocaCola.setCalories(100);
cocaCola.setSodium(35);
cocaCola.setCarbohydrate(27);
```

### ✅ 장점

- 인스턴스 생성이 간단하고 **코드의 가독성이 좋다**
- 설정하고 싶은 매개변수만 설정할 수 있다

### 🚨 치명적인 단점

#### 1. **객체 일관성(consistency)이 무너진다**

```java
NutritionFacts cocaCola = new NutritionFacts();
// ← 이 시점에 cocaCola는 불완전한 상태!
// servingSize와 servings가 -1로 설정되어 있음

cocaCola.setServingSize(240);
// 여전히 불완전 (servings가 -1)

cocaCola.setServings(8);
// 이제야 최소한의 유효한 상태가 됨
```

여러 번의 메서드 호출로 나누어져 인스턴스가 생성되므로, **생성 과정을 거치는 동안 자바빈 객체가 일관된 상태를 유지하지 못할 수 있다**.

#### 2. **불변 클래스로 만들 수 없다**

```java
public final class NutritionFacts {
    private final int servingSize;  // ❌ setter가 있으면 final 불가능
    private final int servings;
    // ...
}
```

불변성(immutability)의 이점을 누릴 수 없다:
- 스레드 안전성 보장 불가
- 예측 가능성 저하

#### 3. **스레드 안전성 문제**

thread에서 안전성을 유지하려면 **프로그래머의 추가적인 노력**이 필요하다.

---

## ✅ 해결책 3: 빌더 패턴 (Builder Pattern)

점층적 생성자 패턴의 **안전성** + 자바빈즈 패턴의 **가독성**을 결합!

### 구현

```java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;

        // 선택 매개변수 - 기본값으로 초기화
        private int calories     = 0;
        private int fat          = 0;
        private int sodium       = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings    = servings;
        }

        public Builder calories(int val)
        { calories = val;      return this; }
        public Builder fat(int val)
        { fat = val;           return this; }
        public Builder sodium(int val)
        { sodium = val;        return this; }
        public Builder carbohydrate(int val)
        { carbohydrate = val;  return this; }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize  = builder.servingSize;
        servings     = builder.servings;
        calories     = builder.calories;
        fat          = builder.fat;
        sodium       = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
```

> 💻 **코드**: [`builder/NutritionFacts.java`](../../src/main/java/effectivejava/chapter2/item02/builder/NutritionFacts.java)

### 사용

```java
NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
        .calories(100)
        .sodium(35)
        .carbohydrate(27)
        .build();
```

### 빌더 패턴 사용 과정

1. **빌더 객체 얻기**: 필수 매개변수를 생성자에 전달
2. **setter 메서드 호출**: 선택 매개변수 설정 (메서드 체이닝)
3. **build() 호출**: 불변 객체 생성

### ✨ 장점

#### 1. **작성이 쉽고 가독성이 좋다**

```java
// 각 매개변수의 의미가 명확
NutritionFacts pepsi = new NutritionFacts.Builder(240, 8)
        .calories(110)    // ← "칼로리는 110"
        .sodium(40)       // ← "나트륨은 40mg"
        .build();
```

#### 2. **불변 객체를 만들 수 있다**

```java
public class NutritionFacts {
    private final int servingSize;  // ✅ final 가능
    private final int servings;     // ✅ 한 번 설정되면 변경 불가
    // ...
}
```

#### 3. **빌드 시점에 불변식(invariant) 검증 가능**

```java
public NutritionFacts build() {
    // 불변식 검증
    if (servingSize <= 0) {
        throw new IllegalStateException("제공량은 양수여야 합니다");
    }
    if (servings <= 0) {
        throw new IllegalStateException("제공 횟수는 양수여야 합니다");
    }
    return new NutritionFacts(this);
}
```

#### 4. **유연하다**

- 빌더 하나로 여러 객체를 순회하며 만들 수 있다
- 매개변수에 따라 다른 객체를 만드는 등 유연하게 사용 가능
- 여러 개의 가변인자 매개변수를 가질 수 있다

### ⚠️ 단점

#### 1. **성능이 매우 중요한 상황에서는 문제가 될 수 있다**

객체를 만들기 위해 빌더부터 만들어야 한다 (2단계).

대부분의 경우 무시할 수 있는 수준의 오버헤드지만, 성능이 critical한 상황에서는 고려가 필요하다.

#### 2. **코드가 장황할 수 있다**

점층적 생성자 패턴보다 코드가 길어진다.

→ **매개변수가 4개 이상일 때** 사용하는 것이 좋다  
→ 그러나 API는 시간이 지나면서 매개변수가 많아지는 경향이 있으므로, **처음부터 빌더로 시작하는 것이 나을 때가 많다**

---

## 🔥 계층적 빌더 패턴 (Hierarchical Builder Pattern)

빌더 패턴은 **계층적으로 설계된 클래스**와 함께 쓰기 좋다.

### 시나리오: 피자 주문 시스템

추상 클래스에는 추상 빌더를, 구체 클래스에는 구체 빌더를 작성한다.

> 💻 **코드**:
> - [`Pizza.java`](../../src/main/java/effectivejava/chapter2/item02/hierarchicalbuilder/Pizza.java) - 추상 피자 클래스
> - [`NyPizza.java`](../../src/main/java/effectivejava/chapter2/item02/hierarchicalbuilder/NyPizza.java) - 뉴욕 피자
> - [`Calzone.java`](../../src/main/java/effectivejava/chapter2/item02/hierarchicalbuilder/Calzone.java) - 칼초네 피자
> - [`PizzaTest.java`](../../src/main/java/effectivejava/chapter2/item02/hierarchicalbuilder/PizzaTest.java) - 실행 가능한 테스트

### 사용

```java
NyPizza pizza = new NyPizza.Builder(SMALL)
        .addTopping(SAUSAGE)
        .addTopping(ONION)
        .build();

Calzone calzone = new Calzone.Builder()
        .addTopping(HAM)
        .sauceInside()
        .build();
```

### 🎯 핵심 기법

#### 1. 시뮬레이트한 셀프 타입 (Simulated Self-Type)

```java
abstract static class Builder<T extends Builder<T>> {
    protected abstract T self();
}
```

**왜 필요한가?**

Java는 self 타입을 직접 지원하지 않으므로, 이렇게 우회해야 메서드 체이닝이 제대로 작동한다.

#### 2. 공변 반환 타이핑 (Covariant Return Typing)

```java
// 부모 클래스
abstract Pizza build();

// 자식 클래스
@Override 
public NyPizza build() {  // Pizza가 아닌 NyPizza 반환!
    return new NyPizza(this);
}
```

하위 클래스의 build() 메서드는 **구체 하위 클래스를 반환**하도록 선언한다.

Java 5부터 지원: 하위 클래스 메서드가 상위 클래스보다 **구체적인 타입**을 반환 가능.

---

## 📊 패턴 비교 요약

| 패턴 | 안전성 | 가독성 | 불변성 | 확장성 | 복잡도 |
|------|--------|--------|--------|--------|--------|
| **점층적 생성자** | ⭐⭐⭐ | ⭐ | ⭐⭐⭐ | ⭐ | ⭐⭐ |
| **자바빈즈** | ⭐ | ⭐⭐⭐ | ❌ | ⭐⭐⭐ | ⭐ |
| **빌더** | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ |

---

## 🎯 실전 적용 가이드

### 언제 빌더 패턴을 사용해야 하는가?

#### ✅ 사용하는 것이 좋은 경우
- [ ] 매개변수가 **4개 이상**
- [ ] 선택적 매개변수가 **많음**
- [ ] 매개변수 타입이 **같은 경우가 많음** (실수 방지)
- [ ] **불변 객체**를 만들고 싶음
- [ ] API가 시간이 지나면서 **확장될 가능성**이 있음

#### ⚠️ 과할 수 있는 경우
- [ ] 매개변수가 **3개 이하**
- [ ] 객체 생성 빈도가 **매우 높음** (성능 critical)
- [ ] 모든 매개변수가 **필수**

### Lombok @Builder 활용

실무에서는 Lombok을 사용하면 더 간단하다:

```java
@Builder
public class NutritionFactsLombok {
    private final int servingSize;
    private final int servings;
    
    @Builder.Default
    private final int calories = 0;
    
    @Builder.Default
    private final int fat = 0;
}

// 사용
NutritionFactsLombok facts = NutritionFactsLombok.builder()
        .servingSize(240)
        .servings(8)
        .calories(100)
        .build();
```

---

## 💡 핵심 정리

```
✅ 생성자나 정적 팩터리가 처리해야 할 매개변수가 많다면 빌더 패턴을 고려하라

✅ 매개변수 중 다수가 필수가 아니거나 같은 타입이면 특히 더 그렇다

✅ 빌더는 점층적 생성자보다 클라이언트 코드를 읽고 쓰기가 훨씬 간결하고,
   자바빈즈보다 훨씬 안전하다

⚠️ 주의: 매개변수가 4개 미만이면 생성자나 정적 팩터리가 더 나을 수 있다

🎯 실전 팁: API가 시간이 지나면서 확장될 가능성이 있다면 
            처음부터 빌더로 시작하는 것이 좋다
```

---

## 🔗 관련 아이템

- **[Item 1](./item01.md)**: 생성자 대신 정적 팩터리 메서드를 고려하라
- **[Item 3](./item03.md)**: private 생성자나 열거 타입으로 싱글턴임을 보증하라
- **[Item 17](../../chapter04/docs/item17.md)**: 변경 가능성을 최소화하라
- **[Item 50](../../chapter08/docs/item50.md)**: 적시에 방어적 복사본을 만들어라

---

**이전**: [Item 1](./item01.md) | **다음**: [Item 3](./item03.md) | **목차**: [Chapter 2 README](../README.md)
