@Module_SAMPLE @UserStory_Read
  Feature: Read data from Sample table for given Id
    Background:
      Given The server is available under path v1

    @Functionality
    Scenario Outline: To verify sample record on given Id
      Given The Test Case Id is TC_Read_01
      Then The Test Data Id is <TC_Read_ID>
      And The input for read is
       | Id | <Id*>|
      When Request to read service
      Then The response should be
        | Expected Id | <Expected_Id>|
        | Expected Name | <Expected_Name>|
      Examples:
        | TCId | Test Objective | Id* | Expected_Name
        | TC1 | identifier and Name | 2 | mallappasss

