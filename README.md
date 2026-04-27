Obiectiv

Această temă pune bazele proiectului final. 
Veți crea un proiect Maven de la zero, veți adăuga TestNG și veți implementa un test data-driven pentru o logică de business inspirată din hapifyme app.

1.  Structura

○	Creați un proiect Maven nou, de la zero, folosind IntelliJ (File > New > Project > Maven Archetype > maven-archetype-quickstart) sau prin linia de comandă.

○	Setați groupId la com.qaschool și artifactId la tema13-hapifyme-validator.

2.  Configurare pom

○	Adăugați dependența org.testng:testng (folosiți o versiune recentă, ex: 7.10.2). Asigurați-vă că are <scope>test</scope>.

○	Adăugați (sau asigurați-vă că există) plugin-ul maven-surefire-plugin în secțiunea <build>.

○	Adăugați secțiunea <properties> pentru a seta Java 11 (sau mai nou):


3.	Creare SUT (Clasa de Testat):

○	În src/main/java/com/qaschool/validators/, creați o clasă numită PostValidator.java.

○	Această clasă va simula logica de validare a unei postări noi pe hapifyme (inspirată din includes/classes/Post.php).

○	Adăugați o singură metodă publică: public String getPostStatus(String postBody).

○	Logica metodei:

■	Dacă postBody este null sau postBody.isEmpty(), returnează "ERROR_EMPTY".
■	Dacă postBody.length() > 250, returnează "ERROR_TOO_LONG".
■	Dacă postBody conține cuvântul "politică" (cu litere mici), returnează "ERROR_FORBIDDEN".
■	În toate celelalte cazuri, returnează "POST_VALID".

4.	Creare Clasă de Test (15p):

○	În src/test/java/com/qaschool/validators/, creați clasa de test PostValidatorTest.java.

○	Adăugați o metodă @BeforeClass pentru a inițializa PostValidator.

○	Adăugați un @DataProvider(name = "postDataProvider").

○	Acest DataProvider trebuie să returneze Object[][] cu cel puțin 5 cazuri de test care să acopere fiecare rezultat posibil (EMPTY, TOO_LONG, FORBIDDEN, VALID).
■	Ex: {"Acesta este un post valid", "POST_VALID"}
■	Ex: {null, "ERROR_EMPTY"}
■	Ex: {"", "ERROR_EMPTY"}
■	Ex: {"Acest post vorbește despre politică.", "ERROR_FORBIDDEN"}
■	Ex: {"Un post foarte lung... (peste 250 caractere)", "ERROR_TOO_LONG"}

○	Adăugați un test parametrizat @Test(dataProvider = "postDataProvider") numit testPostValidationScenarios(String postBody, String expectedStatus).

○	Testul trebuie să apeleze validator.getPostStatus(postBody) și să compare rezultatul cu expectedStatus folosind Assert.assertEquals().




TESTARE

La executarea PostValidatorTest, toate cele 5 teste definite in DataProvider trec cu succes:

    @DataProvider(name = "postDataProvider")
    public Object[][] postDataProvider() {

        // Generăm un text mai lung de 250 caractere
        String longText = "A".repeat(260);

        return new Object[][] {
                {"Acesta este un post valid", "POST_VALID"},
                {null, "ERROR_EMPTY"},
                {"", "ERROR_EMPTY"},
                {"Acest post vorbește despre politică și nu ar trebui acceptat.", "ERROR_FORBIDDEN"},
                {longText, "ERROR_TOO_LONG"}
        };
    }