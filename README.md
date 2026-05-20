# Grameen Light

A comprehensive Android application for managing street light infrastructure in rural areas. The app enables citizens to report street light issues and allows administrators to monitor, track, and resolve complaints efficiently.

## 🌟 Features

### For Citizens (Users)
- **User Registration & Authentication** - Secure sign-up and login with Firebase Authentication
- **Report Street Light Issues** - Submit complaints with photos, descriptions, and location
- **Interactive Map View** - View nearby street light poles using OSMDroid
- **Complaint Tracking** - Monitor the status of submitted complaints in real-time
- **User Profile** - Manage personal information and view complaint history

### For Administrators
- **Admin Dashboard** - Comprehensive overview with analytics and statistics
- **Real-time Alerts** - View and manage incoming complaints instantly
- **Interactive Map** - Monitor all street light poles with status indicators
- **Pole Management** - Track pole status (Working, Fused, Daytime On, Under Repair)
- **Complaint Dispatch** - Assign complaints to maintenance workers
- **Analytics** - Visual charts showing complaint trends and resolution rates
- **Offline Support** - Local database sync ensures functionality without internet

## 🛠 Tech Stack

### Core Technologies
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material3** - Latest Material Design components

### Backend & Database
- **Firebase Authentication** - User authentication and session management
- **Firebase Firestore** - Real-time cloud database
- **Firebase Storage** - Cloud storage for complaint photos
- **Room Database** - Local SQLite database for offline support

### Mapping & Location
- **OSMDroid** - OpenStreetMap integration for offline-capable maps
- **Google Play Services** - Location services

### Libraries
- **Navigation Compose** - In-app navigation
- **Coroutines & Flow** - Asynchronous programming
- **Glide Compose** - Image loading and caching
- **MPAndroidChart** - Analytics visualization
- **Accompanist** - System UI controller

## 🏗 Architecture

The app follows a clean architecture pattern with separation of concerns:

```
├── presentation/ (UI)
│   ├── user/screens/      - User-facing screens
│   └── admin/screens/     - Admin-facing screens
├── domain/ (Business Logic)
│   ├── viewmodel/         - ViewModels for state management
│   └── model/             - Data models (Pole, Complaint, User)
├── data/ (Data Layer)
│   ├── repository/        - Data repositories
│   ├── local/            - Room database
│   └── remote/           - Firebase integration
```

### Key Components

**Data Models:**
- `Pole` - Street light pole with location and status
- `Complaint` - User-reported issues with photos and status
- `AppUser` - User profile with role (USER/ADMIN)

**Repositories:**
- `AuthRepository` - Authentication operations
- `ComplaintRepository` - Complaint CRUD and sync
- `PoleRepository` - Pole data management

**ViewModels:**
- `AuthViewModel` - Authentication state
- `ReportIssueViewModel` - Complaint submission
- `AdminDashboardViewModel` - Admin analytics
- `AdminMapViewModel` - Map state management

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- Android SDK 24 (Android 7.0) or higher
- Firebase account with project setup
- Google Play Services

### Firebase Setup

1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Add an Android app with package name: `com.grameen.light`
3. Download `google-services.json` and place it in `app/` directory
4. Enable the following Firebase services:
   - Authentication (Email/Password)
   - Firestore Database
   - Storage

### Firestore Security Rules

Configure Firestore rules to allow read/write access:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

### Building the Project

1. Clone the repository:
```bash
git clone <repository-url>
cd Grameen-Light
```

2. Open the project in Android Studio

3. Sync Gradle files

4. Build and run:
```bash
./gradlew assembleDebug
./gradlew installDebug
```

## 👤 Admin Credentials

For testing purposes, use the following admin credentials:

- **Email:** `admin@grameenlight.com`
- **Password:** `admin123`

The admin account is auto-created on first login if it doesn't exist in Firebase Auth.

## 📱 Usage Guide

### For Users

1. **Registration**
   - Launch the app
   - Click "Register" and enter your details (name, phone, email, password)
   - Verify your email if required

