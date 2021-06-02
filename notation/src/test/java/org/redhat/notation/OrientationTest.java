package org.redhat.notation;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;



@QuarkusTest
public class OrientationTest {

     
   
     @Test
     public void testModelNonNote() {
 
         /**
          *  Score Global  0
          *  strfin_36 = 25 (dl = 50, ee = 200)
          * rentab_13 = 10 (gg = 5, ga = 4, hp = 3, hq =1)
          * */
 
        String body = "{\"siren\":\"510662190\",\"bilan\":{\"variables\": [{ \"value\":10,\"type\": \"rentab_13\"},{\"value\":25,\"type\": \"strfin_36\"}],\"rules\":[]}}}";
       given()
                .body(body)
                .contentType(ContentType.JSON)
           .when()
                .post("/Orientation")
                .then()
           .assertThat()
              .statusCode(200)
             .body("notation.decoupageSectoriel", is(0))
             .body("notation.typeAiguillage", is("NON_NOTE"));
     }

  /**
   * Model_1
   */
  
    @Test
    public void testModelOneNoteA() {

       String body_calcul_variables = "{\"bilan\": { \"gg\": 5, \"ga\": 2, \"hp\": 1, \"hq\": 2,\"hn\": -52, \"dl\": 50, \"ee\": 2, \"fl\": 3, \"fm\": 7 }}"; 

       given()
               .body(body_calcul_variables)
               .contentType(ContentType.JSON)
          .when()
               .post("/calcul-variables")
               .then()
          .assertThat()
             .statusCode(200)
            .body("rentab_13",is(10))
            .body("strfin_36",is(25.0f));

       String body_orientation = "{\"siren\":\"423646512\",\"bilan\":{\"variables\": [{ \"value\":10,\"type\": \"rentab_13\"},{\"value\":25,\"type\": \"strfin_36\"}],\"rules\":[]}}}";
      given()
               .body(body_orientation)
               .contentType(ContentType.JSON)
          .when()
               .post("/Orientation")
               .then()
          .assertThat()
             .statusCode(200)
            .body("notation.score",is(0))
            .body("notation.decoupageSectoriel", is(1))
            .body("notation.note",is("A"))
            .body("notation.typeAiguillage", is("MODELE_1"));
    }
   // {"Variables":[{"value":10,"type":"rentab_13"},{"value":25,"type":"strfin_36"}],"ContrePartie":{"DecoupageSectoriel":1,"TypeAiguillage":"MODELE_1"},"ScoreFinal":[[0,{"value":10,"type":"rentab_13"}],[0,{"value":25,"type":"strfin_36"}]],"Score":"function Score( Var, CP )","rules":[],"Notation":{"Score":0,"DecoupageSectoriel":1,"Note":"A","TypeAiguillage":"MODELE_1","Orientation":"Favorable","Detail":[[0,{"value":10,"type":"rentab_13"}],[0,{"value":25,"type":"strfin_36"}]]},"siren":"1012Z"}
 
   @Test
   public void testModelOneNoteB() {

     String body_calcul_variables = "{\"bilan\": { \"gg\": 5, \"ga\": 2, \"hp\": 2, \"hq\": 0, \"hn\": -52, \"dl\": 50, \"ee\": 2, \"fl\": 3, \"fm\": 7 }}"; 

     given()
             .body(body_calcul_variables)
             .contentType(ContentType.JSON)
        .when()
             .post("/calcul-variables")
             .then()
        .assertThat()
           .statusCode(200)
          .body("rentab_13",is(9))
          .body("strfin_36",is(25.0f));

      String body = "{\"siren\":\"423646512\",\"bilan\":{\"variables\": [{ \"value\":9,\"type\": \"rentab_13\"},{\"value\":25,\"type\": \"strfin_36\"}],\"rules\":[]}}}";
     given()
              .body(body)
              .contentType(ContentType.JSON)
         .when()
              .post("/Orientation")
              .then()
         .assertThat()
            .statusCode(200)
           .body("notation.score", equalTo(0.26f))
           .body("notation.decoupageSectoriel", is(1))
           .body("notation.note",is("B"))
           .body("notation.typeAiguillage", is("MODELE_1"));
   }
  

