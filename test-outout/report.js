$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/aupost/features/sports.feature");
formatter.feature({
  "line": 1,
  "name": "Sport API Scenarios",
  "description": "",
  "id": "sport-api-scenarios",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Validate the circuit details for 2017",
  "description": "",
  "id": "sport-api-scenarios;validate-the-circuit-details-for-2017",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I hit f1 for the \"2017\"",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "2017",
      "offset": 18
    }
  ],
  "location": "ErgastStepDefinition.getCiruit(String)"
});
formatter.result({
  "duration": 3273146500,
  "status": "passed"
});
});