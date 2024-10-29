plugins {
    alias(libs.plugins.android.application)
   id("com.google.gms.google-services")

}

android {
    namespace = "com.canadore.foodorderingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.canadore.foodorderingapp"
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //implementation platform("com.google.firebase:firebase-bom:31.2.0")
   // implementation("com.google.firebase:firebase-analytics-ktx")
   // implementation platform("com.google.firebase:firebase-bom:33.5.1")
  //  implementation ("com.google.firebase:firebase-analytics")

    implementation ("com.google.firebase:firebase-database:20.0.3")
    implementation ("com.google.firebase:firebase-auth:21.0.1")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("com.squareup.picasso:picasso:2.71828")
}