  @Test
  public void testModelOneNoteD() {

     String body_calcul_variables = "{\"bilan\": { \"gg\": 5, \"ga\": 2, \"hp\": 0, \"hq\": 2, \"hn\": -52, \"dl\": 16, \"ee\": 4, \"fl\": 3, \"fm\": 7 }}"; 

     given()
             .body(body_calcul_variables)
             .contentType(ContentType.JSON)
        .when()
             .post("/calcul-variables")
             .then()
        .assertThat()
           .statusCode(200)
          .body("rentab_13",is(9))
          .body("strfin_36",is(4.0f));

     String body = "{\"siren\":\"423646512\",\"bilan\":{\"variables\": [{ \"value\":9,\"type\": \"rentab_13\"},{\"value\":4,\"type\": \"strfin_36\"}],\"rules\":[]}}}";
    given()
             .body(body)
             .contentType(ContentType.JSON)
        .when()
             .post("/Orientation")
             .then()
        .assertThat()
           .statusCode(200)
          .body("notation.score", equalTo(1.05f))
          .body("notation.decoupageSectoriel", is(1))
          .body("notation.note",is("D"))
          .body("notation.typeAiguillage", is("MODELE_1"));
  }





  /**
   * Model_2
   */
  @Test

  public void testModelTwoNoteA() {

      String body_calcul_variables = "{\"bilan\": {\"gg\": 5, \"ga\": 2, \"hp\": 0, \"hq\": 2, \"hn\": -52, \"ga\": 2, \"fl\": 3, \"fm\": 7, \"dl\": 24, \"ee\": 2}}"; 

       given()
               .body(body_calcul_variables)
               .contentType(ContentType.JSON)
          .when()
               .post("/calcul-variables")
               .then()
          .assertThat()
             .statusCode(200)
            .body("rentab_38",is(-5))
            .body("strfin_36",is(12.0f));

     String body = "{\"siren\":\"542107651\",\"bilan\":{\"variables\": [{ \"value\":-5,\"type\": \"rentab_38\"},{\"value\":12,\"type\": \"strfin_36\"}],\"rules\":[]}}}";
    given()
             .body(body)
             .contentType(ContentType.JSON)
        .when()
             .post("/Orientation")
             .then()
        .assertThat()
           .statusCode(200)
          .body("notation.score",is(0.1f))
          .body("notation.decoupageSectoriel", is(2))
          .body("notation.note",is("A"))
          .body("notation.orientation",is("Approved"))
          .body("notation.typeAiguillage", is("MODELE_2"));
  }

 @Test
 public void testModelTwoNoteB() {

     String body_calcul_variables = "{\"bilan\": { \"gg\": 5, \"ga\": 2, \"hp\": 0, \"hq\": 2,\"hn\": -2, \"ga\": -8, \"fl\": 8, \"fm\": -6, \"dl\": 24, \"ee\": 2 }}"; 

     given()
             .body(body_calcul_variables)
             .contentType(ContentType.JSON)
        .when()
             .post("/calcul-variables")
             .then()
        .assertThat()
           .statusCode(200)
          .body("rentab_38",is(-5))
          .body("strfin_36",is(12.0f));
    String body = "{\"siren\":\"542107651\",\"bilan\":{\"variables\": [{ \"value\":-10,\"type\": \"rentab_38\"},{\"value\":12,\"type\": \"strfin_36\"}],\"rules\":[]}}}";
   given()
            .body(body)
            .contentType(ContentType.JSON)
       .when()
            .post("/Orientation")
            .then()
       .assertThat()
          .statusCode(200)
         .body("notation.score", equalTo(0.34f))
         .body("notation.decoupageSectoriel", is(2))
         .body("notation.note",is("B"))
         .body("notation.typeAiguillage", is("MODELE_2"));
 }

@Test
public void testModelTwoNoteC(){

     String body_calcul_variables = "{\"bilan\": {\"gg\": 5, \"ga\": 2, \"hp\": 0, \"hq\": 2, \"hn\": -6, \"ga\": -9, \"fl\": 3, \"fm\": 0, \"dl\": 76, \"ee\": 76 }}"; 

     given()
             .body(body_calcul_variables)
             .contentType(ContentType.JSON)
        .when()
             .post("/calcul-variables")
             .then()
        .assertThat()
           .statusCode(200)
          .body("rentab_38",is(-5))
          .body("strfin_36",is(1.0f));

   String body = "{\"siren\":\"542107651\",\"bilan\":{\"variables\": [{ \"value\":-11,\"type\": \"rentab_38\"},{\"value\":1,\"type\": \"strfin_36\"}],\"rules\":[]}}}";
  given()
           .body(body)
           .contentType(ContentType.JSON)
      .when()
           .post("/Orientation")
           .then()
      .assertThat()
         .statusCode(200)
        .body("notation.score", equalTo(1.62f))
        .body("notation.decoupageSectoriel", is(2))
        .body("notation.note",is("D"))
        .body("notation.typeAiguillage", is("MODELE_2"));
}

 }