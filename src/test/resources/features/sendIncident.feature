Feature: Being able to fill the form
Scenario: Incidence
  Fill the form
  	
    Given a user: 
    | name | password |
    | 12345678P | 123456 |
    | 12345678A | 123456 | 
    
    When I fill the form with name "FUGA GAS" description "Fuga de gas cocina" location "31,56" tags "bombona,gas" additionalInformation "Butano" properties "bombona:butano" state "Abierta" notification "Si" expireAt "2018-10-25 10:02:29.769579" and assignedTo "oper_rUxl"
    Then I am redirected to the incident deatils