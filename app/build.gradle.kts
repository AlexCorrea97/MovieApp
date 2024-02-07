plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        buildTypes.forEach {
            it.buildConfigField(
                "String",
                "API_KEY",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjOTM5NDJhZDdjNmZhZWIxMGUyNWU1ZGU2ZjhmNTBjMSIsInN1YiI6IjYyMGVlOTZjOTgyNGM4MDAxYmRlNjU1ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3qml91oSpTOaL643XdT7OrfUkKOMmIJLeQlblaxFhIY\""
            )
            it.buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.themoviedb.org/3/\""
            )
            it.buildConfigField(
                "String",
                "IMAGES_BASE_URL",
                "\"https://image.tmdb.org/t/p/w500\""
            )
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /**
     * Implementacion firebase
     */
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")

    /**
     * Navegación
     */
    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    /**
     * Koin
     */
    implementation("io.insert-koin:koin-android:3.2.2")

    /**
     * Retrofit
     */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
}