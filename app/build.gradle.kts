plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jesperancinha.plugins.omni") version "0.4.5"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20"
    id("jacoco")
}

android {
    namespace = "nl.joaofilipesabinoesperancinha.matrixanywhere"
    compileSdk = 36

    defaultConfig {
        applicationId = "nl.joaofilipesabinoesperancinha.matrixanywhere"
        minSdk = 36
        targetSdk = 36
        versionCode = 4
        versionName = "2.0.3"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    lint {
        baseline = file("lint-baseline.xml")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.3")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2025.08.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.9.4")
    implementation(platform("androidx.compose:compose-bom:2025.08.01"))
    implementation("com.google.android.material:material:1.13.0")
    implementation("com.android.support:multidex:1.0.3")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:6.0.3")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.08.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.08.01"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
