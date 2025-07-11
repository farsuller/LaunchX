# 🚀 LaunchX

A Jetpack Compose Android app built using Clean Architecture. It fetches data from the SpaceX public API and caches it using Room for offline support.

---

## 🔧 Tech Stack
- Jetpack Compose
- Kotlin Coroutines & Flow
- Koin (DI)
- Ktor (HTTP Client)
- Room (Persistence)
---

## 🧱 Architecture Overview

- **UI Layer**: Composables + ViewModels with `StateFlow`
- **Domain Layer**: UseCases + Repository interfaces
- **Data Layer**: Repository implementation using Ktor (remote) and Room (local)

---

## ▶️ How to Run the App

1. Clone the repository

```bash
git clone https://github.com/farsuller/LaunchX.git
cd LaunchX
```

1. Open the project in **Android Studio Hedgehog** or later

2. Sync Gradle and build the project

3. Connect a device or emulator and click **Run**

---

### ✅ Run all unit tests via Gradle CLI:

```bash
./gradlew testDebugUnitTest
```

Or run tests directly from **Android Studio → Run Tests**

## 🪪 License

This project is for learning and demo purposes only. Not affiliated with SpaceX.

## 📸 Screenshots

<div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 10px;">
    <img src="https://github.com/farsuller/LaunchX/blob/main/screenshots/screenshot1.png" alt="Screenshot 1" width="200"/>
    <img src="https://github.com/farsuller/LaunchX/blob/main/screenshots/screenshot2.png" alt="Screenshot 2" width="200"/>
    <img src="https://github.com/farsuller/LaunchX/blob/main/screenshots/screenshot3.png" alt="Screenshot 3" width="200"/>
</div>

