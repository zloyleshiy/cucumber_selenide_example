@smoketest
Feature: Test #1, check Yandex Market filter by manufacturer Dell and order by ascending

  Scenario: go to Yandex Market

    Given open url 'yandex.ru'
    And Yandex Logo is displayed
    When Click on element with text link 'Маркет'
    Then Market Logo is displayed
    And URL contains 'market.yandex.ru'


  Scenario: go to the Laptop section

    Given open url 'market.yandex.ru'
    And Market Logo is displayed
    And URL contains 'market.yandex.ru'
    When Hover mouse on element with text link 'Компьютерная техника'
    And Click on element with text link 'Ноутбуки'
    Then Check subtitle Yandex Market section is 'Ноутбуки'


  Scenario: apply filter

    #This is not the best solution, test cases should not be related to each other.
    Given URL contains 'market.yandex.ru'
    And Check subtitle Yandex Market section is 'Ноутбуки'
    When Choose a manufacturer with a name 'DELL'
    And Set minimum price 50000 rubles
    And Set maximum price 150000 rubles
    And Order by 'по цене'
    And Scroll Yandex Market page to last footer end page
    And Make a pause 5 second
    Then Check price ordered by ascending
    And Check that the names of the products found contain 'Ноутбук DELL'