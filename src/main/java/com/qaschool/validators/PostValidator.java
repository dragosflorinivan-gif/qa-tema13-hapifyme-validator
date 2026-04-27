package com.qaschool.validators;


public class PostValidator {

    public String getPostStatus(String postBody) {

        // Verifică dacă este gol sau null
        if (postBody == null || postBody.isEmpty()) {
            return "ERROR_EMPTY";
        }

        // Verifică lungimea maximă
        if (postBody.length() > 250) {
            return "ERROR_TOO_LONG";
        }

        // Verifică dacă apare cuvântul interzis
        if (postBody.contains("politică")) {
            return "ERROR_FORBIDDEN";
        }

        // Dacă totul e ok
        return "POST_VALID";
    }
}
