plugins {
    id("java")
    id("io.freefair.lombok") version "8.2.2"

   }

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.postgresql:postgresql:42.2.27")
    implementation ("org.slf4j:slf4j-api:1.7.32")
    implementation ("com.zaxxer:HikariCP:5.0.1")
    implementation ("org.aspectj:aspectjweaver:1.9.7")
    implementation("com.mchange:c3p0:0.9.5.5")
    implementation ("ch.qos.logback:logback-classic:1.2.6")
    implementation ("org.yaml:snakeyaml:2.2")
    runtimeOnly ("org.aspectj:aspectjweaver:1.9.20")

    compileOnly ("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    testCompileOnly ("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.28")

}


tasks.test {
    useJUnitPlatform()
}