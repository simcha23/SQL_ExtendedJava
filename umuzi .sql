CREATE SEQUENCE customers_customerid_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."customers" (
    "customerid" integer DEFAULT nextval('customers_customerid_seq') NOT NULL,
    "firstname" character varying(50) NOT NULL,
    "lastname" character varying(50) NOT NULL,
    "gender" character varying NOT NULL,
    "address" character varying(200),
    "phone" character varying(10),
    "email" character varying(100),
    "city" character varying(20),
    "country" character varying(50),
    CONSTRAINT "customers_pkey" PRIMARY KEY ("customerid")
) WITH (oids = false);

INSERT INTO "customers" ("customerid", "firstname", "lastname", "gender", "address", "phone", "email", "city", "country") VALUES
(3,	'Leon',	'Glen',	'Male',	'81 Everton Rd,Gillits',	'0820832830',	'Leon@gmail.com',	'Durban',	'South Africa'),
(4,	'Charl',	'Muller',	'Male',	'290A Dorset Ecke',	'0856872553',	'Charl.muller@yahoo.com',	'Berlin',	'Germany'),
(5,	'Julia',	'Stein',	'Female',	'2 Wernerring',	'0867244505',	'Js234@yahoo.com',	'Frankfurt',	'South Africa'),
(1,	'Lerato',	'Mabitso',	'Male',	'284 chaucer st',	'084789657',	'john@gmail.com',	'Johannesburg',	'South Africa');

CREATE SEQUENCE employees_employeeid_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."employees" (
    "employeeid" integer DEFAULT nextval('employees_employeeid_seq') NOT NULL,
    "firstname" character varying(50) NOT NULL,
    "lastname" character varying(50) NOT NULL,
    "email" character varying(100),
    "jobtitle" character varying(20),
    CONSTRAINT "employees_pkey" PRIMARY KEY ("employeeid")
) WITH (oids = false);

INSERT INTO "employees" ("employeeid", "firstname", "lastname", "email", "jobtitle") VALUES
(1,	'Kani',	'Matthew',	'mat@gmail.com',	'Manager'),
(2,	'Lesly',	'Cronje',	'LesC@gmail.com',	'Clerk'),
(3,	'Gideon',	'Maduku',	'm@gmail.com',	'Accountant');

CREATE SEQUENCE orders_orderid_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."orders" (
    "orderid" integer DEFAULT nextval('orders_orderid_seq') NOT NULL,
    "productid" integer,
    "paymentid" integer,
    "fullfilledbyemployeeid" integer,
    "daterequired" date NOT NULL,
    "dateshipped" date,
    "status" character varying(20) NOT NULL,
    CONSTRAINT "orders_pkey" PRIMARY KEY ("orderid"),
    CONSTRAINT "orders_fullfilledbyemployeeid_fkey" FOREIGN KEY (fullfilledbyemployeeid) REFERENCES employees(employeeid) NOT DEFERRABLE,
    CONSTRAINT "orders_paymentid_fkey" FOREIGN KEY (paymentid) REFERENCES payments(paymentid) NOT DEFERRABLE,
    CONSTRAINT "orders_productid_fkey" FOREIGN KEY (productid) REFERENCES products(productid) NOT DEFERRABLE
) WITH (oids = false);

INSERT INTO "orders" ("orderid", "productid", "paymentid", "fullfilledbyemployeeid", "daterequired", "dateshipped", "status") VALUES
(1,	1,	1,	2,	'2020-04-25',	NULL,	'Not shipped'),
(2,	1,	2,	2,	'2020-04-23',	'2020-04-25',	'Shipped'),
(3,	3,	3,	3,	'2020-04-28',	NULL,	'Not shipped');

CREATE SEQUENCE payments_paymentid_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."payments" (
    "customerid" integer,
    "paymentid" integer DEFAULT nextval('payments_paymentid_seq') NOT NULL,
    "paymentdate" date NOT NULL,
    "amount" numeric NOT NULL,
    CONSTRAINT "payments_pkey" PRIMARY KEY ("paymentid"),
    CONSTRAINT "payments_customerid_fkey" FOREIGN KEY (customerid) REFERENCES customers(customerid) NOT DEFERRABLE
) WITH (oids = false);

INSERT INTO "payments" ("customerid", "paymentid", "paymentdate", "amount") VALUES
(1,	1,	'2020-04-24',	150.75),
(3,	3,	'2020-04-24',	700.60),
(1,	2,	'2020-04-24',	150.75);

CREATE SEQUENCE products_productid_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."products" (
    "productid" integer DEFAULT nextval('products_productid_seq') NOT NULL,
    "productname" character varying(100) NOT NULL,
    "description" character varying(300),
    "buyprice" numeric NOT NULL,
    CONSTRAINT "products_pkey" PRIMARY KEY ("productid")
) WITH (oids = false);

INSERT INTO "products" ("productid", "productname", "description", "buyprice") VALUES
(1,	'Harley Davidson Chopper',	'This replica features working kickstand, front suspension, gear-shift lever',	150.75),
(2,	'Classic Car',	'Turnable front wheels, steering function',	150.75),
(3,	'Harley Davidson Chopper',	'Turnable front wheels, steering function',	700.60);

