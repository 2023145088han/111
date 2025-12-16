# 🌟 맞춤형 건강 관리 및 영양 추적 솔루션: 안드로이드 프로그래밍 응용 앱 프로젝트

## 📌 1. 프로젝트 개요 및 동기 (Project Overview and Motivation)

본 프로젝트는 현대인의 건강 관리 트렌드에 발맞춰 **사용자 맞춤형 칼로리 목표 설정**과 **직관적인 일일 식단 기록**을 통합한 모바일 애플리케이션입니다. 단순한 계산기를 넘어, 사용자가 자신의 신체 조건과 라이프스타일을 반영한 과학적 데이터(TDEE)를 기반으로 건강 목표(체중 유지, 감량, 증가)를 체계적으로 달성할 수 있도록 설계되었습니다.

* **프로젝트명:** 안드로이드 프로그래밍 응용 앱 프로젝트
* **개발 목표:**
    1.  정확하고 개인화된 기초 대사량 및 총 에너지 소모량 계산 기능 제공.
    2.  Jetpack Compose를 활용하여 뛰어난 사용자 경험(UX)과 동적인 UI 구현.
    3.  간편한 입력 방식을 통해 식단 기록의 장벽을 낮추고 지속적인 건강 관리를 유도.
* **개발 환경:** Android Studio, Kotlin, Jetpack Compose, Material 3

---

## ✨ 2. 핵심 기능 상세 설명 (In-depth Core Features)

### 2.1. 과학적 목표 칼로리 계산기 (TDEE Calculation)

이 기능은 사용자의 입력 데이터를 활용하여 2단계에 걸쳐 가장 신뢰도 높은 일일 에너지 소모량을 산출합니다.

#### 1단계: 기초 대사량 (BMR, Basal Metabolic Rate) 계산
사용자의 성별, 체중, 키, 나이를 입력받아 생명 유지에 필요한 최소 칼로리인 BMR을 계산합니다.


| 성별 | 사용된 계산 공식 (Harris-Benedict Equation) |
| :--- | :--- |
| **남성** | $$(10 \times \text{체중}(\text{kg})) + (6.25 \times \text{키}(\text{cm})) - (5 \times \text{나이}) + 5$$ |
| **여성** | $$(10 \times \text{체중}(\text{kg})) + (6.25 \times \text{키}(\text{cm})) - (5 \times \text{나이}) - 161$$ |

#### 2단계: 총 일일 에너지 소모량 (TDEE, Total Daily Energy Expenditure) 및 목표 설정
BMR에 활동 계수(Activity Factor)를 적용하여 하루에 소모하는 총 칼로리(TDEE)를 계산합니다. 이후, 사용자의 목표에 따라 최종 섭취 칼로리를 설정합니다.

| 활동 수준 | 활동 계수 (Multiplier) | 목표 설정 (TDEE 기준) |
| :--- | :--- | :--- |
| 거의 활동 안 함 (책상) | 1.2 | 유지: TDEE |
| 가벼운 활동 (주 1-3회) | 1.375 | **감량:** TDEE - 500 kcal |
| 보통 활동 (주 3-5회) | 1.55 | **증가:** TDEE + 500 kcal |
| 격렬한 활동 (매일) | 1.725 | |
| 매우 격렬한 활동 (하루 2회 이상)| 1.9 | |

### 2.2. 직관적인 식단 기록 및 관리 (Meal Tracking)

목표 칼로리가 설정되면, 사용자는 실시간으로 섭취 칼로리를 추적할 수 있는 기록 화면으로 전환됩니다.

* **실시간 잔여 칼로리 시각화:** 목표와 섭취량을 비교하여 남은 칼로리를 명확하게 표시하며, 남은 칼로리가 마이너스(-)가 될 경우 경고 색상(에러 색상)으로 전환하여 직관성을 높였습니다.
* **간편 입력 시스템:** 아침/점심/저녁별 입력 필드에 콤마(`,`)를 구분자로 사용하여 복수의 음식을 한 번에 기록할 수 있습니다.
* **음식 데이터베이스 연동:** 앱 내부에 정의된 **[foodDatabase]** (예: 흰쌀밥 300kcal, 닭가슴살 165kcal 등)를 활용하여 입력된 음식 목록의 총 칼로리를 자동 합산합니다.

---

## 🛠️ 3. 기술 구현 상세 (Technical Implementation Details)

### 3.1. Jetpack Compose를 활용한 UI/UX

* **선언형 UI:** 모든 화면은 Compose 함수로 구현되어, UI 상태가 변경될 때마다 효율적으로 리컴포지션(Recomposition)됩니다.
* **Material 3 적용:** 최신 Android 디자인 가이드라인인 Material 3의 `Card`, `OutlinedTextField`, `ExposedDropdownMenuBox` 등을 사용하여 현대적이고 접근성이 높은 디자인을 적용했습니다.

### 3.2. 상태 관리 및 데이터 흐름 (State Management)

* **상태 위임 (`by remember { mutableStateOf(...) }`):** 사용자 입력(체중, 키 등), 선택된 옵션(성별, 목표), 그리고 계산 결과(총 섭취 칼로리) 등의 상태를 안전하고 효율적으로 관리했습니다.
* **단방향 데이터 흐름:**
    * `MainActivity`가 모든 데이터(activityFactors, foodDatabase)와 계산 로직을 담당합니다.
    * UI Composable 함수(`CalorieInputScreen`, `MealTrackingScreen`)는 데이터를 읽고, 사용자 이벤트를 **콜백 함수**(`onCalculationComplete`, `onRecord`)를 통해 다시 `MainActivity`로 전달하는 구조(Unidirectional Data Flow)로 설계되어, 코드의 유지보수성과 테스트 용이성을 극대화했습니다.

### 3.3. Kotlin 기능 활용

* **널 안전성 (Null Safety) 및 타입 변환:** 사용자 입력 필드(`weightInput`, `heightInput`, `ageInput`)의 문자열을 `.toDoubleOrNull()` 또는 `.toIntOrNull()`을 사용하여 안전하게 숫자로 변환하고, 입력 유효성 검사 로직(`if (weight == null || ...)` )을 통해 앱의 안정성을 확보했습니다.
* **컬렉션 및 맵 활용:** `activityFactors`와 `foodDatabase`는 Kotlin의 `Map` 자료구조를 사용하여 빠르고 효율적인 데이터 조회를 구현했습니다.

---

## 🚀 4. 프로젝트 실행 및 사용 가이드

1.  **프로젝트 클론:**
    ```bash
    # (Optional: Clone the repository if applicable)
    ```
2.  **Android Studio 실행:** 프로젝트를 Android Studio (Giraffe 이상 권장)에서 엽니다.
3.  **빌드 및 실행:** Gradle 빌드가 완료된 후, 상단 툴바에서 에뮬레이터 또는 기기를 선택하고 ▶️(Run 'app') 버튼을 클릭하여 앱을 실행합니다.
4.  **앱 사용:** (섹션 2.1 및 2.2 참조) 계산기 화면에서 정보를 입력하고, 결과에 따라 식단 기록 화면에서 '음식 목록 (예: 흰쌀밥, 닭가슴살)' 형식으로 기록을 진행합니다.

---

## 🧑‍💻 5. 개발자 정보

* **학번:** 2023145088
* **이름:** 한재웅
* **과목:** 안드로이드 프로그래밍 응용
