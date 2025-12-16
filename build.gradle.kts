// 최상위 레벨의 build.gradle.kts 파일
// 여기서 정의하는 버전들은 프로젝트의 모든 모듈에 적용됩니다.

plugins {
    // 안드로이드 애플리케이션 플러그인 버전을 정의합니다.
    id("com.android.application") version "8.2.2" apply false

    // 코틀린 안드로이드 플러그인 버전을 정의합니다.
    // 이 버전(1.9.22)은 Compose Compiler 1.5.8과 호환됩니다.
    kotlin("android") version "1.9.22" apply false
}
