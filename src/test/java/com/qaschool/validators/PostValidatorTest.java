package com.qaschool.validators;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostValidatorTest {

    private static PostValidator validator;

    @BeforeClass
    public void setUp() {
        validator = new PostValidator();
    }

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

    @Test(dataProvider = "postDataProvider")
    public void testPostValidationScenarios(String postBody, String expectedStatus) {

        String actualStatus = validator.getPostStatus(postBody);
        Assert.assertEquals(actualStatus, expectedStatus);
    }
}