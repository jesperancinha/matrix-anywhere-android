plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.omni)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jacoco)
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.google.material)
    implementation(libs.support.multidex)

    testImplementation(libs.junit4)
    testImplementation(libs.kotest.assertions.core.jvm)

    androidTestImplementation(libs.bundles.androidx.test)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.bundles.compose.test)

    debugImplementation(libs.bundles.compose.debug)
}
