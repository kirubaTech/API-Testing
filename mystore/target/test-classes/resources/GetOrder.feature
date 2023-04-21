Feature: Creating new order
Scenario:Verify the getOrder API functionality
    Given API end point is given as "https://mystoreapi.com/order/"
    And OrderId and expected response are given as below
      |	id		 | http_status_code |
      | 69593    | 200              |
      | 69593098 | 404              |
      | 69593#@  | 200              |
    When Order details retrieved successfully
    Then Validate the response
    
  Scenario:Verify the getCatalogProduct API functionality
    Given API end point is given as "https://mystoreapi.com/catalog/product/"
    And OrderId and expected response are given as below
      | id		 | http_status_code |
      | 913296   | 200              |
      | 913294   | 404              |
      | 69593#@  | 404              |
    When Order details retrieved successfully
    Then Validate the response
    
    Scenario:Verify the getCatalogCategoryProduct API functionality
    Given API end point is given as "https://mystoreapi.com/catalog/"
    And OrderId and expected response are given as below
      | id		 | http_status_code |
      | 881980   | 404              |
      | 913294   | 404              |
      | 69593#@  | 404              |
    When Order details retrieved successfully
    Then Validate the response
   