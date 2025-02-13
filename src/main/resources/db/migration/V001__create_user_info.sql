CREATE TABLE IF NOT EXISTS subscriptions_type (
                                                  subscriptions_type_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                  name VARCHAR(255) NOT NULL,
                                                  access_months INT NULL,
                                                  price DECIMAL(10,2) NOT NULL,
                                                  product_key VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS user_type (
                                         user_type_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(100) NOT NULL,
                                         description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
                                     user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     email VARCHAR(255) NOT NULL,
                                     phone VARCHAR(15) NOT NULL,
                                     cpf CHAR(11) NOT NULL,
                                     dt_subscription DATE NOT NULL,
                                     dt_expiration DATE NOT NULL,
                                     user_type_id INT,
                                     subscriptions_type_id INT
);

CREATE TABLE IF NOT EXISTS user_payment_info (
                                                 user_payment_info_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                 card_number CHAR(255) NOT NULL,
                                                 card_expiration_month INT NOT NULL,
                                                 card_expiration_year INT NOT NULL,
                                                 card_security_code CHAR(255) NOT NULL,
                                                 price DECIMAL(10,2) NOT NULL,
                                                 instalments INT NOT NULL,
                                                 dt_payment DATE NOT NULL,
                                                 user_id INT
);

-- Adicionando índices e constraints
CREATE INDEX idx_user_type_id ON users(user_type_id);
CREATE INDEX idx_subscriptions_type_id ON users(subscriptions_type_id);

ALTER TABLE users ADD CONSTRAINT users_email_unique UNIQUE (email);
ALTER TABLE users ADD CONSTRAINT users_cpf_unique UNIQUE (cpf);
ALTER TABLE users ADD CONSTRAINT users_phone_unique UNIQUE (phone);
ALTER TABLE users ADD CONSTRAINT fk_user_type_id FOREIGN KEY (user_type_id) REFERENCES user_type (user_type_id);
ALTER TABLE users ADD CONSTRAINT fk_subscriptions_type_id FOREIGN KEY (subscriptions_type_id) REFERENCES subscriptions_type (subscriptions_type_id);

ALTER TABLE subscriptions_type ADD CONSTRAINT subscriptions_type_product_key_unique UNIQUE (product_key);

ALTER TABLE user_payment_info ADD CONSTRAINT user_payment_info_card_number_unique UNIQUE (card_number);
ALTER TABLE user_payment_info ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);

-- Inserindo dados de exemplo
INSERT INTO subscriptions_type (name, access_months, price, product_key)
VALUES
    ('PLANO MENSAL', 1, 100.00, 'MONTH22'),
    ('PLANO ANUAL', 12, 1000.00, 'YEAR22'),
    ('PLANO VITALICIO', NULL, 2000.00, 'PERPETUAL22');

INSERT INTO user_type (name, description)
VALUES
    ('PROFESSOR', 'Professores da plataforma - cadastro administrativo'),
    ('ADMINISTRADOR', 'Administrador da plataforma - cadastro administrativo'),
    ('ALUNO', 'Aluno da plataforma - cadastro via fluxo normal');