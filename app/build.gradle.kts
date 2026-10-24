/*Ho deciso di lasciare questo file molto commentato perchè, durante le lezioni mi è stato utile ripescare
* (copiando e incollando) le varie dipendenze -e i task necessari per farle funzionare al meglio - direttamente
* da queste righe.*/
plugins {
    id("org.openjfx.javafxplugin") version "0.1.0" //Serve per JAVAFX
    application }
repositories { mavenCentral() }

dependencies {
    // Ultima versione stabile di Hibernate Core:
    implementation("org.hibernate.orm:hibernate-core:6.6.59.Final")
    //Per le JPA java persistent api(per usare le annotation (tipo @Enetity, ecc...)
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    /* NOTA: È obbligatorio includere anche il driver del database che usi, ad esempio
       PostgreSQL o MySQL. Runtime perchè serve solo a tempo di esecuzione, non viene
       compilato nulla.*/
    runtimeOnly("org.postgresql:postgresql:42.7.5")

    /*Libreria per il database in memoria:
    * Vedere il file "src/main/resources/META-INF/persistence.xml"
    * per i dettagli della connessione al DB!
    * NOTA: È nella cartella statica, non confondersi con la sua copia
    * dopo aver fatto gradle build!*/
    implementation("com.h2database:h2:2.2.224")

    //GSon json:
    implementation ("com.google.code.gson:gson:2.14.0")

    //JAVAFX(25):
    implementation("org.openjfx:javafx-controls:25") // 25 numero di versione
    implementation("org.openjfx:javafx-fxml:25") // 25 numero di versione
    //JUNIT(6):
    testImplementation(platform("org.junit:junit-bom:6.1.1")) //importa junit6:
    testImplementation("org.junit.jupiter:junit-jupiter") //importa l'engine di junit6:
    // RISOLUZIONE DEL PROBLEMA: Launcher necessario a Gradle/IDE per eseguire JUnit 6
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
}
//JAVAFX(25):
javafx {
    version = "25" // 25 numero di versione non è neccessario che sia = alla versione di java!
    modules = listOf("javafx.controls", "javafx.fxml")
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
//CLASSE MAIN:
application { mainClass = "it.unicam.cs.mpgc.rpg120666.Main.Main3Serializzazioni" }
tasks.test { // Obbligatorio per eseguire i test con JUnit
    useJUnitPlatform()
    /*Ti fa vedere test per test se passato, skippato o fallito!*/
    testLogging{ events("passed", "skipped", "failed")}
}