2. **Report an Issue**
   - Navigate to the map view to find nearby poles
   - Select a pole and tap "Report Issue"
   - Choose issue type (Fused Bulb, Daytime On, Flickering, etc.)
   - Add description and optional photo
   - Submit the complaint

3. **Track Complaints**
   - Go to "My Issues" screen
   - View status updates (Pending → Assigned → In Progress → Fixed)

### For Admins

1. **Login**
   - Use admin credentials to access the admin dashboard
   - Navigate between Dashboard, Alerts, Map, and Settings tabs

2. **Monitor Alerts**
   - View incoming complaints in the Alerts tab
   - Review complaint details and photos
   - Click on alert to view pole location on map

3. **Dispatch Complaints**
   - Select a complaint from the dashboard or alerts
   - Assign to a maintenance worker
   - Update status as work progresses

4. **View Analytics**
   - Check the Dashboard for statistics
   - View complaint trends and resolution rates
   - Monitor pole status distribution

## 📁 Project Structure

```
app/src/main/java/com/grameen/light/
├── MainActivity.kt                    - Main activity
├── admin/                            - Admin module
│   ├── AdminActivity.kt              - Admin container activity
│   └── screens/
│       ├── AdminLoginScreen.kt       - Admin login
│       ├── AdminDashboardScreen.kt   - Dashboard UI
│       ├── AdminAlertsScreen.kt      - Alerts management
│       ├── AdminMapScreen.kt         - Map with pole markers
│       └── fragments/                - Bottom nav fragments
├── user/                             - User module
│   └── screens/
│       ├── UserLoginScreen.kt        - User login
│       ├── UserRegistrationScreen.kt - Registration
│       ├── ReportIssueScreen.kt      - Issue reporting
│       ├── PoleMapScreen.kt          - User map view
│       ├── ComplaintTrackingScreen.kt - My Issues
│       └── UserProfileScreen.kt      - User profile
├── core/                             - Core module
│   ├── AppContainer.kt               - Dependency injection
│   ├── model/                        - Data models
│   ├── repository/                   - Data repositories
│   ├── viewmodel/                    - ViewModels
│   ├── local/                        - Room database
│   │   ├── AppDatabase.kt
│   │   ├── dao/
│   │   └── entity/
│   └── data/                         - Network monitor
└── ui/theme/                         - Compose theme
```

## 🔧 Configuration

### Network Security

The app uses a network security config (`@xml/network_security_config`) for HTTPS configuration. Ensure your Firebase endpoints are properly configured.

### Permissions

The app requires the following permissions (declared in `AndroidManifest.xml`):
- `INTERNET` - Network access
- `ACCESS_FINE_LOCATION` - Precise location for maps
- `ACCESS_COARSE_LOCATION` - Approximate location
- `CAMERA` - Photo capture for complaints
- `READ_EXTERNAL_STORAGE` - Access photos
- `WRITE_EXTERNAL_STORAGE` - Save photos (SDK < 30)

### Mock Data

The app includes mock data for testing:
- Mock complaints appear when no real complaints exist
- Mock poles populate the map when Firestore is empty
- Mock alerts appear in the admin dashboard

## 🐛 Troubleshooting

### Admin Login Issues
- Ensure Firebase Authentication is enabled
- Check if `google-services.json` is correctly placed
- Verify internet connectivity

### Map Not Loading
- Check location permissions are granted
- Ensure OSMDroid tiles can be downloaded
- Verify network connectivity

### Complaints Not Syncing
- Check Firestore rules allow read/write
- Ensure Firebase Storage is configured for photo uploads
- Verify user is authenticated

### Build Errors
- Ensure JDK 17 is configured
- Update Android Gradle Plugin if needed
- Clean and rebuild: `./gradlew clean build`

### 👩‍💻 Author
##  Priya ✨

Built with passion ❤️ to empower smarter rural street light management 🌍💡
Driving innovation for safer villages, sustainable energy ⚡, and brighter communities 🌱🏘️


