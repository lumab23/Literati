plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id ("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.aula.literatiapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aula.literatiapp"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        // Access the API keys
        val googleApiKey = project.findProperty("GOOGLE_API_KEY")?.toString() ?: ""
        val geminiApiKey = project.findProperty("GEMINI_API_KEY")?.toString() ?: ""

        // Pass them to BuildConfig
        buildConfigField("String", "GOOGLE_API_KEY", "\"$googleApiKey\"")
        buildConfigField("String", "GEMINI_API_KEY", "\"$geminiApiKey\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.generativeai)
    implementation(libs.coil)
    implementation(libs.dagger.hilt.android)
    implementation(libs.firebase.database.ktx)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime.android)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.compose.material3:material3:1.2.1")
    kapt(libs.dagger.hilt.compiler)
    implementation ("androidx.compose.ui:ui:1.5.1")
    kapt ("com.google.dagger:hilt-compiler:2.48")
    testImplementation ("junit:junit:4.13.2")
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation ("com.google.code.gson:gson:2.8.8") // Ou a versão mais recente
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.constraintlayout.compose)

    // material icons
    implementation(libs.androidx.material.icons.extended)

    // ViewModel dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose.v253)

    // for images
    implementation(libs.coil.compose)
    implementation("io.coil-kt.coil3:coil-compose:3.0.2")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.2")

    // firebase authentication
    implementation(platform(libs.firebase.bom))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation(libs.play.services.auth)

    // credentials
    //implementation("androidx.credentials:credentials:1.5.0-alpha05")
    //implementation("androidx.credentials:credentials-play-services-auth:1.5.0-alpha05")
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")


    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.compose.material:material:1.4.0")
    implementation(kotlin("script-runtime"))

    // gemini
    implementation(libs.generativeai)

    // for nav bar animation
    implementation("com.exyte:animated-navigation-bar:1.0.0")

    // splash screen
    implementation(libs.core.splashscreen)


}