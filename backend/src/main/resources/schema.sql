SET REFERENTIAL_INTEGRITY FALSE;


CREATE TABLE Hardware (
      id       INT           AUTO_INCREMENT PRIMARY KEY
    , code     VARCHAR(100)  NOT NULL UNIQUE
    , naziv    VARCHAR(250)  NOT NULL
    , tip      VARCHAR(100)  NOT NULL
    , kolicina INT           NOT NULL
    , cijena   REAL          NOT NULL
);

CREATE TABLE Review (
      id          INT            AUTO_INCREMENT PRIMARY KEY
    , naslov      VARCHAR(100)   NOT NULL
    , tekst       VARCHAR(1000)  NOT NULL
    , ocjena      TINYINT        NOT NULL
    , hardware_id INT
    , FOREIGN KEY (hardware_id) REFERENCES Hardware(id)
);

create table if not exists user (
    id identity,
    username varchar(100) not null unique,
    password varchar(1000) not null
);

create table if not exists authority (
    id identity,
    authority_name varchar(100) not null unique
);

create table if not exists user_authority (
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);