# Тестовый проект "Банковское приложение"

Проект не закончен, но работает.

## Общее описание проекта

Проект позволяет создавать объекты банков, клиентов и счетов.
Удалять и редактировать их. Манипуляции со счетами сохраняются в транзакциях.


## Инструкция по запуску проекта
Создайте базу данных myClevertecDB в postgres. 
Дайте права на управление alex.
В константах укажите свой путь, куда клонировали проект.

String DDL_INITIALIZATION_SCRIPT_PATH = "D:\\AllbackProgect\\23.08.23_Clevertec_test\\Clevertec_test\\src\\main\\resources\\database\\DDL_myBase.sql";

String DML_INITIALIZATION_SCRIPT_PATH = "D:\\AllbackProgect\\23.08.23_Clevertec_test\\Clevertec_test\\src\\main\\resources\\database\\DML_myBase.sql";

String DDL_INITIALIZATION_DROP_PATH = "D:\\AllbackProgect\\23.08.23_Clevertec_test\\Clevertec_test\\src\\main\\resources\\database\\Drop_myBase.sql";

Запустите метод Runner.run()

Примеры сущьностей:

Банк:
INSERT INTO banks (brand) VALUES
('Bank A')

Клиент:
INSERT INTO clients (personalIdentifier, first_name, surname) VALUES
('1234567890', 'John', 'Doe')

Счет:
INSERT INTO bank_accounts (account_number, client_id, bank_id, currency, open_date, account_balance) VALUES
('111111111', 1, 1, 'USD', '2022-01-01', 1000)