-- ----------------------------
-- Schema for nupex services
-- ----------------------------
CREATE SCHEMA IF NOT EXISTS nupex;

-- ----------------------------
-- ENUMS
-- ----------------------------
CREATE TYPE client_type AS ENUM ('COMPANY', 'PERSON');
CREATE TYPE client_status AS ENUM ('ACTIVE', 'INACTIVE');
CREATE TYPE contract_status AS ENUM ('ACTIVE', 'PAID', 'CANCELED', 'DUE');

-- ----------------------------
-- Table structure for Company
-- ----------------------------
CREATE SEQUENCE company_id_seq;
CREATE TABLE IF NOT EXISTS Company
(
    id BIGINT PRIMARY KEY DEFAULT nextval('company_id_seq'),
    name VARCHAR(255) NOT NULL,   
    address VARCHAR(500),
    phone INTEGER
);

-- ------------------------------------
-- Table structure for Business_Unit
-- ------------------------------------
CREATE SEQUENCE business_unit_id_seq;
CREATE TABLE IF NOT EXISTS Business_Unit
(
     id BIGINT PRIMARY KEY DEFAULT nextval('business_unit_id_seq'),
     company_id BIGINT  NOT NULL CONSTRAINT fk_business_unit_company REFERENCES Company (id) ON DELETE CASCADE,
     name VARCHAR(255) NOT NULL,   
     address VARCHAR(500),
     phone INTEGER
);

-- ----------------------------
-- Table structure for Client
-- ----------------------------
CREATE SEQUENCE client_id_seq;
CREATE TABLE IF NOT EXISTS Client
(
    id BIGINT PRIMARY KEY DEFAULT nextval('client_id_seq'),
    business_unit_id BIGINT  NOT NULL CONSTRAINT fk_client_business_unit REFERENCES Business_Unit (id) ON DELETE CASCADE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    company_name VARCHAR(255),
    phone INTEGER,
    email VARCHAR(255),
    address VARCHAR(500),
    commercial_activity VARCHAR(1000),
    client_type client_type,
    client_code VARCHAR(10),
    status client_status,
    national_id  VARCHAR(25),
    relationship_start_date TIMESTAMP WITH TIME ZONE     
);


-- ----------------------------
-- Table structure for Contract
-- ----------------------------
CREATE SEQUENCE contract_id_seq;
CREATE TABLE IF NOT EXISTS Contract
(
    id BIGINT PRIMARY KEY DEFAULT nextval('contract_id_seq'),
    client_id BIGINT  NOT NULL CONSTRAINT fk_contract_client REFERENCES Client (id) ON DELETE CASCADE,
    due_date TIMESTAMP WITH TIME ZONE NOT NULL,
    credit_amount NUMERIC(10,4) NOT NULL,
    interest_rate NUMERIC(10,4) NOT NULL,
    overdue_payment_interest_rate NUMERIC(10,4) NOT NULL,
    quantity_of_months INTEGER NOT NULL,
    quantity_of_fortnights INTEGER,
    release_date TIMESTAMP WITH TIME ZONE,
    quantity_of_due_days INTEGER,
    Status contract_status
);

-- ---------------------------------------
-- Table structure for Amortization_Table
-- ---------------------------------------
CREATE SEQUENCE amortization_table_id_seq;
CREATE TABLE IF NOT EXISTS Amortization_Table 
(
    id BIGINT PRIMARY KEY DEFAULT nextval('amortization_table_id_seq'),
    contract_id BIGINT  NOT NULL CONSTRAINT fk_amortization_table_contract REFERENCES Contract (id) ON DELETE CASCADE,
    year INTEGER NOT NULL,
    month INTEGER NOT NULL,
    fortnight INTEGER NOT NULL,
    collection_date TIMESTAMP WITH TIME ZONE,
    due_date TIMESTAMP WITH TIME ZONE,
    fee NUMERIC(10,4),
    interest_amount NUMERIC(10,4),
    capital NUMERIC(10,4),
    balance NUMERIC(10,4),
    overdue_amount NUMERIC(10,4),
    overdue_days INTEGER,
    date_paid TIMESTAMP WITH TIME ZONE,
    Status contract_status
);


-- ---------------------------------------
-- Table structure for Payment
-- ---------------------------------------
CREATE SEQUENCE payment_id_seq;
CREATE TABLE IF NOT EXISTS Payment
(
    id BIGINT PRIMARY KEY DEFAULT nextval('payment_id_seq'),
    amortization_table_id BIGINT  NOT NULL CONSTRAINT fk_payment_amortization_table REFERENCES Amortization_Table (id) ON DELETE CASCADE,
    date_of_payment TIMESTAMP WITH TIME ZONE,
    amount NUMERIC(10,4),
    receipt_number VARCHAR(50),
    reference VARCHAR(255)
);