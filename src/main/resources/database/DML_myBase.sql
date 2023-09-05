INSERT INTO banks (brand) VALUES
    ('Bank A'),
    ('Bank B'),
    ('Bank C'),
    ('Bank D'),
    ('Bank E');

INSERT INTO clients (personalIdentifier, first_name, surname) VALUES
    ('1234567890', 'John', 'Doe'),
    ('0987654321', 'Jane', 'Smith'),
    ('9877543210', 'Michael', 'Johnson'),
    ('0123456789', 'Emily', 'Brown'),
    ('5432119876', 'David', 'Davis'),
    ('6789054321', 'Olivia', 'Miller'),
    ('3456789998', 'Daniel', 'Wilson'),
    ('2109876545', 'Sophia', 'Martinez'),
    ('8765433109', 'Matthew', 'Anderson'),
    ('4568890123', 'Emma', 'Taylor'),
    ('5532109876', 'Andrew', 'Thomas'),
    ('7890123456', 'Isabella', 'Hernandez'),
    ('6545510987', 'Joseph', 'Moore'),
    ('2109876543', 'Ava', 'Garcia'),
    ('5462109876', 'Ryan', 'Lee'),
    ('9876543210', 'Abigail', 'Clark'),
    ('3456789098', 'Ethan', 'Lewis'),
    ('8765432109', 'Mia', 'Young'),
    ('4567890123', 'Alexander', 'Allen'),
    ('6543210987', 'Grace', 'Hall');

INSERT INTO bank_accounts (account_number, client_id, bank_id, currency, open_date, account_balance) VALUES
    ('111111111', 1, 1, 'USD', '2022-01-01', 1000),
    ('222222222', 2, 2, 'EUR', '2022-02-01', 2500),
    ('333333333', 3, 3, 'RUB', '2022-03-01', 500),
    ('444444444', 4, 4, 'BYN', '2022-04-01', 8000),
    ('555555555', 5, 5, 'BYN', '2022-05-01', 1500),
    ('666666666', 6, 1, 'USD', '2022-06-01', 200),
    ('777777777', 7, 2, 'EUR', '2022-07-01', 3500),
    ('888888888', 8, 3, 'RUB', '2022-08-01', 1000),
    ('999999999', 9, 4, 'BYN', '2022-09-01', 5000),
    ('101010101', 10, 5, 'RUB', '2022-10-01', 2000),
    ('111111112', 11, 1, 'USD', '2022-11-01', 150),
    ('121212122', 12, 2, 'EUR', '2022-12-01', 4000),
    ('131313133', 13, 3, 'BYN', '2023-01-01', 800),
    ('141414144', 14, 4, 'RUB', '2023-02-01', 7000),
    ('151515155', 15, 5, 'RUB', '2023-03-01', 3000),
    ('161616166', 16, 1, 'USD', '2023-04-01', 50),
    ('171717177', 17, 2, 'EUR', '2023-05-01', 2000),
    ('181818188', 18, 3, 'BYN', '2023-06-01', 600),
    ('191919199', 19, 4, 'USD', '2023-07-01', 4000),
    ('202020200', 20, 5, 'USD', '2023-08-01', 2500);